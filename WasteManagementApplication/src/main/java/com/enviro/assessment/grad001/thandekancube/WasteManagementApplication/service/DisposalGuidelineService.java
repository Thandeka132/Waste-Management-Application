package com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.service;

import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.model.DisposalGuideline;
import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.repository.DisposalGuidelinesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisposalGuidelineService {

    @Autowired
    @SuppressWarnings("unused")
    private DisposalGuidelinesRepository disposalGuidelinesRepository;

    public List<DisposalGuideline> getAllDisposalGuidelines() {
        return disposalGuidelinesRepository.findAll();
    }

    public Optional<DisposalGuideline> getDisposalGuidelineById(Long id) {
        return disposalGuidelinesRepository.findById(id);
    }

    public DisposalGuideline saveDisposalGuideline(DisposalGuideline disposalGuideline) {
        return disposalGuidelinesRepository.save(disposalGuideline);
    }

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

    public void deleteDisposalGuideline(Long id) {
        disposalGuidelinesRepository.deleteById(id);
    }
}
