
public class Singer extends Person{
	
	private String debutAlbum;
	private Date debutAlbumRelease;
	
	//constructor
	public Singer(String name, Date birthDate, String gender, String debut, Date albumRelease, double difficulty) {
		super(name, birthDate, gender, difficulty);
		this.debutAlbum = debut;
		this.debutAlbumRelease = new Date(albumRelease);
	}
	
	//copy constructor
	public Singer(Singer copy) {
		super(copy);
		this.debutAlbum = copy.debutAlbum;
		this.debutAlbumRelease = new Date(copy.debutAlbumRelease);
		
	}
	
	//toString
	public String toString() {
		return (super.toString() + "\nDebut Album: " + debutAlbum + 
				"\nReleased : " +  debutAlbumRelease);
	}
	
	//implementation of abstract methods
	public String entityType() {
		return "This entity is a singer!";
	}
	
	public Singer clone() {
		return (new Singer(this));
	}

}
