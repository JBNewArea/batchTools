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
		String query_project="select id,build_detail_place from project_declare";
		PreparedStatement pstmt = null;
		try {
			pstmt = (PreparedStatement)conn.prepareStatement(query_project);
			ResultSet rs = pstmt.executeQuery();
			int nullCount=0;
			while (rs.next()) {
				System.out.println(rs.getString(2));
	            String resLong = mapLongItudeUtils.getLngAndLat(rs.getString(2));
	            if(!resLong.isEmpty()){
	            	//区分经纬度
	            	 String[] longItArr = resLong.split(",");
	            	 String update_Project = "update project_declare set longitude='"+longItArr[0]+"' and latitude='"+longItArr[1]+"'  where id='"+rs.getString(1)+"'";
	            	 boolean upres = JDBC.execute(update_Project,"");
	            	 System.out.println(upres);
	            }else{
	            	 nullCount++;
	            }
	        }
			System.out.println(nullCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(pstmt !=null){
					pstmt.close();
				}
				if(conn !=null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		 String update_Project = "update project_declare set longitude=?    where id=5446456451";
    	 boolean upres = JDBC.execute(update_Project,"999");
    	 System.out.println(upres);
	}
}
