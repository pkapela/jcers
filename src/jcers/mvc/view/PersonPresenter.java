package jcers.mvc.view;

/**
 * File:		PersonPresenter.java
 * Created:		Sep/21/2015
 * Author:		Piotr Kapela https://github.com/pkapela
 * Description:		The purpose of this class is to add interactivity to 
 * 			PersonView. Both classes are tightly coupled and 
 * 			logically should be consider as one entity. The code takes 
 * 			advantage of Lambda expressions which are part of Java 8.
 */

import jcers.mvc.model.DBase;
import jcers.mvc.model.PersonModel;
import jcers.mvc.model.SimpleDialog;
import jcers.mvc.model.ValidationModule;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class PersonPresenter 
{
	private final PersonModel model;
	private final PersonView view;
	private final DBase database;
	private final ValidationModule validator;

	// Constructor(s) 
	public PersonPresenter(PersonView view, PersonModel model, DBase database) 
	{
		this.view = view;
		this.model = model;
		this.database = database;
		validator = new ValidationModule();

		attachEvents();
	}

	// Method(s) [Private Access]
	private void attachEvents() 
	{
		view.cancelBtn.setOnAction(e -> view.getScene().getWindow().hide());
		view.addBtn.setOnAction(e -> addRecord());
	}

	private void addRecord()
	{
		boolean isValidToAdd = true;

		if(!validator.isValidFirstName(view.firstNameFld.getText()))
		{
			isValidToAdd = false;
		}
		if(!validator.isValidLastName(view.lastNameFld.getText()))
		{
			isValidToAdd = false;
		}
		if(!validator.isValidTelephone(view.telephoneFld.getText()))
		{
			isValidToAdd = false;
		}
		if(!validator.isValidEmail(view.emailFld.getText()))
		{
			isValidToAdd = false;
		}
		if(!validator.isValidAddress(view.addressFld.getText()))
		{
			isValidToAdd = false;
		}
		if(!validator.isValidCity(view.cityFld.getText()))
		{
			isValidToAdd = false;
		}
		if(!validator.isValidState(view.stateFld.getText()))
		{
			isValidToAdd = false;
		}
		if(!validator.isValidZip(view.zipFld.getText()))
		{
			isValidToAdd = false;
		}
		if(!validator.isValidBirthDate(view.dobFld.getText()))
		{
			isValidToAdd = false;
			view.syncBirthDate();
		}
		else
		{
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(view.DATE_FORMAT);
			LocalDate bdate = LocalDate.parse(view.dobFld.getText(), formatter);
			model.setDob(bdate);
		}

		if(isValidToAdd)
		{
			database.addRecord(model);
			SimpleDialog dialog = new SimpleDialog("Record added successfully.");
			dialog.display();
			view.getScene().getWindow().hide();
			
			return;
		}
		else
		{
			validator.displayErrors();
			validator.clearErrorList();
			return;
		}
	}

} // End of PersonPresenter Class