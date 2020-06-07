package jcers.mvc.model;

/**
 * File:		PersonModel.java
 * Created:		Oct/14/2015
 * Author:		Piotr Kapela https://github.com/pkapela
 * Description:		This is the primary model for the application which
 * 			carries domain data and logic for both student and 
 * 			instructor records. The model is independent and is loosely 
 * 			coupled with DBase class.   
 */			

import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;


public class PersonModel 
{
	public enum AgeCategory {
		MINOR, ADULT, SENIOR, UNKNOWN
	};

	public enum Sex {
		MALE, FEMALE, OTHER
	};

	private final StringProperty firstName = new SimpleStringProperty(this, "firstName", null);
	private final StringProperty lastName = new SimpleStringProperty(this, "lastName", null); 
	private final StringProperty telephone = new SimpleStringProperty(this, "telephone", null);
	private final StringProperty email = new SimpleStringProperty(this, "email", null);
	private final StringProperty address = new SimpleStringProperty(this, "address", null);
	private final StringProperty city = new SimpleStringProperty(this, "city", null);
	private final StringProperty state = new SimpleStringProperty(this, "state", null);
	private final StringProperty zip = new SimpleStringProperty(this, "zip", null);
	private final StringProperty id = new SimpleStringProperty(this, "id", null);
	private final ObjectProperty<Sex> sex = new SimpleObjectProperty<Sex>(this, "sex", null);
	private final ObjectProperty<LocalDate> dob = new SimpleObjectProperty<LocalDate>(this, "dob", null);
	private final ObjectProperty<LocalDateTime> cDate = new SimpleObjectProperty<LocalDateTime>(this, "cDate", null);	// Record creation 
	private final ObjectProperty<LocalDateTime> mDate = new SimpleObjectProperty<LocalDateTime>(this, "mDate", null);	// Record modification 

	// Constructor(s) 
	public PersonModel()
	{
		this(null, null, null, null, null, null, null, null, null, null, null);
	}

	public PersonModel(String firstName, String lastName, String telephone, String email, String address, String city, String state,
			String zip, Sex sex, LocalDate dob, String id) 
	{
		this.firstName.set(firstName);
		this.lastName.set(lastName);
		this.telephone.set(telephone);
		this.email.set(email);
		this.address.set(address);
		this.city.set(city);
		this.state.set(state);
		this.zip.set(zip);
		this.sex.set(sex);
		this.dob.set(dob);
		this.id.set(id);
	}

	public PersonModel(String firstName, String lastName, LocalDate dob, String telephone, String email, String id)
	{
		this.firstName.set(firstName);
		this.lastName.set(lastName);
		this.dob.set(dob);
		this.telephone.set(telephone);
		this.email.set(email);
		this.id.set(id);

		// Arguments are not provided
		this.address.set(null);
		this.city.set(null);
		this.state.set(null);
		this.sex.set(null);
		this.zip.set(null);
	}

