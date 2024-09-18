package com.bitcoin.history.feign;

import com.bitcoin.history.domain.BaseCountDeskProvider;
import com.bitcoin.history.domain.entities.BitcoinPriceIndex;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "CountDeskProvider", url = "${count-desk.url}")
public interface CountDeskProvider extends BaseCountDeskProvider {

    @GetMapping(path = "v1/bpi/historical/close.json")
    BitcoinPriceIndex getBitCoinHistory();

}
