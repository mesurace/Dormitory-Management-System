package dms.model;
/**
 * @author surajlama
 *
 */
public class BuildingModel {
	public int DormId;
	public boolean IsEmpty;
	public int RoomNumber;
	public int DormNumber;
	public BuildingModel(int DormId,boolean IsEmpty,int RoomNumber,int DormNumber){
		this.DormId=DormId;
		this.IsEmpty=IsEmpty;
		this.RoomNumber=RoomNumber;
		this.DormNumber=DormNumber;
	}
	public int getDormId() {
		return DormId;
	}
	public void setDormId(int dormId) {
		DormId = dormId;
	}
	public boolean isIsEmpty() {
		return IsEmpty;
	}
	public void setIsEmpty(boolean isEmpty) {
		IsEmpty = isEmpty;
	}
	public int getRoomNumber() {
		return RoomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		RoomNumber = roomNumber;
	}
	public int getDormNumber() {
		return DormNumber;
	}
	public void setDormNumber(int dormNumber) {
		DormNumber = dormNumber;
	}

}
