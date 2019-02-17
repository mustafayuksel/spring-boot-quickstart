package com.mustafayuksel.quickstart.springbootquickstart.service;

import java.io.IOException;

import com.mustafayuksel.quickstart.springbootquickstart.request.MediatorRequest;
import com.mustafayuksel.quickstart.springbootquickstart.response.MediatorResponse;

public interface MediatorService {
    MediatorResponse doHttpCall(MediatorRequest mediatorRequest) throws IOException ;
}