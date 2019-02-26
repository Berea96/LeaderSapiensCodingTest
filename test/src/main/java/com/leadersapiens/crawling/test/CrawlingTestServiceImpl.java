package com.leadersapiens.crawling.test;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

//크롤링의 비즈니스 로직을 담당하고있다
@Service
public class CrawlingTestServiceImpl implements CrawlingTestService {

    //크롤링 연습하려고 만든 메소드임
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

    //파라미터로 받아온 축구리그에 따라 정보를 긁어오는 메소드이다.
    @Override
    public String crawlingFootballData(String leagueName) {

        String result = "";
        String url = "https://sports.news.naver.com/wfootball/record/index.nhn";

        try {
            Document doc = Jsoup.connect(url)
                    .data("category", leagueName)
                    .data("tab", "team")
                    .get();

            Elements script = doc.select("script");

            result = script.get(11).html().split("jsonTeamRecord:")[1]
                     .split(",\n" +
                             "        teamRecordBodyName:")[0];

            JSONObject leagueData = createJsonObject(result, leagueName);

            System.out.println("야호 크롤링에 성공했습니다! 이건 정말 재미있습니다");

            result = leagueData.toString();
        } catch (IOException e) {
            e.printStackTrace();
            result = leagueName + "리그 가져오기 실패했습니다ㅠㅠ";
        }

        return result;
    }

    //Json 객체를 만들어 주는 메소드이다.
    public JSONObject createJsonObject(String footBallData, String leagueName) {

        JSONObject footBallDataObj = new JSONObject(footBallData);

        JSONArray regularTeamRecordList = footBallDataObj.getJSONArray("regularTeamRecordList");
        JSONArray league_rank = new JSONArray();

        for(int i = 0; i < regularTeamRecordList.length(); i++) {
            JSONObject data = (JSONObject)regularTeamRecordList.get(i);

            league_rank.put(getDataList(data));
        }

        JSONObject leagueObj = new JSONObject();
        leagueObj.put("league_rank", league_rank);
        leagueObj.put("league_name", leagueName);

        return leagueObj;
    }

    //얻어온 데이터를 반복문을 통해 원하는 값들만 뽑고 가공하는 메소드
    public JSONObject getDataList(JSONObject data) {

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

        return newData;
    }
}
