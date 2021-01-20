package eu.ecct.synthesysterminal.security.model;

import eu.ecct.synthesysterminal.entity.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

public class AccountDetails implements UserDetails {

    private final Account account;

    public AccountDetails(Account account) {
        this.account = account;
    }

    public UUID getUserId() {
        return account.getId();
    }

    public String getAvatarUrl() {
        return account.getAvatarUrl();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
