
public class Person extends Entity{

	private String gender;
	
	//constructor
	public Person(String name, Date birthDate, String gender, double difficulty) {
		super(name, birthDate, difficulty);
		this.gender = gender;
	}
	
	//copy constructor
	public Person(Person copy) {
		super(copy);
		this.gender = copy.gender;
	}
	
	//toString
	public String toString() {
		return (super.toString() + "\nGender: " + gender);
	}
	
	//implementation of abstract classes
	public String entityType() {
		return "This entity is a person!";
	}
	
	public Person clone() {
		return (new Person(this));
	}
	
}
