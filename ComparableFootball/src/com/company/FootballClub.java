package com.company;

import java.util.Objects;

public class FootballClub extends SportsClub {
    private int noGoals_scored;
    private int noGoals_received;

    public FootballClub(String club_name, String location) {
        super(club_name, location);
    }


    public FootballClub(String club_name, String location, int noOf_matches, int wins, int defeats, int draws, int points, int nogoals_scored, int nogoals_received) {
        super(club_name, location, noOf_matches, wins, defeats, draws, points);
        this.noGoals_scored = nogoals_scored;
        this.noGoals_received = nogoals_received;
    }


    public int getNoGoals_scored() {
        return noGoals_scored;
    }

    public void setNoGoals_scored(int noGoals_scored) {
        this.noGoals_scored = noGoals_scored;
    }

    public int getNoGoals_received() {
        return noGoals_received;
    }

    public void setNoGoals_received(int noGoals_received) {
        this.noGoals_received = noGoals_received;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FootballClub that = (FootballClub) o;
        return noGoals_scored == that.noGoals_scored &&
                noGoals_received == that.noGoals_received;
    }

    @Override
    public int hashCode() {
        return Objects.hash(noGoals_scored, noGoals_received);
    }


    @Override
    public String toString() {
        return super.toString() +
                "Goals_scored=" + noGoals_scored + "|" +
                "Goals_received=" + noGoals_received + "\n";
    }
}



