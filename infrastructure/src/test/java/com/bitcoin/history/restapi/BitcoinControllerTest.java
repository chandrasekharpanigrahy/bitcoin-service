package com.bitcoin.history.restapi;

import com.bitcoin.history.domain.BitcoinService;
import com.bitcoin.history.domain.entities.BitcoinCommand;
import com.bitcoin.history.domain.entities.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import static org.mockito.Mockito.*;

public class BitcoinControllerTest {

    private BitcoinService service;
    private BitcoinController controller;

    @BeforeEach
    public void init() {
        service = mock(BitcoinService.class);
        controller = new BitcoinController(service);
        when(service.getHistory(any()))
                .thenReturn(new Response(Map.of(LocalDate.now(), new BigDecimal("1234.321")), new BigDecimal("1234.321"), new BigDecimal("1234.321")));
    }
    @Test
    void should_call_service_to_get_history() {
        controller.getHistory(new BitcoinRequest(LocalDate.now(), LocalDate.now(),
                "USD", new BigDecimal("1234.321")));
        verify(service).getHistory(any(BitcoinCommand.class));
    }
}