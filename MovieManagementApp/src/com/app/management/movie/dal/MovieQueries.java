package com.app.management.movie.dal;

public enum MovieQueries {
	SELECT_ALL_MOVIES("select * from movie"),
	INSERT_MOVIE("INSERT INTO movie VALUES('%d','%s','%s','%s','%tD','%s')"),
	DELETE_MOVIE("DELETE FROM movie WHERE mov_title = '%s'"),
	SELECT_MAX_MOVIE_ID ("SELECT MAX(mov_id) from movie");	
	private String query;
    private MovieQueries(String q) {
		 this.query=q;
		 
	 }
    
    public String getQuery() {
    	 return this.query;
    }

}
