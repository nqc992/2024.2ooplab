package Table;

public class Deck extends BaseEntity implements QueryGenerator {
    private String deck_id;
    private String author_id;
    private String deck_name;
    private Boolean isPublic; // Đổi tên thuộc tính từ 'public' thành 'isPublic' cho rõ ràng hơn trong Java
    private String front_lang;
    private String back_lang;
    private Integer daily_review_limit;

    public Deck() {}

    // Getters and Setters
    public String getDeck_id() { return deck_id; }
    public void setDeck_id(String deck_id) { this.deck_id = deck_id; }
    public String getAuthor_id() { return author_id; }
    public void setAuthor_id(String author_id) { this.author_id = author_id; }
    public String getDeck_name() { return deck_name; }
    public void setDeck_name(String deck_name) { this.deck_name = deck_name; }
    public Boolean getIsPublic() { return isPublic; } // Getter cho isPublic
    public void setIsPublic(Boolean isPublic) { this.isPublic = isPublic; } // Setter cho isPublic
    public String getFront_lang() { return front_lang; }
    public void setFront_lang(String front_lang) { this.front_lang = front_lang; }
    public String getBack_lang() { return back_lang; }
    public void setBack_lang(String back_lang) { this.back_lang = back_lang; }
    public Integer getDaily_review_limit() { return daily_review_limit; }
    public void setDaily_review_limit(Integer daily_review_limit) { this.daily_review_limit = daily_review_limit; }

    @Override
    public String getTableName() {
        return "deck"; // Đảm bảo khớp với tên bảng trong sơ đồ DB
    }

    @Override
    public String selectAllQuery() {
        // Liệt kê rõ ràng các cột thay vì SELECT *
        return "SELECT deck_id, author_id, deck_name, public, front_lang, back_lang, daily_review_limit FROM " + getTableName();
    }

    @Override
    public String selectByIdQuery() {
        // Liệt kê rõ ràng các cột thay vì SELECT *
        return "SELECT deck_id, author_id, deck_name, public, front_lang, back_lang, daily_review_limit FROM " + getTableName() + " WHERE deck_id = '{}'";
    }

    @Override
    public String insertQuery() {
        // Cột 'public' trong DB tương ứng với 'isPublic' trong Java
        return "INSERT INTO " + getTableName() +
               " (deck_id, author_id, deck_name, public, front_lang, back_lang, daily_review_limit) VALUES ('{}', '{}', '{}', {}, '{}', '{}', {})";
    }

    @Override
    public String updateQuery() {
        // Cột 'public' trong DB tương ứng với 'isPublic' trong Java
        return "UPDATE " + getTableName() + " SET " +
               "author_id = '{}', " +
               "deck_name = '{}', " +
               "public = {}, " +
               "front_lang = '{}', " +
               "back_lang = '{}', " +
               "daily_review_limit = {} " +
               "WHERE deck_id = '{}'";
    }

    @Override
    public String deleteByIdQuery() {
        return "DELETE FROM " + getTableName() + " WHERE deck_id = '{}'";
    }

    // --- Bổ sung các query đặc trưng từ yêu cầu của bạn ---

    // Lấy các deck_id mà user_id có thể sử dụng (đã có đủ cột)
    public static String getAvailableDeckIdsForUserQuery() {
        return "SELECT deck_id, deck_name, author_id, public, back_lang FROM deck WHERE author_id='{}' OR public=true";
    }

    // Lấy deck theo author (đã sửa SELECT *)
    public static String getDecksByAuthorQuery() {
        return "SELECT deck_id, author_id, deck_name, public, front_lang, back_lang, daily_review_limit FROM deck WHERE author_id = '{}'";
    }

    // Lấy tên và ngôn ngữ deck (đã có đủ cột)
    public static String getDeckNameAndLanguageByIdQuery() {
        return "SELECT deck_name, back_lang FROM deck WHERE deck_id = '{}'";
    }

    // Lấy thông tin cơ bản của 1 deck theo deck_id (đã có đủ cột)
    public static String getDeckBasicInfoByIdQuery() {
        return "SELECT deck_name, author_id, public, back_lang FROM deck WHERE deck_id='{}'";
    }
}