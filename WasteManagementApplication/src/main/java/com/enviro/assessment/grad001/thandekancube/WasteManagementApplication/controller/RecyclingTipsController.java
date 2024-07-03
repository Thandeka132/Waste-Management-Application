package com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.controller;

import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.model.RecyclingTip;
import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.service.RecyclingTipsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081") // Allows cross-origin requests from specified domain
@RestController // Marks the class as a RESTful web service controller
@RequestMapping("/api") // Maps requests to /api URL
public class RecyclingTipsController {

    @Autowired // Injects the RecyclingTipsService dependency
    private RecyclingTipsService recyclingTipsService;

    // Handles GET requests for all recycling tips
    @GetMapping("/tip")
    public ResponseEntity<List<RecyclingTip>> getAllRecyclingTips() {
        try {
            List<RecyclingTip> recyclingTips = recyclingTipsService.getAllRecyclingTips();

            if(recyclingTips.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Returns 204 No Content if list is empty
            }

            return new ResponseEntity<>(recyclingTips, HttpStatus.OK); // Returns 200 OK with the list
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // Returns 500 Internal Server Error on exception
        }
    }

    // Handles GET requests for a specific recycling tip by ID
    @GetMapping("/tip/{id}")
    public ResponseEntity<RecyclingTip> getRecyclingTipById(@PathVariable("id") long id) {
        Optional<RecyclingTip> recyclingTips = recyclingTipsService.getRecyclingTipsById(id);

        if(recyclingTips.isPresent()) {
            return new ResponseEntity<>(recyclingTips.get(), HttpStatus.OK); // Returns 200 OK with the recycling tip
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Returns 404 Not Found if recycling tip not found
        }
    }

    // Handles POST requests to add a new recycling tip
    @PostMapping("/tip")
    public ResponseEntity<RecyclingTip> saveTip(@RequestBody RecyclingTip recyclingTip) {
        try{
            RecyclingTip tipObj = recyclingTipsService.saveRecyclingTip(recyclingTip);
            return new ResponseEntity<>(tipObj, HttpStatus.OK); // Returns 200 OK with the created recycling tip
        } catch(Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // Returns 500 Internal Server Error on exception
        }
    }

    // Handles PUT requests to update an existing recycling tip by ID
    @PutMapping("/tip/{id}")
    public ResponseEntity<RecyclingTip> updateRecyclingTipById(@PathVariable("id") long id, @RequestBody RecyclingTip updatedRecyclingTip) {
        try {
            RecyclingTip updatedData = recyclingTipsService.updateRecyclingTip(id, updatedRecyclingTip);

            if(updatedData != null) {
                return new ResponseEntity<>(HttpStatus.OK); // Returns 200 OK if successfully updated
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Returns 404 Not Found if resource not found
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Returns 500 Internal Server Error for other exceptions
        }
    }

    // Handles DELETE requests to remove a recycling tip by ID
    @DeleteMapping("tip/{id}")
    public ResponseEntity<HttpStatus> deleteRecyclingTipById(@PathVariable("id") long id) {
        try{
            recyclingTipsService.deleteRecyclingTip(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Returns 204 No Content after successful deletion
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Returns 500 Internal Server Error on exception
        }
    }
}
