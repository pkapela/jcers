package jcers.mvc.model;

/**
 * File:		DBase.java
 * Created:		Dec/26/2015
 * Author:		Piotr Kapela https://github.com/pkapela
 * Description:		The class acts like an abstraction to all database operations. 
 * 			It uses PersonModel class as a main data carrier between 
 * 			external classes. DBase doesn't do any data validation; it has 
 * 			to be done by external classes.
 */				

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import com.mysql.jdbc.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class DBase 
{
	private Connection connection;

	// Constant(s) [Private Access]
	private final String DBASE_LOC = "jdbc:mysql://localhost/jCERS_B";
	private final String USR_NAME = "root";
	private final String PSWD = "Orw@884red";

	// Constructor(s)
	public DBase()
	{

	}

	// Method(s) [Public Access]
	public void addRecord(PersonModel person)
	{
		final String ADD_QUERY = "INSERT INTO student (student_id, fname, lname, telephone, email, addr, city, state, zip, dob, sex, cre_date)"
				+ "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try
		{
			initializeConnection();
			PreparedStatement addStatement = connection.prepareStatement(ADD_QUERY);
			Date nowDate = new Date();
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");

			addStatement.setString(1, null);
			addStatement.setString(2, person.getFirstName());
			addStatement.setString(3, person.getLastName());
			addStatement.setString(4, person.getTelephone());
			addStatement.setString(5, person.getEmail());
			addStatement.setString(6, person.getAddress());
			addStatement.setString(7, person.getCity());
			addStatement.setString(8, person.getState());
			addStatement.setString(9, person.getZip());
			addStatement.setString(10, person.getDob().toString());
			addStatement.setString(11, person.getSex().toString().substring(0, 1));
			addStatement.setString(12, ft.format(nowDate));

			addStatement.executeUpdate();
			connection.close();	 
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}

		defaultPassword(searchRecord(person).get(0));

		return;
	}

	public ArrayList<PersonModel> searchRecord(PersonModel person)
	{
		ArrayList<PersonModel> searchResults = new ArrayList<PersonModel>();
		final String SEARCH_QUERY = searchQueryConstructor(person);

		try
		{
			initializeConnection();
			Statement searchStatement = (Statement) connection.createStatement();
			ResultSet rset = searchStatement.executeQuery(SEARCH_QUERY);

			while(rset.next())
			{
				PersonModel np = new PersonModel();
				np.setId(rset.getString(1));
				np.setFirstName(rset.getString(2));
				np.setLastName(rset.getString(3));
				np.setTelephone(rset.getString(4));
				np.setEmail(rset.getString(5));
				np.setAddress(rset.getString(6));
				np.setCity(rset.getString(7));
				np.setState(rset.getString(8));
				np.setZip(rset.getString(9));
				np.setDob(dateTranslate(rset.getString(10)));
				np.setSex(sexTranslate(rset.getString(11)));
				np.setCDate(dateTimeTranslate(rset.getString(12)));
				np.setMDate(dateTimeTranslate(rset.getString(13)));

				searchResults.add(np);
			}

			connection.close();
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}

		return searchResults;
	}

	public boolean removeRecord(PersonModel person)
	{
		final String REMOVE_QUERY = "DELETE FROM student WHERE student_id = ? AND fname = ? AND lname = ?";

		try
		{
			initializeConnection();
			PreparedStatement removeStatement = connection.prepareStatement(REMOVE_QUERY);

			removeStatement.setInt(1, Integer.parseInt(person.getId()));
			removeStatement.setString(2, person.getFirstName());
			removeStatement.setString(3, person.getLastName());

			removeStatement.executeUpdate();
			connection.close();	 
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean updateRecord(PersonModel person)
	{
		final String UPDATE_QUERY = "UPDATE student SET fname = ?, lname = ?, telephone = ?, email = ?, addr = ?, city = ?, state = ?, zip = ?, dob = ?,"
				+ " mod_date = ? WHERE student_id = ?";
		try
		{
			initializeConnection();
			PreparedStatement updateStatement = connection.prepareStatement(UPDATE_QUERY);
			Date nowDate = new Date();
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");

			updateStatement.setString(1, person.getFirstName());
			updateStatement.setString(2, person.getLastName());
			updateStatement.setString(3, person.getTelephone());
			updateStatement.setString(4, person.getEmail());
			updateStatement.setString(5, person.getAddress());
			updateStatement.setString(6, person.getCity());
			updateStatement.setString(7, person.getState());
			updateStatement.setString(8, person.getZip());
			updateStatement.setString(9, person.getDob().toString());
			updateStatement.setString(10, ft.format(nowDate));
			updateStatement.setString(11, person.getId());

			updateStatement.executeUpdate();
			connection.close();
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean defaultPassword(PersonModel person)
	{
		final String ADD_QUERY = "INSERT INTO stu_credentials(student_id, passw, email)"
				+ " VALUES (?, SHA(?), ?)";
		try
		{
			initializeConnection();
			PreparedStatement addStatement = connection.prepareStatement(ADD_QUERY);

			addStatement.setString(1, person.getId());
			addStatement.setString(2, person.getId());
			addStatement.setString(3, person.getEmail());

			addStatement.executeUpdate();
			connection.close();
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
			return false;
		}

		return true;
	}

	public DatabaseMetaData getMetaData()
	{
		initializeConnection();
		try 
		{
			return connection.getMetaData();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return null;
	}

	// For debug purpose
	public static ObservableList<CourseModel> loadRegData()
	{
		ObservableList<CourseModel> data = FXCollections.observableArrayList();

		data.add(new CourseModel("BSSINT", "Intro to Business", 600, new Date()));
		data.add(new CourseModel("HHTML", "HTML", 800, new Date()));
		data.add(new CourseModel("DIGG1", "Dignity Act", 125, new Date()));
		data.add(new CourseModel("ICD1", "ICD-10", 900, new Date()));
		data.add(new CourseModel("CCGE", "Marketing 101", 250, new Date()));

		return data;
	}
	
	public static ObservableList<PaymentModel> loadPayData()
	{
		ObservableList<PaymentModel> data = FXCollections.observableArrayList();
		
		data.add(new PaymentModel("1200", "MasterCard", "John Doe", "X4435"));
		data.add(new PaymentModel("750", "Visa", "Marry Jane", "X2983"));
		
		return data;
	}

	// Method(s) [Private Access]
	private void initializeConnection()
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DBASE_LOC, USR_NAME, PSWD);
		} 
		catch(ClassNotFoundException ex) 
		{
			ex.printStackTrace();
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}

	private String searchQueryConstructor(PersonModel person)
	{
		StringBuilder searchQuery = new StringBuilder("SELECT * FROM student WHERE");
		boolean first = true;

		if(person.getId() != null)
		{
			searchQuery.append(" student_id = '" + person.getId() + "'");
			first = false;
		}
		if(person.getFirstName() != null)
		{
			if(first == true)
			{
				searchQuery.append(" fname = '" + person.getFirstName() + "'");
				first = false;
			}
			else
			{
				searchQuery.append(" AND fname = '" + person.getFirstName() + "'");
			}
		}
		if(person.getLastName() != null)
		{
			if(first == true)
			{
				searchQuery.append(" lname = '" + person.getLastName() + "'");
				first = false;
			}
			else
			{
				searchQuery.append(" AND lname = '" + person.getLastName() + "'");
			}
		}
		if(person.getDob() != null)
		{
			if(first == true)
			{
				searchQuery.append(" dob = '" + person.getDob().toString() + "'");
				first = false;
			}
			else
			{
				searchQuery.append(" AND dob = '" + person.getDob().toString() + "'");
			}
		}
		if(person.getTelephone() != null)
		{
			if(first == true)
			{
				searchQuery.append(" telephone = '" + person.getTelephone() + "'");
				first = false;
			}
			else
			{
				searchQuery.append(" AND telephone = '" + person.getTelephone() + "'");
			}
		}
		if(person.getEmail() != null)
		{
			if(first == true)
			{
				searchQuery.append(" email = '" + person.getEmail() + "'");
				first = false;
			}
			else
			{
				searchQuery.append(" AND email = '" + person.getEmail() + "'");
			}
		}

		return searchQuery.toString();
	}

	private LocalDate dateTranslate(String dateStr)
	{
		LocalDate date;
		if(dateStr != null)
		{
			try
			{
				date = LocalDate.parse(dateStr);
			}
			catch(DateTimeParseException ex)
			{
				date = null;
			}
		}
		else
		{
			date = null;
		}

		return date;
	}

	private LocalDateTime dateTimeTranslate(String dateTimeStr)
	{
		LocalDateTime dateTime;
		if(dateTimeStr != null)
		{
			try
			{
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
				dateTime = LocalDateTime.parse(dateTimeStr, formatter);
			}
			catch(DateTimeParseException ex)
			{
				dateTime = null;
			}
		}
		else
		{
			dateTime = null;
		}

		return dateTime;
	}

	private PersonModel.Sex sexTranslate(String sexStr)
	{
		if(sexStr == null)
		{
			return PersonModel.Sex.OTHER;
		}
		else if(sexStr.equals("O"))
		{
			return PersonModel.Sex.OTHER;
		}
		else if(sexStr.equals("F"))
		{
			return PersonModel.Sex.FEMALE;
		}
		else
		{
			return PersonModel.Sex.MALE;
		}
	}

} // End of DBase Class