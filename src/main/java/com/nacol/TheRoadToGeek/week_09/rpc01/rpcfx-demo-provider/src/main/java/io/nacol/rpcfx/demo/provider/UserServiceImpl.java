package io.nacol.rpcfx.demo.provider;

import io.nacol.rpcfx.demo.api.User;
import io.nacol.rpcfx.demo.api.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public User findById(int id) {
        return new User(id, "KK" + System.currentTimeMillis());
    }
}
