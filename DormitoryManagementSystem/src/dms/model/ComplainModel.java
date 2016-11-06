package dms.model;
/**
 * @author surajlama
 *
 */
public class ComplainModel {
	public int ComplainId;
	public int StudentId;
	public String ComplainDate;
	public String Complain;

	public ComplainModel(int ComplainId, int StudentId, String ComplainDate, String Complain) {
		this.ComplainId=ComplainId;
		this.StudentId=StudentId;
		this.ComplainDate=ComplainDate;
		this.Complain=Complain;
	}

	public int getComplainId() {
		return ComplainId;
	}

	public void setComplainId(int complainId) {
		ComplainId = complainId;
	}

	public int getStudentId() {
		return StudentId;
	}

	public void setStudentId(int studentId) {
		StudentId = studentId;
	}

	public String getComplainDate() {
		return ComplainDate;
	}

	public void setComplainDate(String complainDate) {
		ComplainDate = complainDate;
	}

	public String getComplain() {
		return Complain;
	}

	public void setComplain(String complain) {
		Complain = complain;
	}
}
