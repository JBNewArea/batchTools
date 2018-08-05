package com.batch.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.batch.framwork.jdbc.JDBC;
import com.batch.framwork.utils.mapLongItudeUtils;

public class projectAction {

	
	
	
	public static void execute(){
		Connection conn = JDBC.connect();
		String query_project="select build_detail_place from project_declare";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement)conn.prepareStatement(query_project);
			ResultSet rs = pstmt.executeQuery();
			int nullCount=0;
			while (rs.next()) {
	            String resLong = mapLongItudeUtils.getLngAndLat(rs.getString(1));
	            if(!resLong.isEmpty()){
	            	 System.out.println(resLong);
	            }else{
	            	 System.out.println(rs.getString(1));
	            	 nullCount++;
	            }
	        }
			System.out.println(nullCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		execute();
	}
}
