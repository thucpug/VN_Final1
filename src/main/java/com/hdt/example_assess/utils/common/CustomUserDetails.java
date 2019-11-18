package com.hdt.example_assess.utils.common;

import com.hdt.example_assess.entity.Role;
import com.hdt.example_assess.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Mặc định mình sẽ để tất cả là ROLE_USER. Để demo cho đơn giản.
        //return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
        final Set<GrantedAuthority> _grntdAuths = new HashSet<GrantedAuthority>();
        Set<Role> _roles = null;
        if (user != null) {
            _roles = user.getRoles();
        }
        if (_roles != null) {
            for (Role _role : _roles) {
                _grntdAuths.add(new SimpleGrantedAuthority(_role.getName()));
            }
        }
        return _grntdAuths;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }


    @Override
    public String getUsername() {
        return user.getUserName();
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
