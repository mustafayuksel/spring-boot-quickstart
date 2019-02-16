package com.mustafayuksel.quickstart.springbootquickstart.service;

import com.mustafayuksel.quickstart.springbootquickstart.request.MediatorRequest;
import com.mustafayuksel.quickstart.springbootquickstart.response.MediatorResponse;

public interface MediatorService {
    MediatorResponse doHttpCall(MediatorRequest mediatorRequest);
}