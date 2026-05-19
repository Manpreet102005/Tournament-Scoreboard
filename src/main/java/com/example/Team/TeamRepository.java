package com.example.Team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team,Integer> {
    boolean existsByTeamName(String teamName);
}
