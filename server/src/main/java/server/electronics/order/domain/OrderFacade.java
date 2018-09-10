package server.electronics.order.domain;

import lombok.AllArgsConstructor;
import server.electronics.order.dto.OrderDto;
import server.electronics.order.dto.ShowOrderDto;
import server.electronics.order.exceptions.OrderException;
import server.electronics.product.domain.ProductFacade;
import server.electronics.product.domain.dto.product.ProductDto;
import server.electronics.util.mapper.SuperConverter;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Transactional
@AllArgsConstructor
public class OrderFacade {

    private OrderRepository orderRepository;
    private ProductFacade productFacade;
    private SuperConverter<OrderDto,ShowOrderDto, Order> superConverter;

    public void processOrder(OrderDto orderDto){
        Order order = superConverter.convert(orderDto);
        orderRepository.save(order);
        productFacade.subtractInStockNumber(orderDto.getCart());
    }

    public void actualizeOrder(OrderDto orderDto){
        Order existingOrder = orderRepository.findOrderById(orderDto.getId())
                .orElseThrow(() -> new OrderException("Order with id "+ orderDto.getId() + " not found"));

        Order order = superConverter.update(orderDto, existingOrder);
        orderRepository.save(order);
    }

    public ShowOrderDto showOrder(Long id){
        Order order = orderRepository.findOrderById(id)
                .orElseThrow(() -> new OrderException("Order with id "+ id + " not found"));

        Set<ProductDto> productDtos = order.getOrderItems()
                .stream()
                .map(orderItem -> productFacade.showProduct(orderItem.getProductId()))
                .collect(Collectors.toSet());

        return OrderConverter.convertToDto(order, productDtos);
    }

    public List<ShowOrderDto> findAll(){
        return orderRepository.findAll()
                .stream()
                .map(superConverter::convertToDto)
                .collect(Collectors.toList());
    }

    public void removeOrder(Long id){
        orderRepository.deleteById(id);
    }

}
