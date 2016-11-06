package dms.model;

/**
 * @author sureshadhikari
 *
 */
public class DormFacilityModel {

	public DormFacilityModel(String facility) {

		this.facility = facility;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	private String facility;

}
