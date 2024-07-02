package com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.service;

import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.model.WasteCategory;
import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.repository.WasteCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WasteCategoryService {

    @Autowired
    @SuppressWarnings("unused")
    private WasteCategoryRepository wasteCategoryRepository;

    public List<WasteCategory> getAllWasteCategories() {
        return wasteCategoryRepository.findAll();
    }

    public Optional<WasteCategory>  getWasteCategoryById(Long id) {
        return wasteCategoryRepository.findById(id);
    }

    public WasteCategory saveWasteCategory(WasteCategory wasteCategory) {
        return wasteCategoryRepository.save(wasteCategory);
    }

    public WasteCategory updateCategory(Long id, WasteCategory newCategoryData) {
        Optional<WasteCategory> oldCategory = wasteCategoryRepository.findById(id);

        if(oldCategory.isPresent()) {
            WasteCategory updatedCategory = oldCategory.get();
            updatedCategory.setName(newCategoryData.getName());
            updatedCategory.setDescription(newCategoryData.getDescription());

            return wasteCategoryRepository.save(updatedCategory);
        } else {
            return null;
        }
    }

    public void deleteWasteCategory(Long id) {
        wasteCategoryRepository.deleteById(id);
    }

}
