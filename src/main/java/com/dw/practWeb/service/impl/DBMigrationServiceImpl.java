package com.dw.practWeb.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dw.practWeb.service.DBMigrationService;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class DBMigrationServiceImpl implements DBMigrationService {

}
