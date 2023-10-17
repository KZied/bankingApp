package com.zied.bankingApp.service;

import com.zied.bankingApp.dto.UserDto;

public interface UserService extends AbstractService <UserDto> {

    Integer validateAccount(Integer id);

    Integer InvalidateAccount(Integer id);
}
