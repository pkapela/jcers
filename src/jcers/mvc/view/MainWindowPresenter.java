package jcers.mvc.view;

/**
 * File:		MainWindowPresenter.java
 * Created:		Dec/26/2015
 * Author:		Piotr Kapela https://github.com/pkapela
 * Description:		The purpose of this class is to add interactivity to MainWindowView class. 
 * 			Both classes are tightly coupled and logically should be consider as one entity. 
 * 			The code takes advantage of Lambda expressions which are part of Java 8.
 */				

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jcers.mvc.model.DBase;
import jcers.mvc.model.PersonModel;


public class MainWindowPresenter 
{
	private final MainWindowView view;

	// Constructor(s)
	public MainWindowPresenter(MainWindowView view)
	{
		this.view = view;
		attachEvents();
	}

	// Method(s) [Private Access]
	private void attachEvents()
	{
		view.studentItem.setOnAction(e -> studentWindow());
		view.facultyItem.setOnAction(e -> facultyWindow());
		view.exitItem.setOnAction(e -> Platform.exit());
		
		view.dbMetaItem.setOnAction(e -> metaWindow());

		return;
	}

	private void studentWindow()
	{
		PersonModel model = new PersonModel();
		PersonSearchView view = new PersonSearchView(model);
		DBase database = new DBase();
		Scene scene = new Scene(view, 1240, 580);
		@SuppressWarnings("unused")
		PersonSearchPresenter presenter = new PersonSearchPresenter(view, model, database);

		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Student Browser");
		stage.setResizable(false);
		stage.show();

		return;
	}

	private void facultyWindow()
	{
		return;
	}
	
	private void metaWindow()
	{
		DBase database = new DBase();
		DatabaseMetaView view = new DatabaseMetaView(database);
		Scene scene = new Scene(view, 600, 400);
		@SuppressWarnings("unused")
		DatabaseMetaPresenter presenter = new DatabaseMetaPresenter(view);
		
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Database Metadata");
		stage.setResizable(false);
		stage.show();
		
		return;
	}

} // End of MainWindowPresenter Class
