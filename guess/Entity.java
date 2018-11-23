

public abstract class Entity {
	private String name;
	private Date born;
	private double difficulty;
	
	//normal constructor
	public Entity(String name, Date birthDate, double difficulty) {
		this.name = name;
		this.born = new Date(birthDate); //no privacy leak
		this.difficulty = difficulty;
	}
	
	//copy constructor
	public Entity(Entity entity) {
		this.name = entity.name;
		this.born = new Date(entity.born); //no privacy leak
		this.difficulty = entity.difficulty;
	}
	
	//toString
	public String toString() {
		return ("Name: " + name + "\nBorn on: " + born);
	}
	
	//getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBorn() {
		return new Date(born);
	}

	public void setBorn(Date born) {
		if (born == null) {
			System.out.println("Error: No Date");
			return;
		}
		else
			this.born = new Date(born);
	}
	
	//get ticket number
	public int getAwardedTicketNumber() {
		return (int)(difficulty * 100);
	}
	
	//entityType - abstract method - returns string
	public abstract String entityType();
	
	//clone - abstract method - returns entity
	public abstract Entity clone();
	
	//welcomeMessage - using entityType, prints welcome message
	public String welcomeMessage() {
		return ("Welcome! Let's start the game!\n" + entityType());
	}
	
	//closingMessage - using toString, prints information about entity after user guesses correctly
	public String closingMessage() {
		return ("Here's some more info about the entity: \n" + toString());
	}

}
