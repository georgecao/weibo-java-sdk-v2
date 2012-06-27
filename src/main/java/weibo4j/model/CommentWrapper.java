package weibo4j.model;

import java.io.Serializable;
import java.util.List;

public class CommentWrapper implements Serializable {
    private static final long serialVersionUID = 2733844889553220819L;
    private List<Comment> comments;
    private long previousCursor;
    private long nextCursor;
    private long totalNumber;
    private String hasVisible;

    public CommentWrapper(List<Comment> comments, long previousCursor,
                          long nextCursor, long totalNumber, String hasVisible) {
        this.comments = comments;
        this.previousCursor = previousCursor;
        this.nextCursor = nextCursor;
        this.totalNumber = totalNumber;
        this.hasVisible = hasVisible;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
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