	// Object's Override Method(s)
	@Override
	public String toString()
	{
		return new String("firstName: " + this.getFirstName() + " lastName: " + this.getLastName() + " D.O.B: " + this.getDob().toString() 
				+ " telephone: " + this.getTelephone());
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj == null)
		{
			return false;
		}
		if(obj instanceof PersonModel)
		{
			PersonModel person = (PersonModel) obj;

			if(!this.getId().equals(person.getId()))
			{
				return false;
			}
			if(!this.getFirstName().equals(person.getFirstName()))
			{
				return false;
			}
			if(!this.getLastName().equals(person.getLastName()))
			{
				return false;
			}
			if(!this.getTelephone().equals(person.getTelephone()))
			{
				return false;
			}
			if(!this.getEmail().equals(person.getEmail()))
			{
				return false;
			}
			if(!this.getAddress().equals(person.getAddress()))
			{
				return false;
			}
			if(!this.getCity().equals(person.getCity()))
			{
				return false;
			}
			if(!this.getState().equals(person.getState()))
			{
				return false;
			}
			if(!this.getZip().equals(person.getZip()))
			{
				return false;
			}
			if(!this.getDob().equals(person.getDob()))
			{
				return false;
			}
			if(!this.getSex().equals(person.getSex()))
			{
				return false;
			}

			return true;
		}
		else
		{
			return false;
		}
	}

	// First Name Property
	public final StringProperty firstNameProperty() 
	{
		return this.firstName;
	}

	public final String getFirstName() 
	{
		return this.firstNameProperty().get();
	}

	public final void setFirstName(final String firstName) 
	{
		this.firstNameProperty().set(firstName);
	}

	// Last Name Property
	public final StringProperty lastNameProperty() 
	{
		return this.lastName;
	}

	public final String getLastName() 
	{
		return this.lastNameProperty().get();
	}

	public final void setLastName(final String lastName) 
	{
		this.lastNameProperty().set(lastName);
	}

	// Telephone Property
	public final StringProperty telephoneProperty() 
	{
		return this.telephone;
	}

	public final String getTelephone() 
	{
		return this.telephoneProperty().get();
	}

	public final void setTelephone(final String telephone) 
	{
		this.telephoneProperty().set(telephone);
	}

	// Email Property
	public final StringProperty emailProperty() 
	{
		return this.email;
	}

	public final String getEmail() 
	{
		return this.emailProperty().get();
	}

	public final void setEmail(final String email) 
	{
		this.emailProperty().set(email);
	}

	// Address Property
	public final StringProperty addressProperty() 
	{
		return this.address;
	}

	public final String getAddress() 
	{
		return this.addressProperty().get();
	}

	public final void setAddress(final String address) 
	{
		this.addressProperty().set(address);
	}

	// City Property
	public final StringProperty cityProperty() 
	{
		return this.city;
	}

	public final String getCity() 
	{
		return this.cityProperty().get();
	}

	public final void setCity(final String city) 
	{
		this.cityProperty().set(city);
	}

	// State Property
	public final StringProperty stateProperty() 
	{
		return this.state;
	}

	public final String getState() 
	{
		return this.stateProperty().get();
	}

	public final void setState(final String state) 
	{
		this.stateProperty().set(state);
	}

	// Zip Property
	public final StringProperty zipProperty() 
	{
		return this.zip;
	}

	public final String getZip() 
	{
		return this.zipProperty().get();
	}

	public final void setZip(final String zip) 
	{
		this.zipProperty().set(zip);
	}

	// Sex Property
	public final ObjectProperty<Sex> sexProperty() 
	{
		return this.sex;
	}

	public final PersonModel.Sex getSex() 
	{
		return this.sexProperty().get();
	}

	public final void setSex(final PersonModel.Sex sex) 
	{
		this.sexProperty().set(sex);
	}

	// D.O.B. Property
	public final ObjectProperty<LocalDate> dobProperty() 
	{
		return this.dob;
	}

	public final LocalDate getDob() 
	{
		return this.dobProperty().get();
	}

	public final void setDob(final LocalDate dob) 
	{
		this.dobProperty().set(dob);
	}

	// ID Property
	public final StringProperty idProperty() 
	{
		return this.id;
	}

	public final String getId() 
	{
		return this.idProperty().get();
	}

	public final void setId(final String id) 
	{
		this.idProperty().set(id);
	}

	// Creation Date/Time Property
	public final ObjectProperty<LocalDateTime> cDateProperty() 
	{
		return this.cDate;
	}

	public final LocalDateTime getCDate() 
	{
		return this.cDateProperty().get();
	}

	public final void setCDate(final LocalDateTime cDate) 
	{
		this.cDateProperty().set(cDate);
	}

	// Modification Date/Time Property
	public final ObjectProperty<LocalDateTime> mDateProperty() 
	{
		return this.mDate;
	}

	public final LocalDateTime getMDate() 
	{
		return this.mDateProperty().get();
	}

	public final void setMDate(final LocalDateTime mDate) 
	{
		this.mDateProperty().set(mDate);
	}

} // End of PersonModel Class