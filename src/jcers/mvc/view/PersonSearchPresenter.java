package jcers.mvc.view;

/**
 * File:		PersonSearchPresenter.java
 * Created:		Dec/26/2015
 * Author:		Piotr Kapela https://github.com/pkapela
 * Description:		The purpose of this class is to add interactivity to 
 * 			PersonSearchView class. Both classes are tightly coupled and 
 * 			logically should be consider as one entity. The code takes advantage 
 * 			of Lambda expressions which are part of Java 8.
 */

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import jcers.mvc.model.DBase;
import jcers.mvc.model.PersonModel;
import jcers.mvc.model.SimpleDialog;
import java.util.ArrayList;


public class PersonSearchPresenter 
{
	private final PersonSearchView view;
	private final PersonModel model;
	private final DBase database;
	private ArrayList<PersonModel> searchResult;

	// Constructor(s)
	public PersonSearchPresenter(PersonSearchView view, PersonModel model, DBase database)
	{
		this.view = view;
		this.model = model;
		this.database = database;
		this.searchResult = new ArrayList<PersonModel>();
		attachEvents();
		attachEventsToContextMenu();
		attachEventsToSearchBoxes();
	}

	// Method(s) [Private Access]
	private void attachEvents() 
	{
		view.viewBtn.setOnAction(e -> viewStudentWindow());
		view.addBtn.setOnAction(e -> addStudentWindow());
		view.removeBtn.setOnAction(e -> removeRecord());
		view.closeBtn.setOnAction(e -> view.getScene().getWindow().hide());
		view.viewCtx.setOnAction(e -> viewStudentWindow());
		view.addCtx.setOnAction(e -> addStudentWindow());
		view.removeCtx.setOnAction(e -> removeRecord());

		return;
	}

	private void attachEventsToContextMenu()
	{
		view.table.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
		{
			@Override 
			public void handle(MouseEvent e)
			{
				if(e.getButton() == MouseButton.SECONDARY)
				{
					view.contextMenu.show(view.table, e.getScreenX(), e.getScreenY());
				}
			}
		});

		view.table.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
		{
			@Override 
			public void handle(MouseEvent e)
			{
				if(e.getButton() == MouseButton.PRIMARY)
				{
					view.contextMenu.hide();
				}
			}
		});

		return;
	}

	private void attachEventsToSearchBoxes()
	{
		view.seIdFld.setOnAction(e -> searchRecord());
		view.seFirstNameFld.setOnAction(e -> searchRecord());
		view.seLastNameFld.setOnAction(e -> searchRecord());
		view.seDobFld.setOnAction(e -> searchRecord());
		view.seTelephoneFld.setOnAction(e -> searchRecord());
		view.seEmailFld.setOnAction(e -> searchRecord());

		return;
	}

	private void addStudentWindow()
	{
		PersonModel modelAdd = new PersonModel();
		PersonView view = new PersonView(modelAdd);

		Scene scene = new Scene(view, 700, 450);
		@SuppressWarnings("unused")
		PersonPresenter presenter = new PersonPresenter(view, modelAdd, database);

		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Add Student");
		stage.setResizable(false);
		stage.show();

		return;
	}

	private void viewStudentWindow()
	{
		PersonModel selectedPerson = view.table.getSelectionModel().getSelectedItem();

		if(selectedPerson != null)
		{
			StudentView view = new StudentView(selectedPerson);			
			
			Scene scene = new Scene(view, 880, 450);
			@SuppressWarnings("unused")
			StudentPresenter presenter = new StudentPresenter(view, selectedPerson, database);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("View Student");
			stage.setResizable(false);
			stage.show();
		}
		
		return;
	}

	private void searchRecord()
	{
		view.data.clear();
		this.searchResult = database.searchRecord(this.model);

		if(this.searchResult != null)
		{
			for(PersonModel tmp : searchResult)
			{
				view.data.add(tmp);
			}
		}
	}

	private void removeRecord()
	{
		SimpleDialog dialog = new SimpleDialog();
		PersonModel tempPerson = view.table.getSelectionModel().getSelectedItem();
		view.data.remove(tempPerson);

		if(database.removeRecord(tempPerson))
		{
			dialog.addMessage("record removed successfully.");
		}
		else
		{
			dialog.addMessage("failed to remove the record.");
		}
		
		dialog.display();
		return;
	}

} // End of PersonSearchPresenter Class
