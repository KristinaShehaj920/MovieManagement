package com.app.management.movie.dal;

import com.app.management.movie.domain.Actor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ActorDAL extends BaseDAL {

	public void addActor(Actor actor) {
		String queryAddActor = ActorQueries.INSERT_ACTOR.getQuery();
		String replaceQueryWithParams = String.format(queryAddActor,actor.getAct_id(), actor.getAct_fname(),
				actor.getAct_lname(),actor.getAct_gender(),actor.getAct_age());

		  commitStatement(replaceQueryWithParams);
	}
	
	public int getActorNextId() throws SQLException {
		return getTableNextId(ActorQueries.SELECT_MAX_ACTOR_ID.getQuery());
	}

	public void deleteActor(String act_fname) {
		 String queryDeleteActor =ActorQueries.DELETE_ACTOR.getQuery();
		 String replaceQueryWithParams = String.format(queryDeleteActor,act_fname);
		 commitStatement(replaceQueryWithParams);
		 
		 }
	
	
 public void getAllActor() {
	 String queryGetAllActor=ActorQueries.SELECT_ALL_ACTOR.getQuery();
	 String replaceQueryWithParams = String.format(queryGetAllActor);
	 
 }
	
 

}
