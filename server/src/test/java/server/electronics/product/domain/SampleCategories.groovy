package server.electronics.product.domain

import server.electronics.product.domain.dto.category.PostCategoryDto
import server.electronics.product.domain.dto.product.PostProductDto
import server.electronics.product.domain.dto.product.ProductDto

trait SampleCategories {

    PostCategoryDto laptop = createPostCategoryDto(1l, "Laptops")
    PostCategoryDto camera = createPostCategoryDto(2l, "Cameras")
    PostCategoryDto smartphone = createPostCategoryDto(3l, "Smartphones")

    static private PostCategoryDto createPostCategoryDto(Long id, String name){
        return PostCategoryDto.builder()
                .id(id)
                .name(name)
                .build()
    }

}