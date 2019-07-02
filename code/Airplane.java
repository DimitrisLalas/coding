package code;

public class Airplane {
	private String code, description;
	private int seatRows, seatColumns, businessRows;
	
	public Airplane(String code, String description, int seatRows, int seatColumns, int businessRows) {
		this.code = code;
		this.description = description;
		this.seatRows = seatRows;
		this.seatColumns = seatColumns;
		this.businessRows = businessRows;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getSeatRows() {
		return seatRows;
	}
	public void setSeatRows(int seatRows) {
		this.seatRows = seatRows;
	}
	public int getSeatColumns() {
		return seatColumns;
	}
	public void setSeatColumns(int seatColumns) {
		this.seatColumns = seatColumns;
	}
	public int getBusinessRows() {
		return businessRows;
	}
	public void setBusinessRows(int businessRows) {
		this.businessRows = businessRows;
	}
}