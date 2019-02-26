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

            JSONObject leagueData = createJsonObject(result, leagueName);

            System.out.println("야호 크롤링에 성공했습니다! 이건 정말 재미있습니다");

            result = leagueData.toString();
        } catch (IOException e) {
            e.printStackTrace();
            result = "이런 실패했습니다ㅠㅠ";
        }

        return result;
    }

    public JSONObject createJsonObject(String footBallData, String leagueName) {

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

            JSONObject newData = new JSONObject();

            newData.put("rank", rank);
            newData.put("team", team);
            newData.put("game_count", game_count);
            newData.put("win_score", win_score);
            newData.put("win", win);
            newData.put("draw", draw);
            newData.put("lose", lose);
            newData.put("get_point", get_point);
            newData.put("lose_point", lose_point);
            newData.put("diff_point", diff_point);

            System.out.println(newData);

            league_rank.put(newData);
        }

        JSONObject leagueObj = new JSONObject();
        leagueObj.put("league_rank", league_rank);
        leagueObj.put("league_name", leagueName);

        return leagueObj;
    }
}
