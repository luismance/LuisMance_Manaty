package foo.controller; 

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import foo.model.Member;
import foo.model.Shirts;

@ManagedBean
@ViewScoped
public class ShirtControl implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Shirts> shirts;

	String shirtid=null;
	String shirtdesign=null;
	String shirtprice=null;
	
	public ShirtControl()
	{
		
		populateShirtList();
		
	}
	
	private void populateShirtList()
	{
		shirts = new ArrayList<Shirts>();
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			
			conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/testDB","postgres","");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM shirtdb ORDER by shirtid ASC;");
			
			
			while(rs.next())
			{
				
				shirts.add(new Shirts(Integer.parseInt(rs.getString("shirtid")),rs.getString("shirtdesign"),rs.getString("shirtprice")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void reset()
	{
		RequestContext.getCurrentInstance().reset("reg:idinput");
	}
	public String registerShirt(String id,String shirtdesign,String shirtprice)
	{
		
		Connection conn = null;
		PreparedStatement pr= null;
		
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/testDB","postgres","");
			pr=conn.prepareStatement("INSERT INTO shirtdb VALUES("+id+",'"+shirtdesign+"','"+shirtprice+"')");
			pr.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		populateShirtList();
		reset();
		return "index.xhtml";
		
	}
	
	
	
	public String deleteShirt(String deleteId)
	{
		
		Connection conn = null;
		PreparedStatement pr= null;
		
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/testDB","postgres","");
			pr=conn.prepareStatement("DELETE FROM shirtdb WHERE shirtid = "+deleteId+"");
			pr.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		populateShirtList();
		reset();
		return "index.xhtml";
	}
	public String updateShirt(String id,String shirtdesign,String shirtprice)
	{
		Connection conn = null;
		PreparedStatement pr= null;
		
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/testDB","postgres","");
			pr=conn.prepareStatement("UPDATE shirtdb SET shirtdesign = '"+shirtdesign+"',shirtprice = '"+shirtprice+"' WHERE shirtid = "+id+"");
			pr.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		populateShirtList();
		reset();
		return "index.xhtml";
	}
	public List<Shirts> getShirts() {
		
		
		return shirts;
	}

	public void setShirts(ArrayList<Shirts> shirts) {
		this.shirts = shirts;
	}
	
	
}
