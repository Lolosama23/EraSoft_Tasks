package com.lo2ay.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String details;
    private LocalDateTime timestamp;

    public Transaction(String details) {
        this.details = details;
        this.timestamp = LocalDateTime.now(); // يسجل الوقت الحالي تلقائياً
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        // تنسيق الوقت عند الطباعة
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "[" + timestamp.format(formatter) + "] " + details;
    }
}
