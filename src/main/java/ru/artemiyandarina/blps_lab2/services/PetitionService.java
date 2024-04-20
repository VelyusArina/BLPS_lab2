package ru.artemiyandarina.blps_lab2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.artemiyandarina.blps_lab2.models.Petition;
import ru.artemiyandarina.blps_lab2.repositories.PetitionRepository;
import ru.artemiyandarina.blps_lab2.schemas.petition.PetitionCreate;
import ru.artemiyandarina.blps_lab2.schemas.petition.PetitionRead;
import ru.artemiyandarina.blps_lab2.services.mapping.PetitionMapper;
import ru.artemiyandarina.blps_lab2.exceptions.NotFoundException;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetitionService {
    final PetitionRepository petitionRepository;
    final PetitionMapper petitionMapper;
    final SecurityService securityService;

    public Set<PetitionRead> getAll() {
        return petitionRepository.findAll().stream()
                .map(petitionMapper::mapEntityToPetitionRead)
                .collect(Collectors.toSet());
    }

    @Transactional
    public PetitionRead create(PetitionCreate schema) {
        Petition newPetition = petitionMapper.mapPetitionCreateToEntity(schema);
        newPetition.setOwner(securityService.getCurrentUser());
        petitionRepository.save(newPetition);
        return petitionMapper.mapEntityToPetitionRead(newPetition);
    }


    public PetitionRead getById(Long id) {
        Petition petition = petitionRepository.findById(id).orElseThrow(NotFoundException::new);
        return petitionMapper.mapEntityToPetitionRead(petition);
    }

    public void delete(Long documentId) {
        Petition petition = petitionRepository.findById(documentId).orElseThrow(NotFoundException::new);
        securityService.userRequired(petition.getOwner());
        petitionRepository.delete(petition);
    }
}
