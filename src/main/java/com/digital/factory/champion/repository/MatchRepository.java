package com.digital.factory.champion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.digital.factory.champion.entity.MatchEntity;
import com.digital.factory.champion.util.IAvaiableMatchTime;

public interface MatchRepository extends JpaRepository<MatchEntity, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM tennis_match tm WHERE tm.related_round=(SELECT id FROM round r  WHERE r.related_league=?1  ORDER BY id ASC LIMIT 1 OFFSET ?2)")
	List<MatchEntity> getListOfMatchesWithRoundNumber(Long leagueId, Integer roundNo);

	@Query(nativeQuery = true, value = "SELECT COUNT(*) as dailyCount,match_time as lastMatchTime FROM tennis_match WHERE  DATE(match_time) = (SELECT DATE(tmSub.match_time) FROM tennis_match tmSub ORDER BY tmSub.match_time DESC LIMIT 1)")
	IAvaiableMatchTime getCountOfMatchesPerDate();

}
