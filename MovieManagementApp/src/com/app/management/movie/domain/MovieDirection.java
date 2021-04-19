package com.app.management.movie.domain;

public class MovieDirection {
 private int dir_id;
 private int mov_id;
 
 public MovieDirection(int dir_id,int mov_id) {
	 this.dir_id=dir_id;
	 this.mov_id=mov_id;
 }

public int getDir_id() {
	return dir_id;
}

public void setDir_id(int dir_id) {
	this.dir_id = dir_id;
}

public int getMov_id() {
	return mov_id;
}

public void setMov_id(int mov_id) {
	this.mov_id = mov_id;
}
 
 
}

