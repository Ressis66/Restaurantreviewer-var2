package com.ressis.service.user;

import com.ressis.model.User;
import com.ressis.util.exception.NotFoundException;

public interface UserService {

    User create(User user);

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;
}
