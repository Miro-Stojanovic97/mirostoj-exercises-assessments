//Miro Stojanovic
//Module 5 Assessment: Don't Break My House

package learn.house.domain;

import java.util.ArrayList;
import java.util.List;

public class Result<T> {

    private T payload;

    private List<String> messages = new ArrayList<>();

    public T getPayload() { return payload; }
    
    public void setPayload(T payload) { this.payload = payload; }

    public List<String> getErrorMessages() { return new ArrayList<>(messages); }

    public void addErrorMessage(String message) { messages.add(message); }

    public boolean isSuccess() { return messages.size() == 0; }

}
