package com.example.bookmyshowapplication.Services.PaymentService;

import com.razorpay.RazorpayException;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    public String getPaymentLink(Long id) throws RazorpayException;
}
