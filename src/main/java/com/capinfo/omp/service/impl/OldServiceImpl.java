package com.capinfo.omp.service.impl;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.xml.resolver.apps.resolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.capinfo.assistant.platform.ws.card.model.CardPersonMessageBack;
import com.capinfo.assistant.platform.ws.card.service.CardPersonMessageWsServiceProxy;
import com.capinfo.omp.model.OmpOldInfo;
import com.capinfo.omp.service.OldService;
import com.capinfo.omp.utils.Page;
@Service
public class OldServiceImpl implements OldService {
	
	@Autowired
	private JdbcTemplate JdbcTemplate;

	
	
	@Override
	public List<Map<String,Object>> getOldContextList(Page page, String name, String idCard, String zjNumber, String county, String street, String community, String isGenerationOrder,String isindividuation) {
		if (name!=null&&!StringUtils.isEmpty(name)) {
			name = " AND I.NAME  LIKE  '%"+name+"%'";
		}
		if (idCard!=null&&!StringUtils.isEmpty(idCard)) {
			idCard = " AND I.CERTIFICATES_NUMBER = '"+idCard+"'";
		}
		if (zjNumber!=null&&!StringUtils.isEmpty(zjNumber)) {
			zjNumber = "  AND I.ZJNUMBER = '"+zjNumber+"'";
		}
		if (county!=null&&idCard!=null&&!StringUtils.isEmpty(county)) {
			county = " AND I.HOUSEHOLD_COUNTY_ID = '"+county+"'";
		}
		if (street!=null&&!StringUtils.isEmpty(street)) {
			street = " AND I.HOUSEHOLD_STREET_ID = '"+street+"'";
		}
		if (community!=null&&!StringUtils.isEmpty(community)) {
			community = " AND I.HOUSEHOLD_COMMUNITY_ID = '"+community+"'";
		}
		if (isGenerationOrder!=null&&!StringUtils.isEmpty(isGenerationOrder)) {
			isGenerationOrder = " AND I.isGenerationOrder = '"+isGenerationOrder+"'";
		}
		
		if (isindividuation!=null&&!StringUtils.isEmpty(isindividuation)) {
			isindividuation = " AND I.isindividuation = '"+isindividuation+"'";
		}
		/*if (!StringUtils.isEmpty(creationTime)) {
			creationTime = " AND I.creationTime like '%"+creationTime+"%'";
		}*/
		String sql = "select i.id,i.`NAME`,i.ZJNUMBER,i.PHONE,i.TELTYPE,r2.`NAME` q,r3.`NAME` j,r1.`NAME` s,r5.id typeid,i.address,i.CERTIFICATES_NUMBER,i.isindividuation from omp_region r1,omp_region r2,omp_region r3,omp_phone_type r5,omp_old_info i "
				+ "where STATE = 1 "+name+idCard+zjNumber+county+street+community+isGenerationOrder+isindividuation+" and  r1.id = i.HOUSEHOLD_COMMUNITY_ID and r2.id = i.HOUSEHOLD_COUNTY_ID  and r5.phoneType = i.TELTYPE and r3.ID = i.HOUSEHOLD_STREET_ID LIMIT "+(page.getCurrentPage()-1)*page.getPageSize()+", "+page.getPageSize();
		List<Map<String,Object>> queryForList = JdbcTemplate.queryForList(sql);
		return queryForList;
	}


	@Override
	public boolean addOld(OmpOldInfo ompOldInfo, String format) {
		final 	String sql = "insert into omp_old_info"+
	"(household_county_id,household_street_id,household_community_id,workername,workertel,"+
	"name,certificates_number,zjnumber,phone,address,emergencycontact,emergencycontacttle,"
	+ "teltype,state,Ispersonalized,creationTime)values('"+ompOldInfo.getHouseholdCountyId()+"','"+ompOldInfo.getHouseholdStreetId()+"','"+
	ompOldInfo.getHouseholdCommunityId()+"','"+ompOldInfo.getWorkername()+"','"+ompOldInfo.getWorkertel()+
		"','"+ompOldInfo.getName()+"','"+ompOldInfo.getCertificatesNumber()+"','"+ompOldInfo.getzjNumber()+"','"+
		ompOldInfo.getPhone()+"','"+ompOldInfo.getAddress()+"','"+ompOldInfo.getEmergencycontact()+"','"+ompOldInfo.getEmergencycontacttle()+"','"
		+ompOldInfo.getTeltype()+ "',1,0,'"+format+"')";
		System.out.println(sql);
        
		KeyHolder keyHolder = new GeneratedKeyHolder();  
		int autoIncId = 0;  
        
      JdbcTemplate.update(new PreparedStatementCreator() {  
    	  public PreparedStatement createPreparedStatement(Connection con)  
                throws SQLException {  
    		  PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);  
    		  return ps;  
    	  }  
      }, keyHolder);  
  
