package com.honey.member_history_management.envers.revision;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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

    @Column
    @RevisionTimestamp
    private Long revtstmp;

    @Column
    @Setter
    private String updatedBy;

    public LocalDateTime getUpdatedAt() {
        return Instant.ofEpochMilli(revtstmp)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
