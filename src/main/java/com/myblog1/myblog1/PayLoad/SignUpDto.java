package com.myblog1.myblog1.PayLoad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String roleType;
}
