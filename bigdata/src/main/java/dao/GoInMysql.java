package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import util.Util;


public class GoInMysql {

	public ArrayList<String> readCsv(File file) {
        file.setReadable(true);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = "";
        ArrayList<String> allString = new ArrayList<>();
        try {
            while ((line = br.readLine()) != null) {
                allString.add(line);
                break;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return allString;
	}
	
	public int getTableSize(File file) {
		ArrayList<String> list = readCsv(file);
		String[] split = list.get(0).split(",");
		return split.length;
	}
	
	
	public String getFileExtension(File file) {
		String fileName = file.getName();
		if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
			return fileName.substring(fileName.lastIndexOf(".")+1);
		}
		else return "";
	}
	
	public String fileFormat(File file) {
		File tempfile = new File("F:\\TEMP\\tempfile."+getFileExtension(file));
		file.renameTo(tempfile);
		String string = tempfile.toString();
		String newString = string.replaceAll("\\\\", "\\/");
		return newString;
	}
	

	public boolean insertData_csv(File file,String tablename) {
		Connection connection = Util.getConnection();
        PreparedStatement preparedStatement=null; 
        try {
        	String sql = "LOAD DATA INFILE '"+fileFormat(file)+"'ignore INTO TABLE "+tablename+" FIELDS TERMINATED BY '\t' ENCLOSED BY '\"' LINES TERMINATED BY '\\n' IGNORE 1 LINES";       	
        	preparedStatement=connection.prepareStatement(sql);
        	preparedStatement.executeUpdate();
        	System.out.println(file.getName().substring(0,file.getName().indexOf("."))+"-数据插入");
        	return true;
        } catch (SQLException  e) {
            e.printStackTrace();
        } finally{
        	Util.close(preparedStatement);
        	Util.close(connection);
        }
        return false;
	}
	
	public void loadFile(File filepath) {
		File fa[] = filepath.listFiles();
		Dao dao = new Dao();
		String tablename="";
	    for (int i = 0; i < fa.length; i++) {
	    	System.out.println("-------------");
	    	tablename=fa[i].getName().substring(0,fa[i].getName().indexOf("."));
	        if(dao.createTable_csv(readCsv(fa[i]),getTableSize(fa[i]),tablename)) {
	        	if(insertData_csv(fa[i],tablename)) {
	        		dao.insert_tablesData(tablename);
	        	}
	        	else {
	        		dao.dropTable(tablename);
	        	}
	        }
	        
	        new File(fileFormat(fa[i])).delete();
	        System.out.println(fa[i]+"ִ执行完毕");
	        System.out.println("-------------");
	    }
	}
	
	

	public static void main(String[] args) {
		GoInMysql gmGoInMysql = new GoInMysql();
		File file = new File("F:\\TEMP");
		gmGoInMysql.loadFile(file);
		System.out.println(gmGoInMysql.fileFormat(file));

	}
	
	
}
