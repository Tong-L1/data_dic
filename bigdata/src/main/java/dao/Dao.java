package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.*;
import util.Util;


public class Dao {
	
	
	public boolean createTable_csv(ArrayList<String> list,int size,String table_name) {
		Connection connection = Util.getConnection();
		PreparedStatement preparedStatement=null;
		String[] split = list.get(0).split("\t");
		String sql = "create table "+table_name+"(";
		try {
    		for(int i=0;i<size;i++) {
            	sql+=split[i]+" "+"text";
            	if(i<size-1) sql+=",";
            	else sql+=")";
            }
    		preparedStatement=connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println(table_name+"-创建表");
            return true;
		    }catch (SQLException  e) {
		        e.printStackTrace();
		    }finally{
		        Util.close(preparedStatement);
		        Util.close(connection);
		    }
		return false;
	}
	
	public void insert_tablesData(String table_name) {
		Connection connection = Util.getConnection();
        PreparedStatement preparedStatement=null; 
        try {
        	String sql = "insert into tables(table_name,upload_date,table_state) values (?,?,?)";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,table_name);
            preparedStatement.setString(2,GetTime.getTime());
            preparedStatement.setString(3,"已上传");
            preparedStatement.executeUpdate();
            System.out.println(table_name+"-表信息登记");
        } catch (SQLException  e) {
            e.printStackTrace();
        } finally{
        	Util.close(preparedStatement);
        	Util.close(connection);
        }
	}
	public void delete_tablesData(String table_name) {
		Connection connection = Util.getConnection();
        PreparedStatement preparedStatement=null; 
        try {
        	String sql = "delete from tables where table_name = ?";
        	preparedStatement=connection.prepareStatement(sql);
        	preparedStatement.setString(1,table_name);
        	preparedStatement.executeUpdate();
        	System.out.println(table_name+"-删除信息");
        } catch (SQLException  e) {
            e.printStackTrace();
        } finally{
        	Util.close(preparedStatement);
        	Util.close(connection);
        }
	}
	

	public List<Tables> getTables(){
		List<Tables> list = new ArrayList<Tables>();
		Connection connection = Util.getConnection();
		PreparedStatement preparedStatement = null; 
		ResultSet rs = null;
		try {
	        String sql="select * from tables";
	        preparedStatement = connection.prepareStatement(sql);
	        rs = preparedStatement.executeQuery();
	        while(rs.next()) {
	        	Tables tables = new Tables();
	        	tables.setTable_id(rs.getInt("table_id"));
	        	tables.setTable_name(rs.getString("table_name"));
	        	tables.setUpload_date(rs.getString("upload_date"));
	            list.add(tables);
	        }
	    }catch(SQLException e) {
	        e.printStackTrace();
	    }finally{
	    	Util.close(rs);
        	Util.close(preparedStatement);
        	Util.close(connection);
        }
		return list;
	}
	
	
	public List<Object> getColumnName(String table_name) {
		List<Object> list = new ArrayList<Object>();
		Connection connection = Util.getConnection();
		PreparedStatement preparedStatement = null; 
		ResultSet rs = null;
		try {
	        String sql="select COLUMN_NAME from information_schema.columns where table_name=? and TABLE_SCHEMA='bigdata'";
	        preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1,table_name);
	        rs = preparedStatement.executeQuery();
	        while(rs.next()) {
	        	list.add(rs.getObject(1));
	        }
	    }catch(SQLException e) {
	        e.printStackTrace();
	    }finally{
	    	Util.close(rs);
        	Util.close(preparedStatement);
        	Util.close(connection);
        }
		return list;
	}
	
	public List<Object> getTableData(String table_name) {
		List<Object> columnList = getColumnName(table_name);
		List<Object> list = new ArrayList<Object>();
		Connection connection = Util.getConnection();
		PreparedStatement preparedStatement = null; 
		ResultSet rs = null;
		try {
	        String sql="select * from "+table_name;
	        preparedStatement = connection.prepareStatement(sql);
	        rs = preparedStatement.executeQuery();
	        while(rs.next()) {
	        	for(int i=0;i<columnList.size();i++) {
	        		list.add(rs.getObject(i+1));
	        	}
	        }
	    }catch(SQLException e) {
	        e.printStackTrace();
	    }finally{
	    	Util.close(rs);
        	Util.close(preparedStatement);
        	Util.close(connection);
        }
		return list;
	}
	
	
	public void dropTable(String table_name) {
		Connection connection = Util.getConnection();
        PreparedStatement preparedStatement=null; 
        try {
        	String sql = "DROP TABLE "+table_name;
        	preparedStatement=connection.prepareStatement(sql);
        	preparedStatement.executeUpdate();
        	System.out.println(table_name+"-移除");
        } catch (SQLException  e) {
            e.printStackTrace();
        } finally{
        	Util.close(preparedStatement);
        	Util.close(connection);
        }
	}
	
	public List<Thedd> getThedd(String table_name){
		List<Thedd> list = new ArrayList<Thedd>();
		Connection connection = Util.getConnection();
		PreparedStatement preparedStatement = null; 
		ResultSet rs = null;
		try {
	        String sql="SELECT COLUMN_NAME,COLUMN_TYPE,COLUMN_KEY,COLUMN_COMMENT "
	        		+ "FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = 'bigdata' AND TABLE_NAME = '"+table_name+"'";
	        preparedStatement = connection.prepareStatement(sql);
	        rs = preparedStatement.executeQuery();
	        while(rs.next()) {
	        	Thedd dd = new Thedd();
	        	dd.setColumn_name(rs.getString("COLUMN_NAME"));
	        	dd.setColumn_type(rs.getString("COLUMN_TYPE"));
	        	dd.setColumn_key(rs.getString("COLUMN_KEY"));
	        	dd.setColumn_comment(rs.getString("COLUMN_COMMENT"));
	            list.add(dd);
	        }
	    }catch(SQLException e) {
	        e.printStackTrace();
	    }finally{
	    	Util.close(rs);
        	Util.close(preparedStatement);
        	Util.close(connection);
        }
		return list;
	}
	
	
	public List<Thedd> getAllThedd(){
		List<Thedd> list = new ArrayList<Thedd>();
		Connection connection = Util.getConnection();
		PreparedStatement preparedStatement = null; 
		ResultSet rs = null;
		try {
	        String sql="SELECT TABLE_NAME,COLUMN_NAME,COLUMN_TYPE,COLUMN_KEY,COLUMN_COMMENT "
	        		+ "FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = 'bigdata' and TABLE_NAME != 'tables' ";
	        preparedStatement = connection.prepareStatement(sql);
	        rs = preparedStatement.executeQuery();
	        while(rs.next()) {
	        	Thedd dd = new Thedd();
	        	dd.setTable_name(rs.getString("TABLE_NAME"));
	        	dd.setColumn_name(rs.getString("COLUMN_NAME"));
	        	dd.setColumn_type(rs.getString("COLUMN_TYPE"));
	        	dd.setColumn_key(rs.getString("COLUMN_KEY"));
	        	dd.setColumn_comment(rs.getString("COLUMN_COMMENT"));
	            list.add(dd);
	        }
	    }catch(SQLException e) {
	        e.printStackTrace();
	    }finally{
	    	Util.close(rs);
        	Util.close(preparedStatement);
        	Util.close(connection);
        }
		return list;
	}
	

	public void changeTableComment(String table_name,String column_name,String column_type,String newComment) {
		Connection connection = Util.getConnection();
        PreparedStatement preparedStatement=null; 
        try {
        	String sql = "alter table "+table_name+" modify column "+column_name+" "+column_type+" comment '"+newComment+"'";
        	preparedStatement=connection.prepareStatement(sql);
        	preparedStatement.executeUpdate();
        	System.out.println(table_name+"修改注释");
        } catch (SQLException  e) {
            e.printStackTrace();
        } finally{
        	Util.close(preparedStatement);
        	Util.close(connection);
        }
	}
	
	
	public static void main(String args[]) {
		Dao dao = new Dao();
		//dao.changeTableComment("product", "ptype", "text", "设备类型");
		
	}
	
	
	
	
}