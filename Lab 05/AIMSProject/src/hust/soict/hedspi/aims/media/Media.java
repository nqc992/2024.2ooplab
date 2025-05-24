package hust.soict.hedspi.aims.media;

import java.util.Objects;

/**
 * The Media class is an abstract base class representing common properties
 * and behaviors for all types of media items such as books, CDs, DVDs, etc.
 */
public abstract class Media implements Comparable<Media> {
    protected int id;
    protected String title;
    protected String category;
    protected float cost;

    /**
     * Full constructor with id, title, category, and cost.
     */
    public Media(int id, String title, String category, float cost) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    /**
     * Constructor with only title.
     */
    public Media(String title) {
        this.title = title;
    }

    /**
     * Constructor with title, category, and cost.
     */
    public Media(String title, String category, float cost) {
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    /**
     * Checks equality based on title and cost.
     * Uses safe comparisons for null and floating-point values.
     */
    @Override
    public boolean equals(Object obj) {
        // Step 1: Check if it's the same reference
        if (this == obj) {
            return true;
        }

        // Step 2: Check type and null
        if (!(obj instanceof Media)) {
            return false;
        }

        // Step 3: Cast and compare title and cost
        Media other = (Media) obj;
        return Objects.equals(this.title, other.title) &&
                Float.compare(this.cost, other.cost) == 0;
    }

    /**
     * Generates a hash code based on title and cost.
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, cost);
    }

    /**
     * Compares this media item with another based on title first, then cost.
     *
     * @param other the media object to compare with
     * @return a negative integer, zero, or a positive integer as this object
     *         is less than, equal to, or greater than the specified object
     * @throws NullPointerException if the other media is null
     */
    @Override
    public int compareTo(Media other) {
        if (other == null) {
            throw new NullPointerException("Cannot compare Media with a null object.");
        }

        // Compare by title
        int titleComparison = this.title.compareTo(other.title);
        if (titleComparison != 0) {
            return titleComparison;
        }

        // If titles are the same, compare by cost
        return Float.compare(this.cost, other.cost);
    }
}
