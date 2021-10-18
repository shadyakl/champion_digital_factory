package com.digital.factory.champion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digital.factory.champion.entity.RoundEntity;

public interface RoundRepository extends JpaRepository<RoundEntity, Long> {

	List<RoundEntity> findByLeagueId(Long leagueId);

}
