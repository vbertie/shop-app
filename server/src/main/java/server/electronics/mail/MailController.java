package server.electronics.mail;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.electronics.mail.domain.MailFacade;
import server.electronics.order.dto.OrderDto;

@RestController
@AllArgsConstructor
@RequestMapping("mail")
class MailController {

    private MailFacade mailFacade;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void sendEmail(@RequestBody OrderDto orderDto){
        mailFacade.sendEmailToCustomer(orderDto);
    }
}
