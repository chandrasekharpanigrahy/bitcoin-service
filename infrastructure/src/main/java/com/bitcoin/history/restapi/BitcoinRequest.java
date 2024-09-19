package com.bitcoin.history.restapi;

import com.bitcoin.history.domain.entities.BitcoinCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BitcoinRequest(@RequestParam @NotBlank @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                             @Schema(name = "Enter Start Date from when you want to get History", required = true) LocalDate from,
                             @RequestParam @NotBlank @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                             @Schema(name = "Enter end Date till when you want to get History", required = true) LocalDate to,
                             @RequestParam @NotBlank @Schema(name = "Enter the currency you want to see the price in", required = true) String currency,
                             @NotBlank @RequestParam @Schema(name = "Enter the exchange rate of the currency to USD", required = true) BigDecimal value) {

    public BitcoinCommand toCommand(){
        return new BitcoinCommand(this.from, this.to, this.currency, this.value);

    }
}
