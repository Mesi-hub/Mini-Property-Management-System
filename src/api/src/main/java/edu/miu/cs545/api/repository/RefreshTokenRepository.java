package edu.miu.cs545.api.repository;


import edu.miu.cs545.api.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
    Collection<RefreshToken> findRefreshTokenByUserNameEquals(String name);
}
