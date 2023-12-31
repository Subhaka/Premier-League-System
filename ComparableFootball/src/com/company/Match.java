package com.company;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Match implements Serializable {
    private String club1;
    private String club2;
    private Date date;
    private int club1_score;
    private int club2_score;

    public Match(Date date, String club1, String club2, int club1_score, int club2_score) {
        this.club1 = club1;
        this.club2 = club2;
        this.club1_score = club1_score;
        this.club2_score = club2_score;
        this.date = date;
    }

    public String getClub1() {
        return club1;
    }

    public void setClub1(String club1) {
        this.club1 = club1;
    }

    public String getClub2() {
        return club2;
    }

    public void setClub2(String club2) {
        this.club2 = club2;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getClub1_score() {
        return club1_score;
    }

    public void setClub1_score(int club1_score) {
        this.club1_score = club1_score;
    }

    public int getClub2_score() {
        return club2_score;
    }

    public void setClub2_score(int club2_score) {
        this.club2_score = club2_score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return club1_score == match.club1_score &&
                club2_score == match.club2_score &&
                Objects.equals(club1, match.club1) &&
                Objects.equals(club2, match.club2) &&
                Objects.equals(date, match.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(club1, club2, date, club1_score, club2_score);
    }

    @Override
    public String toString() {
        return "\nDate = " + date + " || " +
                "Club 1= " + club1 + " || " +
                " Club1 Score= " + club1_score + " || " +
                " Club2= " + club2 + " || " +
                "Club2_Score= " + club2_score;
    }


}

