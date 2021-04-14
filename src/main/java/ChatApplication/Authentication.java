package ChatApplication;

import java.util.HashMap;

public class Authentication {

    private final HashMap<String, User> users;
    private String loggedUser;
    private static Authentication singleton = null;
    
    public Authentication() {
        this.users = new HashMap<>();
        this.loggedUser = "";
    }
    
    public static synchronized Authentication getInstance() {
        // Create a singleton to only create one instance at a time
        if (singleton == null) {
            singleton = new Authentication();
        }
        return singleton;
    }

    public boolean authenticateUser(String username, String password) {
        // Check if user exists and if password matches
        if (this.users.containsKey(username)) {
            if (this.users.get(username).password.equals(password)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean addUser(String username, String password, String email, String nickname) {
        // Add a new user to hashmap if one with the given name does not yet exist
        User newUser = new User(username, password, email, nickname);
        
        if (!this.users.containsKey(username)) {
            this.users.put(username, newUser);
            return true;
        }
        return false;
    }
    
    public void setLoggedUser(String name) {
        // Set user status to logged in
        this.loggedUser = name;
    }
    
    public String getLoggedUser() {
        // Get name of a user currently logged in
        return this.loggedUser;
    }
}
