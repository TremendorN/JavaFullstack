package com.pu.ums.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pu.ums.model.UsersModel;
import com.pu.ums.repository.UsersRepository;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public UsersModel registerUser(String login, String password, String email) {
        if (login != null && password != null) {
            if (usersRepository.findFirstByLogin(login).isPresent()) {
                System.out.println("Duplicate login");
                return null;
            }
            UsersModel usersModel = new UsersModel();
            usersModel.setLogin(login);
            usersModel.setPassword(password);
            usersModel.setEmail(email);
            return usersRepository.save(usersModel);
        } else {
            System.out.println("Login or Password is null");
            return null;
        }
    }

    public UsersModel authentication(String login, String password) {
        return usersRepository.findByLoginAndPassword(login, password).orElse(null);
    }
}
