package com.dw.practWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.practWeb.model.Sample;

public interface SampleRepository extends JpaRepository<Sample, String> {

}
