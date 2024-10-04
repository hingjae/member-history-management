package com.honey.member_history_management.envers;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Table(name = "revinfo")
@Entity
@RevisionEntity(CustomRevisionListener.class)
public class Revinfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RevisionNumber
    private Long rev;

    @RevisionTimestamp
    private Long revtstmp;

    @Setter
    private String updatedBy;

    public LocalDateTime getUpdatedAt() {
        return Instant.ofEpochMilli(revtstmp)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
