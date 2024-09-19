package com.bitcoin.history.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

// Todo rename class and change the data type of datetoPrice to List of object
public record Response(Map<LocalDate, BigDecimal> dateToPrice, BigDecimal max, BigDecimal min) {
}
