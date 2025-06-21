package Table;

public class DeckSetting extends BaseEntity implements QueryGenerator {
    private String user_id;
    private String deck_id;
    private Integer card_per_day;

    public DeckSetting() {}

    // Getters and Setters
    public String getUser_id() { return user_id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }
    public String getDeck_id() { return deck_id; }
    public void setDeck_id(String deck_id) { this.deck_id = deck_id; }
    public Integer getCard_per_day() { return card_per_day; }
    public void setCard_per_day(Integer card_per_day) { this.card_per_day = card_per_day; }

    @Override
    public String getTableName() {
        return "deck_setting"; // Đảm bảo khớp với tên bảng trong sơ đồ DB
    }

    @Override
    public String selectAllQuery() {
        // Liệt kê rõ ràng các cột thay vì SELECT *
        return "SELECT user_id, deck_id, card_per_day FROM " + getTableName();
    }

    @Override
    public String selectByIdQuery() {
        // Giữ nguyên UnsupportedOperationException vì đây là khóa chính composite
        throw new UnsupportedOperationException("selectByIdQuery for DeckSetting requires both user_id and deck_id. Use getCardsPerDayQuery() or a custom method for composite ID lookup.");
    }

    @Override
    public String insertQuery() {
        return "INSERT INTO " + getTableName() +
               " (user_id, deck_id, card_per_day) VALUES ('{}', '{}', {})";
    }

    @Override
    public String updateQuery() {
        return "UPDATE " + getTableName() + " SET " +
               "card_per_day = {} " +
               "WHERE user_id = '{}' AND deck_id = '{}'";
    }

    @Override
    public String deleteByIdQuery() {
        // Giữ nguyên UnsupportedOperationException vì đây là khóa chính composite
        throw new UnsupportedOperationException("deleteByIdQuery for DeckSetting requires both user_id and deck_id. Use deleteByCompositeIdQuery().");
    }

    public static String deleteByCompositeIdQuery() {
        return "DELETE FROM deck_setting WHERE user_id = '{}' AND deck_id = '{}'";
    }

    // --- Bổ sung các query đặc trưng từ yêu cầu của bạn ---

    // Lấy số từ học mỗi ngày của user cho một deck cụ thể (đã có đủ cột)
    public static String getCardsPerDayQuery() {
        return "SELECT card_per_day FROM deck_setting WHERE user_id = '{}' AND deck_id = '{}'";
    }

    // Lấy tốc độ học của user
    // Query này trùng với getCardsPerDayQuery(). Đặt tên rõ ràng hơn nếu có ý nghĩa khác.
    // Nếu nó chỉ là một alias, thì vẫn dùng cùng một query.
    public static String getLearningSpeedQuery() {
        return "SELECT card_per_day FROM deck_setting WHERE user_id = '{}' AND deck_id = '{}'";
    }
}