package pets.domain;

import java.util.ArrayList;

public class Result<T> {

    private ResultType type = ResultType.SUCCESS;
    private T payload;
    private ArrayList<String> messages = new ArrayList<>();

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }



    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }

    public ResultType getType() {
        return type;
    }

    public void addMessage(String message, ResultType resultType) {
        type = resultType;
        this.messages.add(message);
    }
}
