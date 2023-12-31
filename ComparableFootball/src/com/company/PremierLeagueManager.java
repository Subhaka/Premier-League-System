package com.company;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PremierLeagueManager implements LeagueManager {

    private static final List<FootballClub> club_list = new ArrayList<>();
    private static final ArrayList<Match> match_list = new ArrayList<>();
    public static final int max_count = 20;

    public static List<FootballClub> getClub_list() {
        return club_list;
    }

    public static ArrayList<Match> getMatch_list() {
        return match_list;
    }

    @Override
    public void addClub(FootballClub object) {
        try {

            for (FootballClub object1 : club_list) {
                if (object.equals(object1)) {       //checking if the object entered already exist in arraylist or not
                    System.out.println("Club already exist!!!");
                    return;
                }
            }
            if (club_list.size() <= max_count) {
                club_list.add(object);
                System.out.println(object.getClub_name() + " Club added successfully");
                System.out.println(club_list.size() + " clubs exist!!");                //adding object to aaraylist
                System.out.println(max_count - club_list.size() + " No.of clubs can be added");
            } else if (object instanceof UniversityFootballClub || object instanceof SchoolFootballClub) {
                club_list.add(object);
                System.out.println(object.getClub_name() + " Club added successfully");
                System.out.println(club_list.size() + " clubs exist");
                System.out.println(max_count - club_list.size() + " No.of teams can be added ");
            } else System.out.println("clubs cannot be added");

        } catch (NullPointerException e) {
            System.out.println();
        }

    }

    @Override
    public void deleteClub() {
        Collections.sort(club_list, new Comparing().reversed()); //sorting arraylist
        club_list.remove(club_list.size() - 1);
        System.out.println("Club with least points removed successfully");          //removing club with least points
        System.out.println(max_count - club_list.size() + 1 + "No.of clubs can be added");
    }

    @Override
    public void displayClubStats(String clubName) {
        boolean foundClub = false;
        for (FootballClub obj : club_list) {
            if (obj.getClub_name().equals(clubName)) {   //checking if club name exist in arraylist and if yes printing the object
                foundClub = true;
                System.out.println(obj);

            }
        }
        if (!foundClub) {
            System.out.println("Club not found!!");
        }

    }

    @Override
    public void displayStats() {
        if (club_list.isEmpty()) {                  //checking if club lsit has existing clubs
            System.out.println("No Clubs found");
            return;
        }
        // Displaying club stats in table format
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%10s %15s %10s %9s %10s %10s %10s %15s %15s", "Club Name ", "Location", "Matches", "Wins", "Defeat", "Draws", "Points", "Goal scored", "Goal received");
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
        for (FootballClub club : club_list) {
            System.out.format("%-17s %-8s %7s %10s %9s %11s %9s %13s %14d", club.getClub_name(), club.getLocation(), club.getNoOf_matches(), club.getWins(), club.getDefeats(), club.getDraws(), club.getPoints(), club.getNoGoals_scored(), club.getNoGoals_received());
            System.out.println();
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");


    }

    @Override
    //displaying match in table format
    public void displayMatch() {
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%10s %35s %13s %20s %20s", "Date", "Club 1", "Club 2", "Club 1 Scores", "Club 2 Scores");
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
        for (Match match : match_list) {
            System.out.format("%-37s %-15s %-20s %1s %20d\n", match.getDate(), match.getClub1(), match.getClub2(), match.getClub1_score(), match.getClub2_score());
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");

    }

    @Override
    public void addPlayed_match(Date date, String team1, String team2, int goals_scoreA, int goals_scoredB) {

        FootballClub cluba = null;
        for (FootballClub club : club_list) {       //checking if club a exist in arraylist
            if (club.getClub_name().equals(team1))
                cluba = club;
        }
        if (cluba == null) {
            System.out.println("No clubs found as " + team1);
            return;
        }

        FootballClub clubb = null;
        for (FootballClub club : club_list) {
            if (club.getClub_name().equals(team2))
                clubb = club;
        }
        if (clubb == null) {
            System.out.println("No clubs found as " + team2);
            return;
        }

        Match match = new Match(date, cluba.getClub_name(), clubb.getClub_name(), goals_scoreA, goals_scoredB);// match object
        match_list.add(match);
        System.out.println("MATCH ADDED SUCCESSFULLY!!");


        cluba.setNoGoals_scored(cluba.getNoGoals_scored() + goals_scoreA);  //setting the goal scored and received of both clubs
        clubb.setNoGoals_scored(clubb.getNoGoals_scored() + goals_scoredB);
        cluba.setNoGoals_received(cluba.getNoGoals_received() + goals_scoredB);
        clubb.setNoGoals_received(clubb.getNoGoals_received() + goals_scoreA);

        cluba.setNoOf_matches(cluba.getNoOf_matches() + 1);
        clubb.setNoOf_matches(clubb.getNoOf_matches() + 1);
        if (goals_scoreA > goals_scoredB) {
            cluba.setWins(cluba.getWins() + 1);     //updating club stats according to the played match
            cluba.setPoints(cluba.getPoints() + 3);
            clubb.setDefeats(clubb.getDefeats() + 1);

        } else if (goals_scoreA < goals_scoredB) {
            clubb.setWins(clubb.getWins() + 1);
            clubb.setPoints(clubb.getPoints() + 3);
            cluba.setDefeats(cluba.getDefeats() + 1);

        } else {
            cluba.setDraws(cluba.getDraws() + 1);
            cluba.setPoints(cluba.getPoints() + 1);
            clubb.setDraws(clubb.getDraws() + 1);
            clubb.setPoints(clubb.getPoints() + 1);
        }


    }

    public static int random(int lowerBound, int upperBound) {
        return (lowerBound + (int) Math.round(Math.random()
                * (upperBound - lowerBound)));
    }

    public void randomMatch() throws ParseException {
        Random r1 = new Random();
        int index = r1.nextInt(club_list.size());   //getting random club from arraylist
        FootballClub club1 = club_list.get(index);
        int index2 = r1.nextInt(club_list.size());
        FootballClub club2 = club_list.get(index2);
        if (club1.equals(club2)) {
            return;
        }

        Random goals = new Random();

        int goalsA;
        goalsA = goals.nextInt(10); //random number generator to get no.of goals scored
        int goalsB;
        goalsB = goals.nextInt(10);


        int year_date = random(2020, 2020);
        int month_date = random(1, 12);
        int day_date = random(1, 31);
        String year = Integer.toString(year_date);
        String month = Integer.toString(month_date);        //randomly generating date
        String day = Integer.toString(day_date);
        String dateFormat = day + "/" + month + "/" + year;
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateFormat);
        try {
            Match match = new Match(date, club1.getClub_name(), club2.getClub_name(), goalsA, goalsB);
            match.setDate(date);
            match.setClub1(club1.getClub_name());
            match.setClub2(club2.getClub_name());  //creating match object and setting the random values
            match.setClub1_score(goalsA);
            match.setClub2_score(goalsB);
            match.setDate(date);
            System.out.println(match); //displaying match object

            addPlayed_match(date, club1.getClub_name(), club2.getClub_name(), goalsA, goalsB);//calling add played match method t get the function of updating stats.
            //avoids duplication of code
            //calling of played match method inside random match
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void saveChanges() {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("club stats.txt");
        } catch (FileNotFoundException exception) {
            System.out.println("File Not Found!!");
        }
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
        } catch (IOException exception) {
            System.out.println("Error occured while adding check in th file");
        }
        for (FootballClub club : club_list) {
            try {
                objectOutputStream.writeObject(club);       //saving club details to file as objects
            } catch (IOException exception) {
                System.out.println("error");
            } catch (NullPointerException e) {
                System.out.println();

            }
        }
        try {
            objectOutputStream.flush();
            fileOutputStream.close();
            objectOutputStream.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }


    }


    @Override
    public void retrieve() {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("club stats.txt");
        } catch (FileNotFoundException exception) {
            System.out.println("File Not Found!!");
        }
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(fileInputStream);
        } catch (IOException exception) {
            System.out.println("Clubs Not Found");
        }
        for (; ; ) {
            try {
                FootballClub footballClub = (FootballClub) objectInputStream.readObject(); //retrieving objects from file to arraylist
                club_list.add(footballClub);
                System.out.println(footballClub);
            } catch (EOFException e) {
                break;
            } catch (IOException exception) {
                System.out.println("error");
            } catch (ClassNotFoundException e) {
                System.out.println("Class is not found!!");

            } catch (NullPointerException e) {
                return;
            }
        }

        if (club_list.isEmpty()) {
            System.out.println("No clubs Found");  //checking if club list is empty
        }
        try {
            fileInputStream.close();
            objectInputStream.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    @Override

    public void Match_details() {
        FileOutputStream fileOutputStream1 = null;
        try {
            fileOutputStream1 = new FileOutputStream("match_details.txt");
        } catch (FileNotFoundException exception) {
            System.out.println("File Not Found");
        }
        ObjectOutputStream objectOutputStream1 = null;
        try {
            objectOutputStream1 = new ObjectOutputStream(fileOutputStream1);
        } catch (IOException exception) {
            System.out.println("error");
        }
        for (Match club : match_list) {
            try {
                objectOutputStream1.writeObject(club);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        try {
            objectOutputStream1.flush();
            fileOutputStream1.close();
            objectOutputStream1.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }


    @Override
    public void retrieveDetails() {
        FileInputStream fileInputStream1 = null;
        try {
            fileInputStream1 = new FileInputStream("match_details.txt");
        } catch (FileNotFoundException exception) {
            System.out.println("File Not Found");
        }
        ObjectInputStream objectInputStream1 = null;
        try {
            objectInputStream1 = new ObjectInputStream(fileInputStream1);
        } catch (IOException exception) {
            System.out.println("Matches has not been added");
        }
        for (; ; ) {
            try {
                Match match = (Match) objectInputStream1.readObject();
                match_list.add(match);
            } catch (EOFException e) {
                break;
            } catch (IOException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println("Class Not Found");
            } catch (NullPointerException e) {
                return;
            }
        }
        try {
            fileInputStream1.close();
            objectInputStream1.close();
        } catch (IOException exception) {
            exception.printStackTrace();

        }
    }
}

