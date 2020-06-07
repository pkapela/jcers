package jcers.mvc;

/**
 * File:		JCersApplication.java
 * Created:		Sep/20/2015
 * Author:		Piotr Kapela https://github.com/pkapela
 * Description:		jCERS is a simple registration system which includes a client
 * 			application as well as web application working together with
 * 			MySQL database.
 */				

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jcers.mvc.view.MainWindowPresenter;
import jcers.mvc.view.MainWindowView;


public class JCersApplication extends Application
{
	@Override
	public void start(Stage primaryStage) 
	{
		createInitialLayout(primaryStage);	 
	}

	public static void main(String[] args) 
	{
		launch(args);
	}

	public static void createInitialLayout(Stage primaryStage) 
	{
		MainWindowView view = new MainWindowView();
		Scene scene = new Scene(view, 430, 555, Color.WHITE);
		@SuppressWarnings("unused")
		MainWindowPresenter presenter = new MainWindowPresenter(view);

		primaryStage.setScene(scene);
		primaryStage.setTitle("jCERS 0.1 Beta");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
} // End of JCersApplication Class