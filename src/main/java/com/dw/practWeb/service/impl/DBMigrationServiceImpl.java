package com.dw.practWeb.service.impl;

import com.dw.practWeb.service.DBMigrationService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class DBMigrationServiceImpl implements DBMigrationService {

}
