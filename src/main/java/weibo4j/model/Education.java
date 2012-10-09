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
 * "area" : 0,
 * "city" : 0,
 * "department" : "商学院",
 * "department_id" : 0,
 * "id" : 11590757,
 * "is_verified" : "",
 * "province" : 11,
 * "school" : "北京工商大学",
 * "school_id" : 244001,
 * "type" : 1,
 * "visible" : 2,
 * "year" : 2006
 * }
 * <pre>
 * User: George
 * Date: 12-9-27
 * Time: 12:03
 * </pre>
 */
public class Education extends WeiboResponse {
    private static final Logger LOG = LoggerFactory.getLogger(Education.class);
    private static final long serialVersionUID = -1556605752519700393L;
    VisibleScope visible;
    SchoolType type;
    School school;
    int year;
    int province;
    String provinceName;
    boolean isVerified;
    long id;
    String department;
    int departmentId;
    int city;
    String cityName;
    int area;
    String areaName;

    public Education(Response res) throws WeiboException {
        super(res);
        init(res.asJSONObject());
    }

    public Education(JSONObject edu) {
        init(edu);
    }

    public Education() {
    }

    public static List<Education> constructEducations(Response response) throws WeiboException {
        JSONArray array = response.asJSONArray();
        int length = array.length();
        List<Education> educations = new ArrayList<Education>(length);
        for (int i = 0; i < length; i++) {
            try {
                Education edu = new Education(array.getJSONObject(i));
                educations.add(edu);
            } catch (JSONException e) {
                LOG.error("Error occurred:", e);
            }
        }
        return educations;
    }

    private void init(JSONObject edu) {
        area = getInt("area", edu);
        city = getInt("city", edu);
        department = getString("department", edu, false);
        departmentId = getInt("department_id", edu);
        id = getLong("id", edu);
        int code = getInt("is_verified", edu);
        isVerified = code == 0;
        province = getInt("province", edu);
        school = new School(getInt("school_id", edu), getString("school", edu, false));
        type = SchoolType.valueOf(getInt("type", edu));
        visible = VisibleScope.valueOf(getInt("visible", edu));
        year = getInt("year", edu);
    }

    public VisibleScope getVisible() {
        return visible;
    }

    public void setVisible(VisibleScope visible) {
        this.visible = visible;
    }

    public SchoolType getType() {
        return type;
    }

    public void setType(SchoolType type) {
        this.type = type;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
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

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Education");
        sb.append("{visible=").append(visible);
        sb.append(", type=").append(type);
        sb.append(", school=").append(school);
        sb.append(", year=").append(year);
        sb.append(", province=").append(province);
        sb.append(", provinceName='").append(provinceName).append('\'');
        sb.append(", isVerified=").append(isVerified);
        sb.append(", id=").append(id);
        sb.append(", department='").append(department).append('\'');
        sb.append(", departmentId=").append(departmentId);
        sb.append(", city=").append(city);
        sb.append(", cityName='").append(cityName).append('\'');
        sb.append(", area=").append(area);
        sb.append(", areaName='").append(areaName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
