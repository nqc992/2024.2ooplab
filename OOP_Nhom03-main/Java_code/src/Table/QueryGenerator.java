package Table;

// Giao diện định nghĩa khả năng tạo ra các truy vấn SQL cơ bản
public interface QueryGenerator {
    String selectAllQuery();
    String selectByIdQuery(); // Không còn tham số ID trực tiếp nữa
    String insertQuery();     // Không còn tham số nào nữa
    String updateQuery();     // Không còn tham số nào nữa
    String deleteByIdQuery(); // Không còn tham số ID trực tiếp nữa
    // Các query đặc trưng cần định nghĩa riêng trong từng class thực thể nếu chúng phức tạp
}