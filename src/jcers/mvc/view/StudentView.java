package jcers.mvc.view;

/**
 * File:		StudentView.java
 * Created:		Jan/15/2016
 * Author:		Piotr Kapela https://github.com/pkapela
 * Description:		StudentView class is responsible for a record presentation 
 * 			from the database. The class is extended from PersonView class 
 * 			and has a few minor additional details.
 * 
 */

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import jcers.mvc.model.PersonModel;


public class StudentView extends PersonView 
{
	private PersonModel model;
	
	// Constant(s) [Private Access]
	private final String BTN_STYLE = "-fx-background-color: -fx-outer-border, -fx-inner-border, -fx-body-color;"
			+ "-fx-background-insets: 0, 1, 2;"
			+ "-fx-background-radius: 5, 4, 3;";
	
	// Button(s) [Default Access]
	Button okBtn = new Button("Ok");
	Button cancelBtn = new Button("Cancel");
	Button modifyBtn = new Button("Modify");
	Button registerBtn = new Button("Register");
	Button memoBtn = new Button("Memo");

	// ImageView(s) [Private Access]
	private ImageView cancelBtnImgView = new ImageView(
			new Image("/jcers/images/cancel_icon.png"));
	private ImageView okBtnImgView = new ImageView(
			new Image("/jcers/images/ok_icon.png"));

	// Constructor(s)
	public StudentView(PersonModel model) 
	{
		super(model);
		this.model = model;
		super.setEditableTextField(false);
		super.disableFocus();
		layoutForm();
		bindFieldsToModel();
		this.setStyle("-fx-background-color: #F0EFEE;");
	}
	
	// Method(s) [Public Access]
	public void modifyFields()
	{
		super.modifyStyle();
		super.setEditableTextField(true);
		return;
	}
	
	public void regularFields()
	{
		super.disableFocus();
		super.setEditableTextField(false);	
		return;
	}

	// Method(s) [Private Access]
	private void layoutForm()
	{
		HBox lowerSection = addHBox();
		VBox leftSection = addVBox();

		this.setBottom(lowerSection);
		this.setLeft(leftSection);

		return;
	}

	private HBox addHBox() 
	{
		// General Properties
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10);
		hbox.setStyle("-fx-background-color: #999966;");

		// Buttons Properties
		okBtn.setGraphic(okBtnImgView);
		okBtn.setPrefSize(100, 20);
		cancelBtn.setGraphic(cancelBtnImgView);
		cancelBtn.setPrefSize(100, 20);
		cancelBtn.setVisible(false);
		
		okBtn.setStyle(BTN_STYLE);
		cancelBtn.setStyle(BTN_STYLE);

		hbox.setAlignment(Pos.BASELINE_RIGHT);
		hbox.getChildren().addAll(cancelBtn, okBtn);

		return hbox;
	}

	private VBox addVBox()
	{
		// General Properties
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(15, 12, 15, 12));
		vbox.setSpacing(10);
		vbox.setStyle("-fx-background-color: #999966;");

		// Buttons Properties
		modifyBtn.setPrefSize(100, 20);
		registerBtn.setPrefSize(100, 20);
		memoBtn.setPrefSize(100, 20);
		
		modifyBtn.setStyle(BTN_STYLE);
		registerBtn.setStyle(BTN_STYLE);
		memoBtn.setStyle(BTN_STYLE);

		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(modifyBtn, registerBtn, memoBtn);

		return vbox;
	}

	private void bindFieldsToModel()
	{	
		return;
	}

} // End of PersonViewView Class