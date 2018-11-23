
public class Country extends Entity {
	private String capital;
	
	//constructor
	public Country(String name, Date birthDate, String Capital, double difficulty) {
		super(name, birthDate, difficulty);
		this.capital = Capital;
	}
	
	//copy constructor
	public Country(Country copy) {
		super(copy);
		this.capital = copy.capital;
	}
	
	//toString
	public String toString() {
		return (super.toString() + "\nCapital: " + capital);
	}
	
	//implementation of abstract methods
	public String entityType() {
		return "This entity is a country!";
	}
	
	public Country clone() {
		return (new Country(this));
	}
}
