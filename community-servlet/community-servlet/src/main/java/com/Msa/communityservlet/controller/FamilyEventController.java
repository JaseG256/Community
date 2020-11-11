package com.Msa.communityservlet.controller;

import com.Msa.communityservlet.model.FamilyEvent;
import com.Msa.communityservlet.service.FamilyEventService;
import com.Msa.communityservlet.service.FamilyEventServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class FamilyEventController {

    @Qualifier("eventServicer")
    private final FamilyEventServiceImpl eventService;

    public FamilyEventController(FamilyEventServiceImpl eventService) {
        this.eventService = eventService;
    }

//    @PreAuthorize("hasAnyRole('ADMIN, USER')")
    @GetMapping(path = "/familyEvents")
    public List<FamilyEvent> findAll() {
        return eventService.listAll();
    }
}
