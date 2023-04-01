package com.springdatastore.tutorial;

import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;

import java.util.List;

public interface AddressRepository extends DatastoreRepository<Address, Long> {
    List<Address> findAddressesByUserId(Long id);
}
