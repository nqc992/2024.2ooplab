package Table;

public class UserAccount extends BaseEntity implements QueryGenerator {
    // Thuộc tính để làm mô hình dữ liệu (Data Transfer Object - DTO)
    private String user_id;
    private String type;
    private String theme;
    private String password;
    private String user_name;
    private String email;

    public UserAccount() {}

    // Getters and Setters
    public String getUser_id() { return user_id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getTheme() { return theme; }
    public void setTheme(String theme) { this.theme = theme; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getUser_name() { return user_name; }
    public void setUser_name(String user_name) { this.user_name = user_name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String getTableName() {
        return "user"; // Đã thay đổi từ "user_account" thành "user" để khớp với sơ đồ DB
    }

    @Override
    public String selectAllQuery() {
        // Liệt kê rõ ràng các cột thay vì SELECT *
        return "SELECT user_id, type, theme, password, user_name, email FROM " + getTableName();
    }

    @Override
    public String selectByIdQuery() {
        // Liệt kê rõ ràng các cột thay vì SELECT *
        return "SELECT user_id, type, theme, password, user_name, email FROM " + getTableName() + " WHERE user_id = '{}'";
    }

    @Override
    public String insertQuery() {
        return "INSERT INTO " + getTableName() +
               " (user_id, type, theme, password, user_name, email) VALUES ('{}', '{}', '{}', '{}', '{}', '{}')";
    }

    @Override
    public String updateQuery() {
        return "UPDATE " + getTableName() + " SET " +
               "type = '{}', " +
               "theme = '{}', " +
               "password = '{}', " +
               "user_name = '{}', " +
               "email = '{}' " +
               "WHERE user_id = '{}'";
    }

    @Override
    public String deleteByIdQuery() {
        return "DELETE FROM " + getTableName() + " WHERE user_id = '{}'";
    }




    public static String getPasswordByEmailQuery() {
        return "SELECT password FROM \"user\" WHERE email='{}'"; // Sử dụng dấu ngoặc kép cho tên bảng "user" nếu nó là từ khóa trong SQL
    }

    // Lấy user_id từ email
    public static String getUserIdByEmailQuery() {
        return "SELECT user_id FROM \"user\" WHERE email='{}'";
    }

    // Lấy user_name từ user_id
    public static String getUserNameByIdQuery() {
        return "SELECT user_name FROM \"user\" WHERE user_id='{}'";
    }

    // Lấy theme của user
    public static String getThemeByIdQuery() {
        return "SELECT theme FROM \"user\" WHERE user_id = '{}'";
    }
}