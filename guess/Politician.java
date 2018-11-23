
public class Politician extends Person{
	
	private String party;
	
	//constructor
	public Politician(String name, Date birthDate, String gender, String party, double difficulty) {
		super(name, birthDate, gender, difficulty);
		this.party = party;
	}
	
	//copy constructor
	public Politician(Politician copy) {
		super(copy);
		this.party = copy.party;
	}
	
	//toString
	public String toString() {
		return (super.toString() + "\nParty: " + party);
	}
	
	//implementation of abstract methods
	public String entityType() {
		return "This entity is a politician!";
	}
	
	public Politician clone() {
		return (new Politician(this));
	}
}
