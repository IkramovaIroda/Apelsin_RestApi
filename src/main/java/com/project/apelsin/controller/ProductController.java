package com.project.apelsin.controller;

import com.project.apelsin.dto.ApiResponse;
import com.project.apelsin.dto.CategoryDto;
import com.project.apelsin.dto.ProductDto;
import com.project.apelsin.entity.Product;
import com.project.apelsin.repository.ProductRepository;
import com.project.apelsin.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    final ProductRepository productRepository;
    final ProductService productService;

    @GetMapping("/list")
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok().body(productRepository.findAll());
    }
    @GetMapping("/list/details")
    public HttpEntity<?> getOne(@RequestParam("product_id")Long id){
        if(id==null){
            return ResponseEntity.badRequest().body(new ApiResponse("product_id required param", false));
        }
        Optional<Product> byId = productRepository.findById(id);
        if(byId.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(byId.get());
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody ProductDto productDto){
        ApiResponse apiResponse = productService.add(productDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.OK:HttpStatus.BAD_REQUEST)
                .body(apiResponse);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Long id, ProductDto productDto){
        ApiResponse edit = productService.edit(id, productDto);
        return ResponseEntity.status(edit.isSuccess()?200:400).body(edit.getMessage());
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id){
        if (!productRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productRepository.deleteById(id);
        return ResponseEntity.ok().body("Deleted!");
    }
}
