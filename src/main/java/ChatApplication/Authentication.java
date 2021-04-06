package ChatApplication;

import java.util.HashMap;

public class Authentication {

    private HashMap<String, User> users;
    
    public Authentication() {
        this.users = new HashMap<>();
    }

    public boolean authenticateUser(String username, String password) {
        
        if (this.users.containsKey(username)) {
            if (this.users.get(username).password.equals(password)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean addUser(String username, String password, String email, String nickname) {

        User newUser = new User(username, password, email, nickname);
        
        if (!this.users.containsKey(username)) {
            this.users.put(username, newUser);
            return true;
        }
        return false;
    }
    public HashMap<String, User> getUsers() {
        return users;
    }
}
