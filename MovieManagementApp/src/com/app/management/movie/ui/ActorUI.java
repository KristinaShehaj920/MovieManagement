package com.app.management.movie.ui;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
//import java.sql.Date;
import java.sql.*;


import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
import org.jdatepicker.impl.UtilDateModel;

import com.app.management.movie.dal.ActorDAL;
import com.app.management.movie.dal.ActorQueries;
import com.app.management.movie.dal.BaseDAL;
import com.app.management.movie.dal.DirectorDAL;
import com.app.management.movie.dal.DirectorQueries;
import com.app.management.movie.dal.MovieDAL;
import com.app.management.movie.dal.MovieDirectionDAL;
import com.app.management.movie.dal.MovieDirectionQueries;
import com.app.management.movie.dal.MovieQueries;
import com.app.management.movie.dal.RoleDAL;
import com.app.management.movie.dal.RoleQueries;
import com.app.management.movie.domain.Actor;
import com.app.management.movie.domain.Director;
import com.app.management.movie.domain.Movie;
import com.app.management.movie.domain.MovieDirection;
import com.app.management.movie.domain.Role;

import net.proteanit.sql.DbUtils;


public class ActorUI extends BaseDAL {
	private ActorDAL actDAL;
	private DirectorDAL dirDAL;
	private MovieDAL movDAL;
	private RoleDAL rolDAL;
	private MovieDirectionDAL movDirDAL;
	private JFrame frame;
	private JPanel  panel_all, panel_actor ,panel_director,panel_movie, panel_role,panel_movie_direction;
	
//	private JTextField id_actorTF;
	private JTextField name_actorTF,lastname_actorTF,age_actorTF;
	private JComboBox comboGender;
	
	private JTextField id_directorTF,name_directorTF,lastname_directorTF;
	private JComboBox comboGender_Director;
	
	private JTextField id_movieTF, title_movieTF,lang_movieTF , movie_date_relTF;
    private JComboBox comboCountry,comboMovieGenre;

    private JTextField act_id_roleTF;
    private JTextField mov_id_roleTF;
    private JComboBox comboRole;
	
    private JTextField dir_id_directionTF;
    private JTextField mov_id_directionTF;
	
	private JButton btnActor;
	private JButton btnDirector;
	private JButton btnMovie;
	private JButton btnRole;
	private JButton btnMovie_Direction;
	
	private JButton btnADD_Actor;
	private JButton btnDELETE_Actor;
	private JButton btnNextPage_Actor ;
	private JButton btnRESET_Actor;
	DefaultTableModel model1;
	 JTable table_actor;
	 DefaultTableModel model2;
	 JTable table_director;
	 DefaultTableModel model3;
	 JTable table_actor_role;
	 DefaultTableModel model4;
	 JTable table_movie_role;
	 DefaultTableModel model5;
	 JTable table_role;
	 DefaultTableModel model6;
	 JTable table_director_direction;
	 DefaultTableModel model7;
	 JTable table_movie_direction;
	 DefaultTableModel model8;
	 JTable table_movieDirection;
	 DefaultTableModel model9;
	 JTable table_movie;
	 DefaultTableModel model_join;
	 JTable table_join_movie_cast;
	 DefaultTableModel model_join_direction;
	 JTable table_join_movie_direction;
    private JButton btnAdd_Director;
    private JButton btnDelete_Director;
    private JButton btnReset_Director;
	private JButton btnNextPage_Director;
	
	private JButton btnAdd_Movie;
	private JButton btnDelete_Movie;
	private JButton btnReset_Movie;
	private JButton btnNext_Movie;
	private JButton buttonBack_movie;
	JDatePanelImpl datePanel;
	JDatePickerImpl datePicker;
	SqlDateModel model;
	
	private JButton btnAdd_Role;
	private JButton btnDelete_Role;
	private JButton btnReset_Role;
	private JButton btnNext_Role;
	
	private JButton btnAdd_Movie_Direction;
	private JButton btnDelete_Movie_Direction;
	private JButton btnReset_Movie_Direction;
	private JButton btnBack_Movie_Direction;
	
	private ActorListener1 actlistener1;
	private DirectorListener2 actlistener2;
	private MovieListener3 actlistener3;
	private RoleListener4 actlistener4;
	private MovieDirectionListerner5 actlistener5;
	Connection con=null;
	PreparedStatement pst=null;
	ResultSet rs= null;
	
