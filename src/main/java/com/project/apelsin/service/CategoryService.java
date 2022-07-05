package com.project.apelsin.service;

import com.project.apelsin.dto.CategoryDto;
import com.project.apelsin.entity.Category;
import com.project.apelsin.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    final CategoryRepository categoryRepository;

    public void add(CategoryDto categoryDto){
        Category category=new Category();
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
    }
    public void edit(Long id,CategoryDto categoryDto){
        Optional<Category> byId = categoryRepository.findById(id);
        if(byId.isEmpty()){
            return;
        }
        Category category = byId.get();
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
    }
}
