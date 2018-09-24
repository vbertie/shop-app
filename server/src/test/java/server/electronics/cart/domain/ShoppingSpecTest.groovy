package server.electronics.cart.domain

import server.electronics.cart.domain.dto.CartDto
import server.electronics.cart.domain.dto.CartItemDto
import server.electronics.cart.domain.dto.UpdateCartDto
import server.electronics.cart.domain.exception.CartException
import server.electronics.product.domain.ProductConfiguration
import server.electronics.product.domain.ProductFacade
import server.electronics.product.domain.SampleCategories
import server.electronics.product.domain.SampleProducts
import spock.lang.Specification

class ShoppingSpecTest extends Specification implements SampleProducts, SampleCategories{

    private ProductFacade productFacade = new ProductConfiguration().productFacade();
    private ShoppingFacade shoppingFacade = new ShoppingConfiguration().shoppingFacade(productFacade);

    def setup(){
        productFacade.addCategory(smartphone)
        productFacade.addCategory(camera)
        productFacade.addCategory(laptop)
        productFacade.addProduct(iphone)
        productFacade.addProduct(acer)
    }

    def "Should create shopping cart"  (){

        given: "Session id"
            String CART_ID = "1ID"

        when: "We pass sessionId"
            shoppingFacade.createCart(CART_ID)

        then: "Out cart is created and present"
            shoppingFacade.showCart(CART_ID).isPresent() == true

    }

    def "Should add cart item to shopping cart"(){

        given: "We have shopping cart, and two items"
            String CART_ID = "1ID"
            CartDto cartDto = shoppingFacade.createCart(CART_ID)

            UpdateCartDto addCartDto1 = new UpdateCartDto(1l, 3)
            UpdateCartDto addCartDto2 = new UpdateCartDto(2l, 4)

        when:"We addProduct product into the card "
            shoppingFacade.addCartItem(cartDto.getCartId(), addCartDto1)
            shoppingFacade.addCartItem(cartDto.getCartId(), addCartDto2)

            CartItemDto cartItem1 = shoppingFacade.showCart(cartDto.getCartId()).get().getCartItems().get(1l)
            CartItemDto cartItem2 = shoppingFacade.showCart(cartDto.getCartId()).get().getCartItems().get(2l)

        then:"We have two cart items in cart"
            cartItem1.quantity == 3
            cartItem2.quantity == 4
            cartDto.cartItems.size() == 2
            shoppingFacade.showCart(CART_ID).get().grandTotal == cartItem1.totalPrice + cartItem2.totalPrice
}

    def "Should remove cart item from shopping cart" () {

        given: "We have added two cart items into our cart"
            String CART_ID = "1ID"
            CartDto cartDto = shoppingFacade.createCart(CART_ID)

            UpdateCartDto addCartDto1 = new UpdateCartDto(1l, 3)
            UpdateCartDto addCartDto2 = new UpdateCartDto(2l, 2)

            shoppingFacade.addCartItem(cartDto.getCartId(), addCartDto1)
            shoppingFacade.addCartItem(cartDto.getCartId(), addCartDto2)

        when: "We want to remove one of the items"
            shoppingFacade.removeCartItem(cartDto.getCartId(), acer.id)

        then: "Our shopping cart size is one"
            cartDto.cartItems.size() == 1

    }

    def "Should throw exception if requested amount is bigger that in stock number"(){

        given: "We have cart and UpdateDto"
            String CART_ID = "1ID"
            CartDto cart = shoppingFacade.createCart(CART_ID)
            UpdateCartDto updateCartDto = new UpdateCartDto(2l,10)

        when: "We send request to addProduct cart item"
            shoppingFacade.addCartItem(cart.getCartId(), updateCartDto)

        then: "Then we are expecting exception"
            thrown(CartException.class)
    }

    def "Should remove all cart items"(){

        given: "We have some cart items in shopping cart"
            String CART_ID = "1ID"
            CartDto cart = shoppingFacade.createCart(CART_ID)

            UpdateCartDto updateCartDto = new UpdateCartDto(2l,3)
            UpdateCartDto updateCartDto2 = new UpdateCartDto(1l,2)

            shoppingFacade.addCartItem(cart.cartId, updateCartDto)
            shoppingFacade.addCartItem(cart.cartId, updateCartDto2)

        when: "We want to remove all cart items"
            shoppingFacade.removeAllCartItems(cart.cartId)

        then: "Our cartDto is empty"
            CartDto clearedCart = shoppingFacade.showCart(CART_ID).get()
            clearedCart.grandTotal == 0
            clearedCart.cartItems.isEmpty()
    }
}
