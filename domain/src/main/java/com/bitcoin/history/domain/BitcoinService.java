package com.bitcoin.history.domain;

import com.bitcoin.history.domain.entities.BitcoinCommand;
import com.bitcoin.history.domain.entities.Response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class BitcoinService {
    private final BaseCountDeskProvider provider;

    public BitcoinService(BaseCountDeskProvider provider) {
        this.provider = provider;
    }

    public Response getHistory(BitcoinCommand command) {
        // Todo add offline feature, add a provider which will decide offline or online based on property toggle
        var bitcoinPriceIndex = provider.getBitCoinHistory().bpi();
        // there can be multiple max and min value (in case of same value) so instead of key getting value
        Map<LocalDate, BigDecimal> bitcoinPriceBetweenFromAndTo = priceBetweenTwoDates(command, bitcoinPriceIndex);

        BigDecimal max = bitcoinPriceBetweenFromAndTo.entrySet().stream().max(Map.Entry.comparingByValue())
                .orElseGet(() -> Map.entry(LocalDate.now(), BigDecimal.ZERO)).getValue();
        BigDecimal min = bitcoinPriceBetweenFromAndTo.entrySet().stream().min(Map.Entry.comparingByValue())
                .orElseGet(() -> Map.entry(LocalDate.now(), BigDecimal.ZERO)).getValue();

        return new Response(bitcoinPriceBetweenFromAndTo, max, min);
    }

    private Map<LocalDate, BigDecimal> priceBetweenTwoDates(BitcoinCommand command, Map<LocalDate, BigDecimal> bitcoinPriceIndex) {
        Map<LocalDate, BigDecimal> bitcoinPriceBetweenFromAndTo = new HashMap<>();

        bitcoinPriceIndex.keySet().stream()
                .filter(it -> it.isAfter(command.from().minusDays(1)) && it.isBefore(command.to().plusDays(1)))
                .forEach(it -> bitcoinPriceBetweenFromAndTo.put(it, bitcoinPriceIndex.get(it).multiply(command.value())));

        return bitcoinPriceBetweenFromAndTo;
    }
}
