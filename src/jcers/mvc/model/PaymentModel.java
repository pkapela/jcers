package jcers.mvc.model;

/**
 * File:		PaymentModel.java
 * Created:		May/06/2016
 * Author:		Piotr Kapela https://github.com/pkapela
 * Description:		
 */

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class PaymentModel 
{
	private final StringProperty tuitionPaid = new SimpleStringProperty(this, "tuitionPaid", "");
	private final StringProperty paymentMethod = new SimpleStringProperty(this, "paymentMethod", "");
	private final StringProperty payerName = new SimpleStringProperty(this, "payerName", "");
	private final StringProperty cardDigits = new SimpleStringProperty(this, "cardDigits", "");

	// Constructor(s) 
	public PaymentModel()
	{
		this(null, null, null, null);
	}

	public PaymentModel(String tuitionPaid, String paymentMethod, String payerName, String cardDigits)
	{
		this.tuitionPaid.set(tuitionPaid);
		this.paymentMethod.set(paymentMethod);
		this.payerName.set(payerName);
		this.cardDigits.set(cardDigits);
	}
	
	// Object's Override Method(s)
	@Override
	public String toString()
	{
		return new String("tuitionPaid: " + this.getTuitionPaid() + " paymentMethod: " + this.getPaymentMethod() + " payerName: "
							+ this.getPayerName());
	}

	// Tuition Paid Property
	public final StringProperty tuitionPaidProperty() 
	{
		return this.tuitionPaid;
	}
	
	public final java.lang.String getTuitionPaid() 
	{
		return this.tuitionPaidProperty().get();
	}

	public final void setTuitionPaid(final java.lang.String tuitionPaid) 
	{
		this.tuitionPaidProperty().set(tuitionPaid);
	}
	
	// Payment Method Property
	public final StringProperty paymentMethodProperty() 
	{
		return this.paymentMethod;
	}

	public final java.lang.String getPaymentMethod() 
	{
		return this.paymentMethodProperty().get();
	}

	public final void setPaymentMethod(final java.lang.String paymentMethod) 
	{
		this.paymentMethodProperty().set(paymentMethod);
	}
	
	// Payer Name Property
	public final StringProperty payerNameProperty() 
	{
		return this.payerName;
	}
	public final java.lang.String getPayerName() 
	{
		return this.payerNameProperty().get();
	}

	public final void setPayerName(final java.lang.String payerName) 
	{
		this.payerNameProperty().set(payerName);
	}

	// Card Digits Property
	public final StringProperty cardDigitsProperty() 
	{
		return this.cardDigits;
	}

	public final java.lang.String getCardDigits() 
	{
		return this.cardDigitsProperty().get();
	}

	public final void setCardDigits(final java.lang.String cardDigits) 
	{
		this.cardDigitsProperty().set(cardDigits);
	}
	
} // End of PaymentModel class
