package com.app.management.movie.domain;

public class Director {
	
	   private int dir_id;
	   private String dir_fname;
	   private String dir_lname;
	   private String dir_gender;
	   
	   public Director( int dir_id,String dir_fname,String dir_lname,String dir_gender) {
		   this.dir_id=dir_id;
		   this.dir_fname=dir_fname;
		   this.dir_lname=dir_lname;
		   this.dir_gender= dir_gender;
		   
	   }

	public int getDir_id() {
		return dir_id;
	}

	public void setDir_id(int dir_id) {
		this.dir_id = dir_id;
	}

	public String getDir_fname() {
		return dir_fname;
	}

	public void setDir_fname(String dir_fname) {
		this.dir_fname = dir_fname;
	}

	public String getDir_lname() {
		return dir_lname;
	}

	public void setDir_lname(String dir_lname) {
		this.dir_lname = dir_lname;
	}

	public String getDir_gender() {
		return dir_gender;
	}

	public void setDir_gender(String dir_gender) {
		this.dir_gender = dir_gender;
	}
	   
	   

}
