package tuespotsolutions.android.nbse;

public class Course_Model {

    String sub_code,sub;

    public Course_Model(String sub_code, String sub) {
        this.sub_code = sub_code;
        this.sub = sub;
    }

    public Course_Model() {
    }

    public String getSub_code() {
        return sub_code;
    }

    public void setSub_code(String sub_code) {
        this.sub_code = sub_code;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }
}