	public static void main(String[] args) {
		try {
			ActorUI window = new ActorUI();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public ActorUI(){
		actDAL = new ActorDAL();
		dirDAL= new DirectorDAL();
		movDAL=new MovieDAL();
		rolDAL= new RoleDAL();
		movDirDAL= new MovieDirectionDAL();
		actlistener1 = new ActorListener1();
		actlistener2= new DirectorListener2();
		actlistener3= new MovieListener3();
		actlistener4= new RoleListener4();
		actlistener5= new MovieDirectionListerner5();
		initialize();
		
	 }
	
	
	private void initialize() {
		frame = new JFrame();
		
//		KRIJIMI I TABELAVE PER SECILIN PANEL
		
	    model1 = new DefaultTableModel();
	    table_actor = new JTable(model1);
	    model1.addColumn("act_id");
	    model1.addColumn("act_fname");
	    model1.addColumn("act_lname");
	    model1.addColumn("act_gender");
	    model1.addColumn("act_age");
	  
    	
    	try {
    		String query = ActorQueries.SELECT_ALL_ACTOR.getQuery();
    		ResultSet rs = getResultSet(query);
    		while (rs.next()) {
    			model1.addRow(new Object[]{rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)});
            }
    			   
    	}
    	 catch (SQLException e) {
    			JOptionPane.showMessageDialog(null, "An error occured due invalid/conflict in customer data inputted.", "",
    					JOptionPane.ERROR_MESSAGE);
    			e.printStackTrace();}
    	
	    JScrollPane pg = new JScrollPane(table_actor);
	    pg.setBounds(470, 38,500,255);
	   
	    model2 = new DefaultTableModel();
	    table_director = new JTable(model2);
	    model2.addColumn("dir_id");
	    model2.addColumn("dir_fname");
	    model2.addColumn("dir_lname");
	    model2.addColumn("dir_gender");
	    try {
    		String query = DirectorQueries.SELECT_ALL_DIRECTOR.getQuery();
    		ResultSet rs = getResultSet(query);
    		while (rs.next()) {
    			model2.addRow(new Object[]{rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4)});
            }
    			   
    	}
    	 catch (SQLException e) {
    			JOptionPane.showMessageDialog(null, "An error occured due invalid/conflict in customer data inputted.", "",
    					JOptionPane.ERROR_MESSAGE);
    			e.printStackTrace();}
    	
	    JScrollPane pg1= new JScrollPane(table_director);
	    pg1.setBounds(470, 38, 500, 260);
	    
	    model9 = new DefaultTableModel();
	    table_movie = new JTable(model9);
	    model9.addColumn("mov_id");
	    model9.addColumn("mov_title");
	    model9.addColumn("mov_lang");
	    model9.addColumn("mov_rel_country");
	    model9.addColumn("mov_date_rel");
	    model9.addColumn("mov_genre");
    	
    	try {
    		String query =MovieQueries.SELECT_ALL_MOVIES.getQuery();
    		ResultSet rs = getResultSet(query);
    		while (rs.next()) {
    			model9.addRow(new Object[]{rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6)});
            }
    			  
    	}
    	 catch (SQLException e) {
    			JOptionPane.showMessageDialog(null, "An error occured due invalid/conflict in customer data inputted.", "",
    					JOptionPane.ERROR_MESSAGE);
    			e.printStackTrace();}
    	
	    JScrollPane pg_movie= new JScrollPane(table_movie);
	    pg_movie.setBounds(440,50, 500,260);
	    
	    model3 = new DefaultTableModel();
	    table_actor_role = new JTable(model3);
	    model3.addColumn("act_id");
	    model3.addColumn("act_fname");
	    model3.addColumn("act_lname");
	    model3.addColumn("act_gender");
	    model3.addColumn("act_age");
    	
    	try {
    		String query = ActorQueries.SELECT_ALL_ACTOR.getQuery();
    		ResultSet rs = getResultSet(query);
    		while (rs.next()) {
    			model3.addRow(new Object[]{rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)});
            }
    			   
    	}
    	 catch (SQLException e) {
    			JOptionPane.showMessageDialog(null, "An error occured due invalid/conflict in customer data inputted.", "",
    					JOptionPane.ERROR_MESSAGE);
    			e.printStackTrace();}
    	
	    JScrollPane pg_role_actor = new JScrollPane(table_actor_role);
	    pg_role_actor.setBounds(475,8, 500, 150);
	   
	    model4 = new DefaultTableModel();
	    table_movie_role = new JTable(model4);
	    model4.addColumn("mov_id");
	    model4.addColumn("mov_title");
	    model4.addColumn("mov_lang");
	    model4.addColumn("mov_rel_country");
	    model4.addColumn("mov_date_rel");
	    model4.addColumn("mov_genre");
    	
    	try {
    		String query =MovieQueries.SELECT_ALL_MOVIES.getQuery();
    		ResultSet rs = getResultSet(query);
    		while (rs.next()) {
    			model4.addRow(new Object[]{rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6)});
            }
    			   
    	}
    	 catch (SQLException e) {
    			JOptionPane.showMessageDialog(null, "An error occured due invalid/conflict in customer data inputted.", "",
    					JOptionPane.ERROR_MESSAGE);
    			e.printStackTrace();}
    	
	    JScrollPane pg_movie_role = new JScrollPane(table_movie_role);
	    pg_movie_role.setBounds(475,180, 500,130);
	    
	     model_join= new DefaultTableModel();
	     table_join_movie_cast= new JTable(model_join);
	    model_join.addColumn("act_fname");
	    model_join.addColumn("act_lname");
	    model_join.addColumn("role");
	    model_join.addColumn("mov_title");
	
	    try {
			String query= RoleQueries.JOIN_ACTOR_CAST_MOVIE.getQuery();
    		ResultSet rs = getResultSet(query);
    		while(rs.next()) {
    			model_join.addRow(new Object[]{rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4)});

    		}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "An error occured due invalid/conflict in customer data inputted.", "",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	    JScrollPane pg_role = new JScrollPane(table_join_movie_cast);
	    pg_role.setBounds(475,320, 500,200);
	   
	   
	    model6 = new DefaultTableModel();
	    table_director_direction = new JTable(model6);
	    model6.addColumn("dir_id");
	    model6.addColumn("dir_fname");
	    model6.addColumn("dir_lname");
	    model6.addColumn("dir_gender");
	    try {
    		String query = DirectorQueries.SELECT_ALL_DIRECTOR.getQuery();
    		ResultSet rs = getResultSet(query);
    		while (rs.next()) {
    			model6.addRow(new Object[]{rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4)});
            }
    			   
    	}
    	 catch (SQLException e) {
    			JOptionPane.showMessageDialog(null, "An error occured due invalid/conflict in customer data inputted.", "",
    					JOptionPane.ERROR_MESSAGE);
    			e.printStackTrace();}
    	
	    JScrollPane pg_director= new JScrollPane(table_director_direction);
	    pg_director.setBounds(420,8, 500,160);
	    
	    model7= new DefaultTableModel();
	    table_movie_direction = new JTable(model7);
	    model7.addColumn("mov_id");
	    model7.addColumn("mov_title");
	    model7.addColumn("mov_lang");
	    model7.addColumn("mov_rel_country");
	    model7.addColumn("mov_date_rel");
	    model7.addColumn("mov_genre");
    	
    	try {
    		String query =MovieQueries.SELECT_ALL_MOVIES.getQuery();
    		ResultSet rs = getResultSet(query);
    		while (rs.next()) {
    			model7.addRow(new Object[]{rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});
            }
    			   
    	}
    	 catch (SQLException e) {
    			JOptionPane.showMessageDialog(null, "An error occured due invalid/conflict in customer data inputted.", "",
    					JOptionPane.ERROR_MESSAGE);
    			e.printStackTrace();}
    	
	    JScrollPane pg_movie_direction= new JScrollPane(table_movie_direction);
	    pg_movie_direction.setBounds(420,190, 500, 140);
	    
	    model_join_direction= new DefaultTableModel();
	    table_join_movie_direction = new JTable(model_join_direction);
	    model_join_direction.addColumn("dir_fname");
	    model_join_direction.addColumn("dir_lname");
	    model_join_direction.addColumn("mov_title");
	    model_join_direction.addColumn("film_genre");
              try {
				String query= MovieDirectionQueries.JOIN_MOVIE_DIRECTION.getQuery();
	    		ResultSet rs = getResultSet(query);
	    		while(rs.next()) {
	    			model_join_direction.addRow(new Object[] {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)});
	    		}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "An error occured due invalid/conflict in customer data inputted.", "",
    					JOptionPane.ERROR_MESSAGE);
    			e.printStackTrace();
			}
   	
	    JScrollPane pg_movieDirection = new JScrollPane(table_join_movie_direction);
	    pg_movieDirection.setBounds(420,350, 500, 180);
	    
	    
