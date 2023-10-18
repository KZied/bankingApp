package com.zied.bankingApp.services.impl;

import com.zied.bankingApp.dto.AccountDto;
import com.zied.bankingApp.exceptions.ObjectsValidator;
import com.zied.bankingApp.exceptions.OperationNonPermittedException;
import com.zied.bankingApp.models.Account;
import com.zied.bankingApp.repositories.AccountRepository;
import com.zied.bankingApp.services.AccountService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {


    private final AccountRepository accountRepository;
    private final ObjectsValidator<AccountDto> validator;

    @Override
    public Integer save(AccountDto dto) {
        // block account update -> iban cannot be updated
        if (dto.getId() != null){
            throw new OperationNonPermittedException(
                    "Account cannot be updated",
                    "save account",
                    "Account",
                    "Update not permitted"
            );
        }
        validator.validate(dto);
        Account account = AccountDto.toEntity(dto);
        boolean userHasAlreadyAnAccount = accountRepository.findByUserId(account.getUser().getId()).isPresent();
        if(userHasAlreadyAnAccount){
            throw new OperationNonPermittedException(
                    "The selected user has already an active account",
                    "Create account",
                    "Account service",
                    "Account creation"
            );
        }
        // another way to block iban update is ti check: if(dto.getId() == null)
        account.setIban(generateRandomIban());
        return accountRepository.save(account).getId();
    }

    @Override
    public List<AccountDto> findAll() {
        return accountRepository.findAll()
                .stream()
                .map(AccountDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto findById(Integer id) {
        return accountRepository.findById(id)
                .map(AccountDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("No account was found with the ID: "+ id));
    }

    @Override
    public void delete(Integer id) {
        // toDo check delete account
        accountRepository.deleteById(id);
    }

    // https://github.com/arturmkrtchyan/iban4j
    private String generateRandomIban() {
        // generate a random iban
        String iban = Iban.random(CountryCode.ES).toFormattedString();
        // check if already exists
        boolean ibanExists = accountRepository.findByIban(iban).isPresent();
        // if exists
        if (ibanExists){
            generateRandomIban();
        }
        // if it does not exist
        return iban;
    }
}
