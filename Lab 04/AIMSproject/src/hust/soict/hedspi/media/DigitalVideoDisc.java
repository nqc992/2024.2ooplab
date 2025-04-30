package hust.soict.hedspi.aims.media;

public class DigitalVideoDisc extends Disc implements Playable {
	private static int nbDigitalVideoDiscs = 0;
	public DigitalVideoDisc(String title) {
		super(++nbDigitalVideoDiscs, title, "", 0.0f, 0, "");
	}
	public DigitalVideoDisc(String title, String category, float cost) {
        super(++nbDigitalVideoDiscs, title, category, cost, 0, "");
	}
	public DigitalVideoDisc(String title, String category, String director, float cost) {
        super(++nbDigitalVideoDiscs, title, category, cost, 0, director);
	}
	public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(++nbDigitalVideoDiscs, title, category, cost, length, director);
	}

	public static int getNbDigitalVideoDisc() {
		return nbDigitalVideoDiscs;
	}

	
	
	@Override
	public String toString() {
	    return "DVD - Title: " + getTitle() + " - Director: " + getDirector()
	         + " - Length: " + getLength() + " mins - Cost: $" + getCost();
	}
	
	@Override
    public void play() {
        System.out.println("Playing DVD: " + this.getTitle());
        System.out.println("DVD length: " + this.getLength());
    }
	public boolean isMatch(String title) {
	    if (getTitle() == null || title == null) {
	        return false;
	    }

	    String discTitleLower = getTitle().toLowerCase();
	    String[] keywords = title.toLowerCase().split("\\s+");

	    for (String keyword : keywords) {
	        if (discTitleLower.contains(keyword)) {
	            return true;
	        }
	    }
	    return false;
	}
	
}
