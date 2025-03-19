package com.mio.todosimple.Auth;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
public class LoginRequest {

    @NotEmpty
    @NotNull
    private String username;
    @NotEmpty
    @NotNull
    private String email;
    @NotEmpty
    @NotNull
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
