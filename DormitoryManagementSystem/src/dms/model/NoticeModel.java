package dms.model;

public class NoticeModel {

	private String Notice;
	private String NoticeDate;

	public NoticeModel(String Notice, String NoticeDate) {
		this.Notice = Notice;
		this.NoticeDate = NoticeDate;
	}

	public NoticeModel() {
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
