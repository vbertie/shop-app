package server.electronics.mail.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import server.electronics.order.dto.CustomerDto;
import server.electronics.order.dto.OrderDto;

public class MailFacade {

    public MailFacade(MailConstructor mailConstructor){
        this.mailConstructor = mailConstructor;
    }

    @Autowired
    private JavaMailSender mailSender;

    private MailConstructor mailConstructor;

    public void sendEmailToCustomer(OrderDto orderDto) {
        CustomerDto customerDto = orderDto.getCustomer();

        SimpleMailMessage email = mailConstructor.constructOrderEmail(customerDto);
        mailSender.send(email);
    }
}
