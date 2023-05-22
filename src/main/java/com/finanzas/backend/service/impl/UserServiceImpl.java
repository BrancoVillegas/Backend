package com.finanzas.backend.service.impl;

import com.finanzas.backend.entities.User;
import com.finanzas.backend.repository.IUserRepository;
import com.finanzas.backend.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public User save(User user) throws Exception {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAll() throws Exception {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getById(Long id) throws Exception {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findByFirstName(String firstName) throws Exception {
        return userRepository.findByFirstName(firstName);
    }

    @Override
    public User findByEmail(String email) throws Exception {
        return userRepository.findByEmail(email);
    }

}