//		KRIJOJM PANEL KU DO NDODHEN BUTONI AKTOR ;MOVIE ;DIRECTOR ; SET ROLE ; SET MOVIE DIRECTION
		
		panel_all=new JPanel();	
		panel_all.setLayout(null);
		frame.add(panel_all);
		
		JLabel firstlabel= new JLabel("WELCOME TO MOVIE MANAGEMENT SYSTEM ");
		firstlabel.setFont(new Font("Engravers MT",Font.PLAIN,16));
	    firstlabel.setBounds(250,30,700, 35);
	     panel_all.add(firstlabel);
		
		btnActor=new JButton("ACTOR");
		btnActor.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnActor.setBounds(370,110,220,35);
		btnActor.setFocusable(false);
		btnActor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				frame.setContentPane(panel_actor);
				panel_actor.revalidate();
			}
		});
		
		
		panel_all.add(btnActor);
		
		btnDirector= new JButton("DIRECTOR");
		btnDirector.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDirector.setBounds(370,170,220,35);
		btnDirector.setFocusable(false);
		btnDirector.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				frame.setContentPane(panel_director);
				panel_director.revalidate();
			}
		});

		panel_all.add(btnDirector);

		
		btnMovie= new JButton("MOVIE");
		btnMovie.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnMovie.setBounds(370,230,	220, 35);
		btnMovie.setFocusable(false);
		btnMovie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				frame.setContentPane(panel_movie);
				panel_movie.revalidate();
			}
		});

		panel_all.add(btnMovie);

		btnRole= new JButton(" SET ROLE");
		btnRole.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRole.setBounds(370,290,220, 35);
		btnRole.setFocusable(false);
		btnRole.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
 				 frame.setContentPane(panel_role);
 				 panel_role.revalidate();
			}
			
		});
		panel_all.add(btnRole);

		
		btnMovie_Direction= new JButton(" SET MOVIE DIRECTION ");
		btnMovie_Direction.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnMovie_Direction.setFocusable(false);
		btnMovie_Direction.setBounds(370,350, 220, 35);
		btnMovie_Direction.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(panel_movie_direction);
				panel_movie_direction.revalidate();
			}
			
		});
		panel_all.add(btnMovie_Direction);
		


		
