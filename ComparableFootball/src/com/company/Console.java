package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Console {

    private static final LeagueManager premierLeagueManager = new PremierLeagueManager();

    public static boolean name(String in) {
        return Pattern.matches("[a-zA-Z]+", in);
    }

    private static void addClub() {
        Scanner sc = new Scanner(System.in);
        FootballClub object = null;
        while (true) {
            System.out.println("Enter Club Name to be inserted : ");
            String clubName = sc.nextLine().toLowerCase();
            if (name(clubName)) {
                System.out.println("Enter Location of Club: ");
                String location = sc.nextLine().toLowerCase();
                if (name(location)) {


                    System.out.println("Please enter the type of FootBall Club(1-'Football Club, '2-'University Football Club', 3-'SchoolFootball Club'):");
                    int footballClub_Type = sc.nextInt();

                    switch (footballClub_Type) {

                        case 1:
                            object = new FootballClub(clubName, location);
                            break;

                        case 2:
                            Scanner scc = new Scanner(System.in);
                            System.out.println("Enter University Name:");
                            String uni_name = scc.nextLine();
                            object = new UniversityFootballClub(clubName, location, uni_name);
                            break;
                        case 3:
                            Scanner ft = new Scanner(System.in);
                            System.out.println("Enter School Name:");
                            String school = ft.nextLine();
                            object = new SchoolFootballClub(clubName, location, school);
                            break;
                        default:
                            System.out.println("====Invalid Input !! Please Enter one of the Inputs mentioned above!====");
                            continue;
                    }
                    premierLeagueManager.addClub(object);
                    break;
                } else System.out.println("Invalid Input!!");
            } else System.out.println("Invalid Input!!");
            return;
        }
    }

    private static void deleteClub() {

        premierLeagueManager.deleteClub();
    }

    private static void displayClubstats() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Club Name:");
        String clubName = sc.nextLine().toLowerCase();
        premierLeagueManager.displayClubStats(clubName);
    }

    private static void printList_ofClubs() {
        premierLeagueManager.displayStats();
    }

    private static void displayMatch(){
        premierLeagueManager.displayMatch();
    }

    private static void addMatch() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter club 1:");
        String club1 = sc.nextLine().toLowerCase();

        System.out.println("Enter club 2:");
        String club2 = sc.nextLine().toLowerCase();

        System.out.println("Enter Date in dd/mm/yyyy format:");
        String date = sc.nextLine();

        Date dateFormat;
        try {
            dateFormat = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
            System.out.println("Please Enter the date in 'dd/MM/yyyy' format");
            return;
        }
        System.out.println("Enter Goals scored by club 1:");
        int goals_scoredA = sc.nextInt();

        System.out.println("Enter Goals scored by club 2:");
        int goals_scoredB = sc.nextInt();

        premierLeagueManager.addPlayed_match(dateFormat, club1, club2, goals_scoredA, goals_scoredB);

    }


    public static void main(String[] args) {
        System.out.println("-----------------------------------------*********************EXISTING CLUBS***********************----------------------------------------------\n");
        premierLeagueManager.retrieve();
        premierLeagueManager.retrieveDetails();

        menuLoop:
        while (true) {
            try {
                System.out.println("\n|=================================*********************FOOTBALL PREMIER LEAGUE***********************==========================================| ");
                System.out.println("ENTER 1 TO ADD A CLUB");
                System.out.println("ENTER 2 TO DELETE CLUB WITH LEAST POINTS");
                System.out.println("ENTER 3 TO DISPLAY STATS OF SELECTED CLUB");
                System.out.println("ENTER 4 TO DISPLAY STATISTICS");
                System.out.println("ENTER 5 TO DISPLAY MATCH DETAILS");
                System.out.println("ENTER 6 TO ADD PLAYED MATCH");
                System.out.println("ENTER 7 TO DISPLAY GUI");
                System.out.println("ENTER 8 TO QUIT");
                System.out.println("\nEnter: ");

                Scanner sc = new Scanner(System.in);
                int userInput = sc.nextInt();
                switch (userInput) {

                    case 1:
                        addClub();
                        break;

                    case 2:
                        deleteClub();
                        break;
                    case 3:
                        premierLeagueManager.saveChanges();
                        displayClubstats();
                        break;
                    case 4:
                        premierLeagueManager.saveChanges();
                        printList_ofClubs();
                        break;
                    case 5:
                        displayMatch();
                        break;
                    case 6:
                        addMatch();
                        premierLeagueManager.Match_details();
                        premierLeagueManager.saveChanges();
                        break;

                    case 7:
                        GUI.main(args);
                        premierLeagueManager.Match_details();
                        premierLeagueManager.saveChanges();
                        break;

                    case 8:
                        premierLeagueManager.saveChanges();
                        break menuLoop;


                    default:
                        System.out.println("Invalid Input!! Please Enter one of the Inputs asked above!! ");

                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input!!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
