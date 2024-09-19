package com.bitcoin.history.domain;

import com.bitcoin.history.domain.entities.BitcoinPriceIndex;

public class CountDeskService {
    private final BaseCountDeskProvider provider;

    private final InMemoryCountDeskProvider inMemoryProvider;

    private boolean offlineMode;

    public CountDeskService(BaseCountDeskProvider provider, InMemoryCountDeskProvider inMemoryProvider, boolean offlineMode) {
        this.provider = provider;
        this.inMemoryProvider = inMemoryProvider;
        this.offlineMode = offlineMode;
    }

    public BitcoinPriceIndex getBitCoinHistory() {
        if (offlineMode) return inMemoryProvider.getBitCoinHistory();
        else {
            BitcoinPriceIndex bitCoinHistory = provider.getBitCoinHistory();
            inMemoryProvider.setBitCoinHistory(bitCoinHistory.bpi());
            return bitCoinHistory;
        }
    }
}
