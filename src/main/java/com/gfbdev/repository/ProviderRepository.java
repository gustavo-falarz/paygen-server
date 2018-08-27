package com.gfbdev.repository;

import com.gfbdev.entity.Provider;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderRepository extends MongoRepository<Provider, String> {

    Provider findByEmail(String email);

    boolean existsByToken(String token);

    List<Provider> findByLocationNearOrderByLocation(Point location,  Distance distance);

}
