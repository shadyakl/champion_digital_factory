package com.digital.factory.champion.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import com.digital.factory.champion.enums.RoundStatusEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "round")
@Data
@NoArgsConstructor
public class RoundEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "round_status")
	@Enumerated(EnumType.STRING)
	private RoundStatusEnum roundStatus;

	@ManyToOne
	@JoinColumn(name = "related_league")
	private LeagueEntity league;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date")
	private Date creationDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modification_date")
	private Date modificationDate;

	public RoundEntity(RoundStatusEnum roundStatus, LeagueEntity league) {
		super();
		this.roundStatus = roundStatus;
		this.league = league;
	}

}
