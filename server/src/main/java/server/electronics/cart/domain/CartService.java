package server.electronics.cart.domain;

import server.electronics.cart.domain.dto.CartDto;
import server.electronics.cart.domain.dto.UpdateCartDto;
import server.electronics.product.domain.dto.product.ProductDto;

interface CartService {

    CartDto addCartItem(CartDto cartDto, UpdateCartDto updateCartDto, ProductDto product);

    CartDto removeCartItem(CartDto productCart, Long id);
}
