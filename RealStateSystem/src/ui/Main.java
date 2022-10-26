package ui;

import java.util.Scanner;
import model.RealStateController;
import model.TelephoneType;


public class Main{


	private Scanner reader;
	private RealStateController controller;

	public Main() {
		reader = new Scanner(System.in); 
		controller = new RealStateController();

	}

	public static void main(String[] args) {
		Main main = new Main(); 

		int option = -1; 
		do{

			option = main.getOptionShowMenu(); 
			main.executeOption(option);

		}while(option != 0);

	}

	public Scanner getReader() {
		return this.reader;
	}

	public void setReader(Scanner reader) {
		this.reader = reader;
	}

	public int getOptionShowMenu(){
		int option = 0; 
		System.out.println(printMenu());

		option = validateIntegerOption(); 

		return option; 
	}

	public String printMenu(){
		return 
			"\n" +
			"<< --------------------------------------------------------------------- >>\n" +
			"<< -                                Welcome                            - >>\n" +
			"<< --------------------------------------------------------------------- >>\n" +
			"1. Register building \n" +
			"2. Add apartment to building \n" + 
			"3. Register owner \n" +
			"4. Register tenant \n" +
			"5. Consult free apartments \n" +
			"6. Consult total monthly value of the rented apartments of a building\n" +
			"7. Consult status of a specific apartment \n" +
			"8. Mostrar quantity of leased apartments of a specific owner \n" +
			"9. Calculate and show monthly value of owner apartments \n" +
			"0. Exit.\n"; 
	}

	public void buildingRegister(){
		System.out.println("Type building name:");
		String nameId = getBuildingName();
		System.out.println("Type building adress:");
		String adress = reader.next();
		System.out.println("Type building number of aparments:");
		int aparmentsNumber = getIntegerData();
		String msj = controller.addBuilding(nameId, adress, aparmentsNumber);
		System.out.println(msj);
	}

	public String getBuildingName(){
		String msj = "This name is already use. Type another.";
		String nameId = "";
		int pos = 0;
		while(pos != -1){
			nameId = reader.next();
			pos = controller.searchBuildingByName(nameId);
			if (pos != -1){
				System.out.println(msj);
			}
		}
		return nameId;
	}

	public void registerAparmentToBuilding(){
		String msj = "The building is not exist.";
		System.out.println("Type building name to add aparment:");
		String buildingName = reader.next();
		int buildingPos = controller.searchBuildingByName(buildingName);
		if (buildingPos != -1){
			msj = "The owner number id is not registered.";
			System.out.println("Type owner id number of the aparment:");
			String ownerNumberId = reader.next();
			int ownerPos = controller.searchOwnerByNumberId(ownerNumberId);
			if (ownerPos != -1){
				System.out.println("Type apartment number id:");
				String numberId = getAparmentNumerId(buildingPos);
				System.out.println("Type apartment number of rooms:");
				int quantRooms = getIntegerData();
				System.out.println("Type apartment number of bathrooms:");
				int quantBaths = getIntegerData();
				System.out.println("Will The apartment have a balcony? S/N:");
				boolean balcony = getBalcony();
				System.out.println("Type apartment monthly value:");
				double monthlyValue = reader.nextDouble();
				msj = controller.addApartment(numberId, quantRooms, quantBaths, balcony, monthlyValue, buildingPos, ownerPos);
			}
		}

		System.out.println(msj);
	}

	public String getAparmentNumerId(int buildingPos){
		String msj = "This number id is already use. Type another.";
		String numberId = "";
		int pos = 0;
		while(pos != -1){
			numberId = reader.next();
			pos = controller.getBuildings()[buildingPos].searchAparmentsById(numberId);
			if (pos != -1){
				System.out.println(msj);
			}
		}
		return numberId;
	}

	public boolean getBalcony(){
		String option = "";
		boolean statusBalcony = false;
		while(!option.equals("S") && !option.equals("N")){
			option = reader.next();
			switch(option){
				case "S":
					statusBalcony = true;
					break;

				case "N":
					statusBalcony = false;
					break;

				default:
					System.out.println("Invalid Option!");
					break;
			}
		}
		return statusBalcony;
	}

	public void ownerRegister(){
		String msj = "The number id is already on use.";
		System.out.println("Type identification number:");
		String numberId = reader.next();
		int pos = controller.searchOwnerByNumberId(numberId);
		if (pos == -1){
			System.out.println("Type identification type:"); 
			String typeId = reader.next();
			System.out.println("Type owner name:");
			String name = reader.next();
			System.out.println("Type owner telephone number:");
			String telephoneNumber = reader.next();
			System.out.println("Select a type telephone option: 1)HOME, 2)OFFICE, 3)MOVIL, 4)FAMILY, 5)OTHER");
			TelephoneType telephoneType = getTelephoneType();
			System.out.println("Type account number:");
			String accountNumber = reader.next();
			System.out.println("Type account bank name:");
			String accountBank = reader.next();
			msj = controller.addOwner(typeId, numberId, name, telephoneNumber, telephoneType, accountNumber, accountBank);
		}
		System.out.println(msj);
	}

