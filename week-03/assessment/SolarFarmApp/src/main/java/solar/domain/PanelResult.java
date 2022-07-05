//Miro Stojanovic
//Assessment 3, Solar Panel App

package solar.domain;

import solar.models.Panel;

import java.util.ArrayList;
import java.util.List;

public class PanelResult {

    public List<String> getMessages() {
        return new ArrayList<>(messages);
    }

    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }

    public Panel getPanel() {
        return panel;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }

    private ArrayList<String> messages = new ArrayList<>();
    private Panel panel;

    public void addErrorMessage(String message) {
        messages.add(message);
    }

    public boolean isSuccess() {
        return messages.size() == 0;
    }
}
