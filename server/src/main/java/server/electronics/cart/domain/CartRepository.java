package server.electronics.cart.domain;

import server.electronics.cart.domain.dto.CartDto;

import java.util.Optional;

interface CartRepository {

    Optional<CartDto> showCart(String cartId);

    CartDto createCart(String cartId);

    CartDto updateCart(String cartId, CartDto cartDto);

    void removeAllCartItems(String id);
}
