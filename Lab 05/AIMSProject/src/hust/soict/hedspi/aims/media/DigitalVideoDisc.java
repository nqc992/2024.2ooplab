package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;

public class DigitalVideoDisc extends Disc implements Playable {

	private static int nbDigitalVideoDiscs = 0; // Static counter to track the number of DVDs

	// Constructors with auto-incremented ID
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

	// Getter for the static counter
	public static int getNbDigitalVideoDisc() {
		return nbDigitalVideoDiscs;
	}

	@Override
	public String toString() {
		return "DVD - Title: " + getTitle()
				+ " - Director: " + getDirector()
				+ " - Length: " + getLength() + " mins"
				+ " - Cost: $" + getCost();
	}

	@Override
	public void play() throws PlayerException {
		if (this.getLength() <= 0) {
			System.err.println("ERROR: DVD length is non-positive!");
			throw new PlayerException("ERROR: DVD length is non-positive!");
		}
		System.out.println("Playing DVD: " + this.getTitle());
		System.out.println("DVD Length: " + this.getLength() + " mins");
	}
}
