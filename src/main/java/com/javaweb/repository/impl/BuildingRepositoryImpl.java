package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository{
	static final String URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASSWORD = "Longfb1231@";
	@Override
	public List<BuildingEntity> findAll(String name) {
		String sql = "SELECT * FROM building where name like '%" + name + "%'";
		List<BuildingEntity> result = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {
			while(rs.next()) {
				BuildingEntity building = new BuildingEntity();
				building.setName(rs.getString("name"));
				building.setStreet(rs.getString("street"));
				building.setWard(rs.getString("ward"));
				building.setNumberOfBasement(rs.getInt("numberofbasement"));
				result.add(building);
			}
		} catch (SQLException e) {
			System.out.println("Lỗi khi kết nối: " + e.getMessage());
		}
		return result;
	}
}
