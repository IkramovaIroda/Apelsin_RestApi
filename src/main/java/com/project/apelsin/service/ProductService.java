package com.project.apelsin.service;

import com.project.apelsin.dto.ApiResponse;
import com.project.apelsin.dto.ProductDto;
import com.project.apelsin.entity.Category;
import com.project.apelsin.entity.Product;
import com.project.apelsin.repository.CategoryRepository;
import com.project.apelsin.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    final CategoryRepository categoryRepository;
    final ProductRepository productRepository;

    public ApiResponse add(ProductDto productDto){
        Optional<Category> byId = categoryRepository.findById(productDto.getCategoryId());
        if(byId.isEmpty()){
            return new ApiResponse("Category with id \"" +productDto.getCategoryId()
                    + "\" not found", false);
        }
        Product product=new Product();
        return getApiResponse(productDto, byId, product);
    }

    public ApiResponse edit(Long id, ProductDto productDto){
        Optional<Product> byId1 = productRepository.findById(id);
        if(byId1.isEmpty()){
            return new ApiResponse("Product not found", false);
        }
        Optional<Category> byId = categoryRepository.findById(productDto.getCategoryId());
        if(byId.isEmpty()){
            return new ApiResponse("Category with id \"" +productDto.getCategoryId()
                    + "\" not found", false);
        }
        Product product=byId1.get();
        return getApiResponse(productDto, byId, product);
    }

    private ApiResponse getApiResponse(ProductDto productDto, Optional<Category> byId, Product product) {
        product.setCategory(byId.get());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        product.setPhoto(productDto.getPhoto());
        productRepository.save(product);
        return new ApiResponse("Saved!", true);
    }
}
