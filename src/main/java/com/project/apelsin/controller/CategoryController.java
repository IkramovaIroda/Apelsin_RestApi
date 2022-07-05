package com.project.apelsin.controller;

import com.project.apelsin.dto.ApiResponse;
import com.project.apelsin.dto.CategoryDto;
import com.project.apelsin.entity.Category;
import com.project.apelsin.entity.Product;
import com.project.apelsin.repository.CategoryRepository;
import com.project.apelsin.repository.ProductRepository;
import com.project.apelsin.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    final CategoryRepository categoryRepository;
    final ProductRepository productRepository;
    final CategoryService categoryService;

    @GetMapping
    public HttpEntity<?> getByProduct(@RequestParam("product_id") Long id){

        Optional<Product> byId = productRepository.findById(id);
        if(byId.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(byId.get().getCategory());
    }

    @GetMapping("/list")
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok().body(categoryRepository.findAll());
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody CategoryDto categoryDto){
        categoryService.add(categoryDto);
        return ResponseEntity.ok().body(new ApiResponse("Saved!", true));
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Long id, @RequestBody CategoryDto categoryDto){
        if (!categoryRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        categoryService.edit(id, categoryDto);
        return ResponseEntity.ok().body(new ApiResponse("Edited!", true));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id){
        Optional<Category> byId = categoryRepository.findById(id);
        if(byId.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        categoryRepository.deleteById(id);
        return ResponseEntity.ok().body(new ApiResponse("Deleted!", true));
    }
}
