package com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.controller;

import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.model.RecyclingTip;
import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.service.RecyclingTipsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class RecyclingTipsController {

    @Autowired
    private RecyclingTipsService recyclingTipsService;

    @GetMapping("/tip")
    public ResponseEntity<List<RecyclingTip>> getAllRecyclingTips() {
        try {
            List<RecyclingTip> recyclingTips = recyclingTipsService.getAllRecyclingTips();

            if(recyclingTips.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(recyclingTips, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tip/{id}")
    public ResponseEntity<RecyclingTip> getRecyclingTipById(@PathVariable("id") long id) {
        Optional<RecyclingTip> recyclingTips = recyclingTipsService.getRecyclingTipsById(id);

        if(recyclingTips.isPresent()) {
            return new ResponseEntity<>(recyclingTips.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/tip")
    public ResponseEntity<RecyclingTip> saveTip(@RequestBody RecyclingTip recyclingTip) {
        try{
            RecyclingTip tipObj = recyclingTipsService.saveRecyclingTip(recyclingTip);
            return new ResponseEntity<>(tipObj, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/tip/{id}")
    public ResponseEntity<RecyclingTip> updateRecyclingTipById(@PathVariable("id") long id, @RequestBody RecyclingTip updatedRecyclingTip) {
        try {
            RecyclingTip updatedData = recyclingTipsService.updateRecyclingTip(id, updatedRecyclingTip);

            if(updatedData != null) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("tip/{id}")
    public ResponseEntity<HttpStatus> deleteRecyclingTipById(@PathVariable("id") long id) {
        try{
            recyclingTipsService.deleteRecyclingTip(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
