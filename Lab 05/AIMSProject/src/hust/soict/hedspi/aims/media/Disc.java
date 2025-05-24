package hust.soict.hedspi.aims.media;

/**
 * The Disc class represents a type of media item that has a length and a director,
 * extending the base Media class.
 */
public class Disc extends Media {
	protected int length;
	protected String director;

	/**
	 * Constructor for the Disc class.
	 *
	 * @param id        the unique identifier of the disc
	 * @param title     the title of the disc
	 * @param category  the category or genre of the disc
	 * @param cost      the cost of the disc
	 * @param length    the duration of the disc in minutes
	 * @param director  the director of the disc
	 */
	public Disc(int id, String title, String category, float cost, int length, String director) {
		super(id, title, category, cost);
		this.length = length;
		this.director = director;
	}

	/**
	 * Gets the length of the disc.
	 *
	 * @return the length in minutes
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Gets the director of the disc.
	 *
	 * @return the director's name
	 */
	public String getDirector() {
		return director;
	}
}
