package com.app.management.movie.dal;

import java.sql.SQLException;

import com.app.management.movie.domain.Movie;
import java.util.Date;

import javax.swing.JOptionPane;

import java.sql.*;

public class MovieDAL extends BaseDAL {
	
//	java.util.Date date;
//	java.sql.Date sqldate;
	
	public void addMovie( Movie movie) {
		String queryAddMovie = MovieQueries.INSERT_MOVIE.getQuery();
		String replaceQueryWithParams = String.format(queryAddMovie,movie.getMov_id(),movie.getMov_title(),
				movie.getMov_lang(),movie.getMov_rel_country(),movie.getMov_date_rel(),movie.getMov_genre());

		  commitStatement(replaceQueryWithParams);
	}

	public int getMovieNextId() throws SQLException {
		return getTableNextId(MovieQueries.SELECT_MAX_MOVIE_ID.getQuery());
	}
	
	public void deleteMovie(String mov_title) {
		 String queryDeleteMovie =MovieQueries.DELETE_MOVIE.getQuery();
		 String replaceQueryWithParams = String.format(queryDeleteMovie,mov_title);
		 commitStatement(replaceQueryWithParams);
		 
		 }
	
	public void getAllMovie() {
		 String queryGetAllMovie=MovieQueries.SELECT_ALL_MOVIES.getQuery();
		 String replaceQueryWithParams = String.format(queryGetAllMovie);
		 
	 }
	
}

