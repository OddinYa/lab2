package com.example.lab2.controlers.rest;

import com.example.lab2.models.dto.BuyDTO;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import java.util.List;


@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2025-04-29T17:08:35.048350733Z[GMT]")
@RestController
@CrossOrigin(origins = "*")
public class BuysApiController implements BuysApi {

    private static final Logger log = LoggerFactory.getLogger(BuysApiController.class);

    private final ObjectMapper objectMapper;
    @Autowired
    private HttpServletRequest request;

    private final FinanceService financeService;
    @org.springframework.beans.factory.annotation.Autowired
    public BuysApiController(ObjectMapper objectMapper,FinanceService finance) {
        this.objectMapper = objectMapper;
        this.financeService = finance;
    }

    public ResponseEntity<Void> buysBuyIdFamilyIdDelete(
            @Parameter(in = ParameterIn.PATH, description = "ID покупки", required=true, schema=@Schema())
            @PathVariable("buyId") Integer buyId,
            @Parameter(in = ParameterIn.PATH, description = "ID семьи", required=true, schema=@Schema())
            @PathVariable("familyId") Integer familyId
) {
        String accept = request.getHeader("Accept");
        financeService.deleteBuy(buyId,familyId);
        return new ResponseEntity<Void>(HttpStatus.valueOf(204));
    }

    public ResponseEntity<List<BuyDTO>> buysFamilyIdGet(
            @Parameter(in = ParameterIn.PATH, description = "ID семьи", required=true, schema=@Schema())
            @PathVariable("familyId") Integer familyId
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                var listBuy = financeService.getBuys(familyId);
                return new ResponseEntity<List<BuyDTO>>(listBuy, HttpStatus.valueOf(200));
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<BuyDTO>>(HttpStatus.valueOf(500));
            }
        }

        return new ResponseEntity<List<BuyDTO>>(HttpStatus.valueOf(404));
    }

    public ResponseEntity<Void> buysFamilyIdPost(
            @Parameter(in = ParameterIn.PATH, description = "ID семьи", required=true, schema=@Schema())
            @PathVariable("familyId") Integer familyId,
            @Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema())
            @Valid @RequestBody BuyDTO body
) {
        String accept = request.getHeader("Accept");
        financeService.addBuy(body,familyId);
        return new ResponseEntity<Void>(HttpStatus.valueOf(201));
    }

}
