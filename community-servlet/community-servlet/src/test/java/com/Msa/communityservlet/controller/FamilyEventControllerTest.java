package com.Msa.communityservlet.controller;

import com.Msa.communityservlet.service.FamilyEventService;
import com.Msa.communityservlet.service.FamilyEventServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class FamilyEventControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    @Qualifier("eventServicer")
    FamilyEventServiceImpl familyEventService;

    @Test
    public void contextLoads() {
        assertNotNull(familyEventService);
    }

    @Test
    public void findAll() throws Exception {
        mockMvc.perform(get("/api/familyEvents"))
                .andExpect(status().isOk());
    }
}