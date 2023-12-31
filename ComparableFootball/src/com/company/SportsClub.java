package com.company;

import java.io.Serializable;
import java.util.Objects;

public abstract class SportsClub implements Serializable {
    private String club_name;
    private String location;
    private int noOf_matches;       //variables are made private to ensure encapsulation
    private int wins;
    private int defeats;
    private int draws;
    private int points;

    public SportsClub(String club_name, String location) {
        this.club_name = club_name;
        this.location = location;                   //constructor to initialize object
    }

    public SportsClub(String club_name, String location, int noOf_matches, int wins, int defeats, int draws, int points) {
        this.club_name = club_name;
        this.location = location;
        this.noOf_matches = noOf_matches;
        this.wins = wins;
        this.defeats = defeats;
        this.draws = draws;
        this.points = points;
    }

    public String getClub_name() {
        return club_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public int getNoOf_matches() {
        return noOf_matches;
    }

    public void setNoOf_matches(int noOf_matches) {
        this.noOf_matches = noOf_matches;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDefeats() {
        return defeats;
    }

    public void setDefeats(int defeats) {
        this.defeats = defeats;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }


    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportsClub that = (SportsClub) o;
        return noOf_matches == that.noOf_matches &&
                wins == that.wins &&
                defeats == that.defeats &&
                draws == that.draws &&
                points == that.points &&
                Objects.equals(club_name, that.club_name) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(club_name, location, noOf_matches, wins, defeats, draws, points);
    }

    @Override
    public String toString() {
        return "Club Name= " + club_name +
                " Location= " + location +
                " NoOf_matches=" + noOf_matches + " | " +
                " Wins=" + wins + "| " +
                " Defeats=" + defeats + " | " +
                " Draws=" + draws + " | " +
                " Points=" + points + " | ";
    }
}



