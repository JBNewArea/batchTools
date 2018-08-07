package com.batch.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.batch.framwork.jdbc.JDBC;
import com.batch.framwork.utils.mapLongItudeUtils;

public class companyAction {

	
	
	public static void execute(){
		Connection conn = JDBC.connect();
		String query_company="select id,companydomicile from company where longitude is null LIMIT 23000";
		PreparedStatement pstmt = null;
		try {
			pstmt = (PreparedStatement)conn.prepareStatement(query_company);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				if(rs.getString(2).indexOf("（化工园）")!=-1){
					System.out.println();
					String address = rs.getString(2).replaceAll("（化工园）","");
					String resLong = mapLongItudeUtils.getLngAndLat(address);
					if(!resLong.isEmpty()){
						//区分经纬度
		            	 String[] longItArr = resLong.split(",");
		            	 String update_Company = "update company set longitude=?   where id='"+rs.getString(1)+"'";
		            	 boolean upres = JDBC.execute(update_Company,longItArr[0]);
		            	 if(upres){
		            		 update_Company="update company set  latitude=?  where id='"+rs.getString(1)+"'";
		            		 upres = JDBC.execute(update_Company,longItArr[1]);
		            	 }
		            	 //System.out.println(upres);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		execute();
	}
}
