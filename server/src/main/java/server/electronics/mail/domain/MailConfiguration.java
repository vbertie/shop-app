package server.electronics.mail.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
class MailConfiguration {

    @Bean
    public MailFacade mailFacade(){
        MailConstructor mailConstructor = new MailConstructor();
//        JavaMailSender javaMailSender = new JavaMailSenderImpl();
        return new MailFacade(mailConstructor);
    }
}