//		KRIJOJM PANELIN E AKTORIT
		
 
	    panel_actor= new JPanel();
		 panel_actor.setLayout(null);
		 panel_actor.add(pg);
		 frame.add(panel_actor);
		 
		 
		 JLabel actorlabel= new JLabel("ACTOR");
		 actorlabel.setFont(new Font("Engravers MT",Font.BOLD,16));
		 actorlabel.setBounds(15,0,100, 35);
		 panel_actor.add(actorlabel); 
		 
		 JLabel note= new JLabel("Please fill all the required fields.");
		 note.setFont(new Font("Lucida Bright",Font.PLAIN,13));
		 note.setForeground(Color.RED);
	     note.setBounds(141,190, 260, 22);
	     panel_actor.add(note);
	     
		JLabel lbActorName = new JLabel("Actor name:");
		lbActorName.setFont(new Font("Tahoma",Font.BOLD,13));
		lbActorName.setBounds(18, 42,96, 24);
		panel_actor.add(lbActorName);

		name_actorTF = new JTextField();
		name_actorTF.setBounds(141,42, 260, 28);
		name_actorTF.setColumns(10);
		panel_actor.add(name_actorTF);
		
		JLabel lbActorLastname = new JLabel("Actor Lastname:");
		lbActorLastname.setFont(new Font("Tahoma",Font.BOLD,13));
		lbActorLastname.setBounds(18,80, 120, 24);
		panel_actor.add(lbActorLastname);

		lastname_actorTF = new JTextField();
		lastname_actorTF.setBounds(141,80, 260, 28);
		lastname_actorTF.setColumns(10);
		panel_actor.add(lastname_actorTF);
		
		JLabel lbActorGender= new JLabel("Actor Gender :");
		lbActorGender.setFont(new Font("Tahoma",Font.BOLD,13));
		lbActorGender.setBounds(18,120, 96,24 );
		panel_actor.add(lbActorGender);
		
		String [] gender= {"Female","Male"};
		comboGender= new JComboBox(gender);
		comboGender.setBounds(141,120, 260, 28);
		comboGender.insertItemAt("--Select Gender--", 0);
		comboGender.setSelectedIndex(0);
		 
		panel_actor.add(comboGender);
		
		JLabel lbActorAge=new JLabel("Actor Age :");
		lbActorAge.setFont(new Font("Tahoma",Font.BOLD,13));
		lbActorAge.setBounds(18,160, 96,24);
		panel_actor.add(lbActorAge);
		
		age_actorTF=new JTextField();
		age_actorTF.setBounds(141,160, 260,28);
		panel_actor.add(age_actorTF);
		
		btnADD_Actor = new JButton("ADD");
		btnADD_Actor.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnADD_Actor.setBounds(100,250,140, 30);
		btnADD_Actor.addActionListener(actlistener1);
		panel_actor.add(btnADD_Actor);
		
		btnNextPage_Actor = new JButton("NEXT");
		btnNextPage_Actor.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNextPage_Actor.setBounds(340,300,140,30);
		btnNextPage_Actor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				frame.setContentPane(panel_director);
				panel_director.revalidate();
			}
		});
		panel_actor.add(btnNextPage_Actor);
		
		btnDELETE_Actor = new JButton("DELETE");
		btnDELETE_Actor.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDELETE_Actor.setFocusable(false);
		btnDELETE_Actor.setBounds(260,250, 140,  30);
		btnDELETE_Actor.addActionListener(actlistener1);
		panel_actor.add(btnDELETE_Actor);
		

		 JButton buttonBack_Actor = new JButton("GO BACK");
		 buttonBack_Actor.setFont(new Font("Tahoma", Font.BOLD, 13));
		 buttonBack_Actor.setBounds(26,300,140,30);
		 buttonBack_Actor.setFocusable(false);
		 buttonBack_Actor.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(panel_all);
				panel_all.revalidate();
			   }  
		  });
		 
	     panel_actor.add(buttonBack_Actor);
	     
		btnRESET_Actor=new JButton("RESET");
		btnRESET_Actor.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRESET_Actor.setFocusable(false);
		btnRESET_Actor.setBounds(180,300,140, 30);
		btnRESET_Actor.addActionListener(actlistener1);
		panel_actor.add(btnRESET_Actor);
		
		
//		KRIJOJME PANELIN E DIRECTOR
		
		 panel_director= new JPanel();
		 panel_director.setLayout(null);
		 panel_director.add(pg1);
		 frame.add(panel_director);
		 
		 JLabel directorlabel= new JLabel("DIRECTOR");
		 directorlabel.setFont(new Font("Engravers MT",Font.BOLD,16));
		 directorlabel.setBounds(15,0,400, 35);
		 panel_director.add(directorlabel);
		 
		 JLabel note1= new JLabel("Please fill all the required fields.");
		 note1.setFont(new Font("Lucida Bright",Font.PLAIN,13));
		 note1.setForeground(Color.RED);
	     note1.setBounds(141,170, 260, 22);
	     panel_director.add(note1);
	     
			JLabel lbDirectorName = new JLabel("Director name :");
			lbDirectorName.setFont(new Font("Tahoma", Font.BOLD, 13));
			lbDirectorName.setBounds(18,50, 310, 24);
			panel_director.add(lbDirectorName);

			name_directorTF = new JTextField();
			name_directorTF.setBounds(141,50, 260, 28);
			panel_director.add(name_directorTF);
			
			
			JLabel lbDirectorLastname = new JLabel("Director Lastname:");
			lbDirectorLastname.setFont(new Font("Tahoma", Font.BOLD, 13));
			lbDirectorLastname.setBounds(16,95, 130, 24);
			panel_director.add(lbDirectorLastname);

			lastname_directorTF = new JTextField();
			lastname_directorTF.setBounds(141,95, 260, 28);
			panel_director.add(lastname_directorTF);
			
			JLabel lbDirectorGender= new JLabel("Director Gender:");
			lbDirectorGender.setFont(new Font("Tahoma", Font.BOLD, 13));
			lbDirectorGender.setBounds(18,140 ,130, 24);
			panel_director.add(lbDirectorGender);
			
			String [] gender_director= {"Female","Male"};
			comboGender_Director= new JComboBox(gender_director);
			comboGender_Director.setBounds(141,140, 260, 28);
			comboGender_Director.insertItemAt("--Select Gender--", 0);
			comboGender_Director.setSelectedIndex(0);
			panel_director.add(comboGender_Director);
			
		  JButton button2 = new JButton("GO BACK");
		  button2.setFont(new Font("Tahoma", Font.BOLD, 13));
		  button2.setBounds(26,300,140,30);
		  button2.setFocusable(false);
		  button2.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(panel_all);
				panel_all.revalidate();
			   }  
		  });
		 
	     panel_director.add(button2);
	     
	     btnNextPage_Director = new JButton("NEXT");
	     btnNextPage_Director.setFont(new Font("Tahoma", Font.BOLD, 13));
	     btnNextPage_Director.setBounds(340,300,140,30);
	     btnNextPage_Director.setFocusable(false);

	     btnNextPage_Director.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					frame.setContentPane(panel_movie);
					panel_movie.revalidate();
				}
			});
			panel_director.add(btnNextPage_Director);
	     
			btnAdd_Director=new JButton("ADD");
			btnAdd_Director.setFocusable(false);
			btnAdd_Director.setFont(new Font("Tahoma",Font.BOLD,13));
			btnAdd_Director.setBounds(100,250,140, 30);
			panel_director.add(btnAdd_Director);
			btnAdd_Director.addActionListener(actlistener2);
			
			btnDelete_Director=new JButton("DELETE");
			btnDelete_Director.setFocusable(false);
			btnDelete_Director.setFont(new Font("Tahoma",Font.BOLD,13));
			btnDelete_Director.setBounds(260,250,140,30);
			btnDelete_Director.addActionListener(actlistener2);
			panel_director.add(btnDelete_Director);
			
			btnReset_Director=new JButton("RESET");
			btnReset_Director.setFocusable(false);
			btnReset_Director.setFont(new Font("Tahoma",Font.BOLD,13));
			btnReset_Director.setBounds(180,300,140,30);
			btnReset_Director.addActionListener(actlistener2);
			panel_director.add(btnReset_Director);

	     
