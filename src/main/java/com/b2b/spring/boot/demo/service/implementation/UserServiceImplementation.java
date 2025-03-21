package com.b2b.spring.boot.demo.service.implementation;

import com.b2b.spring.boot.demo.DTO.ModificaUser;
import com.b2b.spring.boot.demo.DTO.NuovoUser;
import com.b2b.spring.boot.demo.DTO.UserRecord;
import com.b2b.spring.boot.demo.Mapper.UserMapper;
import com.b2b.spring.boot.demo.entity.User;
import com.b2b.spring.boot.demo.repository.UserRepository;
import com.b2b.spring.boot.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;


    @Override
    public List<UserRecord> getAllUsers() {
        return userRepository.findAll().stream().map(user -> userMapper.toRecord(user)).toList();
    }

    @Override
    public void insertUser(NuovoUser oUser) {
        userRepository.save(userMapper.toEntity(oUser));
    }

    @Override
    public void updateUser(ModificaUser oUser, Long id) {
        userRepository.save(userMapper.partialUpdate(userRepository.findById(id).orElseThrow(), oUser));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
