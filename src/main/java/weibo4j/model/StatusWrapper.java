package weibo4j.model;

import java.util.List;

public class StatusWrapper {

	private List<Status> statuses;

	private long previousCursor;

	private long nextCursor;

	private long totalNumber;
	
	private String hasVisible;

	public StatusWrapper(List<Status> statuses, long previousCursor,
                         long nextCursor, long totalNumber, String hasVisible) {
		this.statuses = statuses;
		this.previousCursor = previousCursor;
		this.nextCursor = nextCursor;
		this.totalNumber = totalNumber;
		this.hasVisible = hasVisible;
	}

	public List<Status> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<Status> statuses) {
		this.statuses = statuses;
	}

	public long getPreviousCursor() {
		return previousCursor;
	}

	public void setPreviousCursor(long previousCursor) {
		this.previousCursor = previousCursor;
	}

	public long getNextCursor() {
		return nextCursor;
	}

	public void setNextCursor(long nextCursor) {
		this.nextCursor = nextCursor;
	}

	public long getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(long totalNumber) {
		this.totalNumber = totalNumber;
	}

	public String getHasVisible() {
		return hasVisible;
	}

	public void setHasVisible(String hasVisible) {
		this.hasVisible = hasVisible;
	}

}
