package ProtofolioWebpage.example.ProtofolioWebpage.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/sendEmail")
    public String sendEmail(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String message) {
try {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setTo("ananyahsananya@gmail.com"); 
    mailMessage.setFrom(email); 
    mailMessage.setReplyTo(email); 
    mailMessage.setSubject("New message from " + name);

    String fullMessage = "Name: " + name + "\n"
                       + "Email: " + email + "\n\n"
                       + "Message:\n" + message;

    mailMessage.setText(fullMessage);

    mailSender.send(mailMessage);
    return "\tEmail sent successfully from " + email+"Thank you for visting";

} catch (MailException e) {
    return "Error sending email: " + e.getMessage();
}

    }
}
