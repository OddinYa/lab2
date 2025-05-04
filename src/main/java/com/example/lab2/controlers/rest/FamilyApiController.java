package com.example.lab2.controlers.rest;

import com.example.lab2.models.dto.FamilyDTO;
import com.example.lab2.models.dto.MoneyDTO;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.processing.Generated;
import javax.validation.Valid;
import java.util.List;

@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2025-04-29T17:08:35.048350733Z[GMT]")
@RestController
@CrossOrigin(origins = "*")
public class FamilyApiController implements FamilyApi {

    private static final Logger log = LoggerFactory.getLogger(FamilyApiController.class);

    private final ObjectMapper objectMapper;
    @Autowired
    private HttpServletRequest request;

    private final FinanceService financeService;

    @org.springframework.beans.factory.annotation.Autowired
    public FamilyApiController(ObjectMapper objectMapper,FinanceService financeService) {
        this.objectMapper = objectMapper;
        this.financeService = financeService;

    }

    public ResponseEntity<Double> familyBalanceFamilyIdGet(
            @Parameter(in = ParameterIn.PATH, description = "ID семьи", required=true, schema=@Schema())
            @PathVariable("familyId") Integer familyId
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                var balans = financeService.getBalance(familyId);
                return new ResponseEntity<Double>(balans, HttpStatus.valueOf(200));
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Double>(HttpStatus.valueOf(500));
            }
        }

        return new ResponseEntity<Double>(HttpStatus.valueOf(404));
    }

    public ResponseEntity<Void> familyBalanceFamilyIdPost(
            @Parameter(in = ParameterIn.PATH, description = "ID семьи", required=true, schema=@Schema())
            @PathVariable("familyId") Integer familyId,
            @Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema())
            @Valid
            @RequestBody MoneyDTO body) {
        String accept = request.getHeader("Accept");
        financeService.addBalance(body,familyId);
        return new ResponseEntity<Void>(HttpStatus.valueOf(201));
    }


}
