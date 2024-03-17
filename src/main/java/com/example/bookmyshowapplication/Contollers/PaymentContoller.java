package com.example.bookmyshowapplication.Contollers;

import com.example.bookmyshowapplication.Services.PaymentService.PaymentService;
import com.example.bookmyshowapplication.dtos.PaymentRequestDto;
import com.razorpay.RazorpayException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bms")
public class PaymentContoller {
    private PaymentService paymentService;

    PaymentContoller(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping("/payment")
    public String makePayment(@RequestBody PaymentRequestDto paymentRequestDto
    ) throws RazorpayException {

        return paymentService.getPaymentLink(paymentRequestDto.getBookingId());
    }
}
