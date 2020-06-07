package jcers.mvc.view;

/**
 * File:		MainWindowView.java
 * Created:		Dec/26/2015
 * Author:		Piotr Kapela https://github.com/pkapela
 * Description:		MainWindowView is responsible for creation of the initial 
 * 			window which acts like a hub from which you can access all 
 * 			the features of the application. The graphical nodes are 
 * 			manipulated by MainWindowPresenter class.
 */				

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;


public class MainWindowView extends BorderPane 
{
	// MenuBar(s) [Private Access]
	MenuBar menuBar = new MenuBar();

	// Menu(s) [Private Access]
	Menu fileMenu = new Menu("File");
	Menu dataBrowserMenu = new Menu("Data Browser");		 
	Menu aboutMenu = new Menu("About");

	// MenuItem(s) [Default Access]
	MenuItem settingsItem = new MenuItem("Settings");
	MenuItem exitItem = new MenuItem("Exit");
	MenuItem studentItem = new MenuItem("Student");
	MenuItem facultyItem = new MenuItem("Faculty");
	MenuItem courseItem = new MenuItem("Course");
	MenuItem infoItem = new MenuItem("Info");
	MenuItem dbMetaItem = new MenuItem("Database Metadata");

	// ImageView(s) [Private Access]
	private ImageView logoImageView = new ImageView(
			new Image("/jcers/images/jCERS_logo.png"));

	// Constructor(s)
	public MainWindowView()
	{
		layoutForm();
	}

	// Method(s) [Private Access]
	private void layoutForm()
	{
		this.setStyle("-fx-accent: #B20000;");

		// Setting up TopMenu
		menuBar.setStyle("-fx-base: black;");
		this.setTop(menuBar);

		fileMenu.getItems().addAll(settingsItem, exitItem);
		menuBar.getMenus().addAll(fileMenu);

		dataBrowserMenu.getItems().addAll(studentItem, facultyItem, courseItem);
		menuBar.getMenus().addAll(dataBrowserMenu);

		aboutMenu.getItems().addAll(infoItem);
		aboutMenu.getItems().addAll(dbMetaItem);
		menuBar.getMenus().add(aboutMenu);

		// Setting up Logo
		logoImageView.setFitWidth(430);
		logoImageView.setFitHeight(150);
		this.setCenter(logoImageView);

		return;
	}
	
} // End of MainWindowView Class