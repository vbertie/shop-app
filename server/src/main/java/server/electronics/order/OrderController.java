package server.electronics.order;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import server.electronics.order.domain.OrderFacade;
import server.electronics.order.dto.OrderDto;
import server.electronics.order.dto.ShowOrderDto;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
class OrderController {

    private OrderFacade orderFacade;

    @PostMapping("/process")
    @ResponseStatus(code = HttpStatus.OK)
    public void processOrder(@Validated @RequestBody OrderDto orderDto){
        orderFacade.processOrder(orderDto);
    }

    @GetMapping("/getAll")
    @ResponseStatus(code = HttpStatus.OK)
    public List<ShowOrderDto> getOrders(){
        return orderFacade.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ShowOrderDto showOrder(@PathVariable String id){
        return orderFacade.showOrder(Long.valueOf(id));
    }

    @DeleteMapping("/remove/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void removeOrder(@PathVariable Long id){
         orderFacade.removeOrder(id);
    }

    @PutMapping("/edit")
    @ResponseStatus(code = HttpStatus.OK)
    public void editOrder(@RequestBody OrderDto orderDto){
        orderFacade.actualizeOrder(orderDto);
    }
}
