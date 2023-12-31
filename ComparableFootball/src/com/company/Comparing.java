package com.company;

import java.util.Comparator;

public class Comparing implements Comparator<FootballClub> {

    @Override
    public int compare(FootballClub object1, FootballClub object2) {        //Comparing two objects points and goals score of two football object using comparator

        if (object1.getPoints() > object2.getPoints())
            return 1;
        else if (object2.getPoints() > object1.getPoints())
            return -1;
        else {
            int object1_goalDifference = object1.getNoGoals_scored() - object1.getNoGoals_received();
            int object2_goalDifference = object2.getNoGoals_scored() - object2.getNoGoals_received();
            if (object1_goalDifference > object2_goalDifference)
                return 1;
            else if (object2_goalDifference > object1_goalDifference) {
                return -1;
            } else return 0;
        }
    }

}

