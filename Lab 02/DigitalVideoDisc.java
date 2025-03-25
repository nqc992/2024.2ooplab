public class DigitalVideoDisc {
    // Các thuộc tính
    private String title;
    private String category;
    private String director;
    private int length;
    private float cost;

    // Constructor mặc định
    public DigitalVideoDisc() {
    }

    // Constructor với tiêu đề
    public DigitalVideoDisc(String title) {
        this.title = title;
    }

    // Constructor với tiêu đề, thể loại và giá
    public DigitalVideoDisc(String title, String category, float cost) {
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    // Constructor với tiêu đề, thể loại, đạo diễn và giá
    public DigitalVideoDisc(String title, String category, String director, float cost) {
        this.title = title;
        this.category = category;
        this.director = director;
        this.cost = cost;
    }

    // Constructor với tất cả thuộc tính
    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        this.title = title;
        this.category = category;
        this.director = director;
        this.length = length;
        this.cost = cost;
    }

    // Các phương thức getter
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
}
