package jcers.mvc.view;

/**
 * File:		PersonView.java
 * Created:		Sep/21/2015
 * Author:		Piotr Kapela https://github.com/pkapela
 * Description:		PersonView class positions all the graphical nodes 
 * 			and binds them to PersonModel class. Nodes have default 
 * 			access in order to be manipulated by PersonPresenter class. 
 * 			PersonView is model dependent and contains no domain code.  
 */

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import jcers.mvc.model.PersonModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class PersonView extends BorderPane 
{
	// Constant(s) [Private Access]
	private final int COLUMN_COUNT = 10;
	final String DATE_FORMAT = "yyyy-MM-dd";
	private final String BTN_STYLE = "-fx-background-color: -fx-outer-border, -fx-inner-border, -fx-body-color;"
			+ "-fx-background-insets: 0, 1, 2;"
			+ "-fx-background-radius: 5, 4, 3;";
	private final String FLD_STYLE = "-fx-background-color: -fx-outer-border, -fx-inner-border, -fx-body-color;"
			+ "-fx-background-insets: 0, 1, 2;"
			+ "-fx-background-radius: 5, 4, 3;";
	private final String MOD_STYLE = "-fx-background-color: #cbcc00";

	// PersonView is model dependent
	private final PersonModel model;

	// Label(s) [Default Access]
	Label firstNameLbl = new Label("First Name");
	Label lastNameLbl = new Label("Last Name");
	Label telephoneLbl = new Label("Telephone");
	Label emailLbl = new Label("Email");
	Label addressLbl = new Label("Address");
	Label cityLbl = new Label("City");
	Label dobLbl = new Label("DOB");
	Label stateLbl = new Label("State");
	Label zipLbl = new Label("ZIP");
	Label genderLbl = new Label("Gender");
	Label creationLbl = new Label("Created: ");
	Label modifiedLbl = new Label("Modified: ");
	Label dCreationLbl = new Label(); // Dynamic Label
	Label dModifiedLbl = new Label(); // Dynamic Label

	// Field(s) [Default Access]
	TextField firstNameFld = new TextField();
	TextField lastNameFld = new TextField();
	TextField telephoneFld = new TextField();
	TextField emailFld = new TextField();
	TextField addressFld = new TextField();
	TextField cityFld = new TextField();
	TextField stateFld = new TextField();
	TextField zipFld = new TextField();
	TextField dobFld = new TextField();

	// ChoiceBox(es) [Default Access]
	ChoiceBox<PersonModel.Sex> genderChb = new ChoiceBox<PersonModel.Sex>(
			FXCollections.observableArrayList(PersonModel.Sex.FEMALE, PersonModel.Sex.MALE, PersonModel.Sex.OTHER));

	// ImageView(s) [Private Access]
	private ImageView personImgView = new ImageView(
			new Image("/jcers/images/user_icon.png"));
	private ImageView addBtnImgView = new ImageView(
			new Image("/jcers/images/add_icon.png"));
	private ImageView cancelBtnImgView = new ImageView(
			new Image("/jcers/images/cancel_icon.png"));

	// Button(s) [Default Access]
	Button addBtn = new Button("Add");
	Button cancelBtn = new Button("Cancel");

	// Constructor(s)
	public PersonView(PersonModel model) 
	{
		this.model = model;
		layoutForm();
		bindFieldsToModel();
		syncBirthDate();
		setEditableTextField(true);
		this.setStyle("-fx-background-color: #F0EFEE;");
	}

	// Method(s) [Public Access]
	public void syncBirthDate()
	{
		LocalDate bdate = model.getDob();
		if(bdate != null)
		{
			dobFld.setText(bdate.format(DateTimeFormatter.ofPattern(DATE_FORMAT)));
		}

		return;
	}

	public void setEditableTextField(boolean edit)
	{
		firstNameFld.setEditable(edit);
		lastNameFld.setEditable(edit);
		telephoneFld.setEditable(edit);
		emailFld.setEditable(edit);
		addressFld.setEditable(edit);
		cityFld.setEditable(edit);
		stateFld.setEditable(edit);
		zipFld.setEditable(edit);
		dobFld.setEditable(edit);

		return;
	}

	public void disableFocus()
	{
		firstNameFld.setStyle(FLD_STYLE);
		lastNameFld.setStyle(FLD_STYLE);
		telephoneFld.setStyle(FLD_STYLE);
		emailFld.setStyle(FLD_STYLE);
		addressFld.setStyle(FLD_STYLE);
		cityFld.setStyle(FLD_STYLE);
		stateFld.setStyle(FLD_STYLE);
		zipFld.setStyle(FLD_STYLE);
		dobFld.setStyle(FLD_STYLE);
		
		return;	
	}
	
	public void modifyStyle()
	{
		firstNameFld.setStyle(MOD_STYLE);
		lastNameFld.setStyle(MOD_STYLE);
		telephoneFld.setStyle(MOD_STYLE);
		emailFld.setStyle(MOD_STYLE);
		addressFld.setStyle(MOD_STYLE);
		cityFld.setStyle(MOD_STYLE);
		stateFld.setStyle(MOD_STYLE);
		zipFld.setStyle(MOD_STYLE);
		dobFld.setStyle(MOD_STYLE);
		
		return;
	}

	// Method(s) [Private Access]
	private void layoutForm()
	{
		GridPane upperSection = addGridPane();
		HBox lowerSection = addHBox();

		this.setCenter(upperSection);
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

		// Positioning Frist Name
		GridPane.setHalignment(firstNameLbl, HPos.RIGHT);
		gp.add(firstNameLbl, 0, 0);
		firstNameFld.setPrefColumnCount(COLUMN_COUNT);
		gp.add(firstNameFld, 1, 0);

		// Positioning Last Name
		GridPane.setHalignment(lastNameLbl, HPos.RIGHT);
		gp.add(lastNameLbl, 0, 1);
		lastNameFld.setPrefColumnCount(COLUMN_COUNT);
		gp.add(lastNameFld, 1, 1);

		// Positioning Telephone
		GridPane.setHalignment(telephoneLbl, HPos.RIGHT);
		gp.add(telephoneLbl, 0, 2);
		telephoneFld.setPrefColumnCount(COLUMN_COUNT);
		gp.add(telephoneFld, 1, 2);

		// Positioning Email
		GridPane.setHalignment(emailLbl, HPos.RIGHT);
		gp.add(emailLbl, 0, 3);
		emailFld.setPrefColumnCount(COLUMN_COUNT);
		gp.add(emailFld, 1, 3);

		// Positioning Address
		GridPane.setHalignment(addressLbl, HPos.RIGHT);
		gp.add(addressLbl, 0, 4);
		addressFld = new TextField();
		GridPane.setColumnSpan(addressFld, 4);
		addressFld.setPrefColumnCount(COLUMN_COUNT + 5);
		gp.add(addressFld, 1, 4);

		// Positioning City
		GridPane.setHalignment(cityLbl, HPos.RIGHT);
		gp.add(cityLbl, 0, 5);
		cityFld.setPrefColumnCount(COLUMN_COUNT - 7);
		gp.add(cityFld, 1, 5);

		// Positioning D.O.B.
		GridPane.setHalignment(dobLbl, HPos.RIGHT);
		gp.add(dobLbl, 0, 6);
		dobFld.setPrefColumnCount(COLUMN_COUNT - 7);
		gp.add(dobFld, 1, 6);

		// Positioning State
		GridPane.setHalignment(stateLbl, HPos.RIGHT);
		gp.add(stateLbl, 2, 5);
		GridPane.setColumnSpan(stateFld, 2);
		stateFld.setPrefColumnCount(COLUMN_COUNT - 7);
		gp.add(stateFld, 3, 5);

		// Positioning ZIP Code
		GridPane.setHalignment(zipLbl, HPos.RIGHT);
		gp.add(zipLbl, 2, 6);
		GridPane.setColumnSpan(zipFld, 2);
		zipFld.setPrefColumnCount(COLUMN_COUNT - 7);
		gp.add(zipFld, 3, 6);

		// Positioning Gender
		GridPane.setHalignment(genderLbl, HPos.RIGHT);
		gp.add(genderLbl, 2, 7);
		genderChb.setValue(PersonModel.Sex.OTHER);
		gp.add(genderChb, 3, 7);

		// Positioning Person Image Icon
		personImgView.setFitWidth(108);
		personImgView.setFitHeight(108);
		GridPane.setColumnSpan(personImgView, 4);
		GridPane.setRowSpan(personImgView, 4);
		gp.add(personImgView, 2, 0);

		// Positioning Creation Date/Time Label
		creationLbl.setVisible(false);
		creationLbl.setStyle("-fx-background-color: #000000; -fx-text-fill: #FFFFFF;");
		creationLbl.setPadding(new Insets(5, 5, 5, 5));
		gp.add(creationLbl, 8, 1);
		// Dynamic Label for Creation
		dCreationLbl.setVisible(true);
		gp.add(dCreationLbl, 9, 1);

		// Positioning Modification Date/Time Label
		modifiedLbl.setVisible(false);
		modifiedLbl.setStyle("-fx-background-color: #000000; -fx-text-fill: #FFFFFF;");
		modifiedLbl.setPadding(new Insets(5, 5, 5, 5));
		gp.add(modifiedLbl, 8, 2);
		// Dynamic Label for Modification
		dModifiedLbl.setVisible(true);
		gp.add(dModifiedLbl, 9, 2);

		return gp;
	}

	private HBox addHBox() 
	{
		// General Properties
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10);
		hbox.setStyle("-fx-background-color: #999966;");

		// Buttons Properties
		addBtn.setGraphic(addBtnImgView);
		addBtn.setPrefSize(100, 20);
		cancelBtn.setGraphic(cancelBtnImgView);
		cancelBtn.setPrefSize(100, 20);
		
		addBtn.setStyle(BTN_STYLE);
		cancelBtn.setStyle(BTN_STYLE);

		hbox.setAlignment(Pos.BASELINE_RIGHT);
		hbox.getChildren().addAll(cancelBtn, addBtn);

		return hbox;
	}

	private void bindFieldsToModel()
	{
		firstNameFld.textProperty().bindBidirectional(model.firstNameProperty());
		lastNameFld.textProperty().bindBidirectional(model.lastNameProperty());
		telephoneFld.textProperty().bindBidirectional(model.telephoneProperty());
		emailFld.textProperty().bindBidirectional(model.emailProperty());
		addressFld.textProperty().bindBidirectional(model.addressProperty());
		cityFld.textProperty().bindBidirectional(model.cityProperty());
		stateFld.textProperty().bindBidirectional(model.stateProperty());
		zipFld.textProperty().bindBidirectional(model.zipProperty());
		genderChb.valueProperty().bindBidirectional(model.sexProperty());
		//genderChb.setValue(PersonModel.Sex.OTHER);

		return;
	}

} // End of PersonView Class