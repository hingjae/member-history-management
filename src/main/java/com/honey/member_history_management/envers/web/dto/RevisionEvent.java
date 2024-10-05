package com.honey.member_history_management.envers.web.dto;

import lombok.Getter;

public enum RevisionEvent {
    ADD("add", "생성", "badge-primary"),
    DEL("del", "삭제", "badge-danger"),
    TEAM_MOD("team", "팀 변경", "badge-info"),
    PW_MOD("password", "비밀번호 변경", "badge-info"),
    NAME_MOD("name", "이름 변경", "badge-info"),
    AGE_MOD("age", "나이 변경", "badge-info"),
    PHONE_MOD("phoneNumber", "휴대폰 번호 변경", "badge-info"),
    ROLE_MOD("role", "권한 변경", "badge-info");

    @Getter
    private final String field;

    @Getter
    private final String event;

    @Getter
    private final String badge;

    RevisionEvent(String field, String event, String badge) {
        this.field = field;
        this.event = event;
        this.badge = badge;
    }
}
