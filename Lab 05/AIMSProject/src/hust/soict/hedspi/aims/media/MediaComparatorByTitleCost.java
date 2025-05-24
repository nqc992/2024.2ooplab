package hust.soict.hedspi.aims.media;

import java.util.Comparator;

/**
 * A comparator for Media objects that sorts primarily by title in ascending
 * lexicographical order (case-insensitive), and by cost in descending order
 * if titles are equal.
 */
public class MediaComparatorByTitleCost implements Comparator<Media> {

    /**
     * Compares two Media objects by title and then by cost.
     *
     * @param m1 the first media object
     * @param m2 the second media object
     * @return a negative integer, zero, or a positive integer as the first argument
     *         is less than, equal to, or greater than the second, based on sorting rules
     */
    @Override
    public int compare(Media m1, Media m2) {
        // Sort by title in ascending order (case-insensitive)
        int titleCompare = m1.getTitle().compareToIgnoreCase(m2.getTitle());
        if (titleCompare != 0) {
            return titleCompare;
        }

        // If titles are equal, sort by cost in descending order
        return Float.compare(m2.getCost(), m1.getCost());
    }
}