	public TelephoneType getTelephoneType(){
		int option = 0;
		TelephoneType telephoneType = TelephoneType.OTHER;
		while(option != 1 && option != 2 && option != 3 && option != 4 && option != 5){
			option = getIntegerData();
			switch(option){
				case 1:
					telephoneType = TelephoneType.HOME;
					break;

				case 2:
					telephoneType = TelephoneType.OFFICE;
					break;

				case 3:
					telephoneType = TelephoneType.MOVIL;
					break;

				case 4:
					telephoneType = TelephoneType.FAMILY;
					break;

				case 5:
					telephoneType = TelephoneType.OTHER;
					break;

				default:
					System.out.println("Invalid Option!");
					break;
			}
		}
		return telephoneType;
	}

	public void tenantRegister(){
		String msj = "The building is not exist.";
		System.out.println("Type building name where the apartment is located:");
		String buildingName = reader.next();
		int buildingPos = controller.searchBuildingByName(buildingName);

		if (buildingPos != -1){
			msj = "The apartment is not exist.";
			System.out.println("Type apartment number id to lease:");
			String apartmentId = reader.next();
			int apartmentPos = controller.getBuildings()[buildingPos].searchAparmentsById(apartmentId);

			if (apartmentPos != -1){
				System.out.println("Type identification type:");
				String typeId = reader.next();
				System.out.println("Type identification number:");
				String numberId = reader.next();
				System.out.println("Type tenant name:");
				String name = reader.next();
				System.out.println("Type tenant telephone number:");
				String telephoneNumber = reader.next();
				System.out.println("Select a type telephone option: 1)HOME, 2)OFFICE, 3)MOVIL, 4)FAMILY, 5)OTHER");
				TelephoneType telephoneType = getTelephoneType();
				msj = controller.addTenantToBuilding(typeId, numberId, name, telephoneNumber, telephoneType, buildingPos, apartmentPos);
			}			
		}
		System.out.println(msj);
	}

	public void consultFreeApartments(){
		String msj = "The building is not exist.";
		System.out.println("Type building name where the apartment is located:");
		String buildingName = reader.next();
		int buildingPos = controller.searchBuildingByName(buildingName);
		if (buildingPos != -1){
			msj = controller.getFreeApartmentsInformation(buildingPos);
		}
		System.out.println(msj);
	}

	public void consultTotalMonthlyValue(){
		String msj = "The building is not exist.";
		System.out.println("Type building name where the apartment is located:");
		String buildingName = reader.next();
		int buildingPos = controller.searchBuildingByName(buildingName);
		if (buildingPos != -1){
			msj = controller.getTotalApartmentsValue(buildingPos);
		}
		System.out.println(msj);
	}

	public void consultEspecificApartment(){
		String msj = "The building is not exist.";
		System.out.println("Type building name where the apartment is located:");
		String buildingName = reader.next();
		int buildingPos = controller.searchBuildingByName(buildingName);
		if (buildingPos != -1){
			System.out.println("Type apartment number id:");
			String numberId = reader.next();
			msj = controller.consultApartmentStatus(buildingPos, numberId);
		}
		System.out.println(msj);
	}

	public void showOwnerLeasedApartments(){
		String msj = "The owner number id is not registered.";
		System.out.println("Type owner id number to consult information:");
		String ownerNumberId = reader.next();
		int ownerPos = controller.searchOwnerByNumberId(ownerNumberId);
		if (ownerPos != -1){
			msj = controller.getOwnerLeasedApartments(ownerPos);
		}
		System.out.println(msj);
	}

	public void consultOwnerMonthlyValue(){
		String msj = "The owner number id is not registered.";
		System.out.println("Type owner id number to consult information:");
		String ownerNumberId = reader.next();
		int ownerPos = controller.searchOwnerByNumberId(ownerNumberId);
		if (ownerPos != -1){
			msj = controller.calculateDistribution(ownerPos);
		}
		System.out.println(msj);
	}

	public int getIntegerData(){
		String msj = "Invalid character. Type another.";
		int data = -1;
		while(data == -1){
			data = validateIntegerOption();
			if (data == -1){
				System.out.println(msj);
			}
		}
		return data;
	}
	

	public void executeOption(int option){

			switch(option){
				case 1: 
					buildingRegister();
					break; 

				case 2: 
					registerAparmentToBuilding();
					break; 

				case 3: 
					ownerRegister();
					break;

				case 4: 
					tenantRegister();
					break;

				case 5: 
					consultFreeApartments();
					break;

				case 6: 
					consultTotalMonthlyValue();
					break;

				case 7: 
					consultEspecificApartment();
					break;

				case 8: 
					showOwnerLeasedApartments();
					break;

				case 9: 
					consultOwnerMonthlyValue();
					break;

				case 0: 
					System.out.println("Exit program.");
					break; 

				default: 
					System.out.println("Invalid Option");
					break; 
			}
	}

	public int validateIntegerOption(){
		int option = 0; 

		if(reader.hasNextInt()){
			option = reader.nextInt(); 
		}
		else{
			// clear reader. 
			reader.nextLine(); 
			option = -1; 
		}

		return option; 
	}



}
