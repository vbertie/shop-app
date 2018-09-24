package server.electronics.cart.domain;

import server.electronics.cart.domain.dto.CartDto;
import server.electronics.cart.domain.dto.CartItemDto;
import server.electronics.cart.domain.dto.UpdateCartDto;
import server.electronics.cart.domain.exception.CartException;
import server.electronics.product.domain.dto.product.ProductDto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

class CartServiceImpl implements CartService{

    @Override
    public CartDto addCartItem(CartDto cartDto, UpdateCartDto updateCartDto, ProductDto product) {
        throwIfAmountExceedsStock(updateCartDto.getAmount(), product.getInStockNumber());
        Map<Long, CartItemDto> cartItems = new HashMap<>(cartDto.getCartItems());

        CartItemDto newCartItem = cartItems.containsKey(product)
                ? increaseCartItemAmount(cartItems.get(product.getId()), updateCartDto)
                : createCartItem(product, updateCartDto);

        cartItems.put(product.getId(), newCartItem);
        cartDto.getCartItems().put(product.getId(),newCartItem);

        return cartDto
                .withGrandTotal(calculateGrandTotal(cartItems));

    }

    @Override
    public CartDto removeCartItem(CartDto productCart, Long id) {
        Map<Long, CartItemDto> cartItems = new HashMap<>(productCart.getCartItems());

        cartItems.remove(id);
        productCart.getCartItems().remove(id);

        return productCart
                .withGrandTotal(calculateGrandTotal(cartItems));
    }

    private CartItemDto createCartItem(ProductDto product, UpdateCartDto updateCartDto) {

        return CartItemDto.builder()
                .product(product)
                .quantity(updateCartDto.getAmount())
                .totalPrice(product.getPrice().multiply(new BigDecimal(updateCartDto.getAmount())))
                .build();
    }

    private CartItemDto increaseCartItemAmount(CartItemDto cartItemDto, UpdateCartDto updateCartDto) {
        int sumOfQuantities = cartItemDto.getQuantity() + updateCartDto.getAmount();

        throwIfAmountExceedsStock(sumOfQuantities, cartItemDto.getProduct().getInStockNumber());

        return CartItemDto.builder()
                .quantity(sumOfQuantities)
                .totalPrice(cartItemDto.getProduct()
                        .getPrice().multiply(new BigDecimal(sumOfQuantities)))
                .build();
    }

    private void throwIfAmountExceedsStock(int sumOfQuantities, int inStockNumber) {
        if (sumOfQuantities > inStockNumber) {
            throw new CartException("Quantity is bigger");
        }
    }

    private BigDecimal calculateGrandTotal(Map<Long, CartItemDto> cartItems) {
            return cartItems
                    .values()
                    .stream()
                    .map(CartItemDto::getTotalPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