//	     KRIJOJM PANELIN E MOVIES
			
			
	      panel_movie= new JPanel();
	      panel_movie.setLayout(null);
	      panel_movie.add(pg_movie);
	      frame.add(panel_movie);
	      
	      JLabel movielabel= new JLabel("MOVIE");
	      movielabel.setFont(new Font("Engravers MT",Font.BOLD,16));
	      movielabel.setBounds(15,0,400, 35);
		  panel_movie.add(movielabel);
	      
	         JLabel note2= new JLabel("Please fill all the required fields.");
	         note2.setFont(new Font("Lucida Bright",Font.PLAIN,13));
	         note2.setForeground(Color.RED);
	         note2.setBounds(141,240, 260, 22);
		     panel_movie.add(note2);
          
           JLabel lbTitleMovie = new JLabel("Movie Title :");
           lbTitleMovie.setFont(new Font("Tahoma", Font.BOLD, 13));
           lbTitleMovie.setBounds(18,50, 96, 24);
		   panel_movie.add(lbTitleMovie);

		   title_movieTF = new JTextField();
		   title_movieTF.setBounds(141,50, 260, 28);
		   panel_movie.add(title_movieTF);
		   
		   
			JLabel lbMovieLang = new JLabel("Movie Duration :");
			lbMovieLang.setFont(new Font("Tahoma", Font.BOLD, 13));
			lbMovieLang.setBounds(18,90,130, 24);
			panel_movie.add(lbMovieLang);
			
			lang_movieTF=new JTextField();
			lang_movieTF.setBounds(141,90, 260, 28);
			panel_movie.add(lang_movieTF);
			
			JLabel lbReleaseCountry= new JLabel("Country Released:");
			lbReleaseCountry.setFont(new Font("Tahoma", Font.BOLD, 13));
			lbReleaseCountry.setBounds(18,130, 130, 24);
			panel_movie.add(lbReleaseCountry);
			
			String [] country= {"Albania","Californi","Canada","China","England","France","Japan","New York","Norway","Spain","Switzerland"};
			comboCountry= new JComboBox(country);
			comboCountry.setBounds(141,130, 260, 28);
			comboCountry.insertItemAt("--Select Country--", 0);
			comboCountry.setSelectedIndex(0);
			panel_movie.add(comboCountry);
		
	       
			JLabel lbMovieReleaseDate = new JLabel("Release date:");
			lbMovieReleaseDate.setFont(new Font("Tahoma", Font.BOLD, 13));
			lbMovieReleaseDate.setBounds(18, 170, 129, 24);
			panel_movie.add(lbMovieReleaseDate);
		
			JLabel lbMovieGenre= new JLabel("Movie Genre:");
			lbMovieGenre.setFont(new Font("Tahoma", Font.BOLD, 13));
			lbMovieGenre.setBounds(18,210, 129, 24);
			panel_movie.add(lbMovieGenre);

			String [] movie_genre= {"Action","Animation","Comedy","Drama","Horror","Romance","Sience Fiction","Thriller"};
			comboMovieGenre= new JComboBox(movie_genre);
			comboMovieGenre.setBounds(141,210, 260, 28);
			comboMovieGenre.insertItemAt("--Select Genre--", 0);
			comboMovieGenre.setSelectedIndex(0);
			panel_movie.add(comboMovieGenre);
			
			model = new SqlDateModel();
			Properties p= new Properties();
			p.put("text.day", "Day");
			p.put("text.month", "Month");
			p.put("text.year", "Year");
			
		    datePanel = new JDatePanelImpl(model,p);
		    datePicker = new JDatePickerImpl(datePanel,new AbstractFormatter() {
				
				@Override
				public String valueToString(Object value) throws ParseException {
					// TODO Auto-generated method stub
					if(value != null) {
					Calendar cal=(Calendar) value;
					SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
					String strDate=format.format(cal.getTime());
					return strDate;
					}
					return " ";
				}
				
				@Override
				public Object stringToValue(String text) throws ParseException {
					// TODO Auto-generated method stub
					return "";
				}
			});
		    datePicker.setBounds(141,170, 260, 28);
			panel_movie.add(datePicker);
		
	      buttonBack_movie = new JButton("GO BACK");
		  buttonBack_movie.setFont(new Font("Tahoma", Font.BOLD, 13));
		  buttonBack_movie.setBounds(26,350,140,30);
		  buttonBack_movie.setFocusable(false);
		  buttonBack_movie.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(panel_all);
				panel_all.revalidate();
			   }  
		  });
		 
	     panel_movie.add(buttonBack_movie);
	     
	      btnAdd_Movie=new JButton("ADD");
		  btnAdd_Movie.setFont(new Font("Tahoma", Font.BOLD, 13));
		  btnAdd_Movie.setFocusable(false);
	      btnAdd_Movie.setBounds(100,300,140, 30);
	      btnAdd_Movie.addActionListener(actlistener3);
	      panel_movie.add(btnAdd_Movie);
	     
	      btnDelete_Movie=new JButton("DELETE");
	      btnDelete_Movie.setFont(new Font("Tahoma", Font.BOLD, 13));
	      btnDelete_Movie.setFocusable(false);
	      btnDelete_Movie.setBounds(260,300,140,30);
	      btnDelete_Movie.addActionListener(actlistener3);
	      panel_movie.add(btnDelete_Movie);
	      
	      
	      btnReset_Movie=new JButton("RESET");
	      btnReset_Movie.setFont(new Font("Tahoma", Font.BOLD, 13));
	      btnReset_Movie.setFocusable(false);
	      btnReset_Movie.setBounds(180,350,140,30);
	      btnReset_Movie.addActionListener(actlistener3);
	      panel_movie.add(btnReset_Movie);
	      
	      btnNext_Movie= new JButton("NEXT");
		  btnNext_Movie.setFont(new Font("Tahoma", Font.BOLD, 13));
	      btnNext_Movie.setBounds(340,350,140,30);
		  btnNext_Movie.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					frame.setContentPane(panel_role);
					panel_role.revalidate();
				}
			});
			panel_movie.add(btnNext_Movie);
	  

	      
