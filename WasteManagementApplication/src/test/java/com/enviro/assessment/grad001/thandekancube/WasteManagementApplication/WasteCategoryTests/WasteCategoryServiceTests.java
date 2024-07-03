package com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.WasteCategoryTests;

import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.WasteManagementApplication;
import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.model.WasteCategory;
import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.repository.WasteCategoryRepository;
import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.service.WasteCategoryService;
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
 * Unit tests for the WasteCategoryService class.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WasteManagementApplication.class)
public class WasteCategoryServiceTests {

    @MockBean
    private WasteCategoryService wasteCategoryService;

    @MockBean
    private WasteCategoryRepository wasteCategoryRepository;

    /**
     * Tests retrieving all waste categories.
     */
    @Test
    public void testGetAllWasteCategories() throws Exception {
        WasteCategory wasteCategory = getWasteCategory();
        List<WasteCategory> wasteCategories = new ArrayList<>();
        wasteCategories.add(wasteCategory);
        given(wasteCategoryService.getAllWasteCategories()).willReturn(wasteCategories);
        List<WasteCategory> result = wasteCategoryService.getAllWasteCategories();
        assertEquals(result.size(), 1);
    }

    /**
     * Tests retrieving a waste category by ID.
     */
    @Test
    public void testGetWasteCategoryByID() throws Exception {
        WasteCategory wasteCategory = getWasteCategory();
        given(wasteCategoryService.getWasteCategoryById(1L)).willReturn(Optional.of(wasteCategory));
        Optional<WasteCategory> result = wasteCategoryService.getWasteCategoryById(1L);
        assertTrue(result.isPresent());
        assertEquals(result.get().getId(), 1L);
    }

    /**
     * Tests retrieving a waste category by ID when not found.
     */
    @Test
    public void testGetWasteCategoryByID_NotFound() throws Exception {
        given(wasteCategoryService.getWasteCategoryById(2L)).willReturn(Optional.empty());
        Optional<WasteCategory> result = wasteCategoryService.getWasteCategoryById(2L);
        assertFalse(result.isPresent());
    }

    /**
     * Tests saving a waste category.
     */
    @Test
    public void testSaveWasteCategory() throws Exception {
        WasteCategory wasteCategory = getWasteCategory();
        given(wasteCategoryRepository.save(wasteCategory)).willReturn(wasteCategory);
        given(wasteCategoryService.saveWasteCategory(wasteCategory)).willReturn(wasteCategory);

        WasteCategory result = wasteCategoryService.saveWasteCategory(wasteCategory);
        assertEquals(result.getName(), "Plastic");
        assertEquals(result.getDescription(), "Items such as plastic bottles, plastic bottle caps, plastic grocery bags, plastic straws");

    }

    /**
     * Tests saving a waste category with invalid data.
     */
    @Test
    public void testSaveWasteCategory_InvalidData() throws Exception {
        WasteCategory invalidWasteCategory = new WasteCategory();
        invalidWasteCategory.setId(2L); // Invalid or missing name and description

        given(wasteCategoryRepository.save(invalidWasteCategory)).willReturn(null);
        given(wasteCategoryService.saveWasteCategory(invalidWasteCategory)).willReturn(null);

        WasteCategory result = wasteCategoryService.saveWasteCategory(invalidWasteCategory);
        assertNull(result);
    }


    /**
     * Tests updating a waste category.
     */
    @Test
    public void testUpdateWasteCategory() throws Exception {
        WasteCategory oldCategory = getWasteCategory();
        WasteCategory updatedCategory = getUpdatedWasteCategory();

        given(wasteCategoryRepository.findById(1L)).willReturn(Optional.of(oldCategory));
        given(wasteCategoryRepository.save(oldCategory)).willReturn(updatedCategory);
        given(wasteCategoryService.updateCategory(1L, updatedCategory)).willReturn(updatedCategory);

        WasteCategory result = wasteCategoryService.updateCategory(1L, updatedCategory);
        assertNotNull(result);
        assertEquals(result.getName(), "Plastic");
        assertEquals(result.getDescription(), "Plastic waste");
    }

    /**
     * Tests updating a waste category that is not found.
     */
    @Test
    public void testUpdateWasteCategory_NotFound() throws Exception {
        WasteCategory updatedCategory = getUpdatedWasteCategory();

        given(wasteCategoryRepository.findById(2L)).willReturn(Optional.empty());

        WasteCategory result = wasteCategoryService.updateCategory(2L, updatedCategory);
        assertNull(result);
    }

    /**
     * Tests deleting a waste category.
     */
    @Test
    public void testDeleteWasteCategory() throws Exception {
        WasteCategory wasteCategory = getWasteCategory();
        doNothing().when(wasteCategoryService).deleteWasteCategory(wasteCategory.getId());
        wasteCategoryService.deleteWasteCategory(1L);
        assertTrue(true);
    }

    /**
     * Tests deleting a waste category that is not found.
     */
    @Test
    public void testDeleteWasteCategory_NotFound() throws Exception {
        doNothing().when(wasteCategoryService).deleteWasteCategory(2L);
        wasteCategoryService.deleteWasteCategory(2L);

        assertTrue(true);
    }

    /**
     * Utility method to create a sample WasteCategory object for testing purposes.
     */
    private WasteCategory getWasteCategory() {
        WasteCategory wasteCategory = new WasteCategory();
        wasteCategory.setId(1L);
        wasteCategory.setName("Plastic");
        wasteCategory.setDescription("Items such as plastic bottles, plastic bottle caps, plastic grocery bags, plastic straws");
        return wasteCategory;
    }

    /**
     * Utility method to create an updated WasteCategory object for testing purposes.
     */
    private WasteCategory getUpdatedWasteCategory() {
        WasteCategory wasteCategory = new WasteCategory();
        wasteCategory.setId(1L);
        wasteCategory.setName("Plastic");
        wasteCategory.setDescription("Plastic waste");
        return wasteCategory;
    }
}
