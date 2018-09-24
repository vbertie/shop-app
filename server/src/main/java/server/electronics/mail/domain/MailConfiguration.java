package server.electronics.mail.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
class MailConfiguration {

    @Bean
    public MailFacade mailFacade(){
        MailConstructor mailConstructor = new MailConstructor();
        return new MailFacade(mailConstructor);
    }
}
