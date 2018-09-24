package server.electronics.mail.domain;

import org.springframework.mail.SimpleMailMessage;
import server.electronics.order.dto.CustomerDto;

class MailConstructor {

     public SimpleMailMessage constructOrderEmail(CustomerDto customerDto) {
         String message="\nElectronics\n\n" + customerDto.getFirstName() + ", thank you for making shopping in our shop.\nYour order will be placed in next three working days.\n\nBest Regards,\nElectronics";

         SimpleMailMessage email = new SimpleMailMessage();
         email.setTo(customerDto.getEmail());
         email.setSubject("Electronics - Your Order");
         email.setText(message);
         email.setFrom("email1sender12@gmail.com");

         return email;
    }
}
