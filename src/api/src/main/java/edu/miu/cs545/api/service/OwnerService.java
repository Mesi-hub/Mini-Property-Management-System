package edu.miu.cs545.api.service;

import edu.miu.cs545.api.dto.AdministratorDto;
import edu.miu.cs545.api.dto.CustomerDto;
import edu.miu.cs545.api.dto.OwnerDto;
import edu.miu.cs545.api.entity.Owner;

import java.util.List;

public interface OwnerService {

    List<OwnerDto> getAll();

    Owner findById(long id);

    boolean save(Owner owner);

}
