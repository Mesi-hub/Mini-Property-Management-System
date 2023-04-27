package edu.miu.cs545.api.service;

import edu.miu.cs545.api.dto.SavedPropertyDto;
import edu.miu.cs545.api.entity.User;

import java.util.List;

public interface SavedPropertyService {
    List<SavedPropertyDto> getAll();
    List<SavedPropertyDto> getSavedPropertiesForUserOrderByDateTimeDesc(User user);
    SavedPropertyDto saveProperty(SavedPropertyDto savedPropertyDto, User user);
    void delete(Long id, User user);
}
