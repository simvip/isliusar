package my.jpa.models;

import my.jpa.controllers.ItemServlet;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Ivan Sliusar on 31.08.2018.
 * Red Line Soft corp.
 */
public class UserPrincipal implements UserDetails{
    private static final Logger LOGGER = Logger.getLogger(ItemServlet.class);
    private User user;
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    public UserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        authorities.add(new SimpleGrantedAuthority("User"));
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
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

    public static User getSignedUpUser() {
        final SecurityContext ctx = SecurityContextHolder.getContext();

        if (ctx == null) {
            LOGGER.debug("No security context available");
            return null;
        }

        final Authentication auth = ctx.getAuthentication();
        if (auth == null) {
            LOGGER.debug("No authentication available in security context {}", (Throwable) ctx);
            return null;
        }

        final Object principal = auth.getPrincipal();
        if (!(principal instanceof UserPrincipal)){
            LOGGER.warn("Principal {} is not an instance of AUser!", (Throwable) principal);
            return null;
        }

        final UserPrincipal au = (UserPrincipal)principal;
        return au.user;
    }
}
