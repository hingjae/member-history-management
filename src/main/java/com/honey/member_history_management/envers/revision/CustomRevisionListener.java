package com.honey.member_history_management.envers.revision;

import com.honey.member_history_management.security.CustomUserDetails;
import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class CustomRevisionListener implements RevisionListener {
    @Override
    public void newRevision(Object o) {
        Revinfo revinfo = (Revinfo) o;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (Objects.nonNull(authentication) && authentication.getPrincipal() instanceof CustomUserDetails customUserDetails) {
            revinfo.setUpdatedBy(customUserDetails.getUsername());
        }
    }
}
