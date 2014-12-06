package servlets;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by titaevskiy.s on 06.12.14
 */
public class ResponseHelper {

    private StatusCode status = StatusCode.ERROR;
    private Map<String, Object> map = new HashMap<>();

    public void setStatusOk() {
        this.status = StatusCode.OK;
    }

    public void addToResponse(String name, Object obj) {
        map.put(name, obj);
    }

    public String toJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    private enum StatusCode {
        OK,
        ERROR
    }
}
