package weibo4j.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Visible implements Serializable {
    private static final long serialVersionUID = -4372053769518687710L;
    private int type;
    private int listId;

    public Visible(JSONObject json) throws JSONException {
        this.type = json.getInt("type");
        this.listId = json.getInt("list_id");
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + listId;
        result = prime * result + type;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Visible other = (Visible) obj;
        if (listId != other.listId)
            return false;
        return type == other.type;
    }

    @Override
    public String toString() {
        return "Visible [type=" + type + ", list_id=" + listId + "]";
    }

}
