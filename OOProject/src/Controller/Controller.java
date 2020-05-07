package Controller;

import Model.*;
import javafx.util.Callback;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Controller of the application
 */
public class Controller {
	private Team tm;
	private Player pl;
	private Manager mn;
	private League lg;

	/**
	 * Controller constructor
	 */
	public Controller() {
	}
	
	/**
	 * @param teamNameText = team name
	 * @param teamJerseyColorText = team jersey color
	 * @return returns created team object
	 */
	public Team addTeam(String teamNameText, String teamJerseyColorText) {
		tm = new Team(teamNameText, teamJerseyColorText);
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("OOProject");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		entitymanager.persist(tm);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
		return tm;
	}

	/**
	 * @param phoneNumber = player phone number
	 * @param goalsScored = player goals scored/saved
	 * @param goalieInfo = player goal keeper or not
	 * @param firstName = player first name
	 * @param middleName = player middle name
	 * @param lastName = player last name
	 * @param teamName = player team name
	 * @return returns created player object
	 */
	public Player addPlayer(String phoneNumber, int goalsScored, boolean goalieInfo, String firstName,
			String middleName, String lastName, String teamName) {
		pl = new Player(phoneNumber, goalsScored, goalieInfo, firstName, middleName, lastName, teamName);
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("OOProject");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		entitymanager.persist(pl);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
		return pl;
	}

	/**
	 * @param dateOfBirth = manager date of birth
	 * @param firstName = manager first name
	 * @param middleName = manager middle name
	 * @param lastName = manager last name
	 * @param phoneNumber = manager phone number
	 * @param rating = manager rating
	 * @param teamName = manager team name 
	 * @return returns created manager object
	 */
	public Manager addManager(LocalDate dateOfBirth, String firstName, String middleName, String lastName,
			String phoneNumber, int rating, String teamName) {
		mn = new Manager(dateOfBirth.toString(), firstName, middleName, lastName, phoneNumber, rating, teamName);
		mn.setRating(rating);
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("OOProject");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		entitymanager.persist(mn);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
		return mn;
	}

	/**
	 * 
	 */
	public void addLeague() {
		lg = new League();
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("OOProject");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		entitymanager.persist(lg);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	/**
	 * @param name = team name
	 */
	public void removeTeam(String name) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("OOProject");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		tm = entitymanager.find(Team.class, name);
		entitymanager.remove(tm);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	/**
	 * @return returns list of teams
	 */
	public List<Team> getTeams() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("OOProject");
		EntityManager entitymanager = emfactory.createEntityManager();
		Query q1 = entitymanager.createQuery("SELECT x FROM Team x");
		List<Team> teamList = (List<Team>) q1.getResultList();
		entitymanager.close();
		emfactory.close();
		return teamList;
	}

	/**
	 * @return returns list of players
	 */
	public List<Player> getPlayers() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("OOProject");
		EntityManager entitymanager = emfactory.createEntityManager();
		Query q1 = entitymanager.createQuery("SELECT x FROM Player x");
		List<Player> playerList = (List<Player>) q1.getResultList();
		entitymanager.close();
		emfactory.close();
		return playerList;
	}

	/**
	 * @param playerEmail = player email
	 */
	public void removePlayer(String playerEmail) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("OOProject");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		pl = entitymanager.find(Player.class, playerEmail);
		entitymanager.remove(pl);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	/**
	 * @return returns list of managers
	 */
	public List<Manager> getManagers() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("OOProject");
		EntityManager entitymanager = emfactory.createEntityManager();
		Query q1 = entitymanager.createQuery("SELECT x FROM Manager x");
		List<Manager> managerList = (List<Manager>) q1.getResultList();
		entitymanager.close();
		emfactory.close();
		return managerList;
	}

	/**
	 * @param managerEmail = manager email
	 */
	public void removeManager(String managerEmail) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("OOProject");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		mn = entitymanager.find(Manager.class, managerEmail);
		entitymanager.remove(mn);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	/**
	 * @param playerEmail = player email
	 * @return returns player object
	 */
	public Player getPlayer(String playerEmail) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("OOProject");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		pl = entitymanager.find(Player.class, playerEmail);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
		return pl;
	}

	/**
	 * @param teamName = team name
	 * @return returns team object
	 */
	public Team getTeam(String teamName) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("OOProject");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		tm = entitymanager.find(Team.class, teamName);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
		return tm;
	}

	/**
	 * @param pl = player object
	 * @param firstName = player first name
	 * @param middleName = player middle name
	 * @param lastName = player last name
	 * @param phoneNumber = player phone number
	 * @param goals = player goals scored/saved
	 * @param goalie = player goal keeper or not
	 * @param teamName = player team name
	 */
	public void editPlayer(Player pl, String firstName, String middleName, String lastName, String phoneNumber,
			int goals, Boolean goalie, String teamName) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("OOProject");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		pl = entitymanager.find(Player.class, pl.getEmail());
		entitymanager.remove(pl);
		entitymanager.getTransaction().commit();
		entitymanager.getTransaction().begin();
		pl = new Player(phoneNumber, goals, goalie, firstName, middleName, lastName, teamName);
		entitymanager.persist(pl);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	/**
	 * @param team = team name
	 * @return returns list of players in a team sorted alphabetically
	 */
	public List<Player> getPlayersAlphabetically(String team) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("OOProject");
		EntityManager entitymanager = emfactory.createEntityManager();
		Query q1 = entitymanager.createQuery("SELECT x FROM Player x WHERE x.teamName = :team ORDER BY x.firstName ASC").setParameter("team", team);
		List<Player> playerList = (List<Player>) q1.getResultList();
		entitymanager.close();
		emfactory.close();
		return playerList;
	}

	/**
	 * @return returns managers sorted alphabetically
	 */
	public List<Manager> getManagersAlphabetically() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("OOProject");
		EntityManager entitymanager = emfactory.createEntityManager();
		Query q1 = entitymanager.createQuery("SELECT x FROM Manager x ORDER BY x.rating desc");
		List<Manager> managerList = (List<Manager>) q1.getResultList();
		entitymanager.close();
		emfactory.close();
		return managerList;
	}

	/**
	 * @return returns managers sorted by team rating
	 */
	public List<Manager> getManagersStarRating() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("OOProject");
		EntityManager entitymanager = emfactory.createEntityManager();
		Query q1 = entitymanager.createQuery("SELECT x FROM Manager x ORDER BY x.firstName asc");
		List<Manager> managerList = (List<Manager>) q1.getResultList();
		entitymanager.close();
		emfactory.close();
		return managerList;
	}
}
