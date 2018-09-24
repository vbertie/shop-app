package server.electronics.order.domain

import server.electronics.cart.domain.ShoppingConfiguration
import server.electronics.cart.domain.ShoppingFacade
import server.electronics.cart.domain.dto.CartDto
import server.electronics.order.dto.OrderDto
import server.electronics.order.dto.ShowOrderDto
import server.electronics.order.exceptions.OrderException
import server.electronics.product.domain.ProductConfiguration
import server.electronics.product.domain.ProductFacade
import server.electronics.product.domain.SampleCategories
import server.electronics.product.domain.SampleProducts
import spock.lang.Specification

class OrderSpecTest extends Specification implements SampleOrder, SampleProducts, SampleCategories{

    ProductFacade productFacade = new ProductConfiguration().productFacade();
    ShoppingFacade shoppingFacade = new ShoppingConfiguration().shoppingFacade(productFacade)
    OrderFacade orderFacade = new OrderConfiguration().orderFacade(productFacade)

    def setup (){
        productFacade.addCategory(smartphone)
        productFacade.addCategory(camera)
        productFacade.addCategory(laptop)
        productFacade.addProduct(iphone)
        productFacade.addProduct(acer)
    }

    def "Should process order, and save it to database" () {

        given: "We have cart with items in system"
            CartDto cart = shoppingFacade.createCart(CART_ID)

            shoppingFacade.addCartItem(cart.cartId, updateCartDto1)
            shoppingFacade.addCartItem(cart.cartId, updateCartDto2)

            int addedProductInStock1 =
                    productFacade.showProduct(updateCartDto1.productId).inStockNumber

            int addedProductInStock2 =
                    productFacade.showProduct(updateCartDto2.productId).inStockNumber

            CartDto cartWithItems = shoppingFacade.showCart(CART_ID).get()

            OrderDto orderDto = OrderDto.builder()
                            .id(1l)
                            .customer(customerData1)
                            .isPaid(false)
                            .isDelivered(false)
                            .cart(cartWithItems)
                            .build()

        when: "We want to addProduct order to database"
             orderFacade.processOrder(orderDto)

        then: "Order exist in database & products in stock number is reduced "
            ShowOrderDto showOrderDto = orderFacade.showOrder(1l)
            showOrderDto.grandTotal == 5080

            productFacade.showProduct(updateCartDto1.productId)
                    .inStockNumber == addedProductInStock1 - updateCartDto1.amount

            productFacade.showProduct(updateCartDto2.productId)
                    .inStockNumber == addedProductInStock2 - updateCartDto2.amount
    }

    def "Should get list of orders" () {

        given: "We have two orders in database"
            CartDto cart = shoppingFacade.createCart(CART_ID);

            OrderDto orderDto = OrderDto.builder()
                    .id(1l)
                    .customer(customerData1)
                    .isPaid(false)
                    .isDelivered(false)
                    .cart(cart)
                    .build()

            OrderDto orderDto2 = OrderDto.builder()
                    .id(2l)
                    .customer(customerData2)
                    .isPaid(false)
                    .isDelivered(false)
                    .cart(cart)
                    .build()

            orderFacade.processOrder(orderDto)
            orderFacade.processOrder(orderDto2)

        when: "We want to get list of orders"
            List<ShowOrderDto> orders = orderFacade.findAll()

        then: "We get list of all orders in database"
            orders.size() == 2
    }

    def "Should remove order from database" () {

        given: "We have one order in database"
            CartDto cart = shoppingFacade.createCart(CART_ID);

            OrderDto orderDto = OrderDto.builder()
                    .id(1l)
                    .customer(customerData1)
                    .isPaid(false)
                    .isDelivered(false)
                    .cart(cart)
                    .build()

            orderFacade.processOrder(orderDto)

        when: "We delete order, and then trying to get it"
            orderFacade.removeOrder(orderDto.getId())
            orderFacade.showOrder(orderDto.getId())

        then: "We get an exception"
            thrown(OrderException)
    }

    def "Should actualize order" () {
        given: "We have order in database"
            CartDto cart = shoppingFacade.createCart(CART_ID)

            shoppingFacade.addCartItem(cart.cartId, updateCartDto1)
            shoppingFacade.addCartItem(cart.cartId, updateCartDto2)

            OrderDto orderDto = OrderDto.builder()
                    .id(1l)
                    .customer(customerData1)
                    .isPaid(false)
                    .isDelivered(false)
                    .cart(cart)
                    .build()

            orderFacade.processOrder(orderDto)
            ShowOrderDto order = orderFacade.showOrder(1l)
            order.getId() == orderDto.id
            order != null

        when: "We want to actualize order "
            OrderDto updateOrderDto = OrderDto.builder()
                    .id(1l)
                    .customer(customerData1)
                    .isPaid(true)
                    .isDelivered(true)
                    .cart(cart)
                    .build()

            orderFacade.actualizeOrder(updateOrderDto)

        then: "Order is actualized"
            ShowOrderDto actualizedOrder = orderFacade.showOrder(1l)
            actualizedOrder.customer.firstName == orderDto.customer.firstName
            actualizedOrder.isPaid == true
            actualizedOrder.isDelivered == true
    }
}
