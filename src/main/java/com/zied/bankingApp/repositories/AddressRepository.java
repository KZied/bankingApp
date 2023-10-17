package com.zied.bankingApp.repositories;

import com.zied.bankingApp.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
