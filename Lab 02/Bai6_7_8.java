
public class Bai6_7_8 {
	package lab2_6;


	// bài 6
	public class DigitalVideoDisc {
		private String title;
		private String category;
		private String director;
		private int length;
		private float cost;
		
		// bài 7
		public String getTitle() {
			return title;
		}
		public String getCategory() {
			return category;
		}
		
		public String getDirector() {
			return director;
		}
		public int getLength() {
			return length;
		}
		public float getCost() {
			return cost;
		}
		
		
		
		// bài 8
		public DigitalVideoDisc(String title) {
			super();
			this.title = title;
		}
		public DigitalVideoDisc(String title, String category, float cost) {
			super();
			this.title = title;
			this.category = category;
			this.cost = cost;
		}
		public DigitalVideoDisc(String title, String category, String director, float cost) {
			super();
			this.title = title;
			this.category = category;
			this.director = director;
			this.cost = cost;
		}
		public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
			super();
			this.title = title;
			this.category = category;
			this.director = director;
			this.length = length;
			this.cost = cost;
		}
		
		
	}




}
