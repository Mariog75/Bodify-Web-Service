package com.bodify.bodify.controller;

import com.bodify.bodify.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserController userController;

    public static final String URL = "http://localhost:8080/user/";
    public static final String GET_USER_BY_ID = "getUser?id=";

    @Test
    public void getValidUserById_returnOk() throws Exception {
        User user = new User();
        user.setId("equpkVZKCEQ0texRAlEGt4vD4iT2");
        user.setName("Mario");

        given(userController.getUserById(user.getId())).willReturn(new ResponseEntity<>(user, HttpStatus.OK));

        mockMvc.perform( MockMvcRequestBuilders
                        .get(URL + GET_USER_BY_ID + user.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Mario")));
    }

}
