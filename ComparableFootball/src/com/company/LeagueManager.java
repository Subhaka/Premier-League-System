package com.company;

import java.util.Date;

public interface LeagueManager {

    void addClub(FootballClub object);

    void deleteClub();

    void displayClubStats(String clubName);

    void displayStats();

    void displayMatch();

    void addPlayed_match(Date date, String team1, String team2, int goals_scoredA, int goals_scoredB);

    void saveChanges();

    void retrieve();

    void Match_details();

    void retrieveDetails();

}
