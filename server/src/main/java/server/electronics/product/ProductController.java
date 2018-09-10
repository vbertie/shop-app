package server.electronics.product;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import server.electronics.product.domain.ProductFacade;
import server.electronics.product.domain.dto.product.PostProductDto;
import server.electronics.product.domain.dto.product.ProductDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
class ProductController {

    private ProductFacade productFacade;

    @GetMapping("/getAll")
    public List<ProductDto> getProductList(){
        return productFacade.findAllProducts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ProductDto getProduct(@PathVariable String id){
        return productFacade.showProduct(Long.valueOf(id));
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProductDto createNewProduct(@Validated @RequestBody PostProductDto productDto){
        return productFacade.addProduct(productDto);
    }

    @DeleteMapping("/remove/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteProduct(@PathVariable Long id){
        productFacade.deleteProduct(id);
    }

    @GetMapping("/category/{category}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<ProductDto> getProductsWithCategory(@PathVariable String category, HttpServletRequest httpServletRequest){
        return productFacade.findProductsByCategory(category);
    }

    @GetMapping("/search")
    @ResponseStatus(code = HttpStatus.OK)
    public List<ProductDto> getProductsByKeyword(@RequestParam("name") String keyword){
        return productFacade.findProductsWithKeyword(keyword);
    }

    @GetMapping("/promotion/{category}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<ProductDto> getProductsAtPromotion(@PathVariable String category){
        return productFacade.findProductsAtPromotionWithCategory(category);
    }

    @PostMapping("/add/image")
    public ResponseEntity uploadImage(
            @RequestParam("id") Long id, HttpServletRequest request) {
        try {
            productFacade.uploadImage(id, request);
            return new ResponseEntity("Uploaded succecfully", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity("Upload failed", HttpStatus.BAD_REQUEST);
        }
    }
}
