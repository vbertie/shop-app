package server.electronics.cart.domain;

import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;
import server.electronics.cart.domain.dto.CartDto;
import server.electronics.cart.domain.dto.UpdateCartDto;
import server.electronics.cart.domain.exception.CartException;
import server.electronics.product.domain.ProductFacade;
import server.electronics.product.domain.dto.product.ProductDto;

import javax.transaction.Transactional;
import java.util.function.Function;

@Transactional
@AllArgsConstructor
public class ShoppingFacade {

    @Delegate
    private final CartRepository cartRepository;
    private final ProductFacade productFacade;
    private final CartService cartService;

    public void addCartItem(String cartId, UpdateCartDto updateCartDto){
        ProductDto product = productFacade.showProduct(updateCartDto.getProductId());
        updateCartItem(cartId, productCartDto -> cartService.addCartItem(productCartDto, updateCartDto, product));
    }

    public void removeCartItem(String cartId, Long id){
        updateCartItem(cartId, productCart -> cartService.removeCartItem(productCart, id));
    }

    private void updateCartItem(String cartId, Function<CartDto, CartDto> proccessCart){
        cartRepository.showCart(cartId)
                .map(proccessCart)
                .map(cartDto -> cartRepository.updateCart(cartId, cartDto))
                .orElseThrow(() -> new CartException("Cannot find any cart"));
    }
}
