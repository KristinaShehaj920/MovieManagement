package com.app.management.movie.domain;

import java.sql.Date;

public class Movie {
	 
	private int mov_id;
	private String mov_title;
	private String mov_lang ;
	private String mov_rel_country;
	private Date mov_date_rel;
	private String mov_genre;
	public Movie(int mov_id,String mov_title,String mov_lang,String mov_rel_country,Date mov_date_rel,String mov_genre) {
		this.mov_id=mov_id;
		this.mov_title=mov_title;
		this.mov_lang=mov_lang;
		this.mov_rel_country=mov_rel_country;
		this.mov_date_rel=mov_date_rel;
		this.mov_genre=mov_genre;
		
	}

	public int getMov_id() {
		return mov_id;
	}

	public void setMov_id(int mov_id) {
		this.mov_id = mov_id;
	}

	public String getMov_title() {
		return mov_title;
	}

	public void setMov_title(String mov_title) {
		this.mov_title = mov_title;
	}

	public String getMov_lang() {
		return mov_lang;
	}

	public void setMov_lang(String mov_lang) {
		this.mov_lang = mov_lang;
	}

	public String getMov_rel_country() {
		return mov_rel_country;
	}

	public void setMov_rel_country(String mov_rel_country) {
		this.mov_rel_country = mov_rel_country;
	}
	
	public Date getMov_date_rel() {
		return mov_date_rel;
	}

	public void setMov_dt_rel(Date mov_date_rel) {
		this.mov_date_rel = mov_date_rel;
	}

	public String getMov_genre() {
		return mov_genre;
	}

	public void setMov_genre(String mov_genre) {
		this.mov_genre = mov_genre;
	}


	
	
}
