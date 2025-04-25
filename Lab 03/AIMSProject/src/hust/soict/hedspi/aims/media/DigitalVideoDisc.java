package hust.soict.hedspi.aims.media;

public class DigitalVideoDisc extends Disc {
    public DigitalVideoDisc(int id, String title, String category,
                            float cost, int length, String director) {
        super(id, title, category, cost, length, director);
    }

    @Override
    public void play() {
        if (getLength() <= 0) {
            System.out.println("ERROR: DVD " + getTitle() + " cannot be played (length <= 0)");
            return;
        }
        System.out.println("Playing DVD: " + getTitle());
        System.out.println("DVD length: " + getLength());
    }

    @Override
    public String toString() {
        return "DVD - " + super.toString() + " - " + getDirector() + " - " + getLength() + "min";
    }
}