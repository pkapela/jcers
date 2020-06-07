package jcers.mvc.model;

/**
* File:		CourseModel.java
* Created:		Apr/08/2016
* Author:		Piotr Kapela https://github.com/pkapela
* Description:		
*/		

import java.util.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class CourseModel 
{
	private final StringProperty courseID = new SimpleStringProperty(this, "courseID", "");
	private final StringProperty courseName = new SimpleStringProperty(this, "courseName", "");
	private final IntegerProperty courseTuition = new SimpleIntegerProperty(this, "courseTuition");
	private final ObjectProperty<Date> registrationDate = new SimpleObjectProperty<Date>(this, "registrationDate", null);
	
	// Constructor(s) 
	public CourseModel()
	{
		this(null, null, null, null);
	}
	
	public CourseModel(String courseID, String courseName, Integer courseTuition, Date registrationDate)
	{
		this.courseID.set(courseID);
		this.courseName.set(courseName);
		this.courseTuition.set(courseTuition);
		this.registrationDate.set(registrationDate);
	}
	
	// Object's Override Method(s)
	@Override
	public String toString()
	{
		return new String("courseID: " + this.getCourseID() + " courseName: " + this.getCourseName());
	}

	// Course ID Property
	public final StringProperty courseIDProperty() 
	{
		return this.courseID;
	}
	
	public final java.lang.String getCourseID() 
	{
		return this.courseIDProperty().get();
	}
	

	public final void setCourseID(final java.lang.String courseID) 
	{
		this.courseIDProperty().set(courseID);
	}
	
	// Course Name Property
	public final StringProperty courseNameProperty() 
	{
		return this.courseName;
	}
	
	public final java.lang.String getCourseName() 
	{
		return this.courseNameProperty().get();
	}
	
	public final void setCourseName(final java.lang.String courseName) 
	{
		this.courseNameProperty().set(courseName);
	}
	
	// Course Tuition Property
	public final IntegerProperty courseTuitionProperty() 
	{
		return this.courseTuition;
	}
	
	public final int getCourseTuition() 
	{
		return this.courseTuitionProperty().get();
	}
	
	public final void setCourseTuition(final int courseTuition) 
	{
		this.courseTuitionProperty().set(courseTuition);
	}
	
	// Course Registration Date Property
	public final ObjectProperty<Date> registrationDateProperty() 
	{
		return this.registrationDate;
	}
	
	public final java.util.Date getRegistrationDate() 
	{
		return this.registrationDateProperty().get();
	}
	
	public final void setRegistrationDate(final java.util.Date registrationDate) 
	{
		this.registrationDateProperty().set(registrationDate);
	}
	
} // End of CourseModel Class
