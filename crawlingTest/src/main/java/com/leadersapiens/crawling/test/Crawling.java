package com.leadersapiens.crawling.test;

public class Crawling {
    private int rank;
    private String team;
    private int game_count;
    private int win_score;
    private int win;
    private int draw;
    private int lose;
    private int get_point;
    private int lose_point;
    private int diff_point;

    public Crawling() {
    }

    public Crawling(int rank, String team, int game_count, int win_score,
                    int win, int draw, int lose, int get_point,
                    int lose_point, int diff_point) {
        this.rank = rank;
        this.team = team;
        this.game_count = game_count;
        this.win_score = win_score;
        this.win = win;
        this.draw = draw;
        this.lose = lose;
        this.get_point = get_point;
        this.lose_point = lose_point;
        this.diff_point = diff_point;
    }

    public int getRank() {
        return rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getTeam() {
        return team;
    }
    public void setTeam(String team) {
        this.team = team;
    }

    public int getGame_count() {
        return game_count;
    }
    public void setGame_count(int game_count) {
        this.game_count = game_count;
    }

    public int getWin_score() {
        return win_score;
    }
    public void setWin_score(int win_score) {
        this.win_score = win_score;
    }

    public int getWin() {
        return win;
    }
    public void setWin(int win) {
        this.win = win;
    }

    public int getDraw() {
        return draw;
    }
    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getLose() {
        return lose;
    }
    public void setLose(int lose) {
        this.lose = lose;
    }

    public int getGet_point() {
        return get_point;
    }
    public void setGet_point(int get_point) {
        this.get_point = get_point;
    }

    public int getLose_point() {
        return lose_point;
    }
    public void setLose_point(int lose_point) {
        this.lose_point = lose_point;
    }

    public int getDiff_point() {
        return diff_point;
    }
    public void setDiff_point(int diff_point) {
        this.diff_point = diff_point;
    }

    @Override
    public String toString() {
        return "Crawling{" +
                "rank=" + rank +
                ", team='" + team + '\'' +
                ", game_count=" + game_count +
                ", win_score=" + win_score +
                ", win=" + win +
                ", draw=" + draw +
                ", lose=" + lose +
                ", get_point=" + get_point +
                ", lose_point=" + lose_point +
                ", diff_point=" + diff_point +
                '}';
    }
}
