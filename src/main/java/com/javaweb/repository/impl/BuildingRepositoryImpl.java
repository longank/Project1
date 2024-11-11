package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.NumberUtil;
import com.javaweb.utils.StringUtil;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository{
	static final String URL = "jdbc:mysql://localhost:3306/estatebasics?useSSL=false";
	static final String USER = "root";
	static final String PASSWORD = "Longfb1231@";
	
	public static void joinTable(Map<String,Object> params,List<String> typeCode,StringBuilder sql) {
		String staffId = (String)params.get("staffId");
		if(StringUtil.checkString(staffId)){
			sql.append(" Join assignmentbuilding as on b.id = as.buildingid ");
		}
		if(typeCode != null && typeCode.size()!=0) {
			sql.append(" join buildingrenttype br on br.buildingid=b.id ");
			sql.append(" join renttype rt on rt.id=br.renttypeid ");
		}
		String areaTo = (String)params.get("areaTo");
		String areaFrom = (String)params.get("areaFrom");
		if(StringUtil.checkString(areaTo) || StringUtil.checkString(areaFrom)) {
			sql.append(" join rentarea ra on ra.buildingid=b.id ");
		}
	}
	
	public static void queryNormal(Map<String,Object> params,StringBuilder where) {
		for(Map.Entry<String, Object> it : params.entrySet()) {
			if(!it.getKey().equals("staffId") && !it.getKey().equals("typeCode") &&
					!it.getKey().startsWith("area") && !it.getKey().startsWith("rentPrice")) {
				String value = it.getValue().toString();
				if(StringUtil.checkString(value)) {
					if(NumberUtil.isNumber(value)) {
						where.append(" and b." + it.getKey() + " = " + value);
					}
					else {
						where.append(" and b." + it.getKey() + " like '%" + value + "%' ");
					}
				}
			}
		}
	}
	
	public static void querySpecial(Map<String,Object> params,List<String> typeCode,StringBuilder where) {
		String staffId = (String)params.get("staffId");
		if(StringUtil.checkString(staffId)){
			where.append(" and as.staffId = " + staffId);
		}
		String areaTo = (String)params.get("areaTo");
		String areaFrom = (String)params.get("areaFrom");
		if(StringUtil.checkString(areaTo)) {
			where.append(" and ra.value <= " + areaTo);
		}
		if(StringUtil.checkString(areaFrom)) {
			where.append(" and ra.value >= " + areaFrom);
		}
		
		String rentPriceTo = (String)params.get("rentPriceTo");
		String rentPriceFrom = (String)params.get("rentPriceFrom");
		if(StringUtil.checkString(rentPriceTo)) {
			where.append(" and b.rentprice <= " + rentPriceTo);
		}
		if(StringUtil.checkString(rentPriceFrom)) {
			where.append(" and b.rentprice >= " + rentPriceFrom);
		}
		if(typeCode != null && typeCode.size()!=0) {
			List<String> code = new ArrayList<>();
			for(String it : typeCode) {
				code.add("'" + it + "'");
			}
			where.append(" and rt.code in(" + String.join(",", code) + ") ");
		}
	}
	@Override
	public List<BuildingEntity> findAll(Map<String,Object> params,List<String> typeCode) {
		StringBuilder sql = new StringBuilder("SELECT b.id,b.name,b.districtid,b.ward,b.street,b.numberofbasement,b.floorarea,b.rentprice,b.managername,b.managerphonenumber,b.servicefee,b.brokeragefee FROM building b");
		StringBuilder where =new StringBuilder(" where 1=1 ");
		joinTable(params,typeCode,sql);
		queryNormal(params, where);
		querySpecial(params, typeCode, where);
		where.append(" group by id;");
		sql.append(where);
		List<BuildingEntity> result = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString())) {
			while(rs.next()) {
				BuildingEntity building = new BuildingEntity();
				building.setId(rs.getLong("b.id"));
				building.setName(rs.getString("b.name"));
				building.setStreet(rs.getString("b.street"));
				building.setWard(rs.getString("b.ward"));
				building.setDistrictid(rs.getLong("b.districtid"));
				building.setFloorarea(rs.getLong("b.floorarea"));
				building.setRentprice(rs.getLong("b.rentprice"));
				building.setServicefee(rs.getString("b.servicefee"));
				building.setBrokeragefee(rs.getLong("b.brokeragefee"));
				building.setManagername(rs.getString("b.managername"));
				building.setManagerphonenumber(rs.getString("b.managerphonenumber"));
				result.add(building);
			}
		} catch (SQLException e) {
			System.out.println("Lỗi khi kết nối: " + e.getMessage());
		}
		return result;
	}
}
