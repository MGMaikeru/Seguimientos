package model;

public class Tenant extends Person{

	public Tenant(String typeId, String numberId, String name, String telNumber, TelephoneType telType){
		super(typeId, numberId, name, telNumber, telType);
	}

	@Override
	public String addApartmentWithObject(Apartment newApartment){
		String msj = "Maxim capacity reached of apartments .";
		
		return msj;
	}

	@Override
	public double getTotalMonthlyValue(){
		double total = 0;
		return total;
	}
}