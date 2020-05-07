package Model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class Name{
	/**
	 * 
	 */
	String firstName;
	String middleName;
	String lastName;
	@Id
	String email;
	
	public Name() {
		
	}
	
	/**
	 * @param firstName = first name
	 * @param middleName = middle name
	 * @param lastName = last name
	 */
	public Name(String firstName, String middleName, String lastName) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		email = firstName + "." + lastName + "@gmail.com";
	}

	/**
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	/**
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 */
	public void setEmail() {
		email = this.firstName + "." + this.lastName + "@gmail.com";
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return firstName + " " + middleName + " " + lastName;
	}
	
	public void Print() {
		System.out.println(this);
	}
	
}
