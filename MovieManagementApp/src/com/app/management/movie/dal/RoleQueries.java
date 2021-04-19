package com.app.management.movie.dal;

public enum RoleQueries {

	SELECT_ALL_ROLE("select * from movie_cast"),
	INSERT_ROLE("INSERT INTO movie_cast VALUES('%d','%d','%s')"),
	DELETE_ROLE("DELETE FROM movie_cast WHERE act_id = '%d'"),
	JOIN_ACTOR_CAST_MOVIE("select actor.act_fname ,actor.act_lname , movie_cast.role ,movie.mov_title \r\n" + 
			"from movie_cast \r\n" + 
			"inner join actor \r\n" + 
			"on actor.act_id =movie_cast.act_id \r\n" + 
			"inner join movie\r\n" + 
			"on movie_cast.mov_id =movie.mov_id ;");
	private String query;
    private RoleQueries(String q) {
		 this.query=q;
		 
	 }
    
    public String getQuery() {
    	 return this.query;
    }
}
