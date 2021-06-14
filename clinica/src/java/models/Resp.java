package models;

/**
 *
 * @author erika
 */
public class Resp<T> {

    private Boolean ok;
    private String message;
    private T data;

    public Resp(Boolean ok, String message, T data) {
        this.ok = ok;
        this.message = message;
        this.data = data;
    }

    public Resp() {
    }

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
