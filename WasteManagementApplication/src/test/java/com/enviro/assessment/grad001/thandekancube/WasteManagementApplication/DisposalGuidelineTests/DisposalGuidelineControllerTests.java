package com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.DisposalGuidelineTests;

import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.WasteManagementApplication;
import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.controller.DisposalGuidelineController;
import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.model.DisposalGuideline;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WasteManagementApplication.class)
@AutoConfigureMockMvc
public class DisposalGuidelineControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DisposalGuidelineController disposalGuidelineController;

    @Test
    public void testGetAllDisposalGuidelines() throws Exception {
        DisposalGuideline disposalGuideline = getDisposalGuideline();
        List<DisposalGuideline> wasteCategories = new ArrayList<>();
        wasteCategories.add(disposalGuideline);

        ResponseEntity<List<DisposalGuideline>> responseEntity = ResponseEntity.ok(wasteCategories);
        given(disposalGuidelineController.getAllDisposalGuidelines()).willReturn(responseEntity);

        mvc.perform(get("/api/guideline").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].guideline", is(disposalGuideline.getGuideline())));
    }

    @Test
    public void testGetDisposalGuidelineById() throws Exception {
        DisposalGuideline disposalGuideline = getDisposalGuideline();
        given(disposalGuidelineController.getDisposalGuidelineById(1L)).willReturn(ResponseEntity.of(Optional.of(disposalGuideline)));

        mvc.perform(get("/api/guideline/1").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("guideline", is(disposalGuideline.getGuideline())));
    }


    private DisposalGuideline getDisposalGuideline() {
        DisposalGuideline disposalGuideline = new DisposalGuideline();
        disposalGuideline.setId(1);
        disposalGuideline.setGuideline("Avoid excessive build-up of waste, especially where hazardous waste may pose a risk to human health or the environment");
        disposalGuideline.setComplexity("Easy");
        return disposalGuideline;
    }
}
