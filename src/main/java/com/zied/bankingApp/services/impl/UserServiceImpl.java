package com.zied.bankingApp.services.impl;

import com.zied.bankingApp.dto.AccountDto;
import com.zied.bankingApp.dto.UserDto;
import com.zied.bankingApp.exceptions.ObjectsValidator;
import com.zied.bankingApp.models.User;
import com.zied.bankingApp.repositories.UserRepository;
import com.zied.bankingApp.services.AccountService;
import com.zied.bankingApp.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AccountService accountService;
    private final ObjectsValidator<UserDto> validator;


    @Override
    public Integer save(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        return userRepository.save(user).getId();
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Integer id) {
        return userRepository.findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No user was found with the provided ID: " + id));
    }

    @Override
    public void delete(Integer id) {
        // toDo check before delete
        userRepository.deleteById(id);
    }

    @Override
    public Integer validateAccount(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user was found for user account validation"));
        user.setActive(true);
        // create bank account
        AccountDto accountDto = AccountDto.builder()
                        .userDto(UserDto.fromEntity(user))
                        .build();
        accountService.save(accountDto);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public Integer InvalidateAccount(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user was found for user account validation"));
        user.setActive(false);
        userRepository.save(user);
        return user.getId();
    }
}
