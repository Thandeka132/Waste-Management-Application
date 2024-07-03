package com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.service;

import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.model.RecyclingTip;
import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.repository.RecyclingTipsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecyclingTipsService {

    @Autowired
    @SuppressWarnings("unused")
    private RecyclingTipsRepository recyclingTipsRepository;

    public List<RecyclingTip> getAllRecyclingTips() {
        return recyclingTipsRepository.findAll();
    }

    public Optional<RecyclingTip> getRecyclingTipsById(Long id) {
        return recyclingTipsRepository.findById(id);
    }

    public RecyclingTip saveRecyclingTip(RecyclingTip recyclingTip) {
        return recyclingTipsRepository.save(recyclingTip);
    }

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

    public void deleteRecyclingTip(Long id) {
        recyclingTipsRepository.deleteById(id);
    }
}
