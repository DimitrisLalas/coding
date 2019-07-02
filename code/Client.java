package code;

import java.util.ArrayList;
import java.util.Scanner;

public class Client {
	public static ArrayList<Airplane> airplanes = new ArrayList<Airplane>();
	public static ArrayList<Menu> menus = new ArrayList<Menu>();
	public static ArrayList<Flight> flights = new ArrayList<Flight>();
	public static ArrayList<Ticket> tickets = new ArrayList<Ticket>();
	
	public static void main(String[] args) {
		while(true) {
			println("[1] Insert Airplane");
			println("[2] Insert Menu");
			println("[3] Insert Flight");
			println("[4] Cancel Flight");
			println("[5] Book ticket");
			println("[6] Cancel ticket");
			println("[7] Order Menu Items (Available only for business seats)");
			println("[8] Seats Capacity for a certain flight");
			println("[0] Exit");
			Scanner scanner = new Scanner(System.in);
			int selection = Integer.valueOf(scanner.nextLine());
			if(selection == 1) { //Airplane data insertion
				println("#------------Airplane Insertion------------#");
				println("Enter code:");
				String code = scanner.nextLine();
				while(getAirplaneCodes().contains(code)) { println("Airplane with that code already exists!"); code = scanner.nextLine(); }
				println("Enter description:");
				String description = scanner.nextLine();
				println("Enter number of seat rows:");
				int seatRows = Integer.valueOf(scanner.nextLine());
				println("Enter number of seat columns:");
				int seatColumns = Integer.valueOf(scanner.nextLine());
				println("Enter number of which are business rows:");
				int businessRows = Integer.valueOf(scanner.nextLine());
				Airplane airplane = new Airplane(code, description, seatRows, seatColumns, businessRows);
				airplanes.add(airplane);
				println("Airplane has been inserted successfully.");
				println("#------------------------------------------#");
			}
			else if(selection == 2) { //Menu data entry
				println("#----------------Menu entry----------------#");
				println("Enter code:");
				String code = scanner.nextLine();
				while(getMenuCodes().contains(code)) { println("Menu with that code already exists!"); code = scanner.nextLine(); }
				println("Enter list of main plates using ',' in between:");
				String plates = scanner.nextLine();
				println("Enter list of desserts plates using ',' in between:");
				String desserts = scanner.nextLine();
				println("Enter list of drinks plates using ',' in between:");
				String drinks = scanner.nextLine();
				Menu menu = new Menu(code, stringToListComma(plates),
						stringToListComma(desserts),
						stringToListComma(drinks));
				menus.add(menu);
				println("Menu has been inserted successfully.");
				println("#------------------------------------------#");
			}
			else if(selection == 3) {// Flight data entry
				println("#---------------Flight Entry---------------#");
				if(airplanes.size() == 0) {
					println("Please insert an airplane first in order to insert a flight");
				}
				else {
					println("Enter code:");
					String code = scanner.nextLine();
					while(getFlightCodes().contains(code)) { println("Flight with that code already exists!"); code = scanner.nextLine(); }
					println("Enter date:");
					String date = scanner.nextLine();
					println("Enter departure airport:");
					String departure = scanner.nextLine();
					println("Enter arrival airport:");
					String arrival = scanner.nextLine();
					println("Enter airplane code: (Listed below)");
					printAirplanes();
					String airplaneCode = scanner.nextLine();
					while(!getAirplaneCodes().contains(airplaneCode)) { println("Airplane with code " + airplaneCode + " doesn't exist."); airplaneCode = scanner.nextLine(); }
					while(hasAlreadyFlight(airplaneCode, date)) { println("Plane with code " + airplaneCode + " already has a flight that date."); airplaneCode = scanner.nextLine(); }
					println("Enter menu code:");
					String menuCode = scanner.nextLine();
					Flight flight = new Flight(code, date, departure, arrival, findAirplaneClass(airplaneCode), menuCode, 0);
					flights.add(flight);
					println("Flight has been inserted successfully.");
				}
				println("#------------------------------------------#");
			}
			else if(selection == 4) {// Flight cancel
				println("#------------Flight cancelation------------#");
				if(flights.size() == 0) {
					println("There are no flights available.");
				}
				else {
					println("Enter flight code to cancel: (Listed below)");
					printFlights();
					String flightCode = scanner.nextLine();
					while(!getFlightCodes().contains(flightCode)) { println("Flight code " + flightCode + " doesn't exist!"); flightCode = scanner.nextLine(); }
					for(int i = 0; i < getTicketCodesByFlight(flightCode).size(); i++) {
						Ticket tempTicket = findTicketClass(getTicketCodesByFlight(flightCode).get(i));
						println(tempTicket.getFullname() + " needs a refund of " + tempTicket.getPrice());
					}
					deleteTicketsByFlight(flightCode);
					flights.remove(findFlightClass(flightCode));
					println("Flight has been canceled successfully.");
				}
				println("#------------------------------------------#");
			}
			else if(selection == 5) {// Book ticket
				println("#----------------Ticket book---------------#");
				if(flights.size() == 0) {
					println("Please insert a flight in order to book a ticket.");
				}
				else {
					println("Enter code:");
					String ticketCode = scanner.nextLine();
					while(getTicketCodes().contains(ticketCode)) { println("Ticket with that code already exists!"); ticketCode = scanner.nextLine(); }
					println("Enter flight code: (Listed below)");
					printFlights();
					String flightCode = scanner.nextLine();
					while(!getFlightCodes().contains(flightCode)) { println("Flight code " + flightCode + " doesn't exist."); flightCode = scanner.nextLine(); }
					String date = findFlightClass(flightCode).getDate();
					println("Enter type:");
					String type = scanner.nextLine();
					println("Enter full name of customer:");
					String fullName = scanner.nextLine();
					println("Enter seat code:");
					String seatCode = scanner.nextLine();
					println("Enter price:");
					double price = Double.valueOf(scanner.nextLine());
					println("Economy or Business?");
					String choose = scanner.nextLine();
					Flight flight = findFlightClass(flightCode);
					if(choose.equalsIgnoreCase("Economy")) {
						println("Enter row number:");
						int rowNumber = Integer.valueOf(scanner.nextLine());
						while(rowNumber <= findFlightClass(flightCode).getAirplane().getBusinessRows()) {
							println("Row " + rowNumber + " is only for business seats");
							rowNumber = Integer.valueOf(scanner.nextLine());
						}
						println("Enter column number:");
						int columnNumber = Integer.valueOf(scanner.nextLine());
						while(seatIsOccupied(flightCode, rowNumber, columnNumber)) { 
							println("Seat at row " + rowNumber + " and column " + columnNumber + " is already occupied."); 
							println("Enter row number:"); 
							rowNumber = Integer.valueOf(scanner.nextLine()); 
							while(rowNumber <= findFlightClass(flightCode).getAirplane().getBusinessRows()) {
								println("Row " + rowNumber + " is only for business seats");
								rowNumber = Integer.valueOf(scanner.nextLine());
							}
							println("Enter column number:");
							columnNumber = Integer.valueOf(scanner.nextLine());
						}
						Seat tempSeat = new EconomySeat(seatCode, rowNumber - 1, columnNumber - 1, ticketCode);
						Ticket ticket = new Ticket(ticketCode, flightCode, date, type, fullName, tempSeat, price, null, null, null);
						flight.setSeatAt(tempSeat, rowNumber, columnNumber);
						tickets.add(ticket);
					}
					else if(choose.equalsIgnoreCase("Business")) {
						println("Enter row number:");
						int rowNumber = Integer.valueOf(scanner.nextLine());
						while(rowNumber > findFlightClass(flightCode).getAirplane().getBusinessRows()) {
							println("Row " + rowNumber + " is only for economy seats");
							rowNumber = Integer.valueOf(scanner.nextLine());
						}
						println("Enter column number:");
						int columnNumber = Integer.valueOf(scanner.nextLine());
						while(seatIsOccupied(flightCode, rowNumber, columnNumber)) { 
							println("Seat at row " + rowNumber + " and column " + columnNumber + " is already occupied."); 
							println("Enter row number:"); 
							rowNumber = Integer.valueOf(scanner.nextLine()); 
							while(rowNumber > findFlightClass(flightCode).getAirplane().getBusinessRows()) {
								println("Row " + rowNumber + " is only for economy seats");
								rowNumber = Integer.valueOf(scanner.nextLine());
							}
							println("Enter column number:");
							columnNumber = Integer.valueOf(scanner.nextLine());
						}
						Seat tempSeat = new BusinessSeat(seatCode, rowNumber - 1, columnNumber - 1, ticketCode);
						Ticket ticket = new Ticket(ticketCode, flightCode, date, type, fullName, tempSeat, price, null, null, null);
						flight.setSeatAt(tempSeat, rowNumber, columnNumber);
						tickets.add(ticket);
					}
					findFlightClass(flightCode).setUsedSeats(findFlightClass(flightCode).getUsedSeats() + 1);
					println("Ticket has successfully been booked.");
				}
				println("#------------------------------------------#");
			}
			else if(selection == 6) {//Cancel ticket
				println("#------------Ticket Cancelation------------#");
				println("Enter ticket code:");
				String ticketCode = scanner.nextLine();
				while(!getTicketCodes().contains(ticketCode)) { println("Ticket code " + ticketCode + " doesn't exist"); ticketCode = scanner.nextLine(); }
				Ticket ticket = findTicketClass(ticketCode);
				tickets.remove(ticket);
				Flight flight = findFlightClass(ticket.getFlightCode());
				flight.setUsedSeats(flight.getUsedSeats() - 1);
				flight.setSeatAt(null, ticket.getSeat().getRowNumber(), ticket.getSeat().getColumnNumber());
				println("Ticket has successfully been canceled.");
				println("#------------------------------------------#");
			}
			else if(selection ==  7) {//Order menu for business
				println("#---------Menu order for business----------#");
				println("Enter ticket code:");
				String ticketCode = scanner.nextLine();
				while(!getTicketCodes().contains(ticketCode)) { println("Ticket code " + ticketCode + " doesn't exist"); ticketCode = scanner.nextLine(); }
				Ticket ticket = findTicketClass(ticketCode);
				if(ticket.getSeat() instanceof BusinessSeat) {
					println("Enter main plate:");
					String mainplate = scanner.nextLine();
					println("Enter dessert:");
					String dessert = scanner.nextLine();
					println("Enter drink:");
					String drink = scanner.nextLine();
					ticket.setMainPlate(mainplate);
					ticket.setDessert(dessert);
					ticket.setDrink(drink);
					println("Menu on ticket " + ticketCode + " has successfully been ordered.");
				}
				else {
					println("Ticket " + ticketCode + " isn't business seat!");
				}
				println("#------------------------------------------#");
			}
			else if(selection == 8) {//Show seat capacity
				println("#--------------Seat capacity---------------#");
				println("Enter flight code: (Listed below)");
				printFlights();
				String flightCode = scanner.nextLine();
				while(!getFlightCodes().contains(flightCode)) { println("Flight with code " + flightCode + " doesn't exist."); flightCode = scanner.nextLine(); }
				Flight flight = findFlightClass(flightCode);
				Airplane airplane = flight.getAirplane();
				for(int i = 1; i <= airplane.getSeatRows(); i++) {
					for(int j = 1; j <= airplane.getSeatColumns(); j++) {
						if(i <= airplane.getBusinessRows()) {
							if(seatIsOccupied(flightCode, i, j)) {
								print("[[X]]");
							}
							else if(!seatIsOccupied(flightCode, i, j)) {
								print("[[ ]]");
							}
						}
						else if(seatIsOccupied(flightCode, i, j)) {
							print(" [X] ");
						}
						else if(!seatIsOccupied(flightCode, i, j)){
							print(" [ ] ");
						}
					}
					println("");
				}
				println("#------------------------------------------#");
			}
			else if(selection == 0) {//Exit
				scanner.close();
				System.exit(0);
			}
			else {
				println("Could not find selection " + selection);
			}
		}
	}
	public static void print(String msg) {
		System.out.print(msg);
	}
	public static void println(String msg) {
		System.out.println(msg);
	}
	public static ArrayList<String> stringToListComma(String str) {
		ArrayList<String> temp = new ArrayList<String>();
		String[] split = str.split(",");
		for(int i = 0; i < split.length; i++) {
			temp.add(split[i]);
		}
		return temp;
	}
	public static Airplane findAirplaneClass(String code) {
		for(int i = 0; i < airplanes.size(); i++) {
			if(airplanes.get(i).getCode().equals(code)) {
				return airplanes.get(i);
			}
		}
		return null;
	}
	public static Menu findMenuClass(String code) {
		for(int i = 0; i < menus.size(); i++) {
			if(menus.get(i).getCode().equals(code)) {
				return menus.get(i);
			}
		}
		return null;
	}
	public static Flight findFlightClass(String code) {
		for(int i = 0; i < flights.size(); i++) {
			if(flights.get(i).getCode().equals(code)) {
				return flights.get(i);
			}
		}
		return null;
	}
	public static Ticket findTicketClass(String code) {
		for(int i = 0; i < tickets.size(); i++) {
			if(tickets.get(i).getCode().equals(code)) {
				return tickets.get(i);
			}
		}
		return null;
	}
	public static ArrayList<String> getAirplaneCodes() {
		ArrayList<String> temp = new ArrayList<String>();
		for(int i = 0; i < airplanes.size(); i++) {
			temp.add(airplanes.get(i).getCode());
		}
		return temp;
	}
	public static ArrayList<String> getMenuCodes() {
		ArrayList<String> temp = new ArrayList<String>();
		for(int i = 0; i < menus.size(); i++) {
			temp.add(menus.get(i).getCode());
		}
		return temp;
	}
	public static ArrayList<String> getFlightCodes() {
		ArrayList<String> temp = new ArrayList<String>();
		for(int i = 0; i < flights.size(); i++) {
			temp.add(flights.get(i).getCode());
		}
		return temp;
	}
	public static ArrayList<String> getTicketCodes() {
		ArrayList<String> temp = new ArrayList<String>();
		for(int i = 0; i < tickets.size(); i++) {
			temp.add(tickets.get(i).getCode());
		}
		return temp;
	}
	public static boolean hasAlreadyFlight(String airplaneCode, String date) {
		for(int i = 0; i < flights.size(); i++) {
			if(flights.get(i).getAirplane().getCode().equalsIgnoreCase(airplaneCode)
					&& flights.get(i).getDate().equalsIgnoreCase(date)) {
				return true;
			}
		}
		return false;
	}
	public static void printAirplanes() {
		for(int i = 0; i < airplanes.size(); i++) {
			Airplane airplane = airplanes.get(i);
			println("- " + airplane.getCode() + " with description " + airplane.getDescription() + " has " + airplane.getSeatRows() + " seat rows, " + airplane.getSeatColumns() + " seat columns and " + airplane.getBusinessRows() + " business rows");
		} 
	}
	public static void printFlights() {
		for(int i = 0; i < flights.size(); i++) {
			Flight flight = flights.get(i);
			println("- " + flight.getCode() + " flies at " + flight.getDate() + " departures from " + flight.getDeparture() + " and arrives at " + flight.getArrival() + " with airplane code " + flight.getAirplane().getCode() + " with full seats of " + flight.getUsedSeats() + "/" + flight.getTotalSeats());
		} 
	}
	public static void deleteTicketsByFlight(String flightCode) {
		for(int i = 0; i < tickets.size(); i++) {
			if(tickets.get(i).getFlightCode().equalsIgnoreCase(flightCode)) {
				tickets.remove(i);
			}
		}
	}
	public static ArrayList<String> getTicketCodesByFlight(String flightCode) {
		ArrayList<String> temp = new ArrayList<String>();
		for(int i = 0; i < tickets.size(); i++) {
			if(tickets.get(i).getFlightCode().equalsIgnoreCase(flightCode)) {
				temp.add(tickets.get(i).getCode());
			}
		}
		return temp;
	}
	public static boolean seatIsOccupied(String flightCode, int row, int column) {
		for(int i = 0; i < flights.size(); i++) {
			Flight flight = flights.get(i);
			if(flight.getCode().equalsIgnoreCase(flightCode)) {
				if(flight.getSeatAt(row, column) instanceof EconomySeat || flight.getSeatAt(row, column) instanceof BusinessSeat) {
					return true;
				}
			}
		}
		return false;
	}
}