package com.mustafayuksel.quickstart.springbootquickstart.controller;

import com.mustafayuksel.quickstart.springbootquickstart.request.MediatorRequest;
import com.mustafayuksel.quickstart.springbootquickstart.response.MediatorResponse;
import com.mustafayuksel.quickstart.springbootquickstart.service.MediatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/mediators")
public class MediatorRestController {
    private final MediatorService mediatorService;

    @Autowired
    public MediatorRestController(MediatorService mediatorService) {
        this.mediatorService = mediatorService;
    }

    @RequestMapping(value = "/xml", method = RequestMethod.POST,
            produces = {"application/xml", "test/xml"}, consumes = {"application/xml"})
    public MediatorResponse callWithXML(@Valid @RequestBody MediatorRequest mediatorRequest) {
        return mediatorService.doHttpCall(mediatorRequest);
    }

    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public MediatorResponse callWithJSON(@Valid @RequestBody MediatorRequest mediatorRequest) {
        return mediatorService.doHttpCall(mediatorRequest);
    }
}