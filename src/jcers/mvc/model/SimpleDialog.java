package jcers.mvc.model;

/**
 * File:		SimpleDialog.java
 * Created:		Jan/11/2016
 * Author:		Piotr Kapela https://github.com/pkapela
 * Description:		It is a helper class which sole purpose is to 
 * 			display and communicate to the user various information 
 * 			through dialog windows.
 */

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class SimpleDialog 
{
	private List<String> displayList;
	
	// Constructor(s)
	public SimpleDialog()
	{
		this.displayList = new ArrayList<String>();
	}
	
	public SimpleDialog(String message)
	{
		this.displayList = new ArrayList<String>();
		this.displayList.add(message);
	}
	
	public SimpleDialog(List<String> displayList)
	{
		this.displayList = new ArrayList<String>(displayList);
	}
	
	// Method(s) [Public Access]
	public void setDisplayList(List<String> displayList) 
	{
		this.displayList = displayList;
		return;
	}
	
	public void addMessage(String message)
	{
		this.displayList.add(message);
		return;
	}
	
	public void clearDisplayList()
	{
		this.displayList.clear();
		return;
	}
	
	public void display()
	{
		String msg = "";

		if(displayList.isEmpty())
		{
			msg = "No message to display.";
		}
		else
		{
			for(String s : displayList)
			{
				msg = msg + s + "\n";
			}
		}

		Label msgLbl = new Label(msg);
		Button okBtn = new Button("Ok");
		VBox root = new VBox(new StackPane(msgLbl), new StackPane(okBtn));
		root.setPadding(new Insets(15, 15, 15, 20));
		root.setSpacing(10);

		Scene scene = new Scene(root);
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.setScene(scene);

		okBtn.setOnAction(e -> stage.close());

		stage.setTitle("Info");
		stage.sizeToScene();
		stage.showAndWait();

		return;
	}
	
} // End of SimpleDialog Class
