package com.zied.bankingApp.service.impl;

import com.zied.bankingApp.dto.AddressDto;
import com.zied.bankingApp.dto.ContactDto;
import com.zied.bankingApp.exceptions.ObjectsValidator;
import com.zied.bankingApp.models.Contact;
import com.zied.bankingApp.repositories.ContactRepository;
import com.zied.bankingApp.service.ContactService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ObjectsValidator<ContactDto> validator;
    @Override
    public Integer save(ContactDto dto) {
        validator.validate(dto);
        Contact contact = ContactDto.toEntity(dto);
        return contactRepository.save(contact).getId();
    }

    @Override
    public List<ContactDto> findAll() {
        return contactRepository.findAll()
                .stream()
                .map(ContactDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ContactDto findById(Integer id) {
        return contactRepository.findById(id)
                .map(ContactDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No contact was found with the ID: " + id));
    }

    @Override
    public void delete(Integer id) {
        contactRepository.deleteById(id);
    }
}
