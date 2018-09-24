package server.electronics.product.domain;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import server.electronics.cart.domain.dto.CartDto;
import server.electronics.cart.domain.dto.CartItemDto;
import server.electronics.product.domain.dto.category.PostCategoryDto;
import server.electronics.product.domain.dto.category.ShowCategoryDto;
import server.electronics.product.domain.dto.category.UpdateCategoryDto;
import server.electronics.product.domain.dto.product.PostProductDto;
import server.electronics.product.domain.dto.product.ProductDto;
import server.electronics.product.domain.dto.promotion.PostPromotionDto;
import server.electronics.product.domain.dto.promotion.PromotionDto;
import server.electronics.util.mapper.SuperConverter;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@AllArgsConstructor
public class ProductFacade {

    private ProductRepository productRepository;
    private PromotionRepository promotionRepository;
    private SuperConverter<PostProductDto,ProductDto, Product> productConverter;
    private PromotionService promotionService;
    private ImageUploader imageUploader;
    private CategoryRepository categoryRepository;

    public ProductDto addProduct(PostProductDto postProductDto) {
        Product product = productConverter.convert(postProductDto);

        return productConverter.convertToDto(productRepository.save(product));
    }

    public ProductDto showProduct(Long id) {
        return productRepository
                .findById(id)
                .map(productConverter::convertToDto)
                .orElseThrow(() -> new NoResultException("Product with id" + id + " not found."));
    }

    public List<ProductDto> findAllProducts(){
        return productRepository.findAll()
                .stream()
                .map(productConverter::convertToDto)
                .collect(Collectors.toList());
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public List<ProductDto> findProductsByCategory(String category) {
        return productRepository
                .findProductByCategoryName(category)
                .stream()
                .map(productConverter::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> findProductsWithKeyword(String keyword) {
        return productRepository
                .findByKeyword(keyword)
                .stream()
                .map(productConverter::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> findProductsAtPromotion() {
        return productRepository.findAllByPromotionNotNull()
                .stream()
                .map(productConverter::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> findProductsAtPromotionWithCategory(String category) {
        return productRepository.findAllByPromotionNotNullAndCategoryName(category)
                .stream()
                .map(productConverter::convertToDto)
                .collect(Collectors.toList());
    }

    public void addCategory(PostCategoryDto postCategoryDto){
        categoryRepository.save(new Category(postCategoryDto.getName()));
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }

    public ShowCategoryDto showCategory(Long id){
       return categoryRepository.findById(id)
               .map(Category::dto)
               .orElseThrow(() -> new NoResultException("Category with id" + id + " not found."));
    }

    public List<ShowCategoryDto> findCategories(){
        return categoryRepository.findAll()
                .stream()
                .map(Category::dto)
                .collect(Collectors.toList());
    }

    public void updateCategory(UpdateCategoryDto updateCategoryDto) {
        Category category = categoryRepository.findById(updateCategoryDto.getId())
                .orElseThrow(() -> new NoResultException("Category with id" + updateCategoryDto.getId() + " not found."));
          category.setName(updateCategoryDto.getName());

          categoryRepository.save(category);
    }


    public void addPromotion(PostPromotionDto postPromotionDto){
        Promotion promotion = promotionService.convert(postPromotionDto);
        promotionRepository.save(promotion);
    }

    public PromotionDto showPromotion(Long id){
        return promotionRepository.findById(id)
                .map(Promotion::dto)
                .orElseThrow(() -> new NoResultException("Promotion with id " + id + " not found."));
    }

    public Set<PromotionDto> findAllPromotions(){
        return promotionRepository.findAll()
                .stream()
                .map(Promotion::dto)
                .collect(Collectors.toSet());
    }

    public void deletePromotion(Long id){
        Set<Product> products = productRepository.findAllByPromotionId(id);
        Promotion promotion = promotionRepository
                .findById(id)
                .orElseThrow(() -> new NoResultException("Promotion not found"));

        products.stream()
                .map(product -> promotionService.deleteProductPromotion(product, promotion))
                .forEach(productRepository::save);

        promotionRepository.deleteById(id);
    }

    public PromotionDto getProductPromotion(Long id) {
        Promotion promotion = promotionRepository
                .findById(id)
                .orElseThrow(() -> new NoResultException("Promotion with id " + id + " not found."));

        return promotionService.convertToProductDto(promotion);
    }

    public void addProductPromotion(PromotionDto promotionDto){
        Promotion promotion = promotionRepository
                .findById(promotionDto.getId())
                .orElseThrow(() -> new NoResultException("Promotion with id " +
                        promotionDto.getId() + " not found."));

        promotionDto.getProducts()
                .stream()
                .map(productDto -> productRepository.findById(productDto.getId())
                        .orElseThrow(() -> new NoResultException("Product not found")))
                .map(product -> promotionService.addProductPromotion(product, promotion))
                .forEach(product -> productRepository.save(product));

        promotionRepository.save(promotion);
    }

    public void deleteProductPromotion(PromotionDto promotionDto){
        Promotion promotion = promotionRepository
                .findById(promotionDto.getId())
                .orElseThrow(() -> new NoResultException("Promotion not found"));

        promotionDto.getProducts()
                .stream()
                .map(productDto -> productRepository.findById(productDto.getId())
                        .orElseThrow(() -> new NoResultException("Product not found")))
                .map(product -> promotionService.deleteProductPromotion(product, promotion))
                .forEach(productRepository::save);

        promotionRepository.save(promotion);
    }

    public void subtractInStockNumber(CartDto cart) {
        cart.getCartItems()
                .values()
                .stream()
                .forEach(this::updateInStockNumber);
    }

    public void uploadImage(Long id, HttpServletRequest request) {
        try {
            imageUploader.upload(id, request);
        } catch (IOException ex){
            log.error(ex.getMessage());
        }
    }

    private void updateInStockNumber(CartItemDto cartItemDto) {
        Product product = productRepository.findById(cartItemDto.getProduct().getId())
                .orElseThrow(NoResultException::new);

        product.setInStockNumber(product.getInStockNumber() - cartItemDto.getQuantity());
        productRepository.save(product);
    }
}

