package com.bitcoin.history.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BitcoinCommand(LocalDate from, LocalDate to, String currency, BigDecimal value) {
}
