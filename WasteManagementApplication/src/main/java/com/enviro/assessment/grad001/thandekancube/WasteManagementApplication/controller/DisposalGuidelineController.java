package com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.controller;

import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.model.DisposalGuideline;
import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.service.DisposalGuidelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081") // Allows cross-origin requests from specified domain
@RestController // Marks the class as a RESTful web service controller
@RequestMapping("/api") // Maps requests to /api URL
public class DisposalGuidelineController {

    @Autowired // Injects the DisposalGuidelineService dependency
    private DisposalGuidelineService disposalGuidelineService;

    // Handles GET requests for all disposal guidelines
    @GetMapping("/guideline")
    public ResponseEntity<List<DisposalGuideline>> getAllDisposalGuidelines() {
        try {
            List<DisposalGuideline> disposalGuidelines = disposalGuidelineService.getAllDisposalGuidelines();

            if(disposalGuidelines.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Returns 204 No Content if list is empty
            }

            return new ResponseEntity<>(disposalGuidelines, HttpStatus.OK); // Returns 200 OK with the list

        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // Returns 500 Internal Server Error on exception
        }
    }

    // Handles GET requests for a specific disposal guideline by ID
    @GetMapping("/guideline/{id}")
    public ResponseEntity<DisposalGuideline> getDisposalGuidelineById(@PathVariable("id") long id) {
        Optional<DisposalGuideline>  disposalGuidelines = disposalGuidelineService.getDisposalGuidelineById(id);

        if(disposalGuidelines.isPresent()) {
            return new ResponseEntity<>(disposalGuidelines.get(), HttpStatus.OK); // Returns 200 OK with the guideline
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Returns 404 Not Found if guideline not found
        }
    }

    // Handles POST requests to add a new disposal guideline
    @PostMapping("/guideline")
    public ResponseEntity<DisposalGuideline> addGuideline(@RequestBody DisposalGuideline disposalGuideline) {
        try {
            DisposalGuideline guidelineObj = disposalGuidelineService.saveDisposalGuideline(disposalGuideline);
            return new ResponseEntity<>(guidelineObj, HttpStatus.OK); // Returns 200 OK with the created guideline
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // Returns 500 Internal Server Error on exception
        }
    }

    // Handles PUT requests to update an existing disposal guideline by ID
    @PutMapping("/guideline/{id}")
    public ResponseEntity<DisposalGuideline> updateGuidelineById(
            @PathVariable("id") long id,
            @RequestBody DisposalGuideline updatedDisposalGuideline) {

        try {
            DisposalGuideline updatedData = disposalGuidelineService.updateDisposalGuideline(id, updatedDisposalGuideline);

            if (updatedData != null) {
                return new ResponseEntity<>(HttpStatus.OK); // Returns 200 OK if successfully updated
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Returns 404 Not Found if resource not found
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Returns 500 Internal Server Error for other exceptions
        }
    }

    // Handles DELETE requests to remove a disposal guideline by ID
    @DeleteMapping("/guideline/{id}")
    public ResponseEntity<HttpStatus> deleteGuidelineById(@PathVariable("id") long id) {
        try{
            disposalGuidelineService.deleteDisposalGuideline(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Returns 204 No Content after successful deletion

        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Returns 500 Internal Server Error on exception
        }
    }
}
