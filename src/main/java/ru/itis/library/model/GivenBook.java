package ru.itis.library.model;

import java.time.Instant;

public class GivenBook {
    private int userId;
    private int bookId;
    private Instant createdDate;

    public GivenBook(int userId, int bookId, Instant createdDate) {
        this.userId = userId;
        this.bookId = bookId;
        this.createdDate = createdDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "GivenBook, userId: " + userId + " bookId: " + bookId + " given " + createdDate.toString();
    }
}
