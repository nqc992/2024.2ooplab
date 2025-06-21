package Table;

// Không cần import java.time.LocalDate nếu bạn chỉ muốn String cho các trường ngày
// Nếu bạn muốn dùng LocalDate cho review_date, bạn có thể import và khai báo kiểu LocalDate
// import java.time.LocalDate;

public class LearningProgress extends BaseEntity implements QueryGenerator {
    private String user_id;
    private String card_id;
    private Integer confident_bigint; // Thay đổi từ Double sang Integer theo sơ đồ DB
    private String review_date; // Sử dụng String để khớp với cách bạn thường xử lý date trong query template

    public LearningProgress() {}

    // Getters and Setters
    public String getUser_id() { return user_id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }
    public String getCard_id() { return card_id; }
    public void setCard_id(String card_id) { this.card_id = card_id; }
    public Integer getConfident_bigint() { return confident_bigint; }
    public void setConfident_bigint(Integer confident_bigint) { this.confident_bigint = confident_bigint; }
    public String getReview_date() { return review_date; }
    public void setReview_date(String review_date) { this.review_date = review_date; }

    @Override
    public String getTableName() {
        return "learning_progress";
    }

    @Override
    public String selectAllQuery() {
        // Liệt kê rõ ràng các cột theo sơ đồ DB
        return "SELECT user_id, card_id, confident_bigint, review_date FROM " + getTableName();
    }

    @Override
    public String selectByIdQuery() {
        // Giữ nguyên UnsupportedOperationException vì đây là khóa chính composite
        throw new UnsupportedOperationException("selectByIdQuery for LearningProgress requires both user_id and card_id. Use getLearningProgressForCardQuery().");
    }

    @Override
    public String insertQuery() {
        // Chỉ bao gồm các cột có trong sơ đồ DB
        return "INSERT INTO " + getTableName() +
               " (user_id, card_id, confident_bigint, review_date) VALUES ('{}', '{}', {}, '{}')";
    }

    @Override
    public String updateQuery() {
        // Chỉ bao gồm các cột có trong sơ đồ DB
        return "UPDATE " + getTableName() + " SET " +
               "confident_bigint = {}, " +
               "review_date = '{}' " +
               "WHERE user_id = '{}' AND card_id = '{}'";
    }

    @Override
    public String deleteByIdQuery() {
        // Giữ nguyên UnsupportedOperationException vì đây là khóa chính composite
        throw new UnsupportedOperationException("deleteByIdQuery for LearningProgress requires both user_id and card_id. Use deleteByCompositeIdQuery().");
    }

    public static String deleteByCompositeIdQuery() {
        return "DELETE FROM learning_progress WHERE user_id = '{}' AND card_id = '{}'";
    }

    // --- Các query đặc trưng theo yêu cầu ---

    // Lấy tiến trình học của user với 1 card
    public static String getLearningProgressForCardQuery() {
        // Liệt kê rõ ràng các cột theo sơ đồ DB
        return "SELECT user_id, card_id, confident_bigint, review_date FROM learning_progress WHERE user_id = '{}' AND card_id = '{}'";
    }

    // Lấy các card cần ôn trong ngày
    public static String getCardsToReviewTodayQuery() {
        // Liệt kê rõ ràng các cột từ learning_progress và các bảng join
        // Sử dụng 'review_date' như trong sơ đồ DB
        return "SELECT lp.user_id, lp.card_id, lp.confident_bigint, lp.review_date, c.front, c.back, d.deck_name " +
               "FROM learning_progress lp " +
               "JOIN card c ON lp.card_id = c.card_id " +
               "JOIN deck d ON c.deck_id = d.deck_id " +
               "WHERE lp.user_id = '{}' AND lp.review_date <= CURRENT_DATE";
    }

    // Lấy các card cần ôn trong 7 ngày tới
    public static String getCardsToReviewNext7DaysQuery() {
        // Liệt kê rõ ràng các cột từ learning_progress và các bảng join
        // Sử dụng 'review_date' như trong sơ đồ DB
        return "SELECT lp.user_id, lp.card_id, lp.confident_bigint, lp.review_date, c.front, c.back, d.deck_name " +
               "FROM learning_progress lp " +
               "JOIN card c ON lp.card_id = c.card_id " +
               "JOIN deck d ON c.deck_id = d.deck_id " +
               "WHERE lp.user_id = '{}' AND lp.review_date <= (CURRENT_DATE + interval '7 days')";
    }

    // Đếm số lượng card đã học
    public static String countLearnedCardsQuery() {
        // Sử dụng COUNT(card_id) để đếm số lượng bản ghi
        return "SELECT COUNT(card_id) FROM learning_progress WHERE user_id = '{}'";
    }
}