package jcers.mvc.view;

/**
 * File:		RegistrationPresenter.java
 * Created:		May/04/2016
 * Author:		Piotr Kapela https://github.com/pkapela
 * Description:		
 * 		
 */

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


public class RegistrationPresenter 
{
	private final RegistrationView view;
	
	public RegistrationPresenter(RegistrationView view)
	{
		this.view = view;
		attachEvents();
		attachEventsToContextMenu();
	}
	
	// Method(s) [Private Access]
	void attachEvents()
	{
		view.closeBtn.setOnAction(e -> view.getScene().getWindow().hide());
		
		return;
	}
	
	private void attachEventsToContextMenu()
	{
		view.courseTable.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
		{
			@Override 
			public void handle(MouseEvent e)
			{
				if(e.getButton() == MouseButton.SECONDARY)
				{
					view.regContextMenu.show(view.courseTable, e.getScreenX(), e.getScreenY());
				}
			}
		});

		view.courseTable.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
		{
			@Override 
			public void handle(MouseEvent e)
			{
				if(e.getButton() == MouseButton.PRIMARY)
				{
					view.regContextMenu.hide();
				}
			}
		});
		
		view.paymentTable.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
		{
			@Override 
			public void handle(MouseEvent e)
			{
				if(e.getButton() == MouseButton.SECONDARY)
				{
					view.payContexMenu.show(view.paymentTable, e.getScreenX(), e.getScreenY());
				}
			}
		});

		view.paymentTable.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
		{
			@Override 
			public void handle(MouseEvent e)
			{
				if(e.getButton() == MouseButton.PRIMARY)
				{
					view.payContexMenu.hide();
				}
			}
		});

		return;
	}

	
} // End of RegistrationPresenter Class
