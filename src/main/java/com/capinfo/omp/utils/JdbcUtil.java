package com.capinfo.omp.utils;



import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

//import com.sun.org.apache.commons.beanutils.BeanUtils;

public final class JdbcUtil {
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static Connection getConnnection() {
		Connection coll = null;
		String src = "jdbc:oracle:thin:@localhost:1521:orcl";
//		String src = "jdbc:mysql://127.0.0.1:3306/gb?useUnicode=true&characterEncoding=utf8";
		String user = "scott";
		String pwd = "tiger";
		try {
			coll = DriverManager.getConnection(src, user, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return coll;
	}

	public static List getList(Class clazz, String sql) {
		List<Object> list = null;
		Connection coll = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			coll = getConnnection();
			statement = coll.createStatement();
			rs = statement.executeQuery(sql);
			
			list = new ArrayList<Object>();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while(rs.next()) {
				Object obj = clazz.newInstance(); 
				for(int i = 1; i <= columnCount; i ++) {
					BeanUtils.copyProperty(obj, metaData.getColumnName(i), rs.getObject(i));
				}
				list.add(obj);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} finally {
			close(rs, statement, coll);
		}
		return list;
	}

	public static Object getObjectById(Class clazz, String sql) {
		Object obj = null;
		Connection coll = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			coll = getConnnection();
			statement = coll.createStatement();
			rs = statement.executeQuery(sql);
			
			ResultSetMetaData metaData = rs.getMetaData();
			
			int columnCount = metaData.getColumnCount();
			
			while(rs.next()) {
				obj = clazz.newInstance();
				for(int i = 1; i <= columnCount; i ++) {
					BeanUtils.copyProperty(obj, metaData.getColumnName(i), rs.getObject(i));
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} finally {
			close(rs, statement, coll);
		}
		return obj;
	}

	public static void execute(String sql) {
		Connection coll = null;
		Statement statement = null;

		try {
			coll = getConnnection();

			statement = coll.createStatement();

			statement.execute(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, statement, coll);
		}
	}

	private static void close(ResultSet rs, Statement stmt, Connection coll) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (coll != null) {
			try {
				coll.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	
	
	public static int getCount(String sql) {
		Connection conn = getConnnection();
		PreparedStatement pre = null;
		ResultSet rs = null;
		int i=0;
		try {
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
			if(rs.next()){
				i = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public static String getDate() {
		Date time=new Date();
		SimpleDateFormat ss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = ss.format(time);
		return date;
	}
}
