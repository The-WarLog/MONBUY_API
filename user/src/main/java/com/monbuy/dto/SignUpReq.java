package com.monbuy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpReq {
    private String name;
    private String email;
    private String password;
    private String adminKey;
}
