package model;

public class Owner extends Person{
	public static final int QUANTITY_APARTMENTS = 20;

	private String accountNumber;
	private String bankName;
	private Apartment apartments[];
	private int quantityApart;

	public Owner(String typeId, String numberId, String name, String telNumber, TelephoneType telType, String accountNumber, String bankName){
		super(typeId, numberId, name, telNumber, telType);
		this.accountNumber = accountNumber;
		this.bankName = bankName;
		apartments = new Apartment[QUANTITY_APARTMENTS];
	}

	public String getNumberId(){
		return super.getNumberId();
	}

	@Override
	public String addApartmentWithObject(Apartment newApartment){
		String msj = "Maxim capacity reached of apartments .";
		boolean isEmpty = false;
		for (int i = 0; i < QUANTITY_APARTMENTS && !isEmpty; i++){
			if (apartments[i] == null){
				apartments[i] = newApartment;
				msj = "New apartment registered.";
				isEmpty = true;
				quantityApart += 1;
			}
		}
		return msj;
	}

	public String getLeasedApartments(){
		int quanLeasedApartments = 0;
		String information = "There aren't registered apartments.";
		String aux = "";
		for (int i = 0; i<quantityApart; i++){
			if (apartments[i] != null){
				information = "All apartments of owner are free.";
				if (apartments[i].getIsFree() == false){
					quanLeasedApartments += 1;
					information = "There are " + quanLeasedApartments + " leased apartments. \n";
					aux += apartments[i].toString() + "\n";
				}
			}
		}
		information += aux;
		return information;
	}

	@Override
	public double getTotalMonthlyValue(){
		double total = 0;
		for (int i = 0; i < quantityApart; i++){
			if (apartments[i] != null){
				total += apartments[i].getMonthlyValue();
			}
		}
		return total;
	}

	public String toString(){
		return "Name: " + super.getName() + ".  Id Number: " + super.getNumberId() + ". \n" +
			   "----------------------------------------------------------------- \n" +
			   "Total apartments: " + quantityApart + ".\n" +
			   getLeasedApartments();
	}
}