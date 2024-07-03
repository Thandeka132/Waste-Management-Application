package com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.DisposalGuidelineTests;

import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.WasteManagementApplication;
import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.controller.DisposalGuidelineController;
import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.model.DisposalGuideline;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests for the DisposalGuidelineController class.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WasteManagementApplication.class)
@AutoConfigureMockMvc
public class DisposalGuidelineControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DisposalGuidelineController disposalGuidelineController;

    /**
     * Tests the endpoint to retrieve all disposal guidelines.
     */
    @Test
    public void testGetAllDisposalGuidelines() throws Exception {
        // Prepare test data
        DisposalGuideline disposalGuideline = getDisposalGuideline();
        List<DisposalGuideline> disposalGuidelines = new ArrayList<>();
        disposalGuidelines.add(disposalGuideline);

        // Mock controller response
        ResponseEntity<List<DisposalGuideline>> responseEntity = ResponseEntity.ok(disposalGuidelines);
        given(disposalGuidelineController.getAllDisposalGuidelines()).willReturn(responseEntity);

        // Perform GET request and verify response
        mvc.perform(get("/api/guideline").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].guideline", is(disposalGuideline.getGuideline())));
    }

    /**
     * Tests the endpoint to retrieve a specific disposal guideline by ID.
     */
    @Test
    public void testGetDisposalGuidelineById() throws Exception {
        // Prepare test data
        DisposalGuideline disposalGuideline = getDisposalGuideline();
        given(disposalGuidelineController.getDisposalGuidelineById(1L)).willReturn(ResponseEntity.of(Optional.of(disposalGuideline)));

        // Perform GET request and verify response
        mvc.perform(get("/api/guideline/1").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("guideline", is(disposalGuideline.getGuideline())));
    }

    /**
     * Tests the endpoint to update a disposal guideline by ID.
     */
    @Test
    public void testUpdateDisposalGuidelineById() throws Exception {
        // Prepare test data
        DisposalGuideline disposalGuideline = getDisposalGuideline();
        given(disposalGuidelineController.updateGuidelineById(1L, disposalGuideline)).willReturn(new ResponseEntity<>(disposalGuideline, HttpStatus.OK));

        // Perform PUT request and verify response
        mvc.perform(put("/api/guideline/1")
                        .content(asJson(disposalGuideline))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * Tests the endpoint to retrieve all disposal guidelines when the list is empty.
     */
    @Test
    public void testGetAllDisposalGuidelinesEmpty() throws Exception {
        // Prepare test data
        List<DisposalGuideline> disposalGuidelines = new ArrayList<>();
        ResponseEntity<List<DisposalGuideline>> responseEntity = new ResponseEntity<>(disposalGuidelines, HttpStatus.NO_CONTENT);
        given(disposalGuidelineController.getAllDisposalGuidelines()).willReturn(responseEntity);

        // Perform GET request and verify response
        mvc.perform(get("/api/guideline").contentType(APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    /**
     * Tests the endpoint to retrieve a specific disposal guideline by ID when not found.
     */
    @Test
    public void testGetDisposalGuidelineByIdNotFound() throws Exception {
        // Mock controller response for not found case
        given(disposalGuidelineController.getDisposalGuidelineById(1L)).willReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

        // Perform GET request and verify response
        mvc.perform(get("/api/guideline/1").contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    /**
     * Utility method to create a sample DisposalGuideline object for testing purposes.
     */
    private DisposalGuideline getDisposalGuideline() {
        DisposalGuideline disposalGuideline = new DisposalGuideline();
        disposalGuideline.setId(1L);
        disposalGuideline.setGuideline("Avoid excessive build-up of waste, especially where hazardous waste may pose a risk to human health or the environment");
        disposalGuideline.setComplexity("Easy");
        return disposalGuideline;
    }

    /**
     * Utility method to convert an object to its JSON representation.
     */
    private static String asJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
