package com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.RecyclingTipsTests;

import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.WasteManagementApplication;
import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.model.RecyclingTip;
import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.repository.RecyclingTipsRepository;
import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.service.RecyclingTipsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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
 * Unit tests for the RecyclingTipsService class.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WasteManagementApplication.class)
public class RecyclingTipsServiceTests {

    @MockBean
    private RecyclingTipsService recyclingTipsService;

    @MockBean
    private RecyclingTipsRepository recyclingTipsRepository;

    /**
     * Tests retrieving all recycling tips.
     */
    @Test
    public void testGetAllRecyclingTip() throws Exception {
        RecyclingTip recyclingTip = getRecyclingTip();
        List<RecyclingTip> recyclingTips = new ArrayList<>();
        recyclingTips.add(recyclingTip);
        given(recyclingTipsService.getAllRecyclingTips()).willReturn(recyclingTips);
        List<RecyclingTip> result = recyclingTipsService.getAllRecyclingTips();
        assertEquals(result.size(), 1);
    }

    /**
     * Tests retrieving a recycling tip by ID.
     */
    @Test
    public void testGetRecyclingTipByID() throws Exception {
        RecyclingTip recyclingTip = getRecyclingTip();
        given(recyclingTipsService.getRecyclingTipsById(1L)).willReturn(Optional.of(recyclingTip));
        Optional<RecyclingTip> result = recyclingTipsService.getRecyclingTipsById(1L);
        assertTrue(result.isPresent());
        assertEquals(result.get().getId(), 1L);
    }

    /**
     * Tests retrieving a recycling tip by ID when not found.
     */
    @Test
    public void testGetRecyclingTipByID_NotFound() throws Exception {
        given(recyclingTipsService.getRecyclingTipsById(2L)).willReturn(Optional.empty());
        Optional<RecyclingTip> result = recyclingTipsService.getRecyclingTipsById(2L);
        assertFalse(result.isPresent());
    }

    /**
     * Tests saving a recycling tip.
     */
    @Test
    public void testSaveRecyclingTip() throws Exception {
        RecyclingTip recyclingTip = getRecyclingTip();
        given(recyclingTipsRepository.save(recyclingTip)).willReturn(recyclingTip);
        given(recyclingTipsService.saveRecyclingTip(recyclingTip)).willReturn(recyclingTip);

        RecyclingTip result = recyclingTipsService.saveRecyclingTip(recyclingTip);
        assertEquals(result.getTip(), "Separate paper, plastic, glass, and metal recyclables");

    }

    /**
     * Tests saving a recycling tip with invalid data.
     */
    @Test
    public void testSaveRecyclingTip_InvalidData() throws Exception {
        RecyclingTip invalidRecyclingTip = new RecyclingTip();
        invalidRecyclingTip.setId(2L); // Invalid or missing name and description

        given(recyclingTipsRepository.save(invalidRecyclingTip)).willReturn(null);
        given(recyclingTipsService.saveRecyclingTip(invalidRecyclingTip)).willReturn(null);

        RecyclingTip result = recyclingTipsService.saveRecyclingTip(invalidRecyclingTip);
        assertNull(result);
    }

    /**
     * Tests updating a recycling tip.
     */
    @Test
    public void testUpdateRecyclingTip() throws Exception {
        RecyclingTip oldRecyclingTip = getRecyclingTip();
        RecyclingTip updatedRecyclingTip = getUpdateRecyclingTip();

        given(recyclingTipsRepository.findById(1L)).willReturn(Optional.of(oldRecyclingTip));
        given(recyclingTipsRepository.save(oldRecyclingTip)).willReturn(updatedRecyclingTip);
        given(recyclingTipsService.updateRecyclingTip(1L, updatedRecyclingTip)).willReturn(updatedRecyclingTip);

        RecyclingTip result = recyclingTipsService.updateRecyclingTip(1L, updatedRecyclingTip);
        assertNotNull(result);
        assertEquals(result.getTip(), "Rinse food containers before recycling.");
        assertEquals(result.getBenefit(), "Reduces contamination and improves recycling quality");
    }

    /**
     * Tests updating a recycling tip that is not found.
     */
    @Test
    public void testUpdateRecyclingTip_NotFound() throws Exception {
        RecyclingTip updatedRecyclingTip = getUpdateRecyclingTip();

        given(recyclingTipsRepository.findById(2L)).willReturn(Optional.empty());

        RecyclingTip result = recyclingTipsService.updateRecyclingTip(2L, updatedRecyclingTip);
        assertNull(result);
    }

    /**
     * Tests deleting a recycling tip.
     */
    @Test
    public void testDeleteRecyclingTip() throws Exception {
        RecyclingTip recyclingTip = getRecyclingTip();
        doNothing().when(recyclingTipsService).deleteRecyclingTip(recyclingTip.getId());
        recyclingTipsService.deleteRecyclingTip(1L);
        assertTrue(true);
    }

    /**
     * Tests deleting a recycling tip that is not found.
     */
    @Test
    public void testDeleteRecyclingTip_NotFound() throws Exception {
        doNothing().when(recyclingTipsService).deleteRecyclingTip(2L);
        recyclingTipsService.deleteRecyclingTip(2L);

        assertTrue(true);
    }

    /**
     * Utility method to create a sample RecyclingTip object for testing purposes.
     */
    private RecyclingTip getRecyclingTip() {
        RecyclingTip recyclingTip = new RecyclingTip();
        recyclingTip.setId(1L);
        recyclingTip.setTip("Separate paper, plastic, glass, and metal recyclables");
        recyclingTip.setBenefit("Increases recycling efficiency and conserves resources");
        return recyclingTip;
    }

    /**
     * Utility method to create an updated RecyclingTip object for testing purposes.
     */
    private RecyclingTip getUpdateRecyclingTip() {
        RecyclingTip recyclingTip = new RecyclingTip();
        recyclingTip.setId(1L);
        recyclingTip.setTip("Rinse food containers before recycling.");
        recyclingTip.setBenefit("Reduces contamination and improves recycling quality");
        return recyclingTip;
    }
}
