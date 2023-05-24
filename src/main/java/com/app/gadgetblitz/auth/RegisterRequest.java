package com.app.gadgetblitz.auth;

import com.app.gadgetblitz.model.user.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private Name name;
    private String email;
    private String password;

}
