package hust.soict.hedspi.aims.media;

import java.util.Objects; // Cần thiết cho Objects.equals() và Objects.hash()

// Đảm bảo lớp Media implements Comparable<Media>
public abstract class Media implements Comparable<Media> {
    protected int id;
    protected String title;
    protected String category;
    protected float cost;

    // Constructors (giữ nguyên như hiện tại trong code của bạn)
    public Media(int id, String title, String category, float cost) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    public Media(String title) {
        this.title = title;
    }

    public Media(String title, String category, float cost) {
        this.title = title;
        this.category = category;
        this.cost = cost;
    }
    
    // Getters and setters (giữ nguyên như hiện tại trong code của bạn)
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

    // Phương thức equals() đã được sửa đổi
    @Override
    public boolean equals(Object obj) {
        // Bước 1: Kiểm tra xem đối tượng có phải là chính nó không
        if (this == obj) {
            return true;
        }

        // Bước 2: Kiểm tra null và kiểu dữ liệu
        // Sử dụng 'instanceof' để kiểm tra xem obj có phải là một thể hiện của Media
        // hoặc một lớp con của Media hay không.
        // Điều này giúp tránh ClassCastException nếu obj không phải là Media.
        // Đồng thời, nếu obj là null, instanceof sẽ trả về false, xử lý NullPointerException một cách ngầm.
        if (!(obj instanceof Media)) {
            return false;
        }

        // Bước 3: Ép kiểu đối tượng và so sánh các thuộc tính
        Media other = (Media) obj;

        // Hai media được coi là bằng nhau nếu có cùng title và cost
        // Sử dụng Objects.equals để so sánh title một cách an toàn (xử lý null)
        // Sử dụng Float.compare để so sánh float vì so sánh trực tiếp == có thể không chính xác do sai số dấu phẩy động
        return Objects.equals(this.title, other.title) &&
               Float.compare(this.cost, other.cost) == 0;
    }

    // Phương thức hashCode() cũng nên được override khi equals() được override
    @Override
    public int hashCode() {
        return Objects.hash(title, cost);
    }

    // Phương thức compareTo() đã được sửa đổi
    @Override
    public int compareTo(Media other) {
        // Hợp đồng của Comparable.compareTo() nói rằng nó nên ném NullPointerException
        // nếu đối tượng được chỉ định là null.
        if (other == null) {
            throw new NullPointerException("Cannot compare Media with a null object.");
        }

        // So sánh theo tiêu đề (title) trước
        int titleComparison = this.title.compareTo(other.title);
        if (titleComparison != 0) {
            return titleComparison; // Nếu tiêu đề khác nhau, trả về kết quả so sánh tiêu đề
        }

        // Nếu tiêu đề giống nhau, so sánh theo chi phí (cost)
        // Float.compare(float1, float2) trả về:
        // - số âm nếu float1 < float2
        // - 0 nếu float1 == float2
        // - số dương nếu float1 > float2
        return Float.compare(this.cost, other.cost);
    }
}