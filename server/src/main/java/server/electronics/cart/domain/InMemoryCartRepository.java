package server.electronics.cart.domain;

import server.electronics.cart.domain.dto.CartDto;
import server.electronics.cart.domain.exception.CartException;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryCartRepository implements CartRepository {

    private final Map<String, CartDto> carts = new ConcurrentHashMap<>();

    @Override
    public CartDto createCart(String cartId) {
        if (carts.containsKey(cartId)) {
            throw new CartException("Cannot create cart. Cart is already created");
        }

        carts.put(cartId, new CartDto(cartId));

        return carts.get(cartId);
    }

    @Override
    public CartDto updateCart(String cartId, CartDto cartDto) {
        if (carts.containsKey(cartId)){
            carts.put(cartId, cartDto);
        } else {
            throw new CartException("Cannot update cart. Cart not found, create cart before updating");
        }

        return carts.get(cartId);
    }

    @Override
    public void removeAllCartItems(String id) {
        carts.put(id, new CartDto(id));
    }

    @Override
    public Optional<CartDto> showCart(String cartId) {
        return Optional.ofNullable(carts.get(cartId));
    }
}
