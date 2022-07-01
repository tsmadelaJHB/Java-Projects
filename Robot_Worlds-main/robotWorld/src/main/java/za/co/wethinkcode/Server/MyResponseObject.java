package za.co.wethinkcode.Server;

import java.util.Map;

/**
 * Creates a response object
 * Returns the response object
 **/

public class MyResponseObject {
    private String result;
    private Map<Object, Object> data;
    private Map<String, Object> state;

    public void setState(Map<String, Object> state) {
        this.state = state;
    }

    public void setResults(String result) {
        this.result = result;
    }

    public void setData(Map<Object, Object> data) {
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public Map<Object, Object> getData() {
        return data;
    }

    public Map<String, Object> getState() {
        return state;
    }
}
