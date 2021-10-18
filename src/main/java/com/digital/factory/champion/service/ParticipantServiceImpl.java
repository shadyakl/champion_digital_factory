package com.digital.factory.champion.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.factory.champion.entity.ParticipantEntity;
import com.digital.factory.champion.enums.ErrorEnum;
import com.digital.factory.champion.exceptions.InvalidOperationException;
import com.digital.factory.champion.payload.request.ParticipantRequest;
import com.digital.factory.champion.payload.response.ParticipantResponse;
import com.digital.factory.champion.repository.ParticipantRepository;
import com.digital.factory.champion.util.ChampionModelMapper;

@Service
public class ParticipantServiceImpl {

	@Autowired
	private ParticipantRepository participantRepo;

	@Autowired
	private ChampionModelMapper mapper;

	public List<ParticipantResponse> getAllParticipants() {
		return participantRepo.findAll().stream().map(participant -> mapper.map(participant, ParticipantResponse.class))
				.collect(Collectors.toList());
	}

	public ParticipantResponse getParticipantById(Long id) throws InvalidOperationException {

		ParticipantEntity participantEntity = participantRepo.findById(id)
				.orElseThrow(() -> new InvalidOperationException(ErrorEnum.NOT_EXISTING));

		return mapper.map(participantEntity, ParticipantResponse.class);
	}

	public ParticipantResponse createParticipant(ParticipantRequest participantRequest) {
		try {
			ParticipantEntity participantEntity = mapper.map(participantRequest, ParticipantEntity.class);

			return mapper.map(participantRepo.save(participantEntity), ParticipantResponse.class);
		} catch (Exception e) {
			throw new InvalidOperationException(ErrorEnum.NOT_CREATED);
		}

	}

	public Map<String, List<ParticipantResponse>> randomParticipantGrouping(int noOfGroups) {
		Long totalRecords = participantRepo.count();

		Long countOfPartPerGroup = (totalRecords % noOfGroups == 0) ? (totalRecords / noOfGroups)
				: ((totalRecords / noOfGroups) + 1);

		Map<String, List<ParticipantResponse>> groups = new HashMap<String, List<ParticipantResponse>>();

		Set<Long> selectedParticipants = new HashSet<Long>();

		for (int i = 1; i <= noOfGroups; i++) {
			List<ParticipantEntity> participantEntities = null;
			if (selectedParticipants.size() > 0) {
				participantEntities = participantRepo.getRandomParticipants(List.copyOf(selectedParticipants),
						Math.toIntExact(countOfPartPerGroup));

			} else {
				participantEntities = participantRepo.getRandomParticipants(Math.toIntExact(countOfPartPerGroup));
			}
			groups.put("Group " + i,
					participantEntities.stream()
							.map(participantEntity -> mapper.map(participantEntity, ParticipantResponse.class))
							.collect(Collectors.toList()));
			participantEntities.forEach(participantEntity -> selectedParticipants.add(participantEntity.getId()));
		}

		return groups;

	}

}
