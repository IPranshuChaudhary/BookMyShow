//import com.stripe.Stripe;
//import com.stripe.exception.StripeException;
//import com.stripe.model.PaymentLink;
//import org.json.JSONObject;
//
//public class StripePayment {
//
//    public static void main(String[] args) throws StripeException {
//        Stripe.apiKey = "sk_test_YourSecretKey"; // Set your Stripe secret key here
//
//        // Create a JSON object for the payment link request
//        JSONObject paymentLinkRequest = new JSONObject();
//        paymentLinkRequest.put("amount", 1000); // Amount in smallest currency unit (e.g., cents for INR)
//        paymentLinkRequest.put("currency", "INR");
//        paymentLinkRequest.put("accept_partial", true);
//        paymentLinkRequest.put("first_min_partial_amount", 100);
//        paymentLinkRequest.put("expire_by", 1691097057); // Unix timestamp representing expiration time
//        paymentLinkRequest.put("reference_id", "TS1989");
//        paymentLinkRequest.put("description", "Payment for policy no #23456");
//
//        // Create a JSON object for the customer information
//        JSONObject customer = new JSONObject();
//        customer.put("name", "+919999999999");
//        customer.put("contact", "Gaurav Kumar");
//        customer.put("email", "gaurav.kumar@example.com");
//        paymentLinkRequest.put("customer", customer);
//
//        // Create a JSON object for notification preferences
//        JSONObject notify = new JSONObject();
//        notify.put("sms", true);
//        notify.put("email", true);
//        paymentLinkRequest.put("reminder_enable", true);
//
//        // Create a JSON object for additional notes
//        JSONObject notes = new JSONObject();
//        notes.put("policy_name", "Jeevan Bima");
//        paymentLinkRequest.put("notes", notes);
//
//        paymentLinkRequest.put("callback_url", "https://example-callback-url.com/");
//        paymentLinkRequest.put("callback_method", "get");
//
//        // Create the payment link using the Stripe API
//        PaymentLink payment = PaymentLink.create(paymentLinkRequest);
//
//        System.out.println("Payment link URL: " + payment.getUrl());
//    }
//}
