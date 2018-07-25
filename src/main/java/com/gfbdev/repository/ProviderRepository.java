package com.gfbdev.repository;

import com.gfbdev.entity.Provider;
import com.gfbdev.entity.Sale;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends MongoRepository<Provider, String> {

    Provider findByEmail(String email);
}
