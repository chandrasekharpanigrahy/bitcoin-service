package com.bitcoin.history.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public record BitcoinPriceIndex(Map<LocalDate, BigDecimal> bpi) {}
