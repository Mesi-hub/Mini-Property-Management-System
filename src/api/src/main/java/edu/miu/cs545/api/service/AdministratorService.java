package edu.miu.cs545.api.service;

import edu.miu.cs545.api.dto.AdministratorDto;

import java.util.List;

public interface AdministratorService {
    List<AdministratorDto> getAll();
}
