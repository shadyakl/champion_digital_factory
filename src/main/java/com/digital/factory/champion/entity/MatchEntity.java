package com.digital.factory.champion.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tennis_match")
@Data
@NoArgsConstructor
public class MatchEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "participant_1")
	private ParticipantEntity participant1;

	@ManyToOne
	@JoinColumn(name = "participant_2")
	private ParticipantEntity participant2;

	@ManyToOne
	@JoinColumn(name = "match_winner")
	private ParticipantEntity matchWinner;

	@Column(name = "participant_score_1")
	private Integer participantScore1;

	@Column(name = "participant_score_2")
	private Integer participantScore2;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "match_time")
	private Date matchTime;

	@ManyToOne
	@JoinColumn(name = "related_round")
	private RoundEntity round;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date")
	private Date creationDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modification_date")
	private Date modificationDate;

	public MatchEntity(ParticipantEntity participant1, ParticipantEntity participant2, Date matchTime,
			RoundEntity round) {
		super();
		this.participant1 = participant1;
		this.participant2 = participant2;
		this.matchTime = matchTime;
		this.round = round;
	}

}
