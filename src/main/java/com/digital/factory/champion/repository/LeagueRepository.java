package com.digital.factory.champion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digital.factory.champion.entity.LeagueEntity;

public interface LeagueRepository extends JpaRepository<LeagueEntity, Long> {

}
