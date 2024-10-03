package com.honey.member_history_management.team.api;

import com.honey.member_history_management.team.domain.Team;
import com.honey.member_history_management.team.domain.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public Team findById(String id) {
        return teamRepository.findById(id)
                .orElse(null);
    }
}
