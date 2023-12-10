package com.example.bookmyshowapplication.dtos;

import com.example.bookmyshowapplication.Models.ResponseStatus;

public class BookMovieResponseDto {

    private Long bookingId;
    private int amount;
    private ResponseStatus responseStatus;

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}
