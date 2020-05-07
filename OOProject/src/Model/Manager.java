package Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Manager extends Person{
	@GeneratedValue(strategy = GenerationType.AUTO)
	/**
	 * 
	 */
	String DOB;
	int rating;
	String teamName;

	/**
	 * @param DOB = date of birth
	 * @param firstName = first name
	 * @param middleName = middle name
	 * @param lastName = last name
	 * @param phone = phone number
	 * @param rating = rating
	 * @param teamName = team name
	 */
	public Manager(String DOB, String firstName, String middleName, String lastName, String phone, int rating, String teamName) {
		super(phone, firstName, middleName, lastName);
		this.DOB = DOB;
		this.teamName = teamName;
		this.rating = rating;
	}
	
	public Manager() {}

	/**
	 * @return
	 */
	public String getDOB() {
		return DOB;
	}

	/**
	 * @param dOB
	 */
	public void setDOB(String dOB) {
		DOB = dOB;
	}

	/**
	 * @return
	 */
	public int getRating() {
		return rating;
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
	 * @param rating
	 */
	public void setRating(int rating) {
		if (rating > 0 && rating < 11)
			this.rating = rating;
		else 
			System.out.println("Rating has to be between 1 and 10");
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return "Team name: " + teamName + ", Name: " + firstName + " " + middleName
				+ " " + lastName + ", email: " + email + ", rating: " + rating;
	}
	
	

}
