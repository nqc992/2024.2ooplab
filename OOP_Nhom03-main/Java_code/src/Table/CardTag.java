package Table;

public class CardTag extends BaseEntity implements QueryGenerator {
    private String card_id;
    private String tag_id;

    public CardTag() {} // Constructor rỗng

    // Getters and Setters
    public String getCard_id() { return card_id; }
    public void setCard_id(String card_id) { this.card_id = card_id; }
    public String getTag_id() { return tag_id; }
    public void setTag_id(String tag_id) { this.tag_id = tag_id; }

    @Override
    public String getTableName() {
        return "card_tag"; // Đảm bảo khớp với tên bảng trong sơ đồ DB
    }

    // QueryGenerator implementations (trả về với {})
    @Override
    public String selectAllQuery() {
        // Liệt kê rõ ràng các cột thay vì SELECT *
        return "SELECT card_id, tag_id FROM " + getTableName();
    }

    @Override
    public String selectByIdQuery() {
        // Giữ nguyên UnsupportedOperationException vì đây là khóa chính composite
        throw new UnsupportedOperationException("selectByIdQuery for CardTag requires both card_id and tag_id. Use getCardTagsByCardIdQuery() or a custom method for composite ID lookup.");
    }

    @Override
    public String insertQuery() {
        return "INSERT INTO " + getTableName() +
               " (card_id, tag_id) VALUES ('{}', '{}')";
    }

    @Override
    public String updateQuery() {
        // Giữ nguyên UnsupportedOperationException vì bảng nối thường không update trực tiếp
        throw new UnsupportedOperationException("CardTag update is usually not applicable for join tables.");
    }

    @Override
    public String deleteByIdQuery() {
        // Giữ nguyên UnsupportedOperationException vì đây là khóa chính composite
        throw new UnsupportedOperationException("deleteByIdQuery for CardTag requires both card_id and tag_id. Use deleteByCompositeIdQuery().");
    }

    // Phương thức static để xóa dựa trên khóa composite
    public static String deleteByCompositeIdQuery() {
        return "DELETE FROM card_tag WHERE card_id = '{}' AND tag_id = '{}'";
    }

    // --- Bổ sung thêm query để lấy các tag theo card_id ---
    // Query này đã được đề cập trong phần "Tag Management" của các query chung
    public static String getTagsByCardIdQuery() {
        return "SELECT tag_id FROM card_tag WHERE card_id = '{}'";
    }
}