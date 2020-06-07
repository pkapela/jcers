package jcers.mvc.view;

/**
 * File:		StudentPresenter.java
 * Created:		Jan/17/2016
 * Author:		Piotr Kapela https://github.com/pkapela
 * Description:		The purpose of this class is to add interactivity to 
 * 			StudentView class. Both classes are tightly coupled 
 * 			and logically should be consider as one entity. The code 
 * 			takes advantage of Lambda expressions which are part of Java 8. 
 */

import jcers.mvc.model.PersonModel;
import jcers.mvc.model.ValidationModule;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jcers.mvc.model.DBase;


public class StudentPresenter 
{
	private final PersonModel model;
	private final StudentView view;
	private final DBase database;
	private final ValidationModule validator;

	// Constructor(s) 
	public StudentPresenter(StudentView view, PersonModel model, DBase database) 
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
		view.okBtn.setOnAction(e -> view.getScene().getWindow().hide());
		view.modifyBtn.setOnAction(e -> modifyLayout());
		view.registerBtn.setOnAction(e -> registerWindow());

		return;
	}

	private void modifyLayout()
	{
		view.cancelBtn.setVisible(true);
		view.modifyFields();

		view.okBtn.setOnAction(e -> modifyRecord());
		view.cancelBtn.setOnAction( e -> regularLayout());

		return;
	}

	private void regularLayout()
	{
		view.cancelBtn.setVisible(false);
		view.regularFields();

		view.okBtn.setOnAction(e -> view.getScene().getWindow().hide());

		return;
	}

	private void modifyRecord()
	{
		view.cancelBtn.setVisible(false);
		view.regularFields();
		view.okBtn.setOnAction(e -> view.getScene().getWindow().hide());

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
			database.updateRecord(model);
			return;
		}
		else
		{
			validator.displayErrors();
			validator.clearErrorList();
			return;
		}
	}

	private void registerWindow()
	{
		RegistrationView view = new RegistrationView();
		Scene scene = new Scene(view, 1300, 600);
		
		@SuppressWarnings("unused")
		RegistrationPresenter presenter = new RegistrationPresenter(view);

		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Student Registration");
		stage.setResizable(true);
		stage.show();

		return;
	}

} // End of StudentPresenter Class
