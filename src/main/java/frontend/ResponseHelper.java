package frontend;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by titaevskiy.s on 06.12.14
 */
public class ResponseHelper {

    private ResponsesCode status;
    private Map<String, Object> response;

    public void setStatus(ResponsesCode status) {
        this.status = status;
    }

    public void addToResponse(String name, Object obj) {
        if (response == null) {
            response = new HashMap<>();
        }
        response.put(name, obj);
    }

    public String toJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
