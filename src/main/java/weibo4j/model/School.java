package weibo4j.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import weibo4j.http.Response;

import java.util.ArrayList;
import java.util.List;

public class School extends WeiboResponse {
    private static final long serialVersionUID = -5991828656755790609L;
    private int id; // 学校id
    private String name; // 学校名称

    public School(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public School(Response res) throws WeiboException {
        super(res);
        JSONObject json = res.asJSONObject();
        try {
            id = json.getInt("id");
            name = json.getString("name");
        } catch (JSONException je) {
            throw new WeiboException(je.getMessage() + ":" + json.toString(),
                    je);
        }
    }

    public School(JSONObject json) throws WeiboException {
        try {
            id = json.getInt("id");
            name = json.getString("name");
        } catch (JSONException je) {
            throw new WeiboException(je.getMessage() + ":" + json.toString(),
                    je);
        }
    }

    public static List<School> constructSchool(Response res) throws WeiboException {
        try {
            JSONArray list = res.asJSONArray();
            int size = list.length();
            List<School> schools = new ArrayList<School>(size);
            for (int i = 0; i < size; i++) {
                schools.add(new School(list.getJSONObject(i)));
            }
            return schools;
        } catch (JSONException e) {
            throw new WeiboException(e);
        } catch (WeiboException te) {
            throw te;
        }

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
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
        School other = (School) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        return "School [id=" + id + ", name=" + name + "]";
    }

}
