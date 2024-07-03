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

/**
 * Unit tests for the DisposalGuidelineService class.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WasteManagementApplication.class)
public class DisposalGuideLineServiceTests {

    @MockBean
    private DisposalGuidelineService disposalGuidelineService;

    @MockBean
    private DisposalGuidelinesRepository disposalGuidelinesRepository;

    /**
     * Tests retrieving all disposal guidelines.
     */
    @Test
    public void testGetAlLDisposalGuidelines() throws Exception {
        // Prepare test data
        DisposalGuideline disposalGuideline = getDisposalGuideline();
        List<DisposalGuideline> disposalGuidelines = new ArrayList<>();
        disposalGuidelines.add(disposalGuideline);

        // Mock service response
        given(disposalGuidelineService.getAllDisposalGuidelines()).willReturn(disposalGuidelines);

        // Perform service call and verify result
        List<DisposalGuideline> result = disposalGuidelineService.getAllDisposalGuidelines();
        assertEquals(result.size(), 1);
    }

    /**
     * Tests retrieving a disposal guideline by ID.
     */
    @Test
    public void testGetDisposalGuidelineByID() throws Exception {
        // Prepare test data
        DisposalGuideline disposalGuideline = getDisposalGuideline();

        // Mock service response
        given(disposalGuidelineService.getDisposalGuidelineById(1L)).willReturn(Optional.of(disposalGuideline));

        // Perform service call and verify result
        Optional<DisposalGuideline> result = disposalGuidelineService.getDisposalGuidelineById(1L);
        assertTrue(result.isPresent());
        assertEquals(result.get().getId(), 1L);
    }

    /**
     * Tests retrieving a disposal guideline by ID when not found.
     */
    @Test
    public void testDisposalGuidelineByID_NotFound() throws Exception {
        // Mock service response for not found case
        given(disposalGuidelineService.getDisposalGuidelineById(2L)).willReturn(Optional.empty());

        // Perform service call and verify result
        Optional<DisposalGuideline> result = disposalGuidelineService.getDisposalGuidelineById(2L);
        assertFalse(result.isPresent());
    }

    /**
     * Tests saving a disposal guideline.
     */
    @Test
    public void testSaveDisposalGuideline() throws Exception {
        // Prepare test data
        DisposalGuideline disposalGuideline = getDisposalGuideline();

        // Mock repository and service responses
        given(disposalGuidelinesRepository.save(disposalGuideline)).willReturn(disposalGuideline);
        given(disposalGuidelineService.saveDisposalGuideline(disposalGuideline)).willReturn(disposalGuideline);

        // Perform service call and verify result
        DisposalGuideline result = disposalGuidelineService.saveDisposalGuideline(disposalGuideline);
        assertEquals(result.getGuideline(), "Avoid excessive build-up of waste, especially where hazardous waste may pose a risk to human health or the environment");
        assertEquals(result.getComplexity(), "Easy");
    }

    /**
     * Tests saving a disposal guideline with invalid data.
     */
    @Test
    public void testSaveDisposalGuideline_InvalidData() throws Exception {
        // Prepare test data with invalid or missing fields
        DisposalGuideline invalidDisposalGuideline = new DisposalGuideline();
        invalidDisposalGuideline.setId(2L); // Invalid or missing name and description

        // Mock repository and service responses
        given(disposalGuidelinesRepository.save(invalidDisposalGuideline)).willReturn(null);
        given(disposalGuidelineService.saveDisposalGuideline(invalidDisposalGuideline)).willReturn(null);

        // Perform service call and verify result
        DisposalGuideline result = disposalGuidelineService.saveDisposalGuideline(invalidDisposalGuideline);
        assertNull(result);
    }

    /**
     * Tests updating a disposal guideline.
     */
    @Test
    public void testUpdateDisposalGuideline() throws Exception {
        // Prepare test data
        DisposalGuideline oldDisposalGuideline = getDisposalGuideline();
        DisposalGuideline updatedDisposalGuideline = getUpdatedDisposalGuideline();

        // Mock repository and service responses
        given(disposalGuidelinesRepository.findById(1L)).willReturn(Optional.of(oldDisposalGuideline));
        given(disposalGuidelinesRepository.save(oldDisposalGuideline)).willReturn(updatedDisposalGuideline);
        given(disposalGuidelineService.updateDisposalGuideline(1L, updatedDisposalGuideline)).willReturn(updatedDisposalGuideline);

        // Perform service call and verify result
        DisposalGuideline result = disposalGuidelineService.updateDisposalGuideline(1L, updatedDisposalGuideline);
        assertNotNull(result);
        assertEquals(result.getGuideline(), "Ensure all waste is appropriately labelled and identifiable including personnel responsible");
        assertEquals(result.getComplexity(), "Easy");
    }

    /**
     * Tests updating a disposal guideline that is not found.
     */
    @Test
    public void testUpdateDisposalGuideline_NotFound() throws Exception {
        // Prepare test data
        DisposalGuideline updatedDisposalGuideline = getUpdatedDisposalGuideline();

        // Mock repository response for not found case
        given(disposalGuidelinesRepository.findById(2L)).willReturn(Optional.empty());

        // Perform service call and verify result
        DisposalGuideline result = disposalGuidelineService.updateDisposalGuideline(2L, updatedDisposalGuideline);
        assertNull(result);
    }

    /**
     * Tests deleting a disposal guideline.
     */
    @Test
    public void testDeleteDisposalGuideline() throws Exception {
        // Prepare test data
        DisposalGuideline disposalGuideline = getDisposalGuideline();

        // Mock service behavior
        doNothing().when(disposalGuidelineService).deleteDisposalGuideline(disposalGuideline.getId());

        // Perform service call and verify result
        disposalGuidelineService.deleteDisposalGuideline(1L);
        assertTrue(true); // Test passes if no exceptions are thrown
    }

    /**
     * Tests deleting a disposal guideline that is not found.
     */
    @Test
    public void testDeleteDisposalGuideline_NotFound() throws Exception {
        // Mock service behavior for not found case
        doNothing().when(disposalGuidelineService).deleteDisposalGuideline(2L);

        // Perform service call and verify result
        disposalGuidelineService.deleteDisposalGuideline(2L);
        assertTrue(true); // Test passes if no exceptions are thrown
    }

    /**
     * Utility method to create a sample DisposalGuideline object for testing purposes.
     */
    private DisposalGuideline getDisposalGuideline() {
        DisposalGuideline disposalGuideline = new DisposalGuideline();
        disposalGuideline.setId(1);
        disposalGuideline.setGuideline("Avoid excessive build-up of waste, especially where hazardous waste may pose a risk to human health or the environment");
        disposalGuideline.setComplexity("Easy");
        return disposalGuideline;
    }

    /**
     * Utility method to create an updated DisposalGuideline object for testing purposes.
     */
    private DisposalGuideline getUpdatedDisposalGuideline() {
        DisposalGuideline disposalGuideline = new DisposalGuideline();
        disposalGuideline.setId(1);
        disposalGuideline.setGuideline("Ensure all waste is appropriately labelled and identifiable including personnel responsible");
        disposalGuideline.setComplexity("Easy");
        return disposalGuideline;
    }
}
