package code;

public class Flight {
	private String code, date, departure, arrival, menuCode;
	private int totalSeats, usedSeats;
	private Airplane airplane;
	private Seat[][] seats = null;
	
	public Flight(String code, String date, String departure, String arrival, Airplane airplane,
			String menuCode, int usedSeats) {
		this.code = code;
		this.date = date;
		this.departure = departure;
		this.arrival = arrival;
		this.airplane = airplane;
		this.seats = new Seat[airplane.getSeatRows()][airplane.getSeatColumns()];
		this.menuCode = menuCode;
		this.totalSeats = airplane.getSeatRows()*airplane.getSeatColumns();
		this.usedSeats = usedSeats;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDeparture() {
		return departure;
	}
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	public String getArrival() {
		return arrival;
	}
	public void setArrival(String arrival) {
		this.arrival = arrival;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public int getTotalSeats() {
		return totalSeats;
	}
	public int getUsedSeats() {
		return usedSeats;
	}
	public void setUsedSeats(int usedSeats) {
		this.usedSeats = usedSeats;
	}
	public Airplane getAirplane() {
		return airplane;
	}
	public void setAirplane(Airplane airplane) {
		this.airplane = airplane;
	}
	public Seat[][] getSeats() {
		return seats;
	}
	public Seat getSeatAt(int row, int column) {
		return seats[row - 1][column - 1];
	}
	public void setSeatAt(Seat seat, int row, int column) {
		seats[row - 1][column - 1] = seat;
	}
}