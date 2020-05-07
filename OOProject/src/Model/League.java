package Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class League {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	String leagueName;
	/**
	 * 
	 */
	public League() {
		leagueName = "Primary League";
	}

	/**
	 * @return
	 */
	public String getLeagueName() {
		return leagueName;
	}

	/**
	 * @param leagueName
	 */
	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return "leagueName: " + leagueName;
	}
	
	
}
