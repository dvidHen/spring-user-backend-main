package com.spring.user.main.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.user.main.dto.ExistingUserDTO;
import com.spring.user.main.dto.RequestDTO;
import com.spring.user.main.dto.ResponseDTO;
import com.spring.user.main.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Tag(name = "UserController", description = "API destinada para la creación de un usuario")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final UserService userService;
    private static final Logger log = LogManager.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint para registrar un nuevo usuario.
     *
     * @param requestData Datos del usuario a registrar
     * @return ResponseEntity con la respuesta del servicio
     * @throws JsonProcessingException Si ocurre un error al procesar los datos JSON
     */
    @Operation(
            summary = "Registro de usuario",
            description = "Endpoint que recibe un JSON tipo RequestDTO con los datos del nuevo usuario para procesar su registro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro exitoso", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseDTO.class)
            )),
            @ApiResponse(responseCode = "409", description = "El usuario ya está registrado", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ExistingUserDTO.class)
            ))
    })
    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@Valid @RequestBody RequestDTO requestData) throws JsonProcessingException {

        log.info("[registerNewUser] Inicio de proceso de creación de nuevo usuario");

        return userService.userRegister(requestData);
    }
}
