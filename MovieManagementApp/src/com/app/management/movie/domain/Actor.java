package com.app.management.movie.domain;

public class Actor {

   private int act_id;
   private String act_fname;
   private String act_lname;
   private String act_gender;
   private String act_age;
   public Actor( int act_id,String act_fname,String act_lname,String act_gender,String act_age) {
	   this.act_id=act_id;
	   this.act_fname=act_fname;
	   this.act_lname=act_lname;
	   this.act_gender=act_gender;
	   this.act_age=act_age;

   }

public int getAct_id() {
	return act_id;
}

public void setAct_id(int act_id) {
	this.act_id = act_id;
}

public String getAct_fname() {
	return act_fname;
}

public void setAct_fname(String act_fname) {
	this.act_fname = act_fname;
}

public String getAct_lname() {
	return act_lname;
}

public void setAct_lname(String act_lname) {
	this.act_lname = act_lname;
}

public String getAct_gender() {
	return act_gender;
}

public void setAct_gender(String act_gender) {
	this.act_gender = act_gender;
}

public String getAct_age() {
	return act_age;
}

public void setAct_age(String act_age) {
	this.act_age = act_age;
}
   
   
}
