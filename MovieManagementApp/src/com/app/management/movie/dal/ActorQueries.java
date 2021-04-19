package com.app.management.movie.dal;

public enum ActorQueries {
	
	SELECT_ALL_ACTOR("select * from actor"),
	INSERT_ACTOR("INSERT INTO actor VALUES('%s','%s','%s','%s','%s')"),
	DELETE_ACTOR("DELETE FROM actor WHERE act_fname='%s'"),
	SELECT_MAX_ACTOR_ID ("SELECT MAX(act_id) from actor");

	private String query;
    private ActorQueries(String q) {
		 this.query=q;
		 
	 }
    
    public String getQuery() {
    	 return this.query;
    }
}
