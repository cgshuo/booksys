package com.sdust.entity;


public class User {
	private Integer id;
	private String userName;
	private String password;
	private String email;
	private Integer is_locked; //ÊÇ·ñËø¶¨µÄ×´Ì¬
	private String rePassword;
	
	//private List<Orders> orders;
	
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
		public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getIs_locked() {
		return is_locked;
	}
	public void setIs_locked(Integer is_locked) {
		this.is_locked = is_locked;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
