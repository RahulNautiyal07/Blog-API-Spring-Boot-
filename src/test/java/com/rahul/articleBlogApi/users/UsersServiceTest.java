package com.rahul.articleBlogApi.users;

import com.rahul.articleBlogApi.security.jwt.JWTService;
import com.rahul.articleBlogApi.users.dto.CreateUserDTO;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UsersServiceTest {
    @Autowired private UsersRepository usersRepository;
    private UsersService usersService;
    private UsersService getUsersService() {
        if(usersService == null) {
            var modelMapper = new ModelMapper();
            var passwordEncoder = new BCryptPasswordEncoder();
            var jwtService = new JWTService();
            return new UsersService(
                    usersRepository,
                    modelMapper,
                    passwordEncoder,
                    jwtService
            );
        }
        return usersService;
    };


    @Test
    public void testCreateUser() {
        var newUserDTO = new CreateUserDTO();
        newUserDTO.setUsername("rahul");
        newUserDTO.setEmail("rahul@gmail.com");
        newUserDTO.setPassword("test@123");
        var savedUser = getUsersService().createUser(newUserDTO);
        assertNotNull(savedUser);
    };
}
