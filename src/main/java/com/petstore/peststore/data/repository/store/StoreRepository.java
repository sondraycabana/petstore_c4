package com.petstore.peststore.data.repository.store;

import com.petstore.peststore.data.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store,Integer> {
        void  findByName(String name);
}
