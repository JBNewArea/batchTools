package com.batch.action;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.StringUtil;

import com.batch.dto.ProjectDeclare;
import com.batch.framwork.jdbc.JDBC;
import com.batch.framwork.utils.mapLongItudeUtils;

public class ExportExcelAction {

	  public static List<ProjectDeclare> list = new ArrayList<>();
	  public static ProjectDeclare porjectDeclare = null;
	
	  public static void main(String[] args) {
		  //readExcelToObj("/Users/Arthur/Desktop/projectInfo - 全部数据.xlsx");  
		  //System.out.println(list);
		  //queryMain(list);
		  //insertMain(list);
	  }
	  
	  //执行项目办理情况插入
	  public static void insertMain(List<ProjectDeclare> declList){
		  if(!declList.isEmpty()){
			  long oldTime_date;
			  long oldTime_caozuo;
			  Timestamp stamp_date;
			  Timestamp stamp_cz;
			  Connection conn = JDBC.connect();
			  PreparedStatement pstmt = null;
			  for (ProjectDeclare projectDeclare : declList) {
				  if(!projectDeclare.getProjectCode().isEmpty()){
					  //查询项目办理情况信息
					  String query_project="select id from  project_declare where project_code='"+projectDeclare.getProjectCode()+"'";
					  try {
						pstmt = (PreparedStatement)conn.prepareStatement(query_project);
						ResultSet rs = pstmt.executeQuery();
						while (rs.next()) {
							 if(!projectDeclare.getName().isEmpty()){
								  if(!projectDeclare.getDate().isEmpty()){
									    oldTime_date  = (new Date(projectDeclare.getDate())).getTime(); //得到毫秒数
										 stamp_date = new Timestamp(oldTime_date);
								   }else{
									    oldTime_date  = (new Date("2011/11/11 20:10:10")).getTime(); //得到毫秒数
										 stamp_date = new Timestamp(oldTime_date);
								   }
								   
								   if(!projectDeclare.getCaozuoDate().isEmpty()){
									    oldTime_caozuo  = (new Date(projectDeclare.getCaozuoDate())).getTime(); //得到毫秒数
										stamp_cz = new Timestamp(oldTime_caozuo);
								   }else{
									   oldTime_caozuo  = (new Date("2011/11/11 20:10:10")).getTime(); //得到毫秒数
										stamp_cz = new Timestamp(oldTime_caozuo);
								   }
								    
									//插入项目办理情况内容
								  String insert_sql="insert into project_handle(id,project_declare_id,name,handle_status,end_code,date,office,caozuo_date,del_flag,create_by)"
					  						+ "	values('"+UUID.randomUUID()+"','"+rs.getString(1)+"','"+projectDeclare.getName()+"','"+projectDeclare.getHandleStatus()+"',"
					  								+ "'"+projectDeclare.getEndCode()+"','"+stamp_date+"','"+projectDeclare.getOffice()+"','"+stamp_cz+"','0','3a539c8bd1d8438eb4bf0f1cb53a04cc')";
								  boolean upres = JDBC.execute(insert_sql);
								  System.out.println(upres);
							  }
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				  }
			  }
		  }
		  
	  }
	  public static boolean queryMain(String id){
		  boolean flag = false;
		  Connection conn = JDBC.connect();
		  String query_project="select project_name from project_declare where project_code='"+id+"'";
		  PreparedStatement pstmt = null;
		  try {
			pstmt = (PreparedStatement)conn.prepareStatement(query_project);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString(1));
				 flag = true;
			}
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
		  return flag;
	  }
	
	 /**  
	    * 读取excel数据  
	    * @param  path  
	    */  
	    private static void readExcelToObj(String path) {  
	      
	        Workbook wb = null;  
	        try {  
	            wb = WorkbookFactory.create(new File(path));  
	            readExcel(wb, 0, 4, 0);  
	        } catch (InvalidFormatException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	    }  
	      
	    /**  
	    * 读取excel文件  
	    * @param  wb   
	    * @param sheetIndex sheet页下标：从0开始  
	    * @param startReadLine 开始读取的行:从0开始  
	    * @param tailLine 去除最后读取的行  
	    */  
	    private static void readExcel(Workbook wb,int sheetIndex, int startReadLine, int tailLine) {  
	        Sheet sheet = wb.getSheetAt(sheetIndex);  
	        Row row = null;  
	        for(int i=startReadLine; i<sheet.getLastRowNum()-tailLine+1; i++) {  
	            row = sheet.getRow(i); 
	            int cellCount = 0;
	            porjectDeclare = new ProjectDeclare();
	            for(Cell c : row) {  
	                c.setCellType(Cell.CELL_TYPE_STRING);
	                boolean isMerge = isMergedRegion(sheet, i, c.getColumnIndex());  
	                //判断是否具有合并单元格  
	                if(isMerge) {  
	                	cellCount++;
	                    String rs = getMergedRegionValue(sheet, row.getRowNum(), c.getColumnIndex());  
	                    //System.out.print(rs + "  ");  
	                    saveProject(cellCount,rs,porjectDeclare);
	                }else {  
	                	cellCount++;
	                    //System.out.print(cellCount+":"+c.getRichStringCellValue()+"  ");
	                    saveProject(cellCount,String.valueOf(c.getRichStringCellValue()),porjectDeclare);
	                }  
	            }  
	            System.out.println();  
	            list.add(porjectDeclare);
	        }  
	      
	    }  
	      
	    public static void saveProject(int count,String res,ProjectDeclare porjectDeclare){
	    	 switch(count){
	    	 		case 2:
	    	 			porjectDeclare.setDeclareDate(res);break;
	    	 		case 3:
	    	 			porjectDeclare.setProjectCode(res);break;
	    	 		case 18:
	    	 			porjectDeclare.setName(res);break;
	    	 		case 19:
	    	 			porjectDeclare.setHandleStatus(res);break;
	    	 		case 20:
	    	 			porjectDeclare.setEndCode(res);break;
	    	 		case 21:
	    	 			porjectDeclare.setDate(res);
	    	 		case 22:
	    	 			porjectDeclare.setOffice(res);
	    	 		case 23:
	    	 			porjectDeclare.setCaozuoDate(res);
	    	 }
	    }
	    /**   
	    * 获取合并单元格的值   
	    * @param sheet   
	    * @param row   
	    * @param column   
	    * @return   
	    */    
	    public static String getMergedRegionValue(Sheet sheet ,int row , int column){    
	        int sheetMergeCount = sheet.getNumMergedRegions();    
	        for(int i = 0 ; i < sheetMergeCount ; i++){    
	            CellRangeAddress ca = sheet.getMergedRegion(i);    
	            int firstColumn = ca.getFirstColumn();    
	            int lastColumn = ca.getLastColumn();    
	            int firstRow = ca.getFirstRow();    
	            int lastRow = ca.getLastRow();    
	            if(row >= firstRow && row <= lastRow){    
	                if(column >= firstColumn && column <= lastColumn){    
	                    Row fRow = sheet.getRow(firstRow);    
	                    Cell fCell = fRow.getCell(firstColumn);    
	                    return getCellValue(fCell) ;    
	                }    
	            }    
	        }    
	        return null ;    
	    }    
	      
	    /**  
	    * 判断合并了行  
	    * @param sheet  
	    * @param row  
	    * @param column  
	    * @return  
	    */  
	    private boolean isMergedRow(Sheet sheet,int row ,int column) {  
	      int sheetMergeCount = sheet.getNumMergedRegions();  
	      for (int i = 0; i < sheetMergeCount; i++) {  
	        CellRangeAddress range = sheet.getMergedRegion(i);  
	        int firstColumn = range.getFirstColumn();  
	        int lastColumn = range.getLastColumn();  
	        int firstRow = range.getFirstRow();  
	        int lastRow = range.getLastRow();  
	        if(row == firstRow && row == lastRow){  
	            if(column >= firstColumn && column <= lastColumn){  
	                return true;  
	            }  
	        }  
	      }  
	      return false;  
	    }  
	      
	    /**  
	    * 判断指定的单元格是否是合并单元格  
	    * @param sheet   
	    * @param row 行下标  
	    * @param column 列下标  
	    * @return  
	    */  
	    private static boolean isMergedRegion(Sheet sheet,int row ,int column) {  
	      int sheetMergeCount = sheet.getNumMergedRegions();  
	      for (int i = 0; i < sheetMergeCount; i++) {  
	        CellRangeAddress range = sheet.getMergedRegion(i);  
	        int firstColumn = range.getFirstColumn();  
	        int lastColumn = range.getLastColumn();  
	        int firstRow = range.getFirstRow();  
	        int lastRow = range.getLastRow();  
	        if(row >= firstRow && row <= lastRow){  
	            if(column >= firstColumn && column <= lastColumn){  
	                return true;  
	            }  
	        }  
	      }  
	      return false;  
	    }  
	      
	    /**  
	    * 判断sheet页中是否含有合并单元格   
	    * @param sheet   
	    * @return  
	    */  
	    private boolean hasMerged(Sheet sheet) {  
	            return sheet.getNumMergedRegions() > 0 ? true : false;  
	    }  
	      
	    /**  
	    * 合并单元格  
	    * @param sheet   
	    * @param firstRow 开始行  
	    * @param lastRow 结束行  
	    * @param firstCol 开始列  
	    * @param lastCol 结束列  
	    */  
	    private void mergeRegion(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {  
	        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));  
	    }  
	      
	    /**   
	    * 获取单元格的值   
	    * @param cell   
	    * @return   
	    */    
	    public static String getCellValue(Cell cell){    
	        if(cell == null) return "";    
	        if(cell.getCellType() == Cell.CELL_TYPE_STRING){    
	            return cell.getStringCellValue();    
	        }else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){    
	            return String.valueOf(cell.getBooleanCellValue());    
	        }else if(cell.getCellType() == Cell.CELL_TYPE_FORMULA){    
	            return cell.getCellFormula() ;    
	        }else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){    
	            return String.valueOf(cell.getNumericCellValue());    
	        }    
	        return "";    
	    }    
}
