package server.electronics.product;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import server.electronics.product.domain.ProductFacade;
import server.electronics.product.domain.dto.promotion.PostPromotionDto;
import server.electronics.product.domain.dto.promotion.PromotionDto;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/promotion")
class PromotionController {

    private ProductFacade productFacade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addPromotion(@Validated @RequestBody PostPromotionDto postPromotionDto){
        productFacade.addPromotion(postPromotionDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<PromotionDto> getPromotions(){
        return productFacade.findAllPromotions();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePromotion(@PathVariable Long id){
        productFacade.deletePromotion(id);
    }

    @PostMapping("/addProductPromotion")
    @ResponseStatus(HttpStatus.OK)
    public void addProductPromotion(@RequestBody PromotionDto promotionDto){
        productFacade.addProductPromotion(promotionDto);
    }

    @PutMapping("/deleteProductPromotion")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductPromotion(@RequestBody PromotionDto promotionDto){
        productFacade.deleteProductPromotion(promotionDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PromotionDto getProductPromotion(@PathVariable Long id){
        return productFacade.getProductPromotion(id);
    }
}