      autoIncId = keyHolder.getKey().intValue();  
      if (autoIncId > 0) {
         addOldKeyInfo(ompOldInfo, autoIncId);
      }
      
      return autoIncId > 0;  
		
//		int update = JdbcTemplate.update(sql);
//		if (update==1) {
//			return true;
//		}
//		return false;
	}
	// 新增老人，自动匹配社区座机信息
	private  boolean addOldKeyInfo(OmpOldInfo ompOldInfo, int OldID) {
		String CommunityId = ompOldInfo.getHouseholdCommunityId();
		Map<String, Object> maps = getCommunityInfo(CommunityId);
		String sqlString = "";
		if (!"".equals(ompOldInfo.getTeltype()) && "居家型".equals(ompOldInfo.getTeltype())) {
			if (maps.get("jt") != null) {
				sqlString = maps.get("jt").toString();
			}
		}
		if (!"".equals(ompOldInfo.getTeltype()) && "农商型".equals(ompOldInfo.getTeltype())) {
			if (maps.get("nt") != null) {
				sqlString = maps.get("nt").toString();
			}
		}
		if (!"".equals(ompOldInfo.getTeltype()) && "失能型".equals(ompOldInfo.getTeltype())) {
			if (maps.get("nt") != null) {
				sqlString = maps.get("nt").toString();
			}
		}
		if (sqlString != null  && !"".equals(ompOldInfo.getTeltype())) {
			addOmpOldOrderInfo(String.valueOf(OldID), sqlString);
			return true;
		}
		return false;
	}
	// 通过社区编号，获取社区默认键值信息
	private  Map getCommunityInfo(String id) {
		Map<String, Object> maps = new HashMap<String, Object>();
		String sql = "SELECT t.ID, t.SNTYPE st, t.JJTYPE jt, t.NSTYPE nt FROM omp_region t WHERE t.ID = "+id;
		maps = JdbcTemplate.queryForMap(sql);
		
		return maps;
	}
	// 通过座机号，身份证号判断是否存在老人  
	@Override
	public boolean checkOldIsHave(String phoneid, String cardID) {
		String sql = "SELECT COUNT(*) FROM  (SELECT * FROM omp_old_info t WHERE  t.ZJNUMBER = " + phoneid + ") a " ;
		String sql1 = "SELECT COUNT(*) FROM  (SELECT * FROM omp_old_info t WHERE  t.CERTIFICATES_NUMBER ='" +cardID+ "') a1 " ;
		int count = JdbcTemplate.queryForInt(sql);//用queryForInt方法！！！
		int count1 = JdbcTemplate.queryForInt(sql1);//用queryForInt方法！！！
		return (count+count1) > 0;
	}


	@Override
	public boolean updOldById(Map<String, Object> map) {
		// TODO Auto-generated method stub
		String sql = "update omp_old_info "
				+ "set NAME = '"+map.get("name")+"',"
				+ "HOUSEHOLD_COUNTY_ID = '"+map.get("county")+"',"
				+ "HOUSEHOLD_STREET_ID = '"+map.get("street")+"',"
				+ "HOUSEHOLD_COMMUNITY_ID = '"+map.get("community")+"',"
				+ "ZJNUMBER = '"+map.get("zjnumber")+"',"
				+ "PHONE = '"+map.get("phone")+"',"
				+ "ADDRESS = '"+map.get("address")+"',"
				+ "EMERGENCYCONTACT = '"+map.get("emergencycontact")+"',"
				+ "EMERGENCYCONTACTTLE = '"+map.get("emergencycontacttle")+"',"
				+ "TELTYPE = '"+map.get("teltype")+"'"
				+ "where id = "+map.get("id");
		JdbcTemplate.update(sql);
		return false;
	}



	@Override
	public int delOldById(String old) {
		// TODO Auto-generated method stub
		String sql = "UPDATE OMP_OLD_INFO SET STATE = '0' WHERE ID = "+old;
		int i = JdbcTemplate.update(sql);
		return i;
	}



	@Override
	public int getCount(String name, String idCard, String zjNumber, String county, String street, String community, String isGenerationOrder,String isindividuation) {
		if (name!=null&&!StringUtils.isEmpty(name)) {
			name = " AND I.NAME  LIKE  '%"+name+"%'";
		}
		if (idCard!=null&&!StringUtils.isEmpty(idCard)) {
			idCard = " AND I.CERTIFICATES_NUMBER = '"+idCard+"'";
		}
		if (zjNumber!=null&&!StringUtils.isEmpty(zjNumber)) {
			zjNumber = "  AND I.ZJNUMBER = '"+zjNumber+"'";
		}
		if (county!=null&&idCard!=null&&!StringUtils.isEmpty(county)) {
			county = " AND I.HOUSEHOLD_COUNTY_ID = '"+county+"'";
		}
		if (street!=null&&!StringUtils.isEmpty(street)) {
			street = " AND I.HOUSEHOLD_STREET_ID = '"+street+"'";
		}
		if (community!=null&&!StringUtils.isEmpty(community)) {
			community = " AND I.HOUSEHOLD_COMMUNITY_ID = '"+community+"'";
		}
		if (isGenerationOrder!=null&&!StringUtils.isEmpty(isGenerationOrder)) {
			isGenerationOrder = " AND I.isGenerationOrder = '"+isGenerationOrder+"'";
		}
		
		if (isindividuation!=null&&!StringUtils.isEmpty(isindividuation)) {
			isindividuation = " AND I.isindividuation = '"+isindividuation+"'";
		}
		/*if (!StringUtils.isEmpty(creationTime)) {
			creationTime = " AND I.creationTime like '%"+creationTime+"%'";
		}*/
		String sql = "SELECT count(i.ID) FROM	OMP_OLD_INFO i WHERE i.STATE = 1 "+name+idCard+zjNumber+county+street+community+isGenerationOrder+isindividuation;
		int maxRows = JdbcTemplate.queryForInt(sql);
		return maxRows;
	}



	@Override
	public String getIdByName(String parameter,int level) {
		String sql = "select r.id id,r.name name,r.parentid parentid from Omp_Region r where r.name like '%"+parameter+"%' and r.levelid = "+level;
	//	String sql = "select  r.id id,r.name name,r.parentid parentid from OMP_REGION r where r.name='"+parameter+"'";
    	List<Map<String,Object>> queryForList = JdbcTemplate.queryForList(sql);
    	Object object = queryForList.get(0).get("ID");
    	return object.toString();
	}



	@Override
	public List<Map<String,Object>> getOldById(String id) {
		String sql = "SELECT I.*,o.keyPointMessage Kp, R.NAME SNAME FROM OMP_OLD_INFO i,OMP_REGION r,omp_old_order o WHERE I.STATE = 1 AND I.HOUSEHOLD_COMMUNITY_ID = R.ID AND o.oldId= i.ID AND i.ID = "+id;
		List<Map<String,Object>> list = JdbcTemplate.queryForList(sql);
		return list;
	}
    
	
	@Override
	public List<Map<String,Object>> getOldById1(String id) {
		String sql = "SELECT I.*,R. NAME SNAM FROM OMP_OLD_INFO i, OMP_REGION r WHERE I.STATE = 1 AND I.HOUSEHOLD_COMMUNITY_ID = R.ID AND i.ID = "+id;
		List<Map<String,Object>> list = JdbcTemplate.queryForList(sql);
		return list;
	}


	@Override
	public Map getRegionList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String, Object> maps = new HashMap<String, Object>();
		String sql1 = "select r.id,r.name from Omp_Region r where r.levelid = 3";
		List<Map<String,Object>> list1 = JdbcTemplate.queryForList(sql1);
		String sql2 = "select r.id,r.name from Omp_Region r where r.levelid = 4 and r.parentid = "+map.get("HOUSEHOLD_COUNTY_ID");
		List<Map<String,Object>> list2 = JdbcTemplate.queryForList(sql2);
		String sql3 = "select r.id,r.name from Omp_Region r where r.levelid = 5 and r.parentid = "+map.get("HOUSEHOLD_STREET_ID");
		List<Map<String,Object>> list3 = JdbcTemplate.queryForList(sql3);
		maps.put("county", list1);
		maps.put("street", list2);
		maps.put("community", list3);
		return maps;
	}



	@Override
	public List<Map<String,Object>> getRegionById(String county) {
		String sql = "select r.id,r.name from Omp_Region r where r.parentid = "+county;
		List<Map<String,Object>> list = JdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> queryCommunityOrder(String id) {
		String sql = "select oldpo.ID,oldpo.pname,oldpo.comId,k.`key`,oldpo.serviceProviderNumber number "
				+ "from(select oldp.ID,oldp.pname,o.keyId,o.serviceProviderNumber,oldp.HOUSEHOLD_COMMUNITY_ID comId  "
				+ "from(select old.ID,old.HOUSEHOLD_COMMUNITY_ID,p.id ptype,p.phoneType pname "
				+ "from(SELECT i.ID,i.HOUSEHOLD_COMMUNITY_ID,i.TELTYPE telTYPE "
				+ "from omp_old_info i where id = "+id+") old,omp_phone_type p "
				+ "where "
				+ "p.phoneType = old.telTYPE) oldp,omp_community_order o "
				+ "where o.phoneTYPE = oldp.ptype AND o.communityID = oldp.HOUSEHOLD_COMMUNITY_ID) oldpo,omp_key k "
				+ "where k.id = oldpo.keyId";
		System.out.println(sql);
		return JdbcTemplate.queryForList(sql);
		
	}



	@Override
	public boolean saveOrder(String id, String pname, String comId, String keyPointMessage) {
		String sql = "INSERT INTO `omp_old_order` "
				+ "(`oldId`, `phoneName`, `communityOrderId`, `keyPointMessage`) "
				+ "VALUES ('"+id+"', '"+pname+"', '"+comId+"', '"+keyPointMessage+"')";
		int update = JdbcTemplate.update(sql);
		return update>0;
	}



	@Override
	public void updOldState(String id) {
		// TODO Auto-generated method stub
		String sql = "update omp_old_info set isGenerationOrder = '1' where id = "+id;
		int update = JdbcTemplate.update(sql);
	}



	@Override
	public List<Map<String, Object>> getOldKeyPointMessage(String oid) {
		String sql = "select k.`key`,oldo.serviceProviderNumber number from (SELECT o.keyId,o.serviceProviderNumber from (SELECT i.ID,i.HOUSEHOLD_COMMUNITY_ID from omp_old_info i where i.ID = "+oid+") old,omp_community_order o where o.communityID = old.HOUSEHOLD_COMMUNITY_ID)oldo,omp_key k where k.id = oldo.keyId";
		List<Map<String, Object>> list = JdbcTemplate.queryForList(sql);
		return list;
	}
	@Override
	public List<Map<String, Object>> getOldMyPointMessage(String oid) {
		String sql = "SELECT t.keyPointMessage FROM omp_old_order t WHERE t.oldId = " + oid ;
		List<Map<String, Object>> list = JdbcTemplate.queryForList(sql);
		
		return list;
	}

	@Override
	public List<Map<String, Object>> getOmpKeyByType(String pyId) {
		String sql = "select t.* , '' phone FROM omp_key t WHERE t.pyId = " + pyId ;
		List<Map<String, Object>> list = JdbcTemplate.queryForList(sql);
		return list;
	}
	//通过老人ID获取老人座机键值信息
	@Override
	public List<Map<String,Object>> getOldKeyInfoById(String id) {
		String sql = "SELECT * FROM omp_old_order t WHERE t.oldId = "+id;
		List<Map<String,Object>> list = JdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public Boolean uploadOldIndividuation(String id, String json) {
		String sql = "update omp_old_order set keyPointMessage = '" + json +"', send_flag = 2 ,execute_flag = 3  where oldId = "+id;
		int update = JdbcTemplate.update(sql);
		if (update > 0) {
			setOldisIndividuation(id, 1);
		}
		return (update == 1);
	}
	// 设置用户是否个性化   0 未个性化 1 已经个性化
	private Boolean setOldisIndividuation(String id, int value) {
	
		String sql1 = "update omp_old_info set isindividuation = " + value + "  where ID = "+id;
		int update1 = JdbcTemplate.update(sql1);
		return (update1 == 1);
	}
	// 通过老人ID和键值  新增 老人座机键值信息
	@Override
	public Boolean addOmpOldOrderInfo(String id, String json) {
		String sql = "INSERT INTO omp_old_order ("
				+ "oldId, phoneName, communityOrderId, keyPointMessage)"
				+ "  select t.ID,t.TELTYPE,t.HOUSEHOLD_COMMUNITY_ID,'" + json + "' from omp_old_info t WHERE t.ID = " + id;
		int update = JdbcTemplate.update(sql);
		setOldisIndividuation(id, 1);
		return (update == 1);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean checkRe(String idCard, String zjnumber) {
		// TODO Auto-generated method stub
		String sql = "SELECT count(ID) from omp_old_info i where i.CERTIFICATES_NUMBER = '"+idCard+"' OR i.ZJNUMBER = '"+zjnumber+"'";
		int forInt = JdbcTemplate.queryForInt(sql);
		if (forInt>0) {
			return false;
		}else {
			return true;
		}
	}



	@Override
	public boolean checkId(String string) {
		// TODO Auto-generated method stub
		String sql = "SELECT isGenerationOrder from omp_old_info i where  i.id = "+string;
		Map<String, Object> forInt = JdbcTemplate.queryForMap(sql);
		return "0"==forInt.get("isGenerationOrder")||"0".equals(forInt.get("isGenerationOrder"));
	}
	
	
	
	@Override
	public boolean saveLogger(String type,String content,String creater,String state) {
	    SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date date =new Date();
	    String createdate=sim.format(date);
		String sql = "INSERT INTO `omp_log_record` "
				+ "(`type`, `content`, `creater`,`createdate`,`state`) "
				+ "VALUES ('"+type+"', '"+content+"', '"+createdate+"' ,'"+creater+"' ,'"+state+"')";
		int update = JdbcTemplate.update(sql);
		return update>0;
	}


	@Override
	public void saveolInfo(String carid) {
		CardPersonMessageWsServiceProxy d = new CardPersonMessageWsServiceProxy();
		try {
			String[] strs=carid.split(",");
			for(int i=0,len=strs.length;i<len;i++){
			  String cid =  strs[i].toString();
			  CardPersonMessageBack m = d.getCardPersonMessageByIDNumber(cid);
				String housCounty_id = HOUSEHOLD_COUNTY_ID(m.getHouseholdCounty());
				String housStreet_id = HOUSEHOLD_STREET_ID(m.getHouseholdStreet());
				String housCommun_id = HOUSEHOLD_COMMUNITY_ID(m.getHouseholdCommunity());
				String sql = "UPDATE omp_old_info i SET i.HOUSEHOLD_COUNTY_ID = '"+housCounty_id+"',i.HOUSEHOLD_STREET_ID = '"+housStreet_id+"',i.HOUSEHOLD_COMMUNITY_ID = '"+housCommun_id+"',i.ADDRESS = '"+m.getHouseholdAddress()+"',i.sync = '1' WHERE i.CERTIFICATES_NUMBER = '"+cid+"'";
				JdbcTemplate.update(sql);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}


	@Override
	public boolean getcountid() {
		String sql = "select count(i.CERTIFICATES_NUMBER) as id from omp_old_info i where i.sync = 0";
		int i = JdbcTemplate.queryForInt(sql);
		return i>0;
	}


	@Override
	public String checkDeBatchSendInstructions() {
		String sql = "select GROUP_CONCAT(i.CERTIFICATES_NUMBER) as id from omp_old_info i where i.sync = 0"; 
		Map<String, Object> map = JdbcTemplate.queryForMap(sql);
		return map.get("id").toString();
	}


	@Override
	public String HOUSEHOLD_COUNTY_ID(String add) {
		String sql = "select r.id 'id' from omp_region r where r.`NAME` = '"+add+"' limit 1";
		Map<String, Object> map = JdbcTemplate.queryForMap(sql);
		return map.get("id").toString();
	}


	@Override
	public String HOUSEHOLD_STREET_ID(String add) {
		String sql = "select r.id 'id' from omp_region r where r.`NAME` = '"+add+"' limit 1";
		Map<String, Object> map = JdbcTemplate.queryForMap(sql);
		return map.get("id").toString();
	}


	@Override
	public String HOUSEHOLD_COMMUNITY_ID(String add) {
		String sql = "select r.id 'id' from omp_region r where r.`NAME` = '"+add+"' limit 1";
		Map<String, Object> map = JdbcTemplate.queryForMap(sql);
		return map.get("id").toString();
	}
	

}
