package com.leadersapiens.crawling.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//크롤링을 실시하는 메인 컨트롤러라고 할수있다.
@RestController
@RequestMapping(value = "/football")
public class CrawlingTestController {

    @Autowired
    private CrawlingTestService service;

    //크롤링 연습하는 메소드
    @RequestMapping(value = "/get")
    public String crawlingData() {

        return service.crawlingData();
    }

    //본격적으로 축구리그 이름에 따라서 Json을 얻어오는 메소드
    @RequestMapping(value = "/getFootball", method = RequestMethod.GET)
    public String crawlingFootball() {


        String result = "";

        result += service.crawlingFootballData("epl") + "\n";
        result += service.crawlingFootballData("primera") + "\n";
        result += service.crawlingFootballData("bundesliga") + "\n";

        return result;
    }

    //테스트 url로 매핑한 메소드
    @RequestMapping(value = "/rank/{leagueName}", method = RequestMethod.GET)
    public String crawlingFootball(@PathVariable String leagueName) {
        String rank = service.crawlingFootballData(leagueName);

        return rank;
    }
}
