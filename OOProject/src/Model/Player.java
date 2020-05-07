package Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Player extends Person{
	@GeneratedValue(strategy = GenerationType.AUTO)
	/**
	 * 
	 */
	int goals;
	boolean goalie;
	String teamName;

	public Player() {
		super();
	}

	/**
	 * @param phone = phone number
	 * @param goals = goals scored
	 * @param goalie = goal keeper or not
	 * @param firstName = first name
	 * @param middleName = middle name
	 * @param lastName = last name
	 * @param teamName = team name
	 */
	public Player(String phone, int goals, boolean goalie, String firstName, String middleName, String lastName, String teamName) {
		super(phone, firstName, middleName, lastName);
		this.goals = goals;
		this.goalie = goalie;
		this.teamName = teamName;
	}

	/**
	 * @return
	 */
	public int getGoals() {
		return goals;
	}

	/**
	 * @param goals
	 */
	public void addGoals(int goals) {
		this.goals += goals;
	}

	/**
	 * @return
	 */
	public boolean isGoalie() {
		return goalie;
	}

	/**
	 * @param goalie
	 */
	public void setGoalie(boolean goalie) {
		this.goalie = goalie;
	}
	
	/**
	 * @return
	 */
	public String getTeam() {
		return teamName;
	}
	
	/**
	 * @param x
	 */
	public void setTeam(String x) {
		this.teamName = x;
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return "Team name: " + teamName + ", Name: " + firstName + " " + middleName + " " + lastName 
				+ ", email: " + email + ", goals: " + goals + ", goalie: " + goalie;
	}
	
	

}
