package com.honey.member_history_management.security;

import com.honey.member_history_management.member.Member;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Getter
public class CustomUserDetails implements UserDetails {
    private final String username;

    private final String password;

    private final String name;

    private final Set<GrantedAuthority> authorities;

    public static CustomUserDetails from(Member member) {
        return CustomUserDetails.builder()
                .username(member.getId())
                .password(member.getPassword())
                .name(member.getName())
                .authorities(Set.of(new SimpleGrantedAuthority(member.getRoleName())))
                .build();
    }

    @Builder
    public CustomUserDetails(String username, String password, String name, Set<GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.authorities = authorities;
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
}
