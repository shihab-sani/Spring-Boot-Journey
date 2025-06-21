package testproject.store.testing;

public class User {
    private String name;
    private String email;
    private int id;
    private String password;

    public User(String name, String email, int id, String password) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
