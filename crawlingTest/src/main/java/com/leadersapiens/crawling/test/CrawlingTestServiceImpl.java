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
            Document doc = connectJsoup(url, leagueName);

            Elements script = doc.select("script");

            result = splitData(script.get(11).html());

            JSONObject leagueData = createJsonObject(result, leagueName);

            System.out.println("야호 크롤링에 성공했습니다! 이건 정말 재미있습니다");

            result = leagueData.toString();
        } catch (IOException e) {
            e.printStackTrace();
            result = leagueName + "리그 가져오기 실패했습니다ㅠㅠ";
        }

        return result;
    }

    //매개변수의 leagueName과 url에 따라서 Document객체를 받아오는 메소드
    private Document connectJsoup(String url, String leagueName) throws IOException {
        return Jsoup.connect(url)
                    .data("category", leagueName)
                    .data("tab", "team")
                    .get();
    }

    //가져온 script 문장들을 Json 형태로 쪼개주는 메소드
    private String splitData(String html) {
        return html.split("jsonTeamRecord:")[1]
                   .split(",\n" + "        teamRecordBodyName:")[0];
    }

    //Json 객체를 만들어 주는 메소드이다.
    private JSONObject createJsonObject(String footBallData, String leagueName) {

        JSONObject footBallDataObj = new JSONObject(footBallData);

        JSONArray regularTeamRecordList = footBallDataObj.getJSONArray("regularTeamRecordList");
        JSONArray league_rank = new JSONArray();

        for(int i = 0; i < regularTeamRecordList.length(); i++) {
            JSONObject data = (JSONObject)regularTeamRecordList.get(i);

            league_rank.put(getDataList(data));
        }

        return getCreateLeague(league_rank, leagueName);
    }

    //처리된 데이터들을 원하는 형태로 만들어주는 메소드
    private JSONObject getCreateLeague(JSONArray league_rank, String leagueName) {
        JSONObject leagueObj = new JSONObject();
        leagueObj.put("league_rank", league_rank);
        leagueObj.put("league_name", leagueName);

        return leagueObj;
    }

    //얻어온 데이터를 반복문을 통해 원하는 값들만 뽑아내는 메소드
    private JSONObject getDataList(JSONObject data) {

        Crawling crawling = new Crawling();

        crawling.setRank(data.getInt("rank"));
        crawling.setTeam(data.getString("teamName"));
        crawling.setGame_count(data.getInt("gameCount"));
        crawling.setWin_score(data.getInt("gainPoint"));
        crawling.setWin(data.getInt("won"));
        crawling.setDraw(data.getInt("drawn"));
        crawling.setLose(data.getInt("lost"));
        crawling.setGet_point(data.getInt("gainPoint"));
        crawling.setLose_point(data.getInt("loseGoal"));
        crawling.setDiff_point(data.getInt("goalGap"));

        return setDataList(crawling);
    }

    //뽑아낸 데이터를 JSONObject로 만들어주는 메소드
    public JSONObject setDataList(Crawling crawling) {
        JSONObject newData = new JSONObject();

        newData.put("rank", crawling.getRank());
        newData.put("team", crawling.getTeam());
        newData.put("game_count", crawling.getGame_count());
        newData.put("win_score", crawling.getWin_score());
        newData.put("win", crawling.getWin());
        newData.put("draw", crawling.getDraw());
        newData.put("lose", crawling.getLose());
        newData.put("get_point", crawling.getGet_point());
        newData.put("lose_point", crawling.getLose_point());
        newData.put("diff_point", crawling.getDiff_point());

        return newData;
    }
}