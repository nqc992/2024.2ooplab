package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException; // Import PlayerException

public interface Playable {
    // Chữ ký phương thức đã được cập nhật: giờ đây khai báo rằng nó có thể ném PlayerException
    void play() throws PlayerException;
}