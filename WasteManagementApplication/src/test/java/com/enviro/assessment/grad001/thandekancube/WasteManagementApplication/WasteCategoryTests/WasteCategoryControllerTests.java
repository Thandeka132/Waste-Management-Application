package com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.WasteCategoryTests;

import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.WasteManagementApplication;
import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.controller.WasteCategoryController;
import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.model.WasteCategory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WasteManagementApplication.class)
@AutoConfigureMockMvc
public class WasteCategoryControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private WasteCategoryController wasteCategoryController;

    @Test
    public void testGetAllWasteCategories() throws Exception {
        WasteCategory wasteCategory = getWasteCategory();
        List<WasteCategory> wasteCategories = new ArrayList<>();
        wasteCategories.add(wasteCategory);

        ResponseEntity<List<WasteCategory>> responseEntity = ResponseEntity.ok(wasteCategories);
        given(wasteCategoryController.getAllWasteCategories()).willReturn(responseEntity);

        mvc.perform(get("/api/category").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(wasteCategory.getName())));
    }

    @Test
    public void testGetWasteCategoryById() throws Exception {
        WasteCategory wasteCategory = getWasteCategory();
        given(wasteCategoryController.getWasteCategoryById(1L)).willReturn(ResponseEntity.of(Optional.of(wasteCategory)));

        mvc.perform(get("/api/category/1").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is(wasteCategory.getName())));
    }

    @Test
    public void testUpdateWasteCategoryById() throws Exception {
        WasteCategory wasteCategory = getWasteCategory();
        given(wasteCategoryController.updateWasteCategoryById(1L, wasteCategory)).willReturn(new ResponseEntity<>(wasteCategory, HttpStatus.OK));

        mvc.perform(put("/api/category/1")
                        .content(asJson(wasteCategory))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllWasteCategoriesEmpty() throws Exception {
        List<WasteCategory> wasteCategories = new ArrayList<>();
        ResponseEntity<List<WasteCategory>> responseEntity = new ResponseEntity<>(wasteCategories, HttpStatus.NO_CONTENT);
        given(wasteCategoryController.getAllWasteCategories()).willReturn(responseEntity);

        mvc.perform(get("/api/category").contentType(APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetWasteCategoryByIdNotFound() throws Exception {
        given(wasteCategoryController.getWasteCategoryById(1L)).willReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

        mvc.perform(get("/api/category/1").contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    private WasteCategory getWasteCategory() {
        WasteCategory wasteCategory = new WasteCategory();
        wasteCategory.setId(1L);
        wasteCategory.setName("Picnic");
        wasteCategory.setDescription("Items such as plastic bottles, plastic bottle caps, plastic grocery bags, plastic straws");
        return wasteCategory;
    }

    private static String asJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
