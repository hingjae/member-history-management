package com.honey.member_history_management.security;

import com.honey.member_history_management.member.Member;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class CustomUserDetails implements UserDetails {
    private final String username;

    private final String password;

    private final String name;

    public static CustomUserDetails from(Member member) {
        return CustomUserDetails.builder()
                .username(member.getId())
                .password(member.getPassword())
                .name(member.getName())
                .build();
    }

    @Builder
    public CustomUserDetails(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
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