//	      KRIJOJM PANELIN KU VENDOSIM ROLET
	      
	      panel_role= new JPanel();
	      panel_role.setLayout(null);
	      panel_role.add(pg_role_actor);
	      panel_role.add(pg_movie_role);
	      panel_role.add(pg_role);
	      frame.add(panel_role);
	      
	      JLabel rolelabel= new JLabel("SET THE ROLE TO THE ACTORS");
	      rolelabel.setFont(new Font("Engravers MT",Font.PLAIN,16));
	      rolelabel.setBounds(15,0,700, 35);
		  panel_role.add(rolelabel);
	      

	         JLabel note3= new JLabel("Please fill all the required fields.");
	         note3.setFont(new Font("Lucida Bright",Font.PLAIN,13));
	         note3.setForeground(Color.RED);
	         note3.setBounds(121,190, 260, 22);
		     panel_role.add(note3);
	      
	      JLabel lbActor_ID = new JLabel("Actor ID:");
	      lbActor_ID.setFont(new Font("Tahoma",Font.BOLD,13));
	      lbActor_ID.setBounds(18,60, 76, 24);
		  panel_role.add(lbActor_ID);
		  
		  act_id_roleTF= new JTextField();
		  act_id_roleTF.setBounds(121,60,260, 28);
		  act_id_roleTF.setFont(new Font("Tahoma",Font.BOLD,13));
		  panel_role.add(act_id_roleTF);
		  
		   JLabel lbMovie_ID = new JLabel("Movie ID:");
		   lbMovie_ID.setFont(new Font("Tahoma",Font.BOLD,13));
		   lbMovie_ID.setBounds( 18,110, 76, 24);
		   panel_role.add(lbMovie_ID);
		   
		    mov_id_roleTF= new JTextField();
			mov_id_roleTF.setBounds(121,110,260, 28);
		    mov_id_roleTF.setFont(new Font("Tahoma",Font.BOLD,13));
			panel_role.add(mov_id_roleTF);
	
		   
		   JLabel lbRole = new JLabel("Role:");
		   lbRole.setFont(new Font("Tahoma",Font.BOLD,13));
		   lbRole.setBounds(18,160,76,24 );
		   panel_role.add(lbRole);
		   
		    String [] role= {"Main Role","Secondary Role","Small Role","Background Role"};
			comboRole= new JComboBox(role);
			comboRole.setBounds(121,160, 260, 28);
			comboRole.insertItemAt("--Select Role--", 0);
			comboRole.setSelectedIndex(0);
			panel_role.add(comboRole);
			
			
			
			 JButton button_role = new JButton("GO BACK");
			 button_role.setFont(new Font("Tahoma", Font.BOLD, 13));
			 button_role.setBounds(4,300,140,30);
			 button_role.setFocusable(false);
			 button_role.addActionListener(new ActionListener () {

				@Override
				public void actionPerformed(ActionEvent e) {
					frame.setContentPane(panel_all);
					panel_all.revalidate();
				   }  
			  });
			 
		     panel_role.add(button_role);
		     
		     btnAdd_Role=new JButton("ADD");
		     btnAdd_Role.setFont(new Font("Tahoma",Font.BOLD,13));
		     btnAdd_Role.setBounds(80,250,140, 30);
		     btnAdd_Role.setFocusable(false);
		     btnAdd_Role.addActionListener(actlistener4);
		     panel_role.add(btnAdd_Role);
		     
		     
		     btnDelete_Role= new JButton("DELETE");
		     btnDelete_Role.setFont(new Font("Tahoma",Font.BOLD,13));
		     btnDelete_Role.setFocusable(false);
		     btnDelete_Role.setBounds(240,250,140,30);
		     btnDelete_Role.addActionListener(actlistener4);
		     panel_role.add(btnDelete_Role);
		     
             btnReset_Role=new JButton("RESET");
             btnReset_Role.setFont(new Font("Tahoma",Font.BOLD,13));
		     btnReset_Role.setFocusable(false);
		     btnReset_Role.setBounds(160,300,140,30);
		     btnReset_Role.addActionListener(actlistener4);
		     panel_role.add(btnReset_Role);
		     
		     btnNext_Role= new JButton("NEXT");
		     btnNext_Role.setFont(new Font("Tahoma", Font.BOLD, 13));
		     btnNext_Role.setBounds(320,300,140,30);
		     btnNext_Role.setFocusable(false);
		     btnNext_Role.addActionListener(new ActionListener () {

				@Override
				public void actionPerformed(ActionEvent e) {
					frame.setContentPane(panel_movie_direction);
					panel_movie_direction.revalidate();
				   }  
			  });
		     panel_role.add(btnNext_Role);
		  
		     
