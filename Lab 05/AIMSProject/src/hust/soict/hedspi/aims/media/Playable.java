package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException; // Import PlayerException

public interface Playable {
    // Method signature updated: now declares that it can throw PlayerException
    void play() throws PlayerException;
}
