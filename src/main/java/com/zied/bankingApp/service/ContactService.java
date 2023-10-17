package com.zied.bankingApp.service;

import com.zied.bankingApp.dto.ContactDto;

import java.util.List;

public interface ContactService extends AbstractService<ContactDto> {

    List<ContactDto> findAllByUserId(Integer userId);
}
