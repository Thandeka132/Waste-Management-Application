package com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.service;

import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.model.RecyclingTip;
import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.repository.RecyclingTipsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Marks the class as a Spring service component
public class RecyclingTipsService {

    @Autowired // Injects the RecyclingTipsRepository dependency
    @SuppressWarnings("unused")
    private RecyclingTipsRepository recyclingTipsRepository;

    // Retrieves all recycling tips from the repository
    public List<RecyclingTip> getAllRecyclingTips() {
        return recyclingTipsRepository.findAll();
    }

    // Retrieves a specific recycling tip by ID
    public Optional<RecyclingTip> getRecyclingTipsById(Long id) {
        return recyclingTipsRepository.findById(id);
    }

    // Saves a new recycling tip to the repository
    public RecyclingTip saveRecyclingTip(RecyclingTip recyclingTip) {
        return recyclingTipsRepository.save(recyclingTip);
    }

    // Updates an existing recycling tip by ID
    public RecyclingTip updateRecyclingTip(Long id, RecyclingTip newRecyclingTip) {
        Optional<RecyclingTip> oldRecyclingTip = recyclingTipsRepository.findById(id);

        if(oldRecyclingTip.isPresent()) {
            RecyclingTip updatedRecyclingTip = oldRecyclingTip.get();
            updatedRecyclingTip.setTip(newRecyclingTip.getTip());
            updatedRecyclingTip.setBenefit(newRecyclingTip.getBenefit());

            return recyclingTipsRepository.save(updatedRecyclingTip);
        } else {
            return null;
        }
    }

    // Deletes a recycling tip by ID
    public void deleteRecyclingTip(Long id) {
        recyclingTipsRepository.deleteById(id);
    }
}
