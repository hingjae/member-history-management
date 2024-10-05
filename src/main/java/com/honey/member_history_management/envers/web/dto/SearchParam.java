package com.honey.member_history_management.envers.web.dto;

import lombok.Getter;

@Getter
public class SearchParam {
    private final String keyword;
    private final SearchType searchType;  // Enum 타입으로 변경

    public SearchParam(String keyword, SearchType searchType) {
        this.keyword = keyword;
        this.searchType = searchType;
    }
}
