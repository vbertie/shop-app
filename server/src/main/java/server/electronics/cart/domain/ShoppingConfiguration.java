package server.electronics.cart.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import server.electronics.product.domain.ProductFacade;

@Configuration
class ShoppingConfiguration {

    @Bean
    public ShoppingFacade shoppingFacade(ProductFacade productFacade){
        CartRepository cartRepository = new InMemoryCartRepository();
        CartService cartService = new CartServiceImpl();
        return new ShoppingFacade(cartRepository, productFacade, cartService);
    }
}
