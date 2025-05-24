package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import hust.soict.hedspi.aims.exception.PlayerException; // Import PlayerException

public class CompactDisc extends Media implements Playable {
    private String artist;
    private List<Track> tracks = new ArrayList<>();
    private String director; // Thuộc tính này có thể có hoặc không tùy vào thiết kế của bạn

    // Constructors (giữ nguyên hoặc điều chỉnh tùy theo cài đặt của bạn)
    public CompactDisc(int id, String title, String category, float cost, String artist) {
        super(id, title, category, cost);
        this.artist = artist;
    }

    public CompactDisc(int id, String title, String category, float cost, String director, String artist) {
        super(id, title, category, cost);
        this.director = director;
        this.artist = artist;
    }

    public CompactDisc(String title, String category, float cost, String artist) {
        super(title, category, cost);
        this.artist = artist;
    }
    
    public CompactDisc(String title, String category, String artist, String director, float cost) {
        super(title, category, cost);
        this.artist = artist;
        this.director = director;
    }

    // Getters
    public String getArtist() {
        return artist;
    }

    public String getDirector() {
        return director;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    // Add và Remove Track
    public void addTrack(Track track) {
        if (track == null) {
            System.err.println("Cannot add a null track.");
            return;
        }
        if (!tracks.contains(track)) {
            tracks.add(track);
        } else {
            System.out.println("Track '" + track.getTitle() + "' already exists in this CD.");
        }
    }

    public void removeTrack(Track track) {
        if (track == null) {
            System.err.println("Cannot remove a null track.");
            return;
        }
        if (tracks.contains(track)) {
            tracks.remove(track);
        } else {
            System.out.println("Track '" + track.getTitle() + "' not found in this CD.");
        }
    }

    // Tính tổng thời lượng của CD
    @Override
    public int getLength() {
        int totalLength = 0;
        for (Track track : tracks) {
            totalLength += track.getLength();
        }
        return totalLength;
    }

    // Phương thức play() đã được cập nhật với PlayerException
    @Override
    public void play() throws PlayerException {
        // 1. Kiểm tra thời lượng của toàn bộ CD
        if (this.getLength() <= 0) {
            System.err.println("ERROR: CD length is non-positive!");
            throw new PlayerException("ERROR: CD length is non-positive!");
        }

        System.out.println("Playing CD: " + this.getTitle());
        System.out.println("CD Length: " + this.getLength() + "s");
        System.out.println("Artist: " + this.getArtist());

        // Kiểm tra nếu CD không có tracks nào
        if (tracks.isEmpty()) {
            System.err.println("ERROR: CD has no tracks to play!");
            throw new PlayerException("ERROR: CD has no tracks to play!");
        }

        // 2. Lặp qua từng track và gọi play()
        Iterator<Track> iter = tracks.iterator();
        while (iter.hasNext()) {
            Track nextTrack = iter.next();
            try {
                nextTrack.play(); // Gọi phương thức play() của từng track
            } catch (PlayerException e) {
                // Nếu bất kỳ track nào không thể phát, ném lại PlayerException
                // Điều này có nghĩa là CD không thể phát hoàn chỉnh
                System.err.println("ERROR playing track '" + nextTrack.getTitle() + "': " + e.getMessage());
                throw e; // Ném lại ngoại lệ từ track
            }
        }
        System.out.println("Finished playing CD: " + this.getTitle());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CD - ").append(getTitle()).append(" - ").append(getCategory());
        if (director != null && !director.isEmpty()) {
             sb.append(" - ").append(director);
        }
        if (artist != null && !artist.isEmpty()) {
            sb.append(" - ").append(artist);
        }
        sb.append(" - ").append(getLength()).append("s: ").append(getCost()).append("$\n");
        
        if (!tracks.isEmpty()) {
            sb.append("Tracks:\n");
            for (Track track : tracks) {
                sb.append("  - ").append(track.getTitle()).append(" (").append(track.getLength()).append("s)\n");
            }
        } else {
            sb.append("  (No tracks)\n");
        }
        return sb.toString();
    }
}