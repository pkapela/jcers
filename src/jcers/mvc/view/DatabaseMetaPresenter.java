package jcers.mvc.view;

/**
 * File:		DatabaseMetaPresenter.java
 * Created:		Apr/03/2016
 * Author:		Piotr Kapela https://github.com/pkapela
 * Description:		The purpose of this class is to add interactivity to 
 * 			DatabaseMetaView class. Both classes are tightly coupled 
 * 			and logically should be consider as one entity. The code 
 * 			takes advantage of Lambda expressions which are part of Java 8.	
 */		


public class DatabaseMetaPresenter 
{
	private final DatabaseMetaView view;

	// Constructor(s)
	public DatabaseMetaPresenter(DatabaseMetaView view)
	{
		this.view = view;
		attachEvents();
	}

	// Method(s) [Private Access]
	private void attachEvents()
	{
		view.okBtn.setOnAction(e -> view.getScene().getWindow().hide());
	}

} // End of DatabaseMetaPresenter Class
