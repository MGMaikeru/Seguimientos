package model;

public class Apartment{
	private String numberId;
	private int roomsNumber;
	private int bathsNumber;
	private boolean balcony;
	private double monthlyValue;
	private boolean isFree;
	private Person tenant;

	public Apartment(String numberId, int roomsNumber, int bathsNumber, boolean balcony, double monthlyValue){
		this.numberId = numberId;
		this.roomsNumber = roomsNumber;
		this.bathsNumber = bathsNumber;
		this.balcony = balcony;
		this.monthlyValue = monthlyValue;
		this.isFree = true;
	}

	public String getNumberId(){
		return this.numberId;
	}

	public boolean getIsFree(){
		return this.isFree;
	}

	public double getMonthlyValue(){
		return this.monthlyValue;
	}

	public String setTenant(Person newTenant){
		String msj = "This apartment is already lease.";
		if (this.tenant == null){
			this.isFree = false;
			this.tenant = newTenant;
			msj = "Successful operation. This apartment is now lease.";
		}
		return msj;
	}

	public String toString(){
		return  
			"------------------------------- \n" +
		 	"Number ID:" + this.numberId + ". \n" +
		 	"Quantity rooms:" + this.roomsNumber + ". \n" +
		 	"Quantity baths:" + this.bathsNumber + ". \n" +
		 	"Balcony:" + this.balcony + ". \n" +
		 	"Monthly value:" + this.monthlyValue + ". \n" +
		 	"------------------------------- ";
	}
}