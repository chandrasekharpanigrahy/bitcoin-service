package com.bitcoin.history.domain;

import com.bitcoin.history.domain.entities.BitcoinPriceIndex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Map;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static org.mockito.Mockito.*;

public class CountDeskServiceTest {

    private final BaseCountDeskProvider provider = Mockito.mock(BaseCountDeskProvider.class);

    private final InMemoryCountDeskProvider inMemoryProvider = Mockito.mock(InMemoryCountDeskProvider.class);

    CountDeskService countDeskService;

    @BeforeEach
    public void init() {
        when(inMemoryProvider.getBitCoinHistory()).thenReturn(new BitcoinPriceIndex(Map.of(LocalDate.now().minusDays(1), ONE,
                LocalDate.now().minusDays(2), TEN)));
        when(provider.getBitCoinHistory()).thenReturn(new BitcoinPriceIndex(Map.of(LocalDate.now().minusDays(1), ONE,
                LocalDate.now().minusDays(2), TEN)));
    }
    @Test
    void should_call_only_in_memory_provider_in_offline_mode() {
        countDeskService = new CountDeskService(provider, inMemoryProvider, true);

        countDeskService.getBitCoinHistory();
        
        verify(inMemoryProvider).getBitCoinHistory();
       verify(provider, never()).getBitCoinHistory();
    }

    @Test
    void should_call_count_desk_provider_and_in_memory_to_set_value_in_online_mode() {
        countDeskService = new CountDeskService(provider, inMemoryProvider, false);

        countDeskService.getBitCoinHistory();

        verify(provider).getBitCoinHistory();
        verify(inMemoryProvider).setBitCoinHistory(Map.of(LocalDate.now().minusDays(1), ONE,
                LocalDate.now().minusDays(2), TEN));
    }
}