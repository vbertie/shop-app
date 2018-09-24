package server.electronics.product.domain

import server.electronics.product.domain.dto.category.PostCategoryDto
import server.electronics.product.domain.dto.category.ShowCategoryDto
import server.electronics.product.domain.dto.category.UpdateCategoryDto
import spock.lang.Specification

import javax.persistence.NoResultException

class CategorySpecTest extends Specification{

    ProductFacade productFacade = new ProductConfiguration().productFacade();

    def "Should add category" () {

        given: "We have category dto"
            PostCategoryDto categoryDto = PostCategoryDto.builder()
                    .id(1l)
                    .name("Laptops")
                    .build()

        when: "We want to addProduct category to database"
            productFacade.addCategory(categoryDto)

        then: "Category is added to database"
            productFacade.showCategory(1l).name == categoryDto.name
    }

    def "Should delete category" () {

        given: "We have one category in database"
            PostCategoryDto categoryDto = PostCategoryDto.builder()
                    .id(1l)
                    .name("Laptops")
                    .build()

            productFacade.addCategory(categoryDto)

        when: "We want to delete category"
            productFacade.deleteCategory(1l)
            productFacade.showCategory(1l)

        then: "Category is deleted"
            thrown(NoResultException)
    }

    def "Should edit category" () {

        given: "We have existing category in database"
            PostCategoryDto categoryDto = PostCategoryDto.builder()
                    .id(1l)
                    .name("Smartphones")
                    .build()

            productFacade.addCategory(categoryDto)

        when: "We want to edit category"
            UpdateCategoryDto updateCategoryDto = UpdateCategoryDto.builder()
                    .id(1l)
                    .name("New category name")
                    .build()

            productFacade.updateCategory(updateCategoryDto)

        then: "Category name is changed"
            productFacade.showCategory(1l).name == updateCategoryDto.name
    }

    def "Should list all categories" () {

        given: "We have two categories in db"
            PostCategoryDto categoryDto1 = PostCategoryDto.builder()
                    .id(1l)
                    .name("Laptops")
                    .build()

            PostCategoryDto categoryDto2 = PostCategoryDto.builder()
                    .id(2l)
                    .name("Smartphones")
                    .build()

            productFacade.addCategory(categoryDto1)
            productFacade.addCategory(categoryDto2)

        when: "We want to get all categories"
            List<ShowCategoryDto> categories = productFacade.findCategories()

        then: "Set size is == 2"
            categories.size() == 2
    }
}
