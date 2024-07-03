package com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.RecyclingTipsTests;

import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.WasteManagementApplication;
import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.controller.RecyclingTipsController;
import com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.model.RecyclingTip;
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
public class RecyclingTipControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RecyclingTipsController recyclingTipsController;

    @Test
    public void testGetAllRecyclingTips() throws Exception {
        RecyclingTip recyclingTip = getRecyclingTip();
        List<RecyclingTip> recyclingTips = new ArrayList<>();
        recyclingTips.add(recyclingTip);

        ResponseEntity<List<RecyclingTip>> responseEntity = ResponseEntity.ok(recyclingTips);
        given(recyclingTipsController.getAllRecyclingTips()).willReturn(responseEntity);

        mvc.perform(get("/api/tip").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].tip", is(recyclingTip.getTip())));
    }

    @Test
    public void testGetRecyclingTipById() throws Exception {
        RecyclingTip recyclingTip = getRecyclingTip();
        given(recyclingTipsController.getRecyclingTipById(1L)).willReturn(ResponseEntity.of(Optional.of(recyclingTip)));

        mvc.perform(get("/api/tip/1").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("tip", is(recyclingTip.getTip())));
    }

    @Test
    public void testUpdateRecyclingTipById() throws Exception {
        RecyclingTip recyclingTip = getRecyclingTip();
        given(recyclingTipsController.updateRecyclingTipById(1L, recyclingTip)).willReturn(new ResponseEntity<>(recyclingTip, HttpStatus.OK));

        mvc.perform(put("/api/tip/1")
                        .content(asJson(recyclingTip))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllRecyclingTipsEmpty() throws Exception {
        List<RecyclingTip> recyclingTips = new ArrayList<>();
        ResponseEntity<List<RecyclingTip>> responseEntity = new ResponseEntity<>(recyclingTips, HttpStatus.NO_CONTENT);
        given(recyclingTipsController.getAllRecyclingTips()).willReturn(responseEntity);

        mvc.perform(get("/api/tip").contentType(APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetRecyclingTipByIdNotFound() throws Exception {
        given(recyclingTipsController.getRecyclingTipById(1L)).willReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

        mvc.perform(get("/api/tip/1").contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    private RecyclingTip getRecyclingTip() {
        RecyclingTip recyclingTip = new RecyclingTip();
        recyclingTip.setId(1L);
        recyclingTip.setTip("Separate paper, plastic, glass, and metal recyclables");
        recyclingTip.setBenefit("Increases recycling efficiency and conserves resources");
        return recyclingTip;
    }

    private static String asJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
