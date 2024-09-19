package com.bitcoin.history.restapi;

import com.bitcoin.history.domain.BitcoinService;
import com.bitcoin.history.domain.entities.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
// TODO add swagger
public class BitcoinController {

    private final BitcoinService service;

    public BitcoinController(BitcoinService service) {
        this.service = service;
    }

    @GetMapping(path = "/bitcoin/history", produces = {APPLICATION_JSON_VALUE})
    @CrossOrigin(origins = "*")
    // Todo allow only web and remove *
    public ResponseEntity<Response> getHistory(BitcoinRequest bitcoinRequest) {
        return ResponseEntity.ok(service.getHistory(bitcoinRequest.toCommand()));
    }
}
