package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Elcbean;
import util.Util2;

public class Elcdao {
	public List<Elcbean> getTables(){
		List<Elcbean> list = new ArrayList<Elcbean>();
		Connection connection = Util2.getConnection();
		PreparedStatement preparedStatement = null; 
		ResultSet rs = null;
		try {
	        String sql="select id,date,money from cphnew";
	        preparedStatement = connection.prepareStatement(sql);
	        rs = preparedStatement.executeQuery();
	        while(rs.next()) {
	        	Elcbean el = new Elcbean();
	        	el.setId(rs.getString("id"));
	        	el.setDate(rs.getString("date"));
	        	el.setMoney(rs.getString("money"));
	            list.add(el);
	        }
	    }catch(SQLException e) {
	        e.printStackTrace();
	    }finally{
	    	Util2.close(rs);
        	Util2.close(preparedStatement);
        	Util2.close(connection);
        }
		return list;
	}
	
	
	public static void main(String arg[]) {
		Elcdao elcdao = new Elcdao();
		List<Elcbean> list = elcdao.getTables();
		
		String id="";
		for(int i=0;i<list.size();i++) {
			if(!list.get(i).getId().equals(id)) {
				//System.out.println();
				System.out.print("'"+list.get(i).getId()+"',");
				id=list.get(i).getId();
			}
			//System.out.print("['"+list.get(i).getDate()+"',"+list.get(i).getMoney()+"],");
			
		}
	}
}
