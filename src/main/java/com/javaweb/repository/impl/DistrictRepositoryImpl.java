package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.DistrictEntity;
@Repository
public class DistrictRepositoryImpl implements DistrictRepository{
	static final String URL = "jdbc:mysql://localhost:3306/estatebasics?useSSL=false";
	static final String USER = "root";
	static final String PASSWORD = "Longfb1231@";

	@Override
	public DistrictEntity findNameById(Long id) {
		String sql= "select d.name from district d where d.id=" + id + ";";
		DistrictEntity district = new DistrictEntity();
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while(rs.next()) {
				district.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			System.out.println("Lỗi khi kết nối: " + e.getMessage());
		}
		return district;
	}
	
}
