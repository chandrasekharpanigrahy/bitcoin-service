package com.bitcoin.history.config;

import com.bitcoin.history.domain.BitcoinService;
import com.bitcoin.history.domain.CountDeskService;
import com.bitcoin.history.domain.InMemoryCountDeskProvider;
import com.bitcoin.history.domain.entities.BitcoinPriceIndex;
import com.bitcoin.history.feign.CountDeskProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;

@Configuration
@EnableConfigurationProperties
public class DomainConfig {

    @Value("${count-desk.offline}")
    private boolean countDeskOffLine;

    @Bean
    public BitcoinService bitcoinService(CountDeskProvider provider){
        return new BitcoinService(provider);
    }

    @Bean
    public InMemoryCountDeskProvider inMemoryProvider() {
        return  new InMemoryCountDeskProvider(new BitcoinPriceIndex(new HashMap<>()));
    }

    @Bean
    public CountDeskService countDeskService(CountDeskProvider provider, InMemoryCountDeskProvider inMemoryProvider) {
        return new CountDeskService(provider, inMemoryProvider, countDeskOffLine);

    }

}
