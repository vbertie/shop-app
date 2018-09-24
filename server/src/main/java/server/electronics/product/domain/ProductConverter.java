package server.electronics.product.domain;

import lombok.AllArgsConstructor;
import server.electronics.product.domain.dto.product.PostProductDto;
import server.electronics.product.domain.dto.product.ProductDto;
import server.electronics.util.mapper.SuperConverter;

import javax.persistence.NoResultException;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;

import static server.electronics.util.mapper.SuperConverter.setIfNonNull;

@AllArgsConstructor
class ProductConverter implements SuperConverter<PostProductDto,ProductDto, Product> {

    private CategoryRepository categoryRepository;

    @Override
    public Product convert(@NotNull PostProductDto dto) {
        return update(dto, new Product());
    }

    @Override
    public Collection<Product> convert(@NotNull Collection<PostProductDto> result) {
        return Collections.emptyList();
    }

    @Override
    public Product update(PostProductDto updater, Product result) {

        setIfNonNull(updater.getId(), result::setId);
        setIfNonNull(updater.getName(), result::setName);
        setIfNonNull(new BigDecimal(updater.getPrice()), result::setPrice);
        setIfNonNull(updater.getDescription(), result::setDescription);
        setIfNonNull(Integer.valueOf(updater.getInStockNumber()), result::setInStockNumber);
        setIfNonNull(updater.getActive(), result::setActive);
        setIfNonNull(categoryRepository
                .findByName(updater.getType())
                .orElseThrow(() -> new NoResultException("Category not found.")), result::setCategory);

        return result;
    }

    @Override
    public ProductDto convertToDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .inStockNumber(product.getInStockNumber())
                .active(product.getActive())
                .type(product.getCategory().getName())
                .promotionId(product.getPromotion() != null
                        ? product.getPromotion().getId() : null)
                .oldPrice(product.getOldPrice())
                .build();
    }
}
