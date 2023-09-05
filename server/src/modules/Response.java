package modules;

import java.io.Serializable;

public class Response implements Serializable {
    private String result;

    public Response(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}