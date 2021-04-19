package com.app.management.movie.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class BaseDAL {

	protected Connection connection;
	private final String url = "jdbc:postgresql://localhost/movie_management_db";
	private final String user = "postgres";
	private final String password = "kristi2020";
	
	public BaseDAL() {
		setConnection();

	}

	
	public void commitStatement(String query) {
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.executeUpdate();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "An error occured due invalid/conflict in customer data inputted.", "",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}
	
	
	public void setConnection() {
		try {
			this.connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public ResultSet getResultSet(String query) {
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			return rs;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "An error occured due invalid/conflict in customer data inputted.", "",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return null;
		}
	}

	public int getTableNextId(String query) {
		int id = 0;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			if (rs.next()) {
				id = rs.getInt(1);
				id = id + 1;
			}
			return id;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "An error occured due invalid/conflict in customer data inputted.", "",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return 0;
		}
	}
 
	


}
