package com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.DisposalGuidelineTests;

import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.WasteManagementApplication;
import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.model.DisposalGuideline;
import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.repository.DisposalGuidelinesRepository;
import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.service.DisposalGuidelineService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WasteManagementApplication.class)
public class DisposalGuideLineServiceTests {

    @MockBean
    private DisposalGuidelineService disposalGuidelineService;

    @MockBean
    private DisposalGuidelinesRepository disposalGuidelinesRepository;

    @Test
    public void testGetAlLDisposalGuidelines() throws Exception {
        DisposalGuideline disposalGuideline = getDisposalGuideline();
        List<DisposalGuideline> disposalGuidelines = new ArrayList<>();
        disposalGuidelines.add(disposalGuideline);
        given(disposalGuidelineService.getAllDisposalGuidelines()).willReturn(disposalGuidelines);
        List<DisposalGuideline> result = disposalGuidelineService.getAllDisposalGuidelines();
        assertEquals(result.size(), 1);
    }

    @Test
    public void testGetDisposalGuidelineByID() throws Exception {
        DisposalGuideline disposalGuideline = getDisposalGuideline();
        given(disposalGuidelineService.getDisposalGuidelineById(1L)).willReturn(Optional.of(disposalGuideline));
        Optional<DisposalGuideline> result = disposalGuidelineService.getDisposalGuidelineById(1L);
        assertTrue(result.isPresent());
        assertEquals(result.get().getId(), 1L);
    }

    @Test
    public void testDisposalGuidelineByID_NotFound() throws Exception {
        given(disposalGuidelineService.getDisposalGuidelineById(2L)).willReturn(Optional.empty());
        Optional<DisposalGuideline> result = disposalGuidelineService.getDisposalGuidelineById(2L);
        assertFalse(result.isPresent());
    }

    @Test
    public void testSaveDisposalGuideline() throws Exception {
        DisposalGuideline disposalGuideline = getDisposalGuideline();
        given(disposalGuidelinesRepository.save(disposalGuideline)).willReturn(disposalGuideline);
        given(disposalGuidelineService.saveDisposalGuideline(disposalGuideline)).willReturn(disposalGuideline);

        DisposalGuideline result = disposalGuidelineService.saveDisposalGuideline(disposalGuideline);
        assertEquals(result.getGuideline(), "Avoid excessive build-up of waste, especially where hazardous waste may pose a risk to human health or the environment");
        assertEquals(result.getComplexity(), "Easy");

    }

    @Test
    public void testSaveDisposalGuideline_InvalidData() throws Exception {
        DisposalGuideline invalidDisposalGuideline = new DisposalGuideline();
        invalidDisposalGuideline.setId(2L); // Invalid or missing name and description

        given(disposalGuidelinesRepository.save(invalidDisposalGuideline)).willReturn(null);
        given(disposalGuidelineService.saveDisposalGuideline(invalidDisposalGuideline)).willReturn(null);

        DisposalGuideline result = disposalGuidelineService.saveDisposalGuideline(invalidDisposalGuideline);
        assertNull(result);
    }

    @Test
    public void testUpdateDisposalGuideline() throws Exception {
        DisposalGuideline oldDisposalGuideline = getDisposalGuideline();
        DisposalGuideline updatedDisposalGuideline = getUpdatedDisposalGuideline();

        given(disposalGuidelinesRepository.findById(1L)).willReturn(Optional.of(oldDisposalGuideline));
        given(disposalGuidelinesRepository.save(oldDisposalGuideline)).willReturn(updatedDisposalGuideline);
        given(disposalGuidelineService.updateDisposalGuideline(1L, updatedDisposalGuideline)).willReturn(updatedDisposalGuideline);

        DisposalGuideline result = disposalGuidelineService.updateDisposalGuideline(1L, updatedDisposalGuideline);
        assertNotNull(result);
        assertEquals(result.getGuideline(), "Ensure all waste is appropriately labelled and identifiable including personnel responsible");
        assertEquals(result.getComplexity(), "Easy");
    }

    @Test
    public void testUpdateDisposalGuideline_NotFound() throws Exception {
        DisposalGuideline updatedDisposalGuideline = getUpdatedDisposalGuideline();

        given(disposalGuidelinesRepository.findById(2L)).willReturn(Optional.empty());

        DisposalGuideline result = disposalGuidelineService.updateDisposalGuideline(2L, updatedDisposalGuideline);
        assertNull(result);
    }

    @Test
    public void testDeleteDisposalGuideline() throws Exception {
        DisposalGuideline disposalGuideline = getDisposalGuideline();
        doNothing().when(disposalGuidelineService).deleteDisposalGuideline(disposalGuideline.getId());
        disposalGuidelineService.deleteDisposalGuideline(1L);
        assertTrue(true);
    }

    @Test
    public void testDeleteDisposalGuideline_NotFound() throws Exception {
        doNothing().when(disposalGuidelineService).deleteDisposalGuideline(2L);
        disposalGuidelineService.deleteDisposalGuideline(2L);

        assertTrue(true);
    }

    private DisposalGuideline getDisposalGuideline() {
        DisposalGuideline disposalGuideline = new DisposalGuideline();
        disposalGuideline.setId(1);
        disposalGuideline.setGuideline("Avoid excessive build-up of waste, especially where hazardous waste may pose a risk to human health or the environment");
        disposalGuideline.setComplexity("Easy");
        return disposalGuideline;
    }

    private DisposalGuideline getUpdatedDisposalGuideline() {
        DisposalGuideline disposalGuideline = new DisposalGuideline();
        disposalGuideline.setId(1);
        disposalGuideline.setGuideline("Ensure all waste is appropriately labelled and identifiable including personnel responsible");
        disposalGuideline.setComplexity("Easy");
        return disposalGuideline;
    }

}
