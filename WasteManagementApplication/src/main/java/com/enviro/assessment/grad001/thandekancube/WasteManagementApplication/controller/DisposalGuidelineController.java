package com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.controller;

import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.model.DisposalGuideline;
import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.service.DisposalGuidelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class DisposalGuidelineController {

    @Autowired
    private DisposalGuidelineService disposalGuidelineService;

    @GetMapping("/guideline")
    public ResponseEntity<List<DisposalGuideline>> getAllDisposalGuidelines() {
        try {
            List<DisposalGuideline> disposalGuidelines = disposalGuidelineService.getAllDisposalGuidelines();

            if(disposalGuidelines.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(disposalGuidelines, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/guideline/{id}")
    public ResponseEntity<DisposalGuideline> getDisposalGuidelineById(@PathVariable("id") long id) {
        Optional<DisposalGuideline>  disposalGuidelines = disposalGuidelineService.getDisposalGuidelineById(id);

        if(disposalGuidelines.isPresent()) {
            return new ResponseEntity<>(disposalGuidelines.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/guideline")
    public ResponseEntity<DisposalGuideline> addGuideline(@RequestBody DisposalGuideline disposalGuideline) {
        try {
            DisposalGuideline guidelineObj = disposalGuidelineService.saveDisposalGuideline(disposalGuideline);
            return new ResponseEntity<>(guidelineObj, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/guideline/{id}")
    public ResponseEntity<DisposalGuideline> updateGuidelineById(
            @PathVariable("id") long id,
            @RequestBody DisposalGuideline updatedDisposalGuideline) {

        try {
            DisposalGuideline updatedData = disposalGuidelineService.updateDisposalGuideline(id, updatedDisposalGuideline);

            if (updatedData != null) {
                return new ResponseEntity<>(HttpStatus.OK); // 200 OK if successfully updated
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found if resource not found
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error for other exceptions
        }
    }

    @DeleteMapping("/guideline/{id}")
    public ResponseEntity<HttpStatus> deleteGuidelineById(@PathVariable("id") long id) {
        try{
            disposalGuidelineService.deleteDisposalGuideline(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
