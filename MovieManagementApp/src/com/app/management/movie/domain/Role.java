package com.app.management.movie.domain;

public class Role {

	private int act_id;
	private int mov_id;
	private String role;
	 
	public Role(int act_id,int mov_id,String role) {
		this.act_id=act_id;
		this.mov_id=mov_id;
		this.role=role;
	}

	public int getAct_id() {
		return act_id;
	}

	public void setAct_id(int act_id) {
		this.act_id = act_id;
	}

	public int getMov_id() {
		return mov_id;
	}

	public void setMov_id(int mov_id) {
		this.mov_id = mov_id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
