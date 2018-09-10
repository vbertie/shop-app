package server.electronics.order.domain


import server.electronics.cart.domain.dto.UpdateCartDto
import server.electronics.order.dto.CustomerDto

trait SampleOrder {

    final String CART_ID = "1ID";
    CustomerDto customerData1 = createCustomerDataDto("Mark", "Helzfire", "mark.helzfire@wp.pl", "Slaskie","409212023", "Katowice", "41-946")
    CustomerDto customerData2 = createCustomerDataDto("Dany", "Montana", "mark.helzfire@wp.pl", "Slaskie","409212023", "Katowice", "41-946")
    UpdateCartDto updateCartDto1 = createCartItemDto(1l, 1)
    UpdateCartDto updateCartDto2 = createCartItemDto(2l, 1)

    static private CustomerDto createCustomerDataDto(String firstName, String lastName, String email, String province, String phoneNumber, String town, String zipCode){
        return new CustomerDto().builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .town(town)
                .zipCode(zipCode)
                .province(province)
                .build()
    }

    static private UpdateCartDto createCartItemDto(long productId, int quantity){
        return new UpdateCartDto().builder()
                .productId(productId)
                .amount(quantity)
                .build()
    }
}