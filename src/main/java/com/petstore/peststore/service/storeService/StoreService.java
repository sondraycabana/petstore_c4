package com.petstore.peststore.service.storeService;

import com.petstore.peststore.data.model.Pet;
import com.petstore.peststore.data.model.Store;
import com.petstore.peststore.web.controller.exceptions.StoreNotFoundException;

import java.util.List;

public interface StoreService {
    Store saveStore(Store store);
    Store updateStore(Store store);
    Store findStoreById(Integer id);
    void deleteStoreById(Integer id);
    List<Store> findAllStores();
    Store addPet(Integer storeId, Pet pet) throws StoreNotFoundException;

}
