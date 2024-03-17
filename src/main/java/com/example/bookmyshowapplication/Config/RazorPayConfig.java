package com.example.bookmyshowapplication.Config;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorPayConfig {

    private RazorpayClient razorpayClient;

    @Bean
    public RazorpayClient getRazorPayClient() throws RazorpayException {
        if (razorpayClient == null){
            razorpayClient = new RazorpayClient(
                    "rzp_test_0R3H25ehKgZRb4","l8CINcgUdeu6zbIEMwV03cb8");
        }
        return razorpayClient;
    }
}
