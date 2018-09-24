package server.electronics.product;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import server.electronics.product.domain.ProductFacade;
import server.electronics.product.domain.dto.category.PostCategoryDto;
import server.electronics.product.domain.dto.category.ShowCategoryDto;
import server.electronics.product.domain.dto.category.UpdateCategoryDto;

import java.util.List;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
class CategoryController {

    private ProductFacade productFacade;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addCategory(@Validated @RequestBody PostCategoryDto postCategoryDto){
        productFacade.addCategory(postCategoryDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteCategory(@PathVariable Long id){
        productFacade.deleteCategory(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateCategory(@Validated @RequestBody UpdateCategoryDto updateCategoryDto){
        productFacade.updateCategory(updateCategoryDto);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<ShowCategoryDto> getCategories(){
        return productFacade.findCategories();
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ShowCategoryDto getCategory(@PathVariable Long id){
        return productFacade.showCategory(id);
    }
}

