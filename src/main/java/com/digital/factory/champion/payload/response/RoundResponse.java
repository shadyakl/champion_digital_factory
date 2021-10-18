package com.digital.factory.champion.payload.response;

import com.digital.factory.champion.enums.RoundStatusEnum;

import lombok.Data;

@Data
public class RoundResponse {
	private Long id;
	private RoundStatusEnum roundStatus;

}
