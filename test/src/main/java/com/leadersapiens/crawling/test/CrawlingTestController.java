package com.leadersapiens.crawling.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/crawlingTest")
public class CrawlingTestController {

    @Autowired
    private CrawlingTestService service;

    @RequestMapping(value = "/get")
    public String crawlingData() {

        return service.crawlingData();
    }

    @RequestMapping(value = "/getFootball")
    public String crawlingFootball() {

        String result = "";

        result += service.crawlingFootballData("epl") + "\n";
        result += service.crawlingFootballData("primera") + "\n";
        result += service.crawlingFootballData("bundesliga") + "\n";

        return result;
    }
}
