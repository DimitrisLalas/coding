package code;

import java.util.ArrayList;

public class Menu {
	private String code;
	private ArrayList<String> mainPlates, desserts, drinks;
	
	public Menu(String code, ArrayList<String> mainPlates, ArrayList<String> desserts, ArrayList<String> drinks) {
		this.code = code;
		this.mainPlates = mainPlates;
		this.desserts = desserts;
		this.drinks = drinks;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public ArrayList<String> getMainPlates() {
		return mainPlates;
	}
	public void setMainPlates(ArrayList<String> mainPlates) {
		this.mainPlates = mainPlates;
	}
	public ArrayList<String> getDesserts() {
		return desserts;
	}
	public void setDesserts(ArrayList<String> desserts) {
		this.desserts = desserts;
	}
	public ArrayList<String> getDrinks() {
		return drinks;
	}
	public void setDrinks(ArrayList<String> drinks) {
		this.drinks = drinks;
	}
}