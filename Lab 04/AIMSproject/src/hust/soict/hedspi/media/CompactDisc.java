package hust.soict.hedspi.aims.media;

import java.util.ArrayList;

public class CompactDisc extends Disc implements Playable {
    private static int nbCompactDiscs = 0; // Biến tĩnh để theo dõi số lượng CompactDisc
    private String artist;
    private ArrayList<Track> tracks = new ArrayList<>();

    // Constructor sử dụng id tự động tăng
    public CompactDisc(String title, String category, float cost, String director, int length, String artist) {
        super(++nbCompactDiscs, title, category, cost, length, director); // Tăng nbCompactDiscs và cấp ID
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void addTrack(Track track) {
        if (tracks.contains(track)) {
            System.out.println("Track đã tồn tại: " + track.getTitle());
        } else {
            tracks.add(track);
        }
    }

    public void removeTrack(Track track) {
        if (tracks.contains(track)) {
            tracks.remove(track);
        } else {
            System.out.println("Không tìm thấy Track mang tên: " + track.getTitle());
        }
    }

    @Override
    public int getLength() {
        int totalLength = 0;
        for (Track track : tracks) {
            totalLength += track.getLength();
        }
        return totalLength;
    }

    @Override
    public void play() {
        System.out.println("Playing CD: " + this.getTitle());
        System.out.println("Artist: " + this.artist);
        for (Track track : tracks) {
            track.play();
        }
    }

    @Override
    public String toString() {
        return "CD - Title: " + getTitle() + " - Artist: " + getArtist() + " - Cost: $" + getCost();
    }

    public static int getNbCompactDiscs() {
        return nbCompactDiscs;
    }
}
