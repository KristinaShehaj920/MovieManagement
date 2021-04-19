package com.app.management.movie.dal;

public enum MovieDirectionQueries {
	SELECT_ALL_MOVIE_DIRECTION("select * from movie_direction"),
	INSERT_MOVIE_DIRECTION("INSERT INTO movie_direction VALUES('%d','%d')"),
	DELETE_MOVIE_DIRECTION("DELETE FROM movie_direction WHERE dir_id = '%d'"),
	JOIN_MOVIE_DIRECTION("select director.dir_fname ,director.dir_lname , movie.mov_title , movie.film_genre \r\n" + 
			"from movie_direction \r\n" + 
			"inner join director \r\n" + 
			"on director.dir_id = movie_direction.dir_id \r\n" + 
			"inner join movie \r\n" + 
			"on movie_direction.mov_id = movie.mov_id ;");

	private String query;
    private MovieDirectionQueries(String q) {
		 this.query=q;
		 
	 }
    
    public String getQuery() {
    	 return this.query;
    }
}
