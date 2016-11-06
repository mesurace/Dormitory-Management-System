package dms.model;

import java.util.Date;
/**
 * @author surajlama
 *
 */
public class Notice {
	public int NoticeId;
	public int BuildingNumber;
	public String Notice;
	/*public Date NoticeDate;*/
	public String NoticeDate;
	public Notice(String Notice,int BuildingNumber,String NoticeDate,int NoticeId){
		this.BuildingNumber=BuildingNumber;
		this.Notice=Notice;
		this.NoticeDate=NoticeDate;
	}
	public Notice(){}
	public int getBuildingNumber() {
		return BuildingNumber;
	}
	public void setBuildingNumber(int buildingNumber) {
		BuildingNumber = buildingNumber;
	}
	public String getNotice() {
		return Notice;
	}
	public void setNotice(String notice) {
		Notice = notice;
	}
	public String getNoticeDate() {
		return NoticeDate;
	}
	public void setNoticeDate(String noticeDate) {
		NoticeDate = noticeDate;
	}

}
