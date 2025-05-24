package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException; // Import PlayerException

public class Track implements Playable {
    private String title;
    private int length;

    // Constructor
    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    // Phương thức play() đã được cập nhật để ném PlayerException
    @Override
    public void play() throws PlayerException {
        if (this.getLength() <= 0) {
            System.err.println("ERROR: Track length is non-positive!"); // Xuất thông báo lỗi ra console
            throw new PlayerException("ERROR: Track length is non-positive!"); // Ném PlayerException
        }
        System.out.println("Playing track: " + this.getTitle());
        System.out.println("Track Length: " + this.getLength());
    }

    // Optional: equals() method for comparison (từ code trước)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Track track = (Track) obj;
        return length == track.length && title.equals(track.title);
    }

    // Optional: hashCode() method (từ code trước)
    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + length;
        return result;
    }
}