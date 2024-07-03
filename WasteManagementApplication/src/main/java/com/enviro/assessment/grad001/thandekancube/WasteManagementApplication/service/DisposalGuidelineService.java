package com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.service;

import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.model.DisposalGuideline;
import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.repository.DisposalGuidelinesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Marks the class as a Spring service component
public class DisposalGuidelineService {

    @Autowired // Injects the DisposalGuidelinesRepository dependency
    @SuppressWarnings("unused")
    private DisposalGuidelinesRepository disposalGuidelinesRepository;

    // Retrieves all disposal guidelines from the repository
    public List<DisposalGuideline> getAllDisposalGuidelines() {
        return disposalGuidelinesRepository.findAll();
    }

    // Retrieves a specific disposal guideline by ID
    public Optional<DisposalGuideline> getDisposalGuidelineById(Long id) {
        return disposalGuidelinesRepository.findById(id);
    }

    // Saves a new disposal guideline to the repository
    public DisposalGuideline saveDisposalGuideline(DisposalGuideline disposalGuideline) {
        return disposalGuidelinesRepository.save(disposalGuideline);
    }

    // Updates an existing disposal guideline by ID
    public DisposalGuideline updateDisposalGuideline(Long id, DisposalGuideline newDisposalGuideline) {
        Optional<DisposalGuideline> oldDisposalGuideLine = disposalGuidelinesRepository.findById(id);

        if(oldDisposalGuideLine.isPresent()) {
            DisposalGuideline updatedDisposalGuideline = oldDisposalGuideLine.get();
            updatedDisposalGuideline.setGuideline(newDisposalGuideline.getGuideline());
            updatedDisposalGuideline.setComplexity(newDisposalGuideline.getComplexity());

            return disposalGuidelinesRepository.save(updatedDisposalGuideline);
        } else {
            return null;
        }
    }

    // Deletes a disposal guideline by ID
    public void deleteDisposalGuideline(Long id) {
        disposalGuidelinesRepository.deleteById(id);
    }
}
