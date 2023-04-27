package edu.miu.cs545.api.repository;

import edu.miu.cs545.api.entity.AccessToken;
import org.springframework.data.repository.CrudRepository;

public interface AccessTokenRepository extends CrudRepository<AccessToken, String> {
}
