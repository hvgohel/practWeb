package com.dw.practWeb.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dw.practWeb.model.Sample;
import com.dw.practWeb.repository.SampleRepository;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class SampleServieImpl
{
    @Inject
    private SampleRepository sampleRepository;

    public Sample add(Sample sample)
    {
        return sampleRepository.save(sample);
    }
}
