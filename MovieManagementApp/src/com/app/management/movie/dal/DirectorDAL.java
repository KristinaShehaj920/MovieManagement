package com.app.management.movie.dal;

import java.sql.SQLException;

import com.app.management.movie.domain.Director;

public class DirectorDAL extends BaseDAL{
	
	public void addDirector( Director director) {
		String queryAddDirector = DirectorQueries.INSERT_DIRECTOR.getQuery();
		String replaceQueryWithParams = String.format(queryAddDirector,director.getDir_id(),director.getDir_fname(),
				director.getDir_lname(),director.getDir_gender());

		  commitStatement(replaceQueryWithParams);
	}

	public int getDirectorNextId() throws SQLException {
		return getTableNextId(DirectorQueries.SELECT_MAX_DIRECTOR_ID.getQuery());
	}
	
	public void deleteDirector(String dir_fname) {
		 String queryDeleteDirector =DirectorQueries.DELETE_DIRECTOR.getQuery();
		 String replaceQueryWithParams = String.format(queryDeleteDirector,dir_fname);
		 commitStatement(replaceQueryWithParams);
		 
		 }
	 public void getAllDirector() {
		 String queryGetAllDirector=DirectorQueries.SELECT_ALL_DIRECTOR.getQuery();
		 String replaceQueryWithParams = String.format(queryGetAllDirector);
		 
	 }

}
