package com.skrt.inviduelllab.repositories;

import com.skrt.inviduelllab.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByStreetAndPostalCodeAndCity(String street, String postalCode, String city);
}
