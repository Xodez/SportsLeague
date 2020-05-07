package View;

import java.time.LocalDate;
import Controller.Controller;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Model.*;

/**
 * @author Kasparas Skruibis
 * @version 1.0
 */
public class MyMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private Controller c;
    private Team tm;
	private Player pl;
	private Manager mn;

    /**
     * @param primaryStage interface of the application
     * This is the interface of the application where the user can interact with the code.
     */
    @Override
    public void start(Stage primaryStage) {

        /*
         * Initialize controller
         */

        c = new Controller();


        /*
         * Primary stage set up
         */

        primaryStage.setTitle("My League");
        primaryStage.setMaxWidth(1280);
        primaryStage.setMaxHeight(720);
        
        /*
         * Tabs
         */
        
        TabPane tabPanes = new TabPane();
        GridPane addOrRemovePane = new GridPane();
        GridPane editPane = new GridPane();
        GridPane displayPane = new GridPane();
        
        Tab tab1 = new Tab("Create/Remove");
        Tab tab2 = new Tab("Edit");
        Tab tab3 = new Tab("Display");
        
        tab1.setContent(addOrRemovePane);
        tab2.setContent(editPane);
        tab3.setContent(displayPane);
        
        tabPanes.getTabs().addAll(tab1, tab2, tab3);
        
        tab1.setClosable(false);
        tab2.setClosable(false);
        tab3.setClosable(false);
        
        tabPanes.setMaxWidth(Double.MAX_VALUE);

        /*
         * Grid panes for the application 
         * mainPane = main grid pane for the application, it is used for the first tab that the user sees.
         */
        
        Scene scene1 = new Scene(tabPanes, 1280, 720);
        
        // ------------------------------------- Code for tab1 ---------------------------------------------------

		/*
		Text area in first tab to show recent activity of the user
		 */

        TextArea info = new TextArea();
        GridPane.setHgrow(info, Priority.ALWAYS);
        info.setEditable(false);

		/*
		Buttons for the user to add or remove Teams, players and managers
		 */
        
        Button addTeam = new Button("Add a team");
        addTeam.setOnAction(e -> {
        	Stage popUp = new Stage();
        	
            popUp.initModality(Modality.APPLICATION_MODAL);
            popUp.setTitle("Add a team");

            TextField teamName = new TextField();
            teamName.setPromptText("Put team name here");

            TextField teamJerseyColor = new TextField();
            teamJerseyColor.setPromptText("Put team jersey color here");

            Button saveButton = new Button("Save");
            
            saveButton.setOnAction(a -> {
            	String teamNameText = teamName.getText();
            	String teamJerseyColorText = teamJerseyColor.getText();
                tm = c.addTeam(teamNameText, teamJerseyColorText);
                info.setText(tm.toString());
                popUp.close();
            });

            VBox layout = new VBox(15);

            layout.getChildren().addAll(teamName, teamJerseyColor, saveButton);

            layout.setAlignment(Pos.CENTER);

            Scene sceneAddTeam = new Scene(layout, 300, 250);

            popUp.setScene(sceneAddTeam);

            popUp.showAndWait();
        });
        addTeam.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(addTeam, Priority.ALWAYS);

        Button addPlayer = new Button("Add a player");
        addPlayer.setOnAction(e -> {
        	Stage popUp = new Stage();

            popUp.initModality(Modality.APPLICATION_MODAL);
            popUp.setTitle("Add a player");

            TextField playerFirstName = new TextField();
            playerFirstName.setPromptText("Put in player first name");

            TextField playerMiddleName = new TextField();
            playerMiddleName.setPromptText("Put in player middle name");

            TextField playerLastName = new TextField();
            playerLastName.setPromptText("Put in player last name");

            TextField playerPhoneNumber = new TextField();
            playerPhoneNumber.setPromptText("Put in player phone number");

            Label playerGoalScoredInfo = new Label("Put in player goals scored/saved");
            Spinner playerGoalsScored = new Spinner(0, 9999, 0);
            playerGoalsScored.setEditable(true);
            HBox goalHBox = new HBox(2);
            goalHBox.getChildren().addAll(playerGoalScoredInfo, playerGoalsScored);

            CheckBox playerGoalie = new CheckBox("Check if player is a goalie");
            
            Label teamSelect = new Label("Select which team to put the player in");
            ListView<Team> teamView = new ListView<Team>();
            ObservableList<Team> content = FXCollections.observableArrayList(c.getTeams());
            teamView.setItems(content);
            
            Button saveButton = new Button("Save");
            saveButton.setOnAction(a -> {
                String firstName = playerFirstName.getText();
                String middleName = playerMiddleName.getText();
                String lastName = playerLastName.getText();
                String phoneNumber = playerPhoneNumber.getText();
                int goalsScored = (int) playerGoalsScored.getValue();
                boolean goalieInfo = playerGoalie.isSelected();
                tm = teamView.getSelectionModel().getSelectedItem();
                String teamName = tm.getTeamName();
                pl = c.addPlayer(phoneNumber, goalsScored, goalieInfo, firstName, middleName, lastName, teamName);
                info.setText(pl.toString());
                popUp.close();
            });

            VBox layout = new VBox(15);

            layout.getChildren().addAll(playerFirstName, playerMiddleName, playerLastName, playerPhoneNumber, playerGoalie, goalHBox, teamSelect, teamView, saveButton);

            layout.setAlignment(Pos.CENTER);

            Scene sceneAddPlayer = new Scene(layout, 500, 450);

            popUp.setScene(sceneAddPlayer);

            popUp.showAndWait();
        });
        addPlayer.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(addPlayer, Priority.ALWAYS);

        Button addManager = new Button("Add a manager");
        addManager.setOnAction(e -> {
        	Stage popUp = new Stage();

            popUp.initModality(Modality.APPLICATION_MODAL);
            popUp.setTitle("Add a manager");

            TextField playerFirstName = new TextField();
            playerFirstName.setPromptText("Put in managers first name");

            TextField playerMiddleName = new TextField();
            playerMiddleName.setPromptText("Put in managers middle name");

            TextField playerLastName = new TextField();
            playerLastName.setPromptText("Put in managers last name");
            

            TextField playerPhoneNumber = new TextField();
            playerPhoneNumber.setPromptText("Put in player phone number");

            DatePicker DOB = new DatePicker();
            DOB.setPromptText("Select manager date of birth");

            Label managerRatingInfo = new Label("Put in the managers rating");
            Spinner managerRating = new Spinner(0, 10, 0);
            managerRating.setEditable(true);
            HBox ratingHBox = new HBox(2);
            ratingHBox.getChildren().addAll(managerRatingInfo, managerRating);
            
            Label teamSelect = new Label("Select which team to put the manager in");
            ListView<Team> teamView = new ListView<Team>();
            ObservableList<Team> content = FXCollections.observableArrayList(c.getTeams());
            teamView.setItems(content);

            Button saveButton = new Button("Save");
            saveButton.setOnAction(a -> {
                String firstName = playerFirstName.getText();
                String middleName = playerMiddleName.getText();
                String lastName = playerLastName.getText();
                LocalDate dateOfBirth = DOB.getValue();
                String phoneNumber = playerPhoneNumber.getText();
                int rating = (int) managerRating.getValue();
                tm = teamView.getSelectionModel().getSelectedItem();
                String teamName = tm.getTeamName();
                mn = c.addManager(dateOfBirth, firstName, middleName, lastName, phoneNumber, rating, teamName);
                info.setText(mn.toString());
                popUp.close();
            });

            VBox layout = new VBox(15);

            layout.getChildren().addAll(playerFirstName, playerMiddleName, playerLastName, playerPhoneNumber, DOB, ratingHBox, teamSelect, teamView, saveButton);

            layout.setAlignment(Pos.CENTER);

            Scene sceneAddManager = new Scene(layout, 500, 450);

            popUp.setScene(sceneAddManager);

            popUp.showAndWait();
        });
        addManager.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(addManager, Priority.ALWAYS);

        Button removeTeam = new Button("Remove a team");
        removeTeam.setOnAction(e ->{
        	Stage popUp = new Stage();

            popUp.initModality(Modality.APPLICATION_MODAL);
            popUp.setTitle("Remove a team");

            Label teamSelect = new Label("Select which team to remove");
            ListView<Team> teamView = new ListView<Team>();
            ObservableList<Team> content = FXCollections.observableArrayList(c.getTeams());
            teamView.setItems(content);
            
            Button saveButton = new Button("Save");
            saveButton.setOnAction(a -> {
            	tm = teamView.getSelectionModel().getSelectedItem();
                String teamName = tm.getTeamName();
                c.removeTeam(teamName);
                info.setText("Succesfully removed");
                popUp.close();
            });
            
            VBox layout = new VBox(15);

            layout.getChildren().addAll(teamSelect, teamView, saveButton);

            layout.setAlignment(Pos.CENTER);

            Scene sceneRemoveManager = new Scene(layout, 500, 450);

            popUp.setScene(sceneRemoveManager);

            popUp.showAndWait();
        });
        removeTeam.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(removeTeam, Priority.ALWAYS);

        Button removePlayer = new Button("Remove a player");
        removePlayer.setOnAction(e -> {
        	Stage popUp = new Stage();

            popUp.initModality(Modality.APPLICATION_MODAL);
            popUp.setTitle("Remove a player");

            Label playerSelect = new Label("Select which player to remove");
            ListView<Player> playerView = new ListView<Player>();
            ObservableList<Player> content = FXCollections.observableArrayList(c.getPlayers());
            playerView.setItems(content);
            
            Button saveButton = new Button("Save");
            saveButton.setOnAction(a -> {
            	pl = playerView.getSelectionModel().getSelectedItem();
                String playerEmail = pl.getEmail();
                c.removePlayer(playerEmail);
                info.setText("Succesfully removed");
                popUp.close();
            });
            
            VBox layout = new VBox(15);

            layout.getChildren().addAll(playerSelect, playerView, saveButton);

            layout.setAlignment(Pos.CENTER);

            Scene sceneRemovePlayer = new Scene(layout, 500, 450);

            popUp.setScene(sceneRemovePlayer);

            popUp.showAndWait();
        });
        removePlayer.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(removePlayer, Priority.ALWAYS);

        Button removeManager = new Button("Remove a manager");
        removeManager.setOnAction(e -> {
        	Stage popUp = new Stage();

            popUp.initModality(Modality.APPLICATION_MODAL);
            popUp.setTitle("Remove a manager");

            Label managerSelect = new Label("Select which manager to remove");
            ListView<Manager> managerView = new ListView<Manager>();
            ObservableList<Manager> content = FXCollections.observableArrayList(c.getManagers());
            managerView.setItems(content);
            
            Button saveButton = new Button("Save");
            saveButton.setOnAction(a -> {
            	mn = managerView.getSelectionModel().getSelectedItem();
                String managerEmail = mn.getEmail();
                c.removeManager(managerEmail);
                info.setText("Succesfully removed");
                popUp.close();
            });
            
            VBox layout = new VBox(15);

            layout.getChildren().addAll(managerSelect, managerView, saveButton);

            layout.setAlignment(Pos.CENTER);

            Scene sceneRemoveManager = new Scene(layout, 500, 450);

            popUp.setScene(sceneRemoveManager);

            popUp.showAndWait();
        });
        removeManager.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(removeManager, Priority.ALWAYS);

        HBox mainButtons = new HBox();

        mainButtons.getChildren().addAll(addManager, addPlayer, addTeam, removeManager, removePlayer, removeTeam);
        GridPane.setRowIndex(mainButtons, 4);
        GridPane.setColumnIndex(mainButtons, 0);
        
        // ------------------------------------- Code for tab2 ---------------------------------------------------
        
        Label playerSelect = new Label("Select which player to edit");
        ListView<Player> playerView = new ListView<Player>();
        tab2.setOnSelectionChanged(onChange -> {
            ObservableList<Player> content = FXCollections.observableArrayList(c.getPlayers());
            playerView.setItems(content);
        });
        
        playerView.prefWidthProperty().bind(primaryStage.widthProperty());
        playerView.prefHeightProperty().set(primaryStage.getMaxWidth() / 4);
        
        Button selectPlayer = new Button("Select player");
        selectPlayer.setOnAction(e ->{
        	pl = c.getPlayer(playerView.getSelectionModel().getSelectedItem().getEmail());
        	Stage popUp = new Stage();

            popUp.initModality(Modality.APPLICATION_MODAL);
            popUp.setTitle("Update a player");

            Label playerFirstNameInfo = new Label("Player first name");
            TextField playerFirstName = new TextField();
            playerFirstName.setText(pl.getFirstName());

            Label playerMiddleNameInfo = new Label("Player middle name");
            TextField playerMiddleName = new TextField();
            playerMiddleName.setText(pl.getMiddleName());

            Label playerLastNameInfo = new Label("Player last name");
            TextField playerLastName = new TextField();
            playerLastName.setText(pl.getLastName());
            
            Label playerPhoneInfo = new Label("Player phone number");
            TextField playerPhoneNumber = new TextField();
            playerPhoneNumber.setText(pl.getPhone());

            Label playerGoalScoredInfo = new Label("Goals Scored");
            Spinner playerGoalsScored = new Spinner(0, 9999, pl.getGoals());
            playerGoalsScored.setEditable(true);
            HBox goalHBox = new HBox(2);
            goalHBox.getChildren().addAll(playerGoalScoredInfo, playerGoalsScored);

            CheckBox playerGoalie = new CheckBox("Check if player is a goalie");
            playerGoalie.selectedProperty().set(pl.isGoalie());
            
            Label teamSelect = new Label("Player team");
            ListView<Team> teamView = new ListView<Team>();
            ObservableList<Team> content = FXCollections.observableArrayList(c.getTeam(pl.getTeam()));
            Button changeTeam= new Button("Change player team");
            teamView.setItems(content);
            changeTeam.setOnAction(x -> {
            	ObservableList<Team> contents = FXCollections.observableArrayList(c.getTeams());
            	teamView.setItems(contents);
            });
            Button saveButton = new Button("Update");
            saveButton.setOnAction(a -> {
                String firstName = playerFirstName.getText();
                String middleName = playerMiddleName.getText();
                String lastName = playerLastName.getText();
                String phoneNumber = playerPhoneNumber.getText();
                int goals = (int) playerGoalsScored.getValue();
                Boolean goalie = playerGoalie.isSelected();
                String teamName = teamView.getSelectionModel().getSelectedItem().getTeamName();
                c.editPlayer(pl, firstName, middleName, lastName, phoneNumber, goals, goalie, teamName);
                popUp.close();
            });

            VBox layout = new VBox(15);

            layout.getChildren().addAll(playerFirstNameInfo, playerFirstName, playerMiddleNameInfo, 
            		playerMiddleName, playerLastNameInfo, playerLastName, 
            		playerGoalScoredInfo, playerGoalsScored,
            		playerPhoneInfo, playerPhoneNumber, playerGoalie, 
            		changeTeam, teamSelect, teamView, saveButton);

            layout.setAlignment(Pos.CENTER);

            Scene sceneAddPlayer = new Scene(layout, 600, 550);

            popUp.setScene(sceneAddPlayer);

            popUp.showAndWait();
        });
        
        VBox editLayout = new VBox();
        editLayout.getChildren().addAll(playerSelect, playerView, selectPlayer);
        
        // ------------------------------------- Code for tab3 ---------------------------------------------------
        TabPane displayTabs = new TabPane();
        GridPane teamPane = new GridPane();
        GridPane playerPane = new GridPane();
        GridPane managerPane = new GridPane();
        
        Tab teamTab = new Tab("Team");
        teamTab.closableProperty().set(false);
        
        Tab playerTab = new Tab("Player");
        playerTab.closableProperty().set(false);
        
        Tab managerTab = new Tab("Manager");
        managerTab.closableProperty().set(false);
        
        displayTabs.getTabs().addAll(teamTab, playerTab, managerTab);
        
        displayTabs.prefWidthProperty().bind(primaryStage.widthProperty());

        Button displayTeams = new Button("Display teams");
        ListView<Team> teamDisplayView = new ListView<Team>();
        teamDisplayView.prefWidthProperty().bind(primaryStage.widthProperty());
        displayTeams.setOnAction(onClick -> {
        	ObservableList<Team> content = FXCollections.observableArrayList(c.getTeams());
        	teamDisplayView.setItems(content);
        });
        	
        Button displayPlayers = new Button("Display selected teams players");
        
        ListView<Team> selectTeamView = new ListView<Team>();
                playerTab.setOnSelectionChanged(e ->{
        	ObservableList<Team> content = FXCollections.observableArrayList(c.getTeams());
        	selectTeamView.setItems(content);
        });
            selectTeamView.prefWidthProperty().bind(primaryStage.widthProperty());
        
        ListView<Player> playerDisplayView = new ListView<Player>();
        
        displayPlayers.setOnAction(onClick ->{
        	ObservableList<Player> contents = FXCollections.observableArrayList(c.getPlayersAlphabetically(selectTeamView.getSelectionModel().getSelectedItem().getTeamName()));
        	playerDisplayView.setItems(contents);
        });
        
        Button displayManagersAlphabetically = new Button("Display managers alphabetically");
        Button displayManagersStarRating = new Button("Display managers by star rating");
        
        ListView<Manager> managerDisplayView = new ListView<Manager>();
        managerDisplayView.prefWidthProperty().bind(primaryStage.widthProperty());
        
        displayManagersAlphabetically.setOnAction(onClick ->{
        	ObservableList<Manager> contents = FXCollections.observableArrayList(c.getManagersAlphabetically());
        	managerDisplayView.setItems(contents);
        });	
        
        displayManagersStarRating.setOnAction(onClick ->{
        	ObservableList<Manager> contents = FXCollections.observableArrayList(c.getManagersStarRating());
        	managerDisplayView.setItems(contents);
        });	
        
        teamTab.setContent(teamPane);
        
        playerTab.setContent(playerPane);
        
        managerTab.setContent(managerPane);
        
        HBox displayLayout = new HBox();
        displayLayout.getChildren().add(displayTabs);
        
        VBox teamLayout = new VBox();
        teamLayout.getChildren().addAll(teamDisplayView, displayTeams);
        
        VBox playerLayout = new VBox();
        playerLayout.getChildren().addAll(selectTeamView, displayPlayers, playerDisplayView);
        
        VBox managerLayout = new VBox();
        managerLayout.getChildren().addAll(managerDisplayView, displayManagersAlphabetically, displayManagersStarRating);
        
		/*
		Code for adding everything together
		 */
        
        teamPane.getChildren().addAll(teamLayout);
        playerPane.getChildren().addAll(playerLayout);
        managerPane.getChildren().addAll(managerLayout);
        displayPane.getChildren().addAll(displayLayout);
        addOrRemovePane.getChildren().addAll(info, mainButtons);
        editPane.getChildren().addAll(editLayout);
        primaryStage.setScene(scene1);
        primaryStage.show();
    }
}
