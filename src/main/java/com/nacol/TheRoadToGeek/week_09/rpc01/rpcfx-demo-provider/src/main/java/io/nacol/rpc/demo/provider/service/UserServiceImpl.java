package io.nacol.rpc.demo.provider.service;

import io.nacol.rpc.demo.api.User;
import io.nacol.rpc.demo.api.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User findById(int id) {
        return new User(id, "KK" + System.currentTimeMillis());
    }
}
