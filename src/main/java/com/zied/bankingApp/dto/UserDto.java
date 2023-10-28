package com.zied.bankingApp.dto;

import com.zied.bankingApp.models.User;
import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDto {

    private Integer id;

    // NotBlank = does not contain white spaces
    @NotNull
    @NotEmpty
    @NotBlank
    private String firstName;

    @NotNull
    @NotEmpty(message = "Last name cannot be empty")
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @NotNull
    @NotEmpty
    @NotBlank
    @Email(message = "email format is invalid")
    private String email;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 8, max = 16, message = "password's size has to be between 8 and 16 characters")
    private String password;

    public static UserDto fromEntity (User user){
        // null check
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public static User toEntity (UserDto userDto){
        // null check
        return User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }

}
