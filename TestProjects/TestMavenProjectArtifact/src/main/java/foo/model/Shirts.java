package foo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



@ManagedBean
@RequestScoped
public class Shirts implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String shirtdesign;
	private String shirtprice;
	
	
	public Shirts(int id,String shirtdesign,String shirtprice)
	{
		this.id = id;
		this.shirtdesign = shirtdesign;
		this.shirtprice = shirtprice;
	}
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getShirtdesign() {
		return shirtdesign;
	}
	public void setShirtdesign(String shirtdesign) {
		this.shirtdesign = shirtdesign;
	}
	public String getShirtprice() {
		return shirtprice;
	}
	public void setShirtprice(String shirtPrice) {
		this.shirtprice = shirtPrice;
	}
	
	
	
	
}
