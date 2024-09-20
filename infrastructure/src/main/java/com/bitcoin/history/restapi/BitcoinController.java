package com.bitcoin.history.restapi;

import com.bitcoin.history.domain.BitcoinService;
import com.bitcoin.history.domain.entities.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
public class BitcoinController {

    private final BitcoinService service;

    public BitcoinController(BitcoinService service) {
        this.service = service;
    }

    @Operation(summary = "Fetches the historical Bitcoin prices for a given period along with the highest & lowest price markers ")
    @GetMapping(path = "/bitcoin/history", produces = {APPLICATION_JSON_VALUE})
    @CrossOrigin(origins = "*")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Fetched bitcoin history"),
            @ApiResponse(responseCode = "400", description = "Given inputs are not correct")
    })
    // Todo allow only web url and remove *
    // TODO add more validations
    public ResponseEntity<Response> getHistory(BitcoinRequest bitcoinRequest) {
        return ResponseEntity.ok(service.getHistory(bitcoinRequest.toCommand()));
    }
}
