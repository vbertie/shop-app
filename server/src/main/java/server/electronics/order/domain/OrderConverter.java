package server.electronics.order.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import server.electronics.cart.domain.dto.CartDto;
import server.electronics.cart.domain.dto.CartItemDto;
import server.electronics.order.dto.CustomerDto;
import server.electronics.order.dto.OrderDto;
import server.electronics.order.dto.OrderItemDto;
import server.electronics.order.dto.ShowOrderDto;
import server.electronics.product.domain.dto.product.ProductDto;
import server.electronics.util.mapper.SuperConverter;

import javax.persistence.NoResultException;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static server.electronics.util.mapper.SuperConverter.setIfNonNull;

@NoArgsConstructor
@AllArgsConstructor
class OrderConverter implements SuperConverter<OrderDto,ShowOrderDto, Order> {

    private CustomerRepository customerRepository;

    @Override
    public Order convert(@NotNull OrderDto dto) {
        return update(dto, new Order());
    }

    @Override
    public Collection<Order> convert(Collection<OrderDto> result) {
        return Collections.emptyList();
    }

    @Override
    public Order update(@NotNull OrderDto updater, @NotNull Order result) {
        setIfNonNull(updater.getId(), result::setId);
        setIfNonNull(updater.getIsPaid(), result::setPaid);
        setIfNonNull(updater.getIsDelivered(), result::setDelivered);

        CartDto cartDto = updater.getCart();

        if(cartDto != null) {
            setIfNonNull(convertOrderItems(cartDto), result::setOrderItems);
            setIfNonNull(cartDto.getGrandTotal(), result::setOrderTotal);
        }

        CustomerDto customerDataDto = updater.getCustomer();
        setIfNonNull(convertCustomerData(customerDataDto), result::setCustomer);
        setIfNonNull(convertDeliveryAddress(customerDataDto), result::setDeliveryAddress);


        customerRepository.save(result.getCustomer());

        return result;
    }

    @Override
    public ShowOrderDto convertToDto(Order order) {
        return ShowOrderDto.builder()
                .id(order.getId())
                .isPaid(order.isPaid())
                .isDelivered(order.isDelivered())
                .customer(createCustomerDataDto(order))
                .build();
    }

    public static ShowOrderDto convertToDto(Order order, Set<ProductDto> productDtos) {
        return ShowOrderDto.builder()
                .id(order.getId())
                .isPaid(order.isPaid())
                .grandTotal(order.getOrderTotal())
                .isDelivered(order.isDelivered())
                .customer(createCustomerDataDto(order))
                .items(createItemsDto(order, productDtos))
                .build();
    }

    private static CustomerDto createCustomerDataDto(Order order) {
        Customer customer = order.getCustomer();

        return CustomerDto.builder()
                .email(customer.getEmail())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phoneNumber(customer.getPhoneNumber())
                .zipCode(order.getDeliveryAddress().getZipCode())
                .town(order.getDeliveryAddress().getCity().getTown())
                .street(order.getDeliveryAddress().getStreet())
                .province(order.getDeliveryAddress()
                        .getCity().getProvince().toString())
                .build();
    }

    private static Set<OrderItemDto> createItemsDto(Order order, Set<ProductDto> productDtos) {
        return order.getOrderItems()
                .stream()
                .map(orderItem -> {
                    return OrderItemDto.builder()
                            .productId(orderItem.getProductId())
                            .quantity(orderItem.getQuantity())
                            .product(productDtos
                                    .stream()
                                    .filter(productDto -> productDto.getId() == orderItem.getProductId())
                                    .findAny().orElseThrow(() -> new NoResultException("Product not found")))
                            .build();
                }).collect(Collectors.toSet());
    }


    private Customer convertCustomerData(CustomerDto updater){
        Customer customer = new Customer();

        setIfNonNull(updater.getEmail(), customer::setEmail);
        setIfNonNull(updater.getId(), customer::setId);
        setIfNonNull(updater.getFirstName(), customer::setFirstName);
        setIfNonNull(updater.getLastName(), customer::setLastName);
        setIfNonNull(updater.getPhoneNumber(), customer::setPhoneNumber);

        return customer;
    }

    private Set<OrderItem> convertOrderItems(CartDto cartDto){
        return cartDto.getCartItems()
                .values()
                .stream()
                .map(this::convertCartItemToOrderItem)
                .collect(Collectors.toSet());
    }

    private OrderItem convertCartItemToOrderItem(CartItemDto cartItemDto){

        return OrderItem.builder()
                .productId(cartItemDto.getProduct().getId())
                .quantity(cartItemDto.getQuantity())
                .build();
    }

    private DeliveryAddress convertDeliveryAddress(CustomerDto dataDto){
        City city = City.builder()
                .town(dataDto.getTown())
                .province(Province.toProvince(dataDto.getProvince()))
                .build();

        return DeliveryAddress.builder()
                .city(city)
                .street(dataDto.getStreet())
                .zipCode(dataDto.getZipCode())
                .build();
    }
}