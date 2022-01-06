package org.delusion.afterline;

public class AfterlineClientContext {
    public enum State {
        PendingLoginDialogComplete,
        PendingUserAuthentication,
        UserLoggedIn,

    }

    private State state = State.PendingLoginDialogComplete;

    private String username = "$";

    public boolean isLoggedIn() {
        return username.equals("$");
    }

    public void beginUserAuth(String preauthUsername) {

        state = State.PendingUserAuthentication;
    }

    public void userLoggedIn(String username) {
        this.username = username;
        AfterlineClient.LOGGER.info("Logged in as {}", username);
        state = State.UserLoggedIn;
    }
}
