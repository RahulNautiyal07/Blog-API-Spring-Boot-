package com.rahul.articleBlogApi.users.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDTO {

    Integer id;
    String email;
    String username;
    String bio;
    String image;
    String token;
}
