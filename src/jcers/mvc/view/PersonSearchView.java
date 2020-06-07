package jcers.mvc.view;

/**
 * File:		PersonSearchView.java
 * Created:		Dec/26/2015
 * Author:		Piotr Kapela https://github.com/pkapela
 * Description:		PersonSearchView class creates and positions all the graphical 
 * 			nodes appropriately. Nodes have default access in order to be 
 * 			manipulated by PersonSearchPresenter class. PersonSearchView is 
 * 			responsible for creation of search table which together with DBase 
 * 			class allows to browse the student records.
 */				

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import jcers.mvc.model.PersonModel;


public class PersonSearchView extends BorderPane
{
	final PersonModel model;

	// Constant(s) [Private Access]
	private final double COL_WIDTH = 185;
	private final int TEXTFIELD_WIDTH = 13;
	private final String BTN_STYLE = "-fx-background-color: -fx-outer-border, -fx-inner-border, -fx-body-color;"
			+ "-fx-background-insets: 0, 1, 2;"
			+ "-fx-background-radius: 5, 4, 3;";

	// TextField(s) [Default Access]
	TextField seIdFld = new TextField(); 
	TextField seTelephoneFld = new TextField(); 
	TextField seDobFld = new TextField(); 
	TextField seLastNameFld = new TextField();
	TextField seFirstNameFld = new TextField();
	TextField seEmailFld = new TextField();

	// Button(s) [Default Access]
	Button viewBtn = new Button("View");
	Button addBtn = new Button("Add");
	Button removeBtn = new Button("Remove");
	Button closeBtn = new Button("Close");

	// TableView [Default Access]
	TableView<PersonModel> table = new TableView<PersonModel>(); 
	final ObservableList<PersonModel> data = FXCollections.observableArrayList();

	// Table Column(s) [Default Access]
	TableColumn<PersonModel, String> idCol = new TableColumn<PersonModel, String>("ID");
	TableColumn<PersonModel, String> firstNameCol = new TableColumn<PersonModel, String>("First Name");
	TableColumn<PersonModel, String> lastNameCol = new TableColumn<PersonModel, String>("Last Name");
	TableColumn<PersonModel, String> dobCol = new TableColumn<PersonModel, String>("DOB");
	TableColumn<PersonModel, String> telephoneCol = new TableColumn<PersonModel, String>("Telephone");
	TableColumn<PersonModel, String> emailCol = new TableColumn<PersonModel, String>("Email");

	// ImageView(s) [Private Access]
	private ImageView viewImgView = new ImageView(
			new Image("/jcers/images/view_icon.png"));
	private ImageView removeImgView = new ImageView(
			new Image("/jcers/images/cancel_icon.png"));
	private ImageView addImgView = new ImageView(
			new Image("/jcers/images/add_icon.png"));

	// ContextMenu [Default Access]
	ContextMenu contextMenu = new ContextMenu();
	MenuItem viewCtx = new MenuItem("View");
	MenuItem removeCtx = new MenuItem("Remove");
	MenuItem addCtx = new MenuItem("Add");

	// Constructor(s)
	public PersonSearchView(PersonModel model)
	{
		this.model = model;
		layoutForm();
		createContextMenu();
		bindFieldsToModel();
	}

	// Method(s) [Private Access]
	private void layoutForm()
	{
		VBox vbox = addVBox();
		VBox table = addTable();

		this.setLeft(vbox);
		this.setCenter(table);
	}

	private VBox addVBox()
	{
		// General Properties
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(15, 12, 15, 12));
		vbox.setSpacing(10);
		vbox.setStyle("-fx-background-color: #999966;");

		// Setting up Buttons Size
		viewBtn.setPrefSize(100, 20);
		addBtn.setPrefSize(100, 20);
		removeBtn.setPrefSize(100, 20);
		closeBtn.setPrefSize(100, 20);

		viewBtn.setStyle(BTN_STYLE);
		addBtn.setStyle(BTN_STYLE);
		removeBtn.setStyle(BTN_STYLE);
		closeBtn.setStyle(BTN_STYLE);

		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(viewBtn, addBtn, removeBtn, closeBtn);

		return vbox;
	}

	@SuppressWarnings("unchecked")
	private VBox addTable()
	{
		// General Properties
		VBox tablePane = new VBox();
		table.setEditable(false);
		table.setFixedCellSize(25);
		table.setPrefHeight(550);
		table.setStyle("-fx-focus-color: transparent;");

		// Setting up Columns
		idCol.setCellValueFactory(new PropertyValueFactory<PersonModel, String>("id"));
		firstNameCol.setCellValueFactory(new PropertyValueFactory<PersonModel, String>("firstName"));
		lastNameCol.setCellValueFactory(new PropertyValueFactory<PersonModel, String>("lastName"));
		dobCol.setCellValueFactory(new PropertyValueFactory<PersonModel, String>("dob"));
		telephoneCol.setCellValueFactory(new PropertyValueFactory<PersonModel, String>("telephone"));	   
		emailCol.setCellValueFactory(new PropertyValueFactory<PersonModel, String>("email"));

		firstNameCol.setPrefWidth(COL_WIDTH);
		lastNameCol.setPrefWidth(COL_WIDTH);
		dobCol.setPrefWidth(COL_WIDTH);
		telephoneCol.setPrefWidth(COL_WIDTH);
		emailCol.setPrefWidth(COL_WIDTH);
		idCol.setPrefWidth(COL_WIDTH);

		table.getColumns().addAll(idCol, firstNameCol, lastNameCol, dobCol, telephoneCol, emailCol);
		table.setItems(data);

		tablePane.getChildren().addAll(table);

		// Setting up Search Panel
		seIdFld.setPrefColumnCount(TEXTFIELD_WIDTH);
		seFirstNameFld.setPrefColumnCount(TEXTFIELD_WIDTH);
		seLastNameFld.setPrefColumnCount(TEXTFIELD_WIDTH);
		seDobFld.setPrefColumnCount(TEXTFIELD_WIDTH);
		seTelephoneFld.setPrefColumnCount(TEXTFIELD_WIDTH);
		seEmailFld.setPrefColumnCount(TEXTFIELD_WIDTH);

		HBox hbox = new HBox();
		hbox.getChildren().addAll(seIdFld, seFirstNameFld, seLastNameFld, seDobFld, seTelephoneFld, seEmailFld);
		hbox.setSpacing(3);
		hbox.setPadding(new Insets(3, 0, 0, 0));

		tablePane.getChildren().addAll(hbox);

		return tablePane;
	}

	private void createContextMenu() 
	{
		// Adjusting the Size
		viewImgView.setFitHeight(16);
		viewImgView.setFitWidth(16);
		addImgView.setFitHeight(16);
		addImgView.setFitWidth(16);
		removeImgView.setFitHeight(16);
		removeImgView.setFitWidth(16);

		// Adding Images to Context Menu
		viewCtx.setGraphic(viewImgView);
		addCtx.setGraphic(addImgView);
		removeCtx.setGraphic(removeImgView);

		contextMenu.getItems().addAll(viewCtx, addCtx, removeCtx);

		return;
	}

	private void bindFieldsToModel()
	{
		seIdFld.textProperty().bindBidirectional(model.idProperty());
		seTelephoneFld.textProperty().bindBidirectional(model.telephoneProperty());
		seLastNameFld.textProperty().bindBidirectional(model.lastNameProperty());
		seFirstNameFld.textProperty().bindBidirectional(model.firstNameProperty());
		seEmailFld.textProperty().bindBidirectional(model.emailProperty());

		return;
	}

} // End of PersonSearchView Class
