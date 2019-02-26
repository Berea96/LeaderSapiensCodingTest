package com.leadersapiens.crawling.test;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CrawlingTestServiceImpl implements CrawlingTestService {
    @Override
    public String crawlingData() {

        System.out.println("이곳은 크롤링 테스트에 한창입니다!");

        String html = "<div id='wfootballTeamRecordBody'>"
                    + "<title>야호</title>"
                    + "<h1>크롤링성공!</h1>"
                    + "</div>";

        Document doc = Jsoup.parse(html);

        Element football = doc.getElementById("wfootballTeamRecordBody");

        return football.html();
    }

    @Override
    public String crawlingFootballData(String leagueName) {


        String result = "";
        String url = "https://sports.news.naver.com/wfootball/record/index.nhn";

        try {
            Document doc = Jsoup.connect(url)
                    .data("category", leagueName)
                    .data("tab", "team")
                    .get();

            Elements body = doc.select("script");

            result = body.get(11).html().split("jsonTeamRecord:")[1]
                     .split(",\n" +
                             "        teamRecordBodyName:")[0];

            createJsonObject(result);

            System.out.println("야호 크롤링에 성공했습니다! 이건 정말 재미있습니다");
        } catch (IOException e) {
            e.printStackTrace();
            result = "이런 실패했습니다ㅠㅠ";
        }

        return result;
    }

    public JSONObject createJsonObject(String footBallData) {

        JSONObject footBallDataObj = new JSONObject(footBallData);

        JSONArray regularTeamRecordList = footBallDataObj.getJSONArray("regularTeamRecordList");
        JSONArray league_rank = new JSONArray();

        System.out.println(regularTeamRecordList.length());

        for(int i = 0; i < regularTeamRecordList.length(); i++) {
            JSONObject data = (JSONObject)regularTeamRecordList.get(i);
            int rank = data.getInt("rank");
            String team = data.getString("teamName");
            int game_count = data.getInt("gameCount");
            int win_score = data.getInt("gainPoint");
            int win = data.getInt("won");
            int draw = data.getInt("drawn");
            int lose = data.getInt("lost");
            int get_point = data.getInt("gainPoint");
            int lose_point = data.getInt("loseGoal");
            int diff_point = data.getInt("goalGap");
        }

        System.out.println(footBallDataObj);

        return null;
    }
}
