package com.example.bookmyshowapplication.dtos;

import java.util.List;

public class BookMovieRequestDto {
    private Long userId;
    private Long showId;
    private List<Long> showSeatId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public List<Long> getShowSeatId() {
        return showSeatId;
    }

    public void setShowSeatId(List<Long> showSeatId) {
        this.showSeatId = showSeatId;
    }
}
