package com.spring.user.main.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.user.main.controllers.UserController;
import com.spring.user.main.dto.*;

import com.spring.user.main.entities.Phones;
import com.spring.user.main.entities.UserCustomer;
import com.spring.user.main.exceptions.ExistingUserException;
import com.spring.user.main.repository.UserRepository;
import com.spring.user.main.utils.PasswordEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private final TokenService tokenService;

    @Value("${default.user.status}")
    private int defaultUserStatus;
    private final UserRepository userRepository;

    private static final Logger log = LogManager.getLogger(UserController.class);
    public UserService( UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    /**
     * Método para registrar un nuevo usuario.
     *
     * @param enterData Datos del usuario a registrar
     * @return ResponseEntity con la respuesta del servicio
     * @throws JsonProcessingException Si ocurre un error al procesar los datos JSON
     */
    public ResponseEntity<?>  userRegister(RequestDTO enterData) throws JsonProcessingException {
        ResponseDTO responseOkDTO;
        log.info("[userRegister] Datos recibidos para el nuevo registro");
        log.info("[userRegister]" + enterData.toJSON());

        try {
            Boolean userExists = searchUser(enterData.getEmail());
            if (userExists){
                throw new ExistingUserException("El usuario ya está registrado");
            }

            UserCustomer userSaved = userRepository.save(transformRequestDTOToUserCustomer(enterData));
            responseOkDTO = transformUserCustomerToResponseDTO(userSaved);

        } catch (ExistingUserException e) {
            log.error("[userRegister] Error al registrar usuario: " + e.getMessage());
            ExistingUserDTO responseDTO = new ExistingUserDTO(HttpStatus.CONFLICT.value(), "Error al ejecutar la solicitud", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(responseDTO);
        } catch (Exception e) {
            log.error("[userRegister] Error al registrar usuario: " + e.getMessage());
            ExistingUserDTO responseDTO = new ExistingUserDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error interno al procesar la solicitud", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(responseOkDTO);
    }

    /**
     * Método para verificar si un usuario ya existe en la base de datos.
     *
     * @param email Email del usuario a verificar
     * @return true si el usuario existe, false en caso contrario
     */
    public Boolean searchUser(String email) {
        Optional<UserCustomer> userOptional = userRepository.findByEmail(email);
        return userOptional.isPresent();
    }

    /**
     * Método para transformar un objeto UserCustomer en un objeto ResponseDTO.
     *
     * @param userSaved Usuario registrado
     * @return ResponseDTO con la información del usuario registrado
     */
    public ResponseDTO transformUserCustomerToResponseDTO(UserCustomer userSaved) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(201);
        responseDTO.setTitle("Registro exitoso");

        RegisteredUserDTO registeredUserDTO = new RegisteredUserDTO();
        registeredUserDTO.setId(String.valueOf(userSaved.getId()));
        registeredUserDTO.setIsactive(userSaved.getIsactive());
        registeredUserDTO.setToken(userSaved.getToken());
        registeredUserDTO.setLast_login(userSaved.getLast_login());
        registeredUserDTO.setCreated(userSaved.getCreated());
        registeredUserDTO.setModified(userSaved.getModified());

        responseDTO.setData(registeredUserDTO);

        return responseDTO;
    }

    /**
     * Método para transformar un objeto RequestDTO en un objeto UserCustomer.
     *
     * @param requestDTO Datos del usuario a registrar
     * @return UserCustomer con la información del usuario a registrar
     */
    public UserCustomer transformRequestDTOToUserCustomer(RequestDTO requestDTO) {
        log.info("[transformRequestDTOToUserCustomer] Configuración de campos de registro");
        UserCustomer userCustomer = new UserCustomer();
        userCustomer.setId(UUID.randomUUID().toString());
        log.info("[transformRequestDTOToUserCustomer] ID creado");
        userCustomer.setName(requestDTO.getName());
        userCustomer.setEmail(requestDTO.getEmail());
        userCustomer.setPassword(PasswordEncoder.encodePassword(requestDTO.getPassword()));
        log.info("[transformRequestDTOToUserCustomer] Password codificada");
        userCustomer.setIsactive(defaultUserStatus);
        userCustomer.setToken(tokenService.generarToken(userCustomer.getId()));
        log.info("[transformRequestDTOToUserCustomer] Token generado");
        List<Phones> phonesList = new ArrayList<>();
        if (requestDTO.getPhones() != null) {
            for (PhoneDTO phoneDTO : requestDTO.getPhones()) {
                Phones phone = new Phones();
                phone.setNumber(phoneDTO.getNumber());
                phone.setCitycode(phoneDTO.getCitycode());
                phone.setCountrycode(phoneDTO.getCountrycode());
                phone.setUserCustomerId(userCustomer);
                phonesList.add(phone);
            }
        }
        userCustomer.setPhones(phonesList);
        return userCustomer;
    }
}
