package model;
import model.Apartment;

public class Building{
	private String nameId;
	private Apartment[] apartments;
	private int quantityApartments;
	private String adress;

	public Building(String nameId, String adress, int quantityApartments){
		this.nameId = nameId;
		this.adress = adress;
		this.quantityApartments = quantityApartments;
		apartments = new Apartment[quantityApartments];
	}

	public String getName(){
		return this.nameId;
	}

	public String getFreeApartments(){
		int quanFreeApartments = 0;
		String information = "There aren't registered apartments.";
		String aux = "";
		for (int i = 0; i<quantityApartments; i++){
			if (apartments[i] != null){
				information = "All apartments in this building are leased.";
				if (apartments[i].getIsFree() == true){
					quanFreeApartments += 1;
					information = "There are " + quanFreeApartments + " free apartments. \n";
					aux += apartments[i].toString() + "\n";
				}
			}
		}
		information += aux;
		return information;
	}

	public String getApartmentsValue(){
		double totalMonthlyValue = 0;
		String information = "There aren't registered apartments.";
		String aux = "";
		for (int i = 0; i<quantityApartments; i++){
			if (apartments[i] != null){
				information = "All apartments in this building are free.";
				if (apartments[i].getIsFree() == false){
					totalMonthlyValue += apartments[i].getMonthlyValue();
					information = "The total monthly value of rented apartments is of " + totalMonthlyValue + ". \n";
					aux += apartments[i].toString() + "\n";
				}
			}
		}
		information += aux;
		return information;
	}

	public String getApartmentStatus(int apartmentPos){
		boolean status = apartments[apartmentPos].getIsFree();
		String msj = "";
		if (status = true){
			msj = "The apartment is free.";
		}else{
			msj = "Apartment is leased.";
		}
		return msj;
	}

	public int searchAparmentsById(String numberId){
		int pos = -1;
		for (int i = 0; i<quantityApartments; i++){
			if (apartments[i] != null){
				if (apartments[i].getNumberId().equals(numberId)){
					pos = i;
				}
			}	
		}
		return pos;
	}

	public String addApartmentWithObject(Apartment newApartment){
		String msj = "Maxim capacity reached.";
		boolean isEmpty = false;
		for (int i = 0; i < quantityApartments && !isEmpty; i++){
			if (apartments[i] == null){
				apartments[i] = newApartment;
				msj = "New apartment registered.";
				isEmpty = true;
			}
		}
		return msj;
	}

	public String addTenantToApartment(Person tenant, int apartmentPos){
		String msj = apartments[apartmentPos].setTenant(tenant);
		return msj;
	}
}

