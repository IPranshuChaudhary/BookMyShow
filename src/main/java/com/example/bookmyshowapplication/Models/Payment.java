package com.example.bookmyshowapplication.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Payment extends BaseModel{

    private int amount;

    @Enumerated(EnumType.ORDINAL)
    private PaymentMethod paymentMethod;
    private int referenceNumber;
    private Date timeStamp;

    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.ORDINAL)
    private PaymentProvider paymentProvider;

}
