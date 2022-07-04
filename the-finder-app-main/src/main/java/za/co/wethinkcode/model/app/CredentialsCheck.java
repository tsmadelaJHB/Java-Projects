package za.co.wethinkcode.model.app;

public class CredentialsCheck {
    private boolean user;
    private String password;

    public void setUser(boolean user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