//		     KRIJOME PANELIN MOVIE DIRECTION
		     
		     panel_movie_direction= new JPanel();
		     panel_movie_direction.setLayout(null);
		     panel_movie_direction.add(pg_director);
		     panel_movie_direction.add(pg_movie_direction);
		     panel_movie_direction.add(pg_movieDirection);
		     frame.add(panel_movie_direction);
		     
		     JLabel directionlabel= new JLabel("SET THE DIRECTOR TO THE MOVIE");
		     directionlabel.setFont(new Font("Engravers MT",Font.PLAIN,16));
		     directionlabel.setBounds(15,0,700, 35);
			  panel_movie_direction.add(directionlabel);
			  
		     JLabel note4= new JLabel("Please fill all the required fields.");
		     note4.setFont(new Font("Lucida Bright",Font.PLAIN,13));
		     note4.setForeground(Color.RED);
		     note4.setBounds(121,170, 260, 22);
		     panel_movie_direction.add(note4);
		     
		     JLabel lbDirector_ID = new JLabel("Director ID:");
		     lbDirector_ID.setFont(new Font("Tahoma",Font.BOLD,13));
		     lbDirector_ID.setBounds(18,90,90, 24);
			  panel_movie_direction.add(lbDirector_ID);
			  
			  dir_id_directionTF= new JTextField();
			  dir_id_directionTF.setBounds(121,90,260, 28);
			  dir_id_directionTF.setFont(new Font("Tahoma",Font.BOLD,13));
			  panel_movie_direction.add(dir_id_directionTF);
			  
			   JLabel lbMovie_Direction_ID = new JLabel("Movie ID:");
			   lbMovie_Direction_ID.setFont(new Font("Tahoma",Font.BOLD,13));
			   lbMovie_Direction_ID.setBounds( 18,140,90, 24);
			   panel_movie_direction.add(lbMovie_Direction_ID);
			   
			    mov_id_directionTF= new JTextField();
			    mov_id_directionTF.setBounds(121,140,260, 28);
			    mov_id_directionTF.setFont(new Font("Tahoma",Font.BOLD,13));
				panel_movie_direction.add(mov_id_directionTF);
				
				 btnBack_Movie_Direction = new JButton("GO BACK");
				 btnBack_Movie_Direction.setFont(new Font("Tahoma", Font.BOLD, 13));
				 btnBack_Movie_Direction.setBounds(32,300,140,30);
				 btnBack_Movie_Direction.setFocusable(false);
				 btnBack_Movie_Direction.addActionListener(new ActionListener () {

					@Override
					public void actionPerformed(ActionEvent e) {
						frame.setContentPane(panel_all);
						panel_all.revalidate();
					   }  
				  });
				 
			     panel_movie_direction.add(btnBack_Movie_Direction);
			     
			     btnAdd_Movie_Direction= new JButton("ADD");
				 btnAdd_Movie_Direction.setFont(new Font("Tahoma", Font.BOLD, 13));
				 btnAdd_Movie_Direction.setFocusable(false);
				 btnAdd_Movie_Direction.setBounds(32,250,140, 30);
				 btnAdd_Movie_Direction.addActionListener(actlistener5);
				 panel_movie_direction.add(btnAdd_Movie_Direction);
				 
				 btnDelete_Movie_Direction= new JButton("DELETE");
				 btnDelete_Movie_Direction.setFont(new Font("Tahoma",Font.BOLD ,13));
				 btnDelete_Movie_Direction.setFocusable(false);
				 btnDelete_Movie_Direction.setBounds(195,250, 140,30);
				 btnDelete_Movie_Direction.addActionListener(actlistener5);
				 panel_movie_direction.add(btnDelete_Movie_Direction);
				 
				 btnReset_Movie_Direction=new JButton("RESET");
				 btnReset_Movie_Direction.setFont(new Font("Tahoma",Font.BOLD,13));
				 btnReset_Movie_Direction.setFocusable(false);
				 btnReset_Movie_Direction.setBounds(195,300, 140, 30);
				 btnReset_Movie_Direction.addActionListener(actlistener5);
				 panel_movie_direction.add(btnReset_Movie_Direction);


			     
		   
	     frame.setContentPane(panel_all);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.pack();
	    frame.setBounds(150,60, 1000,600);
