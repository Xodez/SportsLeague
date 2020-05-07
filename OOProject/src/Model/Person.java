package Model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Person extends Name{
	String phone;

	public Person() {
		
	}
	
	/**
	 * @param phone = phone number
	 * @param firstName = first name
	 * @param middleName = middle name
	 * @param lastName = last name
	 */
	public Person(String phone, String firstName, String middleName, String lastName) {
		super(firstName, middleName, lastName);
		this.phone = phone;
	
	}

	/**
	 * @param phone
	 */
	public Person(String phone) {
		this.phone = phone;
	}

	/**
	 * @return
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
