package hust.soict.hedspi.aims.media;

import java.util.Comparator;

public abstract class Media {
	private int id;
	private String title;
	private String category;
	private float cost;
	
	 public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
	 public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();
	 
	public int getId() {
		return id;
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
	
	public Media(int id, String title, String category, float cost) {
	    this.id = id;
	    this.title = title;
	    this.category = category;
	    this.cost = cost;
	}
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true; // cùng địa chỉ bộ nhớ
	    if (!(o instanceof Media)) return false; // không phải cùng kiểu

	    Media other = (Media) o;
	    return this.getTitle() != null && this.getTitle().equals(other.getTitle());
	}
	@Override
	public String toString() {
	    return "Media - Title: " + title + " - Category: " + category + " - Cost: $" + cost;
	}

	
}
