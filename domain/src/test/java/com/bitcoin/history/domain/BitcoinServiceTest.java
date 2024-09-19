package com.bitcoin.history.domain;

import com.bitcoin.history.domain.entities.BitcoinCommand;
import com.bitcoin.history.domain.entities.BitcoinPriceIndex;
import com.bitcoin.history.domain.entities.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import static java.math.BigDecimal.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BitcoinServiceTest {

    private CountDeskService countDeskService;

    private BitcoinService service;

    @BeforeEach
    public void init() {
        countDeskService = Mockito.mock(CountDeskService.class);
        service = new BitcoinService(countDeskService);
        when(countDeskService.getBitCoinHistory())
                .thenReturn(new BitcoinPriceIndex(Map.of(LocalDate.now().minusDays(1), ONE,
                        LocalDate.now().minusDays(2), TEN)));
    }

    @Test
    public void should_call_provider_to_get_history_with_min_and_max_for_given_dates_and_multiply_price_to_currency_price() {
        Response response = service.getHistory(new BitcoinCommand(LocalDate.now().minusDays(3), LocalDate.now(), "USD", TEN));
        verify(countDeskService).getBitCoinHistory();
        assertEquals(BigDecimal.valueOf(100L), response.max());
        assertEquals(TEN, response.min());
        assertEquals(TEN, response.dateToPrice().get(LocalDate.now().minusDays(1)));
    }

    @Test
    public void should_get_min_and_max_as_null_if_no_record_in_between_specified_Dates() {
        Response response = service.getHistory(new BitcoinCommand(LocalDate.now().minusDays(10), LocalDate.now().minusDays(5),
                "USD", ONE));
        verify(countDeskService).getBitCoinHistory();
        assertEquals(ZERO, response.max());
        assertEquals(ZERO, response.min());
        assertEquals(Map.of(), response.dateToPrice());
    }
    @Test
    public void should_get_min_and_max_same_if_one_record_in_between_specified_Dates() {
        Response response = service.getHistory(new BitcoinCommand(LocalDate.now().minusDays(5), LocalDate.now().minusDays(2),
                "USD", ONE));
        verify(countDeskService).getBitCoinHistory();
        assertEquals(TEN, response.max());
        assertEquals(TEN, response.min());
        assertEquals(1, response.dateToPrice().size());
    }
    @Test
    public void should_include_records_of_from_and_to_date_requested_by_user() {
        Response response = service.getHistory(new BitcoinCommand(LocalDate.now().minusDays(2), LocalDate.now().minusDays(1),
                "USD", ONE));
        verify(countDeskService).getBitCoinHistory();
        assertEquals(2, response.dateToPrice().size());
    }
}