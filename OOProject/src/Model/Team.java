package Model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Team{
	@GeneratedValue(strategy = GenerationType.AUTO)
	/**
	 * 
	 */
	@Id
	String teamName;
	String jerseyColor;
	String leagueName;

	/**
	 * @param teamName = team name
	 * @param jerseyColor = team jersey color
	 */
	public Team(String teamName, String jerseyColor) {
		this.jerseyColor = jerseyColor;
		this.teamName = teamName;
		leagueName = "Primary League";
	}
	
	public Team() {}

	/**
	 * @return
	 */
	public String getJerseyColor() {
		return jerseyColor;
	}

	/**
	 * @param jerseyColor
	 */
	public void setJerseyColor(String jerseyColor) {
		this.jerseyColor = jerseyColor;
	}

	/**
	 * @return
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * @param teamName
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		String toString = "";
		toString += " Team name: " + teamName + " Jersey Color: " + jerseyColor + " League name: " + leagueName;
		return toString;
	}
}
