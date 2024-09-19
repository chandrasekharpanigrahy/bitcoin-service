package com.bitcoin.history.domain;

import com.bitcoin.history.domain.entities.BitcoinPriceIndex;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryCountDeskProviderTest {

    @Test
    void setBitCoinHistory_when_in_memory_is_empty() {
        InMemoryCountDeskProvider inMemoryCountDeskProvider = new InMemoryCountDeskProvider(new BitcoinPriceIndex(new HashMap<>()));
        Map<LocalDate, BigDecimal> map = new HashMap<>();
        map.put(LocalDate.now(), TEN);
        inMemoryCountDeskProvider.setBitCoinHistory(map);

        assertEquals(TEN, inMemoryCountDeskProvider.getBitCoinHistory().bpi().get(LocalDate.now()));
    }

    @Test
    void setBitCoinHistory_when_in_memory_is_not_empty_and_input_has_different_key(){
        Map<LocalDate, BigDecimal> map = new HashMap<>();
        map.put(LocalDate.now(), TEN);
        InMemoryCountDeskProvider inMemoryCountDeskProvider = new InMemoryCountDeskProvider(new BitcoinPriceIndex(map));
        assertEquals(1, inMemoryCountDeskProvider.getBitCoinHistory().bpi().size());
        map = new HashMap<>();
        map.put(LocalDate.now(), TEN);
        map.put(LocalDate.now().minusDays(1), ONE);

        inMemoryCountDeskProvider.setBitCoinHistory(map);

        assertEquals(2, inMemoryCountDeskProvider.getBitCoinHistory().bpi().size());
        assertEquals(TEN, inMemoryCountDeskProvider.getBitCoinHistory().bpi().get(LocalDate.now()));
        assertEquals(ONE, inMemoryCountDeskProvider.getBitCoinHistory().bpi().get(LocalDate.now().minusDays(1)));
    }
}