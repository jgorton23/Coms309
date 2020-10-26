package iastate.cs309.myexpenses.login;


public class User {
    public final String id;
    public final String username;
    public final String password;

    public User(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return username;
    }
}
