package model;

public interface RankStrategy {
    String getRank(double averageScore);
}

class DefaultRankStrategy implements RankStrategy {
    @Override
    public String getRank(double averageScore) {
        if (averageScore >= 9.0) return "Xuất sắc";
        if (averageScore >= 8.0) return "Giỏi";
        if (averageScore >= 6.5) return "Khá";
        if (averageScore >= 5.0) return "Trung bình";
        return "Yếu";
    }
}
