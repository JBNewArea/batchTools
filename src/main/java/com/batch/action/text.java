package com.batch.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.batch.framwork.jdbc.JDBC;

public class text {

	
	public static void query(){
		Connection conn = JDBC.connect();
		String sql="select id,user_name,password,age from user_t ";
		PreparedStatement pstmt;
		
		try {
			pstmt = (PreparedStatement)conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
	            System.out.println(rs.getString(1));
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		 query();
	}
	
}
