package model;
import model.Building;
import model.Person;

public class RealStateController{
	public static final int QUANTITY_BUILDINGS = 5;
	public static final int QUANTITY_OWNERS = 200;

	private Building[] buildings;
	private Person[] owners;

	public RealStateController(){
		buildings = new Building[QUANTITY_BUILDINGS];
		owners = new Owner[QUANTITY_OWNERS];
	}

	public Building[] getBuildings(){
		return buildings;
	}

	public String addApartment(String numberId, int roomsNumber, int bathsNumber, boolean balcony, double monthlyValue, int buildingPos, int ownerPos){
		Apartment newApartment = new Apartment(numberId, roomsNumber, bathsNumber, balcony, monthlyValue);
		String msj = buildings[buildingPos].addApartmentWithObject(newApartment);
		owners[ownerPos].addApartmentWithObject(newApartment);
		return msj;
	}

	public String addBuilding(String nameId, String adress, int quantityAparments){
		String msj = "Maxim capacity reached.";
		Building newBuilding = new Building(nameId, adress, quantityAparments);
		boolean isEmpty = false;
		for (int i = 0; i<QUANTITY_BUILDINGS && !isEmpty; i++){
			if (buildings[i] == null){
				buildings[i] = newBuilding;
				msj = "Building successful created.";
				isEmpty = true;
			}
		}
		return msj;
	}

	public String addOwner(String typeId, String numberId, String name, String telNumber, TelephoneType telType, String accountNumber, String bankName){
		String msj = "Maxim capacity reached.";
		Person newOwner = new Owner(typeId, numberId, name, telNumber, telType, accountNumber, bankName);
		boolean isEmpty = false;
		for (int i = 0; i<QUANTITY_OWNERS && !isEmpty; i++){
			if (owners[i] == null){
				owners[i] = newOwner;
				isEmpty = true;
				msj = "Owner successful registered.";
			}
		}
		return msj;
	}

	public String addTenantToBuilding(String typeId, String numberId, String name, String telNumber, TelephoneType telType, int buildingPos, int apartmentPos){
		Person newTenant = new Tenant(typeId, numberId, name, telNumber, telType);
		String msj = buildings[buildingPos].addTenantToApartment(newTenant, apartmentPos);
		return msj;
	}

	public String getFreeApartmentsInformation(int pos){
		String msj = buildings[pos].getFreeApartments();
		return msj;
	}

	public String getTotalApartmentsValue(int pos){
		String msj = buildings[pos].getApartmentsValue();
		return msj;
	}

	public String consultApartmentStatus(int buildingPos, String numberId){
		int pos = buildings[buildingPos].searchAparmentsById(numberId);
		String msj = "Apartment doesn't registered.";
		if (pos != -1){
			msj = buildings[buildingPos].getApartmentStatus(pos);
		}
		
		return msj;
	}

	public int searchBuildingByName(String name){
		int pos = -1;
		for (int i = 0; i<QUANTITY_BUILDINGS; i++){
			if (buildings[i] != null){
				if (buildings[i].getName().equals(name)){
					pos = i;
				}
			}
		}
		return pos;
	}

	public int searchOwnerByNumberId(String numberId){
		int pos = -1;
		for (int i = 0; i<QUANTITY_OWNERS; i++){
			if (owners[i] != null){
				if (owners[i].getNumberId().equals(numberId)){
					pos = i;
				}
			}
		}
		return pos;
	}

	public String getOwnerLeasedApartments(int pos){
		String msj = owners[pos].toString();
		return msj;
	}

	public String calculateDistribution(int pos){
		String msj= "";
		double total = owners[pos].getTotalMonthlyValue();
		double realStatePart = total*0.10;
		double ownerPart = total*0.90;
		if (total == 0) {
			msj = "The owner don't have registered apartments.";
		} else {
			msj = "Total monthly value of apartments: " + total + ". \n" +
				"Real state part (10%): " + realStatePart + ". \n" +
				"Owner part (90%): " + ownerPart + ".";
		}

		return msj;
	}
}