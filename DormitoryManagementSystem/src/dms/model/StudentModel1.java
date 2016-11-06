/**
 * 
 */
package dms.model;

/**
 * @author sureshadhikari
 *
 */
public class StudentModel1 {

	private String lastName;
	private String firstName;
	private String intake;
	private String country;
	private String buildingNumber;
	private String roomNumber;
	private String email;

	public StudentModel1(String lastName, String firstName, String intake, String country, String buildingNumber,
			String roomNumber, String email) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.intake = intake;
		this.country = country;
		this.buildingNumber = buildingNumber;
		this.roomNumber = roomNumber;
		this.email = email;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getIntake() {
		return intake;
	}

	public String getCountry() {
		return country;
	}

	public String getBuildingNumber() {
		return buildingNumber;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setIntake(String intake) {
		this.intake = intake;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setBuildingNumber(String buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
