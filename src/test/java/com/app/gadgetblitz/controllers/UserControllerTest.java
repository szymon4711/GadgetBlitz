package com.app.gadgetblitz.controllers;

import com.app.gadgetblitz.controller.UserController;
import com.app.gadgetblitz.model.user.User;
import com.app.gadgetblitz.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testGetAllUsers() throws Exception {
        // Mock data
        User user1 = new User();
        user1.setId("1");
        user1.setUsername("user1");
        User user2 = new User();
        user2.setId("2");
        user2.setUsername("user2");
        List<User> users = Arrays.asList(user1, user2);

        // Mock repository behavior
        Mockito.when(userRepository.findAll()).thenReturn(users);

        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].username").value("user1"))
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].username").value("user2"));
    }
}