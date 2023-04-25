package edu.miu.cs545.api.service;

import edu.miu.cs545.api.dto.AdministratorDto;
import edu.miu.cs545.api.repository.AdministratorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorServiceImpl implements AdministratorService{
    @Autowired
    AdministratorRepository administratorRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<AdministratorDto> getAll() {
        return administratorRepository.findAll().stream()
                .map(x->modelMapper.map(x, AdministratorDto.class))
                .toList();
    }
}
