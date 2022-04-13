package com.kryhowsky.maslibrary.service;

import com.kryhowsky.maslibrary.model.dto.LoginDto;
import com.kryhowsky.maslibrary.model.dto.TokenDto;

public interface LoginService {

    TokenDto authenticateUser(LoginDto loginDto);

}
