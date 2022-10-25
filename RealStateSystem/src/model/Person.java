package model;

public abstract class Person{
	private String typeId;
	private String numberId;
	private String name;
	private String telNumber;
	private TelephoneType telType;

	public Person(String typeId, String numberId, String name, String telNumber, TelephoneType telType){
		this.typeId = typeId;
		this.numberId = numberId;
		this.name = name;
		this.telNumber = telNumber;
	}

	public String getName(){
		return this.name;
	}

	public String getNumberId(){
		return this.name;
	}

	public abstract String addApartmentWithObject(Apartment newApartment);

	public abstract double getTotalMonthlyValue();
}