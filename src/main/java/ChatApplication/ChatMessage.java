package ChatApplication;

public class ChatMessage {

    String user;
    String timestamp;
    String message;

    public ChatMessage(String message, String timestamp) {
        Authentication authentication = Authentication.getInstance();

        this.user = authentication.getLoggedUser();
        this.timestamp = timestamp;
        this.message = message;
    }

    public ChatMessage(String username, String message, String timestamp) {
        this.user = username;
        this.timestamp = timestamp;
        this.message = message;
    }

    public String getUser() {
        return this.user;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return timestamp + " " + this.user + ": " + message;
    }
}
