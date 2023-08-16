package nextstep.auth.principal;

import java.util.Objects;

public class UserPrincipal {
    private static final UserPrincipal UNKNOWN_USER = new UserPrincipal(null, null);
    private String username;
    private String role;

    public UserPrincipal(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public static UserPrincipal createUnknownUser() {
        return UNKNOWN_USER;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(username, that.username) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, role);
    }

    public boolean isAuthenticated() {
        return this.username != null && this.role != null;
    }
}
