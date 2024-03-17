package com.example.bookmyshowapplication.Services.PaymentService;

import com.example.bookmyshowapplication.Config.RazorPayConfig;
import com.example.bookmyshowapplication.Models.Booking;
import com.example.bookmyshowapplication.Models.User;
import com.example.bookmyshowapplication.Repository.BookingRepository;
import com.example.bookmyshowapplication.Repository.UserRepository;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Primary
public class RazorPayPayment implements PaymentService{

    private RazorPayConfig razorPayConfig;
    private BookingRepository bookingRepository;
    private UserRepository userRepository;

    RazorPayPayment(RazorPayConfig razorPayConfig, BookingRepository bookingRepository,
                    UserRepository userRepository){
        this.bookingRepository = bookingRepository;
        this.razorPayConfig = razorPayConfig;
        this.userRepository = userRepository;
    }

    public String getPaymentLink(Long id) throws JSONException, RazorpayException {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isEmpty()) throw new RuntimeException();

        Booking booking = optionalBooking.get();

        Optional<User> optionalUser = userRepository.findById(booking.getUser().getId());
        if (optionalUser.isEmpty()) throw new RuntimeException();

        User user = optionalUser.get();

        int amount = booking.getPrice() * 100;
//        System.out.println(amount);
        String stringId = Long.toString(id);

        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount", amount);
        paymentLinkRequest.put("currency", "INR");
        paymentLinkRequest.put("accept_partial", true);
        paymentLinkRequest.put("first_min_partial_amount", 100);
        paymentLinkRequest.put("expire_by", System.currentTimeMillis() + (15 * 60 * 1000));
        paymentLinkRequest.put("reference_id", stringId);
        paymentLinkRequest.put("description", "Payment for policy no #23456");
        JSONObject customer = new JSONObject();
        customer.put("name", user.getName());
//        customer.put("contact", "+919999999999");
        customer.put("email", user.getEmail());
        paymentLinkRequest.put("customer", customer);
        JSONObject notify = new JSONObject();
        notify.put("sms", false);
        notify.put("email", true);
        paymentLinkRequest.put("reminder_enable", true);
        JSONObject notes = new JSONObject();
        notes.put("policy_name", "Jeevan Bima");
        paymentLinkRequest.put("notes", notes);
        paymentLinkRequest.put("callback_url", "http://localhost:8080/bms/success");
        paymentLinkRequest.put("callback_method", "get");

        PaymentLink payment = razorPayConfig.getRazorPayClient().paymentLink.create(paymentLinkRequest);

        return payment.get("short_url").toString();
    }
}
