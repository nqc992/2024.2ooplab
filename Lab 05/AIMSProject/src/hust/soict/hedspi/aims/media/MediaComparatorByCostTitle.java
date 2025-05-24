package hust.soict.hedspi.aims.media;

import java.util.Comparator;

/**
 * A comparator for Media objects that sorts primarily by cost in descending order,
 * and by title in ascending lexicographical order (case-insensitive) if costs are equal.
 */
public class MediaComparatorByCostTitle implements Comparator<Media> {

    /**
     * Compares two Media objects by cost and then by title.
     *
     * @param m1 the first media object
     * @param m2 the second media object
     * @return a negative integer, zero, or a positive integer as the first argument
     *         is less than, equal to, or greater than the second, based on sorting rules
     */
    @Override
    public int compare(Media m1, Media m2) {
        // Sort by cost in descending order
        int costCompare = Float.compare(m2.getCost(), m1.getCost());
        if (costCompare != 0) {
            return costCompare;
        }

        // If costs are equal, sort by title in ascending order (case-insensitive)
        return m1.getTitle().compareToIgnoreCase(m2.getTitle());
    }
}
