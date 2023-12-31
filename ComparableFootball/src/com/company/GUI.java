package com.company;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GUI extends Application {

    PremierLeagueManager leagueManager = new PremierLeagueManager();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Stage stage = new Stage();

        VBox img = new VBox();
        img.setLayoutX(20);
        img.setLayoutY(20);
        img.setId("logo");
        img.setMinSize(50, 100);
        Label lbl = new Label("PREMIER LEAGUE MANAGEMENT");
        lbl.setLayoutX(130);
        lbl.setLayoutY(30);
        lbl.setId("lbl");


        Button btn_match = new Button("Match Details");
        btn_match.setLayoutX(200);
        btn_match.setLayoutY(100);
        btn_match.setMinHeight(40);
        btn_match.setMinWidth(180);
        btn_match.setId("btn_main");

        Button clubs = new Button("Club Details");
        clubs.setLayoutX(200);
        clubs.setLayoutY(200);
        clubs.setMinWidth(180);
        clubs.setMinHeight(40);
        clubs.setId("btn_main");

        Button random = new Button("Random Match");
        random.setLayoutX(200);
        random.setLayoutY(300);
        random.setMinWidth(180);
        random.setMinHeight(40);
        random.setId("btn_main");


        clubs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage club_stage = new Stage();
                Button btn = new Button("Sort by Points");
                btn.setLayoutX(240);
                btn.setLayoutY(500);
                btn.setId("btn");
                btn.setMinHeight(40);
                btn.setMinWidth(100);


                Button btn_wins = new Button("Sort by Wins");
                btn_wins.setLayoutX(400);
                btn_wins.setLayoutY(500);
                btn_wins.setId("btn");
                btn_wins.setMinWidth(100);
                btn_wins.setMinHeight(40);

                Button btn_score = new Button("Sort by goals scored");
                btn_score.setLayoutX(560);
                btn_score.setLayoutY(500);
                btn_score.setId("btn");
                btn_score.setMinHeight(40);
                btn_score.setMinWidth(100);
                Button back = new Button();
                back.setLayoutX(20);
                back.setLayoutY(20);
                back.setId("back");
                back.setMinWidth(40);
                back.setMinHeight(40);
                back.setShape(new Circle(20));


                Label heading = new Label("Club Statistics");
                heading.setLayoutX(420);
                heading.setLayoutY(20);
                heading.setId("club");

                TableView<FootballClub> tableView = new TableView<>();
                tableView.setId("table-view");
                TableColumn<FootballClub, String> name = new TableColumn<>("Club Name");
                name.setCellValueFactory(new PropertyValueFactory<>("club_name"));
                name.setMinWidth(100);

                TableColumn<FootballClub, String> location = new TableColumn<>("Location");
                location.setCellValueFactory(new PropertyValueFactory<>("location"));
                location.setMinWidth(100);


                TableColumn<FootballClub, Integer> matches = new TableColumn<>("No.Of.Matches");
                matches.setCellValueFactory(new PropertyValueFactory<>("noOf_matches"));
                matches.setMinWidth(50);

                TableColumn<FootballClub, Integer> wins = new TableColumn<>("No.of.wins");
                wins.setCellValueFactory(new PropertyValueFactory<>("wins"));
                wins.setMinWidth(50);


                TableColumn<FootballClub, Integer> defeats = new TableColumn<>(" No.of.defeats");
                defeats.setCellValueFactory(new PropertyValueFactory<>("defeats"));
                defeats.setMinWidth(50);

                TableColumn<FootballClub, Integer> draws = new TableColumn<>("No.of. draws");
                draws.setCellValueFactory(new PropertyValueFactory<>("draws"));
                draws.setMinWidth(50);

                TableColumn<FootballClub, Integer> points = new TableColumn<>("No.of. points");
                points.setCellValueFactory(new PropertyValueFactory<>("points"));
                points.setMinWidth(50);

                TableColumn<FootballClub, Integer> goals_scored = new TableColumn<>("Goals scored");
                goals_scored.setCellValueFactory(new PropertyValueFactory<>("noGoals_scored"));
                goals_scored.setMinWidth(60);

                TableColumn<FootballClub, Integer> goals_received = new TableColumn<>("Goals received");
                goals_received.setCellValueFactory(new PropertyValueFactory<>("noGoals_received"));
                goals_received.setMinWidth(60);

                ObservableList<FootballClub> viewContent = FXCollections.observableArrayList();
                for (FootballClub club : PremierLeagueManager.getClub_list()) {
                    viewContent.add(club);

                }

                tableView.getColumns().addAll(name, location, matches, wins, defeats, draws, points, goals_scored, goals_received);


                tableView.setItems(viewContent);

                btn.setOnAction(event16 -> {
                    points.setSortType(TableColumn.SortType.DESCENDING);
                    tableView.getSortOrder().setAll(points);
                });
                btn_wins.setOnAction(event15 -> {
                    wins.setSortType(TableColumn.SortType.DESCENDING);
                    tableView.getSortOrder().setAll(wins);
                });

                btn_score.setOnAction(event14 -> {
                    goals_scored.setSortType(TableColumn.SortType.DESCENDING);
                    tableView.getSortOrder().setAll(goals_scored);
                });

                back.setOnAction(event13 -> {
                    club_stage.close();
                    primaryStage.show();
                });

                tableView.setPrefSize(840, 400);

                VBox vbox = new VBox(tableView);
                vbox.setLayoutX(70);
                vbox.setLayoutY(70);
                club_stage.setMaxWidth(1000);
                club_stage.setMaxHeight(600);
                club_stage.setMinWidth(1000);
                club_stage.setMinHeight(600);
                club_stage.setTitle("Club Details");
                Pane club_pane = new Pane();
                club_pane.setId("pane2");
                club_pane.getChildren().addAll(vbox, btn_wins, btn, btn_score, back, heading);
                Scene scene = new Scene(club_pane);
                scene.getStylesheets().addAll(this.getClass().getResource("stylesheet.css").toExternalForm());
                club_stage.setScene(scene);
                primaryStage.close();
                club_stage.show();
            }
        });
        random.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            try {
                leagueManager.randomMatch();
            } catch (ParseException e) {
                e.printStackTrace();
                }
            }

        });


        btn_match.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage1 = new Stage();

                Button btn_date = new Button("Sort by Date");
                btn_date.setLayoutX(70);
                btn_date.setLayoutY(25);
                btn_date.setId("btn");

                Button search = new Button();
                search.setLayoutX(600);
                search.setLayoutY(20);
                search.setId("btn");
                search.setId("search");
                search.setShape(new Circle(20));
                search.setMinWidth(40);
                search.setMinHeight(40);

                TextField txt = new TextField();
                txt.setLayoutX(650);
                txt.setLayoutY(30);
                txt.setPromptText("Search by Date");
                txt.setStyle("-fx-border-color: darkblue; -fx-background-color: #fcffe0");


                Button btn_back = new Button();
                btn_back.setLayoutX(20);
                btn_back.setLayoutY(20);
                btn_back.setShape(new Circle(20));
                btn_back.setMinWidth(40);
                btn_back.setMinHeight(40);
                btn_back.setId("back");

                Label match = new Label("Match Details");
                match.setLayoutX(340);
                match.setLayoutY(50);
                match.setId("heading");

                TableView<Match> tableView1 = new TableView<>();
                tableView1.setId("table-view");

                TableColumn<Match, Date> date = new TableColumn<>("Date");
                date.setCellValueFactory(new PropertyValueFactory<>("date"));
                date.setMinWidth(150);


                TableColumn<Match, String> cluba = new TableColumn<>("Club 1");
                cluba.setCellValueFactory(new PropertyValueFactory<>("club1"));
                cluba.setMinWidth(100);

                TableColumn<Match, String> clubb = new TableColumn<>("Club 2");
                clubb.setCellValueFactory(new PropertyValueFactory<>("club2"));
                clubb.setMinWidth(80);

                TableColumn<Match, String> cluba_score = new TableColumn<>("Scores by club 1");
                cluba_score.setCellValueFactory(new PropertyValueFactory<>("club1_score"));
                cluba_score.setMinWidth(60);


                TableColumn<Match, String> clubb_score = new TableColumn<>("Scores by club 2");
                clubb_score.setCellValueFactory(new PropertyValueFactory<>("club2_score"));
                clubb_score.setMinWidth(60);

                ObservableList<Match> viewContent1 = FXCollections.observableArrayList();
                for (Match club1 : PremierLeagueManager.getMatch_list()) {
                    viewContent1.add(club1);

                }

                tableView1.getColumns().addAll(date, cluba, clubb, cluba_score, clubb_score);
                tableView1.setItems(viewContent1);
                VBox vBox1 = new VBox(tableView1);
                vBox1.setLayoutX(156);
                vBox1.setLayoutY(100);
                tableView1.setPrefSize(553, 350);
                btn_back.setOnAction(event17 -> {
                    stage1.close();
                    primaryStage.show();
                });

                search.setOnAction(event12 -> {
                    try {

                        String datex = (txt.getText());
                        Date dateFormat = null;
                        try {
                            dateFormat = new SimpleDateFormat("dd/MM/yyyy").parse(datex);
                        } catch (ParseException e) {
                            System.out.println("Please enter the date");
                        }

                        for (int i = 0; i < tableView1.getItems().size(); i++) {
                            tableView1.getItems().clear();
                        }
                        for (Match match1 : PremierLeagueManager.getMatch_list()) {
                            if (match1.getDate().equals(dateFormat)) {
                                System.out.println(match1);
                                PremierLeagueManager.getMatch_list().add(match1);
                                viewContent1.add(match1);
                            }
                        }

                    } catch (Exception e) {
                        System.out.println("Matches Not Found");
                    }
                });

                btn_date.setOnAction(event1 -> {
                    leagueManager.Match_details();
                    date.setSortType(TableColumn.SortType.DESCENDING);
                    tableView1.getSortOrder().setAll(date);


                });


                stage1.setMinHeight(600);
                stage1.setMaxHeight(600);
                stage1.setMaxWidth(850);
                stage1.setMinWidth(850);
                stage1.setTitle("Match Details");
                Pane pane1 = new Pane();
                pane1.setId("pane1");
                pane1.getChildren().addAll(vBox1, btn_date, btn_back, search, txt, match);
                Scene scene1 = new Scene(pane1);
                scene1.getStylesheets().addAll(this.getClass().getResource("stylesheet.css").toExternalForm());
                primaryStage.close();
                stage1.setScene(scene1);
                stage1.show();

            }
        });


        Pane pane = new Pane();
        pane.setId("pane");
        pane.getChildren().addAll(btn_match, clubs, random, lbl, img);

        Scene scene = new Scene(pane);
        scene.getStylesheets().addAll(this.getClass().getResource("stylesheet.css").toExternalForm());
        stage.setScene(scene);
        primaryStage.setTitle("Football League Manager");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(480);
        primaryStage.setMaxWidth(600);
        primaryStage.setMaxHeight(480);
        primaryStage.show();

    }

}

