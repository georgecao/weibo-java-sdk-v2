package weibo4j.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weibo4j.http.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * JSON data as follow:
 * {
 * "city" : 14,
 * "company" : "国务院办公厅",
 * "department" : "主任",
 * "end" : 9999,
 * "id" : 19447590,
 * "province" : 11,
 * "start" : 2008,
 * "visible" : 2
 * }
 * <pre>
 * User: George
 * Date: 12-9-27
 * Time: 12:05
 * </pre>
 */
public class Career extends WeiboResponse {
    private static final Logger LOG = LoggerFactory.getLogger(Career.class);
    private static final boolean DEBUG = LOG.isDebugEnabled();
    private static final long serialVersionUID = -1586983899982627999L;
    int city;
    String cityName;
    String company;
    String department;
    int end;
    int start;
    long id;
    int province;
    String provinceName;
    VisibleScope visible;

    public Career(JSONObject career) {
        init(career);
    }

    public Career(Response res) {
        super(res);
        try {
            init(res.asJSONObject());
        } catch (WeiboException e) {
            LOG.error("Error occurred:", e);
        }
    }

    public Career() {
    }

    public static List<Career> constructCareers(Response response) throws WeiboException {
        JSONArray array = response.asJSONArray();
        int length = array.length();
        List<Career> careers = new ArrayList<Career>(length);
        for (int i = 0; i < length; i++) {
            try {
                Career career = new Career(array.getJSONObject(i));
                careers.add(career);
            } catch (JSONException e) {
                LOG.error("Error occurred:", e);
            }
        }
        return careers;
    }

    private void init(JSONObject career) {
        city = getInt("city", career);
        company = getString("company", career, false);
        department = getString("department", career, false);
        end = getInt("end", career);
        id = getLong("id", career);
        province = getInt("province", career);
        start = getInt("start", career);
        visible = VisibleScope.valueOf(getInt("visible", career));
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public VisibleScope getVisible() {
        return visible;
    }

    public void setVisible(VisibleScope visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Career");
        sb.append("{city=").append(city);
        sb.append(", cityName='").append(cityName).append('\'');
        sb.append(", company='").append(company).append('\'');
        sb.append(", department='").append(department).append('\'');
        sb.append(", end=").append(end);
        sb.append(", start=").append(start);
        sb.append(", id=").append(id);
        sb.append(", province=").append(province);
        sb.append(", provinceName='").append(provinceName).append('\'');
        sb.append(", visible=").append(visible);
        sb.append('}');
        return sb.toString();
    }
}
