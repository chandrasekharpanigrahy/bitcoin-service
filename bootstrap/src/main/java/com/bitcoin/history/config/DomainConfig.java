package com.bitcoin.history.config;

import com.bitcoin.history.domain.BitcoinService;
import com.bitcoin.history.feign.CountDeskProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class DomainConfig {
    @Bean
    public BitcoinService bitcoinService(CountDeskProvider provider){
        return new BitcoinService(provider);
    }
}
