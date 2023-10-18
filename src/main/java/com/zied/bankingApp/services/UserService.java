package com.zied.bankingApp.services;

import com.zied.bankingApp.dto.UserDto;

public interface UserService extends AbstractService <UserDto> {

    Integer validateAccount(Integer id);

    Integer InvalidateAccount(Integer id);
}
