package com.digital.factory.champion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.digital.factory.champion.entity.ParticipantEntity;

public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {

	@Query(nativeQuery = true, value = "SELECT *  FROM participant ORDER BY RAND() LIMIT ?1")
	List<ParticipantEntity> getRandomParticipants(int countOfPartPerGroup);

	@Query(nativeQuery = true, value = "SELECT *  FROM participant WHERE participant.id NOT IN (?1) ORDER BY RAND() LIMIT ?2")
	List<ParticipantEntity> getRandomParticipants(List<Long> selectedParticipants, int countOfPartPerGroup);

}
