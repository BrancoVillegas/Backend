package com.finanzas.backend.service;

import com.finanzas.backend.entities.User;

import java.util.List;

public interface IUserService extends CrudService<User> {
    List<User> findByFirstName(String firstName) throws Exception;
    User findByEmail(String email) throws Exception;
}
