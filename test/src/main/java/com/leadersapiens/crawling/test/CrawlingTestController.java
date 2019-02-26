package com.leadersapiens.crawling.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/crawlingTest")
public class CrawlingTestController {

    @Autowired
    private CrawlingTestService service;

    @ResponseBody
    @RequestMapping(value = "/get")
    public String crawlingData() {

        return service.crawlingData();
    }

    @ResponseBody
    @RequestMapping(value = "/getFootball")
    public String crawlingFootball() {

        service.crawlingFootballData("epl");
        service.crawlingFootballData("bundesliga");

        return service.crawlingFootballData("primera");
    }
}
