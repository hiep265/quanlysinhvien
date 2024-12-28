package com.example.quanlysinhvienhaui.security.user;


import com.example.quanlysinhvienhaui.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDetail implements UserDetails {
 private int id;
 private String username;
private String password;
private Collection<GrantedAuthority> authorities;

    public static UserDetail buildUserDetail(User user){
      List<GrantedAuthority> authorities = user.getRoles()
              .stream()
              .map(role->new SimpleGrantedAuthority(role.getName()))
              .collect(Collectors.toList());
      return new UserDetail(
              user.getUserId(),
              user.getUsername(),
              user.getPassword(),
              authorities);

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
