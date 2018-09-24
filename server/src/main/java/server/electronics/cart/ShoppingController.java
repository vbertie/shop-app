package server.electronics.cart;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import server.electronics.cart.domain.ShoppingFacade;
import server.electronics.cart.domain.dto.CartDto;
import server.electronics.cart.domain.dto.UpdateCartDto;
import server.electronics.cart.domain.exception.CartException;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/cart")
class ShoppingController {

    private ShoppingFacade shoppingFacade;

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public CartDto createShoppingCart(@RequestBody String cartId){
        return shoppingFacade.createCart(cartId);
    }

    @GetMapping("/{cartId}")
    @ResponseStatus(value = HttpStatus.OK)
    public CartDto readShoppingCart(@PathVariable String cartId){
        return shoppingFacade.showCart(cartId).orElseThrow(() -> new CartException("Cart not found"));
    }

    @PutMapping("/add")
    @ResponseStatus(value = HttpStatus.OK)
    public void addCartItem(HttpServletRequest httpServletRequest,
                                   @Validated @RequestBody UpdateCartDto updateCartDto){
        String sessionId = httpServletRequest.getSession().getId();
        shoppingFacade.addCartItem(sessionId, updateCartDto);
    }

    @DeleteMapping("/removeItem/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void removeCartItem(HttpServletRequest httpServletRequest,
                            @PathVariable Long id ){
        String sessionId = httpServletRequest.getSession().getId();
        shoppingFacade.removeCartItem(sessionId, id);
    }

    @DeleteMapping("/remove")
    @ResponseStatus(value = HttpStatus.OK)
    public void removeCartItem(HttpServletRequest httpServletRequest){
        String sessionId = httpServletRequest.getSession().getId();
        shoppingFacade.removeAllCartItems(sessionId);
    }
}
