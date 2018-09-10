package server.electronics.order.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import server.electronics.order.dto.OrderDto;
import server.electronics.order.dto.ShowOrderDto;
import server.electronics.product.domain.ProductFacade;
import server.electronics.util.mapper.SuperConverter;

@Configuration
class OrderConfiguration {

    public OrderFacade orderFacade(ProductFacade productFacade){
        return orderFacade(productFacade ,new InMemoryCustomerRepository(),
                new InMemoryOrderRepository(), new InMemoryCityRepository());
    }

    @Bean
    public OrderFacade orderFacade(ProductFacade productFacade, CustomerRepository customerRepository,
                                   OrderRepository orderRepository, CityRepository cityRepository){
        SuperConverter<OrderDto, ShowOrderDto, Order> converter = new OrderConverter(customerRepository, cityRepository);
        return new OrderFacade(orderRepository, productFacade, converter);
    }

}
