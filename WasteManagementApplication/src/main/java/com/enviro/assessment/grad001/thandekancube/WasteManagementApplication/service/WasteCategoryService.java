package com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.service;

import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.model.WasteCategory;
import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.repository.WasteCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Marks the class as a Spring service component
public class WasteCategoryService {

    @Autowired // Injects the WasteCategoryRepository dependency
    @SuppressWarnings("unused")
    private WasteCategoryRepository wasteCategoryRepository;

    // Retrieves all waste categories from the repository
    public List<WasteCategory> getAllWasteCategories() {
        return wasteCategoryRepository.findAll();
    }

    // Retrieves a specific waste category by ID
    public Optional<WasteCategory> getWasteCategoryById(Long id) {
        return wasteCategoryRepository.findById(id);
    }

    // Saves a new waste category to the repository
    public WasteCategory saveWasteCategory(WasteCategory wasteCategory) {
        return wasteCategoryRepository.save(wasteCategory);
    }

    // Updates an existing waste category by ID
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

    // Deletes a waste category by ID
    public void deleteWasteCategory(Long id) {
        wasteCategoryRepository.deleteById(id);
    }
}