//		frame.setResizable(false);
		frame.setTitle("Movie Management System");	
		
		
	}
	
	public void refreshTable_Actor() {
			String query = ActorQueries.SELECT_ALL_ACTOR.getQuery();
			ResultSet rs = getResultSet(query);
			
		   table_actor.setModel(DbUtils.resultSetToTableModel(rs));
	
	}
	
	public void refreshTable_Director() {
		
			String query = DirectorQueries.SELECT_ALL_DIRECTOR.getQuery();
			ResultSet rs = getResultSet(query);
			
				table_director.setModel(DbUtils.resultSetToTableModel(rs));
	}
	
	public void refreshTable_Movie() {
		String query= MovieQueries.SELECT_ALL_MOVIES.getQuery();
		ResultSet rs = getResultSet(query);
		table_movie.setModel(DbUtils.resultSetToTableModel(rs));
	}
	public void refreshTable_JoinMovie_Actor() {
			String query = RoleQueries.JOIN_ACTOR_CAST_MOVIE.getQuery();
			ResultSet rs = getResultSet(query);
		  table_join_movie_cast.setModel(DbUtils.resultSetToTableModel(rs));
	}

	
	public void refreshTable_JoinMovie_Direction() {
		String query = MovieDirectionQueries.JOIN_MOVIE_DIRECTION.getQuery();
		ResultSet rs= getResultSet(query);
        table_join_movie_direction.setModel(DbUtils.resultSetToTableModel(rs));
	}
	class ActorListener1 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ev) {
			String ac = ev.getActionCommand();
			switch(ac) {
			case "ADD":
				addActor();
				refreshTable_Actor();
				break;
			case "DELETE":
				deleteActor();
				refreshTable_Actor() ;
				break;
			case "RESET":
				resetActor();
				break;
			case "UPDATE":
				
				
			}
			
	    }
	
	}
	class DirectorListener2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ev) {
			String ac = ev.getActionCommand();
			switch(ac) {
			case "ADD":
                addDirector();
                refreshTable_Director();
                break;
			case "DELETE":
				deleteDirector();
				refreshTable_Director();
				break;
			case "RESET":
				resetDirector();
				break;
			case "UPDATE":
				
				
			}
			
	    }
	
	}
	class MovieListener3 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ev) {
			String ac = ev.getActionCommand();
			switch(ac) {
			case "ADD":
				addMovie() ;
				refreshTable_Movie();
				break;
			case "DELETE":
				deleteMovie();
				refreshTable_Movie();
				break;
			case "RESET":
				resetMovie();
				break;
				
				
			}
			
	    }
	
	}
	
	
	class RoleListener4 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ev) {
			String ac = ev.getActionCommand();
			switch(ac) {
			case "ADD":
                 addRole();
                 refreshTable_JoinMovie_Actor();
                break;
			case "DELETE":
				deleteRole();
				refreshTable_JoinMovie_Actor();
				break;
			case "RESET":
				resetRole();
				break;
			}
			
		}
		
	}
	class MovieDirectionListerner5 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ev) {
			String ac = ev.getActionCommand();
			switch(ac) {
			case "ADD":
				addMovieDirection();
				refreshTable_JoinMovie_Direction();
                break;
			case "DELETE":
				deleteMovieDirection();
				refreshTable_JoinMovie_Direction();
				break;
			case "RESET":
				resetJoinMovieDirection();
				break;
				
				
			}
			
	    }
	
	}
	
	private void addActor() {
		
		try {
			actDAL.addActor(new Actor(actDAL.getActorNextId(),name_actorTF.getText(), lastname_actorTF.getText(),comboGender.getSelectedItem().toString(),age_actorTF.getText()));
			JOptionPane.showMessageDialog(null, " Actor was inserted sucessfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	private void deleteActor() {
		int res = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete  this actor?", "",
				JOptionPane.YES_NO_OPTION);

		if (res == 0) {
			actDAL.deleteActor(name_actorTF.getText());
		}
	}
	
public void resetActor() {
	name_actorTF.setText(null);
	lastname_actorTF.setText(null);
	comboGender.setSelectedIndex(0);
	age_actorTF.setText(null);
}


private void addDirector() {
	
	try {
		
		dirDAL.addDirector(new Director(dirDAL.getDirectorNextId(),name_directorTF.getText(),lastname_directorTF.getText(),comboGender_Director.getSelectedItem().toString()));
		JOptionPane.showMessageDialog(null, " Director was inserted sucessfully");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

private void deleteDirector() {
	int res = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete  this director?", "",
			JOptionPane.YES_NO_OPTION);

	if (res == 0) {
		dirDAL.deleteDirector(name_directorTF.getText());
	}
}

public void resetDirector() {
	name_directorTF.setText(null);
	lastname_directorTF.setText(null);
	comboGender_Director.setSelectedIndex(0);
}

public void addMovie() {
	
try {
	movDAL.addMovie(new Movie(movDAL.getMovieNextId(),title_movieTF.getText(),lang_movieTF.getText(),comboCountry.getSelectedItem().toString(),(java.sql.Date)datePicker.getModel().getValue(),comboMovieGenre.getSelectedItem().toString()));
	JOptionPane.showMessageDialog(null, "Movie successfully added");

	} catch (SQLException e) {
		// TODO Auto-generated catch bloc
		e.printStackTrace();
	}
	
}
public void deleteMovie() {
	int res = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this movie?", "",
			JOptionPane.YES_NO_OPTION);

	if (res == 0) {
		movDAL.deleteMovie(title_movieTF.getText());
		
	}
}
public void resetMovie() {
	title_movieTF.setText(null);
	lang_movieTF.setText(null);
	comboGender.setSelectedIndex(0);			
	model.setSelected(false);
	comboCountry.setSelectedIndex(0);
	comboMovieGenre.setSelectedIndex(0);
}
public void deleteRole() {
	int res = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this role?", "",
			JOptionPane.YES_NO_OPTION);

	if (res == 0) {
		int actor_id=Integer.parseInt(act_id_roleTF.getText());
		rolDAL.deleteRole(actor_id);

	}
}
public void addRole() {
	try {
		
		int actor_id = Integer.parseInt(act_id_roleTF.getText());
		int movie_id = Integer.parseInt(mov_id_roleTF.getText());
		rolDAL.addRole(new Role(actor_id,movie_id,comboRole.getSelectedItem().toString()));
		JOptionPane.showMessageDialog(null,"Role successfully added");
		
	} catch (Exception e2) {
	 JOptionPane.showMessageDialog(null, "Something went wrong");
	}

}

public void resetRole() {
	act_id_roleTF.setText(null);
	mov_id_roleTF.setText(null);
	comboRole.setSelectedIndex(0);
}
public void addMovieDirection() {
	try {
		int director_id=Integer.parseInt(dir_id_directionTF.getText());
		int movie_id=Integer.parseInt(mov_id_directionTF.getText());
		movDirDAL.addMovieDirection(new MovieDirection(director_id,movie_id));
		JOptionPane.showMessageDialog(null, "Movie Direction successfully added");
	} catch (Exception e) {
		JOptionPane.showMessageDialog(null, "Something went wrong");
	}
	
}
public void deleteMovieDirection() {
	int res = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this movie direction ?", "",
			JOptionPane.YES_NO_OPTION);

	if (res == 0) {
	  int director_id= Integer.parseInt(dir_id_directionTF.getText());
		movDirDAL.deleteMovieDirection(director_id);
		
	}
}

public void resetJoinMovieDirection() {
	dir_id_directionTF.setText(null);
	mov_id_directionTF.setText(null);

}
		
}
