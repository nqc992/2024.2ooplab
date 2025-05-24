// File: hust/soict/[globalict||dsai]/aims/exception/PlayerException.java
// (Thay thế [globalict||dsai] bằng tên package thực tế của bạn, ví dụ: hust.soict.hedspi.aims.exception)

package hust.soict.hedspi.aims.exception; // Ví dụ: package hust.soict.hedspi.aims.exception

public class PlayerException extends Exception {

    // Constructor mặc định
    public PlayerException() {
        super();
    }

    // Constructor với một thông điệp lỗi
    public PlayerException(String message) {
        super(message);
    }

    // Constructor với một thông điệp lỗi và một nguyên nhân gốc (cause)
    public PlayerException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor với một nguyên nhân gốc (cause)
    public PlayerException(Throwable cause) {
        super(cause);
    }
}