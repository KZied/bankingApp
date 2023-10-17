package com.zied.bankingApp.repositories;

import com.zied.bankingApp.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

    List<Contact> findAllByUserId(Integer userId);
}
