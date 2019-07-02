package code;

public abstract class Seat {
	private String code, ticketCode;
	private int rowNumber, columnNumber;
	
	public Seat(String code, int rowNumber, int columnNumber, String ticketCode) {
		this.code = code;
		this.rowNumber = rowNumber;
		this.columnNumber = columnNumber;
		this.ticketCode = ticketCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getRowNumber() {
		return rowNumber + 1;
	}
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber - 1;
	}
	public int getColumnNumber() {
		return columnNumber + 1;
	}
	public void setColumnNumber(int columnNumber) {
		this.columnNumber = columnNumber - 1;
	}
	public String getTicket() {
		return ticketCode;
	}
	public void setTicket(String ticketCode) {
		this.ticketCode = ticketCode;
	}
}