package com.zied.bankingApp.service.impl;

import com.zied.bankingApp.dto.AddressDto;
import com.zied.bankingApp.exceptions.ObjectsValidator;
import com.zied.bankingApp.models.Address;
import com.zied.bankingApp.repositories.AddressRepository;
import com.zied.bankingApp.service.AddressService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final ObjectsValidator<AddressDto> validator;

    @Override
    public Integer save(AddressDto dto) {
        validator.validate(dto);
        Address address = AddressDto.toEntity(dto);
        return addressRepository.save(address).getId();
    }

    @Override
    public List<AddressDto> findAll() {
        return addressRepository.findAll()
                .stream()
                .map(AddressDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto findById(Integer id) {
        return addressRepository.findById(id)
                .map(AddressDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No address was found with the ID: " + id));
    }

    @Override
    public void delete(Integer id) {
        // toDo check delete
        addressRepository.deleteById(id);
    }
}
