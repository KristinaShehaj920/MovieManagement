package com.app.management.movie.dal;

import com.app.management.movie.domain.Actor;
import com.app.management.movie.domain.Role;

public class RoleDAL extends BaseDAL {

	public void addRole(Role role) {
		String queryAddRole = RoleQueries.INSERT_ROLE.getQuery();
		String replaceQueryWithParams = String.format(queryAddRole,role.getAct_id(),role.getMov_id(),
				role.getRole());
				

		  commitStatement(replaceQueryWithParams);
	}
	
	public void deleteRole(int act_id) {
		 String queryDeleteRole =RoleQueries.DELETE_ROLE.getQuery();
		 String replaceQueryWithParams = String.format(queryDeleteRole,act_id);
		 commitStatement(replaceQueryWithParams);
		 
		 }
	 public void getAllRole() {
		 String queryGetAllRole= RoleQueries.SELECT_ALL_ROLE.getQuery();
		 String replaceQueryWithParams = String.format(queryGetAllRole);
		 
	 }

}
