package com.bitcoin.history.domain;

import com.bitcoin.history.domain.entities.BitcoinPriceIndex;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

// Todo for readme. we can create bean to start with one provider based on property value
//  but to change to offline mode we need to restart with toggle off
//  want to use providers without even starting service by using client request parameter

// Todo we can avoid this code with spring boot cache and caffine cache.
public class InMemoryCountDeskProvider {

    private BitcoinPriceIndex bitcoinPriceIndex ;

    public InMemoryCountDeskProvider(BitcoinPriceIndex bitcoinPriceIndex) {
        this.bitcoinPriceIndex = bitcoinPriceIndex;
    }

    public BitcoinPriceIndex getBitCoinHistory() {
        return bitcoinPriceIndex;
    }

    public void setBitCoinHistory(Map<LocalDate, BigDecimal> bpi) {
        Map<LocalDate, BigDecimal> currentBPI = bitcoinPriceIndex.bpi();
        // If else can be removed if data is not large
        if(currentBPI.isEmpty()) bitcoinPriceIndex = new BitcoinPriceIndex(bpi);
        else {
            List<LocalDate> keys = bpi.keySet().stream().filter(it -> !currentBPI.containsKey(it)).toList();
            keys.forEach(it -> currentBPI.put(it, bpi.get(it)));
        }

    }
}
