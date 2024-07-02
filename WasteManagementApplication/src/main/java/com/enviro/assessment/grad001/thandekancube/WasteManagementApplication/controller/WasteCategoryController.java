package com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.controller;

import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.model.WasteCategory;
import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.service.WasteCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class WasteCategoryController {

    @Autowired
    private WasteCategoryService wasteCategoryService;

    @GetMapping("/category")
    public ResponseEntity<List<WasteCategory>> getAllWasteCategories() {
        try {
            List<WasteCategory> wasteCategories = wasteCategoryService.getAllWasteCategories();

            if(wasteCategories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(wasteCategories, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<WasteCategory> getWasteCategoryById(@PathVariable("id") long id) {
        Optional<WasteCategory> wasteCategories = wasteCategoryService.getWasteCategoryById(id);

        if(wasteCategories.isPresent()) {
            return new ResponseEntity<>(wasteCategories.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/category")
    public ResponseEntity<WasteCategory> saveCategory(@RequestBody WasteCategory wasteCategory) {
        try {
            WasteCategory categoryObj = wasteCategoryService.saveWasteCategory(wasteCategory);
            return new ResponseEntity<>(categoryObj, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<WasteCategory> updateWasteCategoryById(
            @PathVariable("id") long id,
            @RequestBody WasteCategory updatedWasteCategory) {

        try {
            WasteCategory updatedData = wasteCategoryService.updateCategory(id, updatedWasteCategory);

            if (updatedData != null) {
                return new ResponseEntity<>(HttpStatus.OK); // 200 OK if successfully updated
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found if resource not found
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error for other exceptions
        }
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<HttpStatus> deleteCategoryById(@PathVariable("id") long id) {
        try{
            wasteCategoryService.deleteWasteCategory(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
