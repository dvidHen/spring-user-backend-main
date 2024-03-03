package com.spring.user.main;

import com.spring.user.main.dto.RequestDTO;
import com.spring.user.main.entities.UserCustomer;
import com.spring.user.main.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(null, null);
    }

    @Test
    public void testTransformRequestDTOToUserCustomer() {
        RequestDTO requestDTO = new RequestDTO("John Doe", "john@example.com", "Password123!", Collections.emptyList());
        //when(tokenService.generarToken("test-id")).thenReturn("test-token");

        UserCustomer userCustomer = userService.transformRequestDTOToUserCustomer(requestDTO);

        assertEquals("Henrry Ramirez", userCustomer.getName());
        assertEquals("henrry@example.com", userCustomer.getEmail());
        assertEquals("PruebaPassword123!", userCustomer.getPassword());
        assertEquals(1, userCustomer.getIsactive());
        assertEquals("test-token", userCustomer.getToken());
    }
}
