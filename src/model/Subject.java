package model;

public enum Subject {
    TOAN("Toán"),
    VAN("Văn"),
    ANH("Anh"),
    HOA("Hóa"),
    SINH("Sinh"),
    SU("Sử"),
    DIA("Địa");

    private final String displayName;

    Subject(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    /**
     * Chuyển chuỗi thành enum Subject (hỗ trợ viết thường/hoa và tên hiển thị)
     */
    public static Subject fromString(String value) {
        for (Subject subject : values()) {
            if (subject.name().equalsIgnoreCase(value) || subject.getDisplayName().equalsIgnoreCase(value)) {
                return subject;
            }
        }
        throw new IllegalArgumentException("Không tìm thấy môn học tương ứng: " + value);
    }
}
