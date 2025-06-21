package Table;

public class Card extends BaseEntity implements QueryGenerator {
    private String card_id;
    private String deck_id;
    private String front;
    private String back;

    public Card() {}

    // Getters and Setters
    public String getCard_id() { return card_id; }
    public void setCard_id(String card_id) { this.card_id = card_id; }
    public String getDeck_id() { return deck_id; }
    public void setDeck_id(String deck_id) { this.deck_id = deck_id; }
    public String getFront() { return front; }
    public void setFront(String front) { this.front = front; }
    public String getBack() { return back; }
    public void setBack(String back) { this.back = back; }

    @Override
    public String getTableName() {
        return "card"; // Đảm bảo khớp với tên bảng trong sơ đồ DB
    }

    @Override
    public String selectAllQuery() {
        // Liệt kê rõ ràng các cột thay vì SELECT *
        return "SELECT card_id, deck_id, front, back FROM " + getTableName();
    }

    @Override
    public String selectByIdQuery() {
        // Liệt kê rõ ràng các cột thay vì SELECT *
        return "SELECT card_id, deck_id, front, back FROM " + getTableName() + " WHERE card_id = '{}'";
    }

    @Override
    public String insertQuery() {
        return "INSERT INTO " + getTableName() +
               " (card_id, deck_id, front, back) VALUES ('{}', '{}', '{}', '{}')";
    }

    @Override
    public String updateQuery() {
        return "UPDATE " + getTableName() + " SET " +
               "deck_id = '{}', " +
               "front = '{}', " +
               "back = '{}' " +
               "WHERE card_id = '{}'";
    }

    @Override
    public String deleteByIdQuery() {
        return "DELETE FROM " + getTableName() + " WHERE card_id = '{}'";
    }

    // --- Bổ sung các query đặc trưng từ yêu cầu của bạn ---

    // Lấy các card_id có trong 1 deck_id
    public static String getCardIdsByDeckIdQuery() {
        return "SELECT card_id FROM card WHERE deck_id='{}'";
    }

    // Đếm số card trong deck
    public static String countCardsInDeckQuery() {
        // Sử dụng COUNT(card_id) thay vì COUNT(*)
        return "SELECT COUNT(card_id) FROM card WHERE deck_id = '{}'";
    }

    // Lấy toàn bộ card của user qua deck
    public static String getAllCardsByUserThroughDeckQuery() {
        // Liệt kê rõ ràng các cột từ bảng card thay vì c.*
        return "SELECT c.card_id, c.deck_id, c.front, c.back FROM card c JOIN deck d ON c.deck_id = d.deck_id WHERE d.author_id = '{}'";
    }

    // Lấy thông tin cơ bản của 1 card theo card_id
    public static String getCardBasicInfoByIdQuery() {
        // Query này đã liệt kê rõ ràng các cột front, back
        return "SELECT front, back FROM card WHERE card_id='{}'";
    }
}