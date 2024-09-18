package com.bitcoin.history.restapi;

import com.bitcoin.history.domain.entities.BitcoinCommand;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BitcoinRequest(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to, @RequestParam String currency, @RequestParam BigDecimal value) {

    public BitcoinCommand toCommand(){
        return new BitcoinCommand(this.from, this.to, this.currency, this.value);

    }
}
