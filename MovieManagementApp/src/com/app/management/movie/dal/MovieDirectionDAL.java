package com.app.management.movie.dal;

import com.app.management.movie.domain.Actor;
import com.app.management.movie.domain.MovieDirection;

public class MovieDirectionDAL extends BaseDAL {
	
	public void addMovieDirection(MovieDirection movDir) {
		String queryAddMovieDirection=MovieDirectionQueries.INSERT_MOVIE_DIRECTION.getQuery();
		String replaceQueryWithParams = String.format(queryAddMovieDirection,movDir.getDir_id(),movDir.getMov_id());

		  commitStatement(replaceQueryWithParams);
	}
	public void deleteMovieDirection(int dir_id) {
		 String queryDeleteMovieDirection = MovieDirectionQueries.DELETE_MOVIE_DIRECTION.getQuery();
		 String replaceQueryWithParams = String.format(queryDeleteMovieDirection,dir_id);
		 commitStatement(replaceQueryWithParams);
		 
		 }
	
	public void getAllMovieDirection() {
		 String queryGetAllMovieDirection=MovieDirectionQueries.SELECT_ALL_MOVIE_DIRECTION.getQuery();
		 String replaceQueryWithParams = String.format(queryGetAllMovieDirection);
		 
	 }
}
