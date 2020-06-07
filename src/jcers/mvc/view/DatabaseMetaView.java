package jcers.mvc.view;

/**
 * File:		DatabaseMetaView.java
 * Created:		Apr/02/2016
 * Author:		Piotr Kapela https://github.com/pkapela
 * Description:		DatabaseMetaView class is used to display 
 * 			various information about underlaying database.				
 */				

import jcers.mvc.model.DBase;
import java.sql.SQLException;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


public class DatabaseMetaView extends BorderPane
{
	// Constant(s) [Private Access]
	private final int COLUMN_COUNT = 20;
	private final DBase database;
	private final String FLD_STYLE = "-fx-background-color: -fx-outer-border, -fx-inner-border, -fx-body-color;"
			+ "-fx-background-insets: 0, 1, 2;"
			+ "-fx-background-radius: 5, 4, 3;";

	// Label(s) [Default Access]
	Label dbProductNameLbl = new Label("Database Product Name:");
	Label dbVersionLbl = new Label("Database Version:");
	Label dbDriverNameLbl = new Label("Database Driver Name:");
	Label dbDriverVersionLbl = new Label("Database Driver Version:");

	// Field(s) [Default Access]
	TextField dbProductNameFld = new TextField(); 
	TextField dbVersionFld = new TextField();
	TextField dbDriverNameFld = new TextField();
	TextField dbDriverVersionFld = new TextField();

	// ImageView(s) [Private Access]
	private ImageView okBtnImgView = new ImageView(
			new Image("/jcers/images/ok_icon.png"));

	// Button(s) [Default Access]
	Button okBtn = new Button("Ok");

	// Constructor(s)
	public DatabaseMetaView(DBase database)
	{
		this.database = database;
		layoutForm();
		populateFieldsWithData();
	}

	// Method(s) [Private Access]
	private void layoutForm()
	{
		GridPane centralSection = addGridPane();
		HBox lowerSection = addHBox();

		this.setCenter(centralSection);
		this.setBottom(lowerSection);

		return;
	}

	private GridPane addGridPane()
	{
		// General Properties
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(40, 40, 40, 50));
		gp.setHgap(13);
		gp.setVgap(11);
		gp.setStyle("-fx-font-size: 13px;" + "-fx-focus-color: transparent;");

		// Positioning Database Name
		GridPane.setHalignment(dbProductNameLbl, HPos.RIGHT);
		gp.add(dbProductNameLbl, 0, 0);
		dbProductNameFld.setPrefColumnCount(COLUMN_COUNT);
		dbProductNameFld.setEditable(false);
		dbProductNameFld.setStyle(FLD_STYLE);
		gp.add(dbProductNameFld, 1, 0);

		// Positioning Database Version
		GridPane.setHalignment(dbVersionLbl, HPos.RIGHT);
		gp.add(dbVersionLbl, 0, 1);
		dbVersionFld.setPrefColumnCount(COLUMN_COUNT);
		dbVersionFld.setEditable(false);
		dbVersionFld.setStyle(FLD_STYLE);
		gp.add(dbVersionFld, 1, 1);

		// Positioning Driver Name
		GridPane.setHalignment(dbDriverNameLbl, HPos.RIGHT);
		gp.add(dbDriverNameLbl, 0, 2);
		dbDriverNameFld.setPrefColumnCount(COLUMN_COUNT);
		dbDriverNameFld.setEditable(false);
		dbDriverNameFld.setStyle(FLD_STYLE);
		gp.add(dbDriverNameFld, 1, 2);

		// Positioning Driver Version
		GridPane.setHalignment(dbDriverVersionLbl, HPos.RIGHT);
		gp.add(dbDriverVersionLbl, 0, 3);
		dbDriverVersionFld.setPrefColumnCount(COLUMN_COUNT);
		dbDriverVersionFld.setEditable(false);
		dbDriverVersionFld.setStyle(FLD_STYLE);
		gp.add(dbDriverVersionFld, 1, 3);

		return gp;
	}

	private HBox addHBox()
	{
		// General Properties
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10);
		hbox.setStyle("-fx-background-color: #999966;");

		// Button Properties
		okBtn.setGraphic(okBtnImgView);
		okBtn.setPrefSize(100, 20);

		hbox.setAlignment(Pos.BASELINE_CENTER);
		hbox.getChildren().addAll(okBtn);

		return hbox;
	}

	private void populateFieldsWithData()
	{
		try 
		{
			dbProductNameFld.setText(database.getMetaData().getDatabaseProductName());
			dbVersionFld.setText(database.getMetaData().getDatabaseProductVersion());
			dbDriverNameFld.setText(database.getMetaData().getDriverName());
			dbDriverVersionFld.setText(database.getMetaData().getDriverMajorVersion() + "."
					+ database.getMetaData().getJDBCMinorVersion());	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return;
	}

} // End of DatabaseMetaView Class
