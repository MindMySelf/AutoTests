package com.codecool.logmyphones.service.userservice;

import com.codecool.logmyphones.model.CompanyUser;
import com.codecool.logmyphones.model.DTO.UserDTO;
import com.codecool.logmyphones.model.repository.UserRepository;
import com.codecool.logmyphones.security.JwtService;
import com.codecool.logmyphones.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, JwtService jwtService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.jwtService = jwtService;
    }

    @Override
    public ResponseEntity<Set<UserDTO>> getAllUsers() {
        return new ResponseEntity<>(
                userMapper.toUserDTOs(new HashSet<>(userRepository.findAll())),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> getUserById(String email) {
        CompanyUser companyUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User with email: %s not found".formatted(email)));

        return new ResponseEntity<>(userMapper.toUserDTO(companyUser), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> addNewUser(UserDTO newUserDTO) {
        CompanyUser user = userMapper.toCompanyUser(newUserDTO);
        // TODO EZT MINDENHOL MINDENHOGY ELTUNTETNI
        user.setContacts(new HashSet<>());
        user.setDispatchers(new HashSet<>());
        userRepository.save(user);
        // TODO HTTP-s dolgokhoz a servicenek ne legyen koze soha
        return new ResponseEntity<>(newUserDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> updateUser(String token, UserDTO userDTO) {
        CompanyUser userToUpdate = userRepository.findByEmail(jwtService.extractUsername(token.split(" ")[1])).get();
        return null;
    }

    @Override
    public HttpStatus deleteUser(String token) {
        userRepository.deleteByEmail(jwtService.extractUsername(token.split(" ")[1]));
        return HttpStatus.NO_CONTENT;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        CompanyUser companyUser = userRepository.findByEmail(email).orElseThrow(() ->
                new RuntimeException("No such user"));
        return userMapper.toUserDTO(companyUser);
    }
}
