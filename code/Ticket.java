package code;

public class Ticket {
	private String code, flightCode, date, type, fullname, mainPlate, dessert, drink;
	private Seat seat;
	private double price;
	
	public Ticket(String code, String flightCode, String date, String type, String fullname, Seat seat, double price, String mainPlate, String dessert, String drink) {
		this.code = code;
		this.flightCode = flightCode;
		this.date = date;
		this.type = type;
		this.fullname = fullname;
		this.seat = seat;
		this.price = price;
		this.mainPlate = mainPlate;
		this.dessert = dessert;
		this.drink = drink;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getFlightCode() {
		return flightCode;
	}
	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public Seat getSeat() {
		return seat;
	}
	public void setSeat(Seat seat) {
		this.seat = seat;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getMainPlate() {
		return mainPlate;
	}
	public void setMainPlate(String mainPlate) {
		this.mainPlate = mainPlate;
	}
	public String getDessert() {
		return dessert;
	}
	public void setDessert(String dessert) {
		this.dessert = dessert;
	}
	public String getDrink() {
		return drink;
	}
	public void setDrink(String drink) {
		this.drink = drink;
	}
}