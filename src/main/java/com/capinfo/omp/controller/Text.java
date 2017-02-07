package com.capinfo.omp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

public class Text {
//	public static void main(String[] args) {
//		String a = "{\"keyPointMessage\"=[{\"M5\":\"13552898382\",\"M2\":\"64941122\",\"M1\":\"18001393853\",\"M3\":\"13011889085\",\"M4\":\"13011889085\",\"M8\":\"13810623105\",\"M6\":\"13552898382\",\"M9\":\"13810623105\",\"M7\":\"13693309175\",\"M10\":\"62524415\",\"M14\":\"96003\",\"M13\":\"96003\",\"M11\":\"13701375080\",\"M12\":\"13911738934\",\"M15\":\"96003\",\"M16\":\"96003\"}]}, {keyPointMessage=[{\"M5\":\"13552898382\",\"M2\":\"64941122\",\"M1\":\"18001393853\",\"M3\":\"13011889085\",\"M4\":\"13011889085\",\"M8\":\"13810623105\",\"M6\":\"13552898382\",\"M9\":\"13810623105\",\"M7\":\"13693309175\",\"M10\":\"62524415\",\"M14\":\"96003\",\"M13\":\"96003\",\"M11\":\"13701375080\",\"M12\":\"13911738934\",\"M15\":\"96003\",\"M16\":\"96003\"}]}, {keyPointMessage=[{\"M5\":\"96003\",\"M2\":\"96003\",\"M1\":\"18001393853\",\"M3\":\"96003\",\"M4\":\"96003\",\"M8\":\"96003\",\"M6\":\"96003\",\"M9\":\"96003\",\"M7\":\"96003\",\"M10\":\"96003\",\"M14\":\"96003\",\"M13\":\"96003\",\"M11\":\"96003\",\"M12\":\"96003\",\"M15\":\"96003\",\"M16\":\"96003\"}]}]";
//		System.out.println(a);
//		JSONObject jsonObject = new JSONObject();
//	}
	public static void main(String[] args) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		List<List> list = new ArrayList<>();
		List list1 = new ArrayList<>();
		list.add(list1);
		
	}
}