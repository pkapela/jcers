package jcers.mvc.model;

/**
 * File:		ValidationModule.java
 * Created:		Dec/21/2015
 * Author:		Piotr Kapela https://github.com/pkapela
 * Description:		ValidationModule checks correctness of the PersonView 
 * 			class input fields. The class is operated by the PersonPresenter 
 * 			during record addition into the database. It provides an error 
 * 			aggregator which can be displayed or cleared manually.    
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ValidationModule 
{
	private List<String> errorList;
	final private String DATE_FORMAT = "yyyy-MM-dd";

	// Constructor(s)
	public ValidationModule()
	{
		super();
		errorList = new ArrayList<String>();
	}

	// Method(s) [Public Access]
	public boolean isValidFirstName(String fname)
	{
		String expression = "[a-zA-Z]+";
		String errorMessage = "First Name must consist of at least one character; only letters are allowed for a valid name.";

		return this.mainValidator(fname, expression, errorMessage);
	}

	public boolean isValidLastName(String lname)
	{
		String expression = "[a-zA-Z]+";
		String errorMessage = "Last Name must consist of at least one character; only letters are allowed for a valid name.";

		return this.mainValidator(lname, expression, errorMessage);
		
	}

	public boolean isValidTelephone(String telephone)
	{
		String expression = "[0-9]{3}-[0-9]{3}-[0-9]{4}";
		String errorMessage = "Telephone must match the following pattern XXX-XXX-XXXX.";

		return this.mainValidator(telephone, expression, errorMessage);
	}

	public boolean isValidBirthDate(String bdateStr)
	{
		if(bdateStr.trim().isEmpty())
		{
			this.addErrorMessage("Birth Date must match the following pattern YYYY-MM-DD.");
			return false;
		}
		try
		{
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
			LocalDate bdate = LocalDate.parse(bdateStr, formatter);

			if(bdate == null)
			{
				this.addErrorMessage("Birth Date must match the following pattern YYYY-MM-DD.");
				return false;
			}

			if(bdate.isAfter(LocalDate.now()))
			{
				this.addErrorMessage("Check the Birth Date.");
				return false;
			}
		}
		catch(DateTimeParseException e)
		{
			this.addErrorMessage("Birth Date must match the following pattern YYYY-MM-DD.");
			return false;
		}

		return true;
	}

	public boolean isValidEmail(String email)
	{
		String expression = "^[a-zA-Z0-9\\._]+@[a-zA-Z0-9\\._]+$";
		String errorMessage = "Invalid email format.";

		return this.mainValidator(email, expression, errorMessage);
	}

	public boolean isValidAddress(String addr)
	{
		String expression = "^[a-zA-Z0-9#\\.]{1}[\\sa-zA-Z0-9#\\.]*$";
		String errorMessage = "Address must consist of at least one character.";

		return this.mainValidator(addr, expression, errorMessage);
	}

	public boolean isValidCity(String city)
	{
		String expression = "^[a-zA-z\\.]{1}[a-zA-z\\.\\s]*$";
		String errorMessage = "City must consist of at least one character; only letters are allowed for a valid name.";

		return this.mainValidator(city, expression, errorMessage);
	}

	public boolean isValidState(String state)
	{
		String expression = "^[a-zA-z]{2}$";
		String errorMessage = "Please use state abbreviation.";

		return this.mainValidator(state, expression, errorMessage);
	}

	public boolean isValidZip(String zip)
	{
		String expression = "^[0-9-]+$";
		String errorMessage = "Zip must consist of at least one digit.";

		return this.mainValidator(zip, expression, errorMessage);
	}

	public void displayErrors()
	{
		SimpleDialog dialog = new SimpleDialog(errorList);
		dialog.display();

		return;
	}

	public void addErrorMessage(String errorMessage)
	{
		errorList.add(errorMessage);
		return;
	}

	public void clearErrorList()
	{
		errorList.clear();
		return;
	}

	// Method(s) [Private Access]
	private boolean mainValidator(String input, String expression, String errorMessage)
	{
		if(input == null)
		{
			errorList.add(errorMessage);
			return false;
		}

		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(input);

		if(matcher.matches())
		{
			return true;
		}
		else
		{
			errorList.add(errorMessage);
			return false;
		}
	}

} // End of ValidationModule Class