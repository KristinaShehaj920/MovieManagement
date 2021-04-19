package com.app.management.movie.dal;

public enum DirectorQueries {
	SELECT_ALL_DIRECTOR("select * from director"),
	INSERT_DIRECTOR("INSERT INTO director VALUES('%s','%s','%s','%s')"),
	DELETE_DIRECTOR("DELETE FROM director WHERE dir_fname = '%s'"),
	SELECT_MAX_DIRECTOR_ID ("SELECT MAX(dir_id) from director");
	
	private String query;
    private DirectorQueries(String q) {
		 this.query=q;
		 
	 }
    
    public String getQuery() {
    	 return this.query;
    }

}
