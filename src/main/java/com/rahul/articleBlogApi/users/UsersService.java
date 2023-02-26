package com.rahul.articleBlogApi.users;

import com.rahul.articleBlogApi.security.jwt.JWTService;
import com.rahul.articleBlogApi.users.dto.CreateUserDTO;
import com.rahul.articleBlogApi.users.dto.LoginUserDTO;
import com.rahul.articleBlogApi.users.dto.UserResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    private final JWTService jwtService;

    public UsersService(
           @Autowired UsersRepository usersRepository,
           @Autowired ModelMapper modelMapper,
           @Autowired PasswordEncoder passwordEncoder,
           @Autowired JWTService jwtService
    ) {
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService =  jwtService;
    }

   public UserResponseDTO createUser(CreateUserDTO createUserDTO) {
        //TODO: Validate the email
        //TODO: Check if username is already exists

        // With Model Mapper
        var newUserEntity = modelMapper.map(createUserDTO, UserEntity.class);
        newUserEntity.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        var savedUser = usersRepository.save(newUserEntity);
        var userResponseDTO = modelMapper.map(savedUser, UserResponseDTO.class);
        userResponseDTO.setToken(jwtService.createJWT((savedUser.getId())));
        // Without Model Mapper
//       var newUserEntity = new UserEntity();
//       newUserEntity.setUsername(createUserDTO.getUsername());
//       newUserEntity.setEmail(createUserDTO.getEmail());
//       newUserEntity.setPassword(createUserDTO.getPassword());
//       var savedUser = usersRepository.save(newUserEntity);
//       var userResponseDTO = new UserResponseDTO();
//       userResponseDTO.setId(savedUser.getId());
//       userResponseDTO.setUsername(savedUser.getUsername());
//       userResponseDTO.setEmail(savedUser.getEmail());

        return userResponseDTO;
   }



   public UserResponseDTO loginUser(LoginUserDTO loginUserDTO) {
        var userEntity = usersRepository.findByUsername(loginUserDTO.getUsername());
        if(userEntity == null) {
            throw new UserNotFoundException(loginUserDTO.getUsername());
        }
        // TODO: encrypt the password
        var passMatch = passwordEncoder.matches(loginUserDTO.getPassword(), userEntity.getPassword());
        if(!passMatch) {
            throw new IllegalArgumentException("Incorrect password");
        }
        var userResponseDTO = modelMapper.map(userEntity,UserResponseDTO.class);
        userResponseDTO.setToken(jwtService.createJWT((userEntity.getId())));

       // TODO: implement password matching
       return userResponseDTO;
   }

    public static class UserNotFoundException extends IllegalArgumentException {
        public UserNotFoundException (Integer id) {
            super("User with id " + id + " not found");
        }

        public UserNotFoundException (String username) {
            super("User with id " + username + " not found");
        }

    }
    public static class IncorrectPasswordException extends IllegalArgumentException {
        public IncorrectPasswordException(){
            super("Incorrect passowrd");
        }
    }
}
