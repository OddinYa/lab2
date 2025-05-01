package com.example.lab2.controlers.rest;

import com.example.lab2.models.dto.UserDTO;
import com.example.lab2.service.FinanceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2025-04-29T17:08:35.048350733Z[GMT]")
@RestController
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final FinanceService financeService;
    @Autowired
    private  HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, FinanceService financeService) {
        this.objectMapper = objectMapper;
        this.financeService = financeService;
    }

    public ResponseEntity<Void> usersFamilyIdPost(
            @Parameter(in = ParameterIn.PATH, description = "ID семьи", required=true, schema=@Schema())
            @PathVariable("familyId") Integer familyId,
            @Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema())
            @Valid @RequestBody UserDTO body) {

        String accept = request.getHeader("Accept");
        financeService.addUser(body,familyId);
        return new ResponseEntity<Void>(HttpStatus.valueOf(201));
    }

    public ResponseEntity<Void> usersPost(
            @Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema())
            @Valid @RequestBody UserDTO body
) {
        String accept = request.getHeader("Accept");
        financeService.createNewFamily(body);
        return new ResponseEntity<Void>(HttpStatus.valueOf(201));
    }

    public ResponseEntity<Void> usersUserIdDelete(
            @Parameter(in = ParameterIn.PATH, description = "ID пользователя", required=true, schema=@Schema())
            @PathVariable("userId") Integer userId
) {
        String accept = request.getHeader("Accept");
        financeService.deleteUser(userId);
        return new ResponseEntity<Void>(HttpStatus.valueOf(204));
    }

    public ResponseEntity<UserDTO> usersUserIdGet(
            @Parameter(in = ParameterIn.PATH, description = "ID пользователя", required=true, schema=@Schema())
            @PathVariable("userId") Integer userId
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                var user = financeService.getUser(userId);
                return new ResponseEntity<UserDTO>(user, HttpStatus.valueOf(200));
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<UserDTO>(HttpStatus.valueOf(500));
            }
        }

        return new ResponseEntity<UserDTO>(HttpStatus.valueOf(404));
    }

    public ResponseEntity<Void> usersUserIdPatch(
            @Parameter(in = ParameterIn.PATH, description = "ID пользователя", required=true, schema=@Schema())
            @PathVariable("userId") Integer userId
) {
        String accept = request.getHeader("Accept");
        financeService.updateUser(userId);
        return new ResponseEntity<Void>(HttpStatus.valueOf(200));
    }

}
