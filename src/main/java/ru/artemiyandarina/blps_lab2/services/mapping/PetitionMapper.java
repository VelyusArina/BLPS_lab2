package ru.artemiyandarina.blps_lab2.services.mapping;

import ru.artemiyandarina.blps_lab2.models.Petition;
import ru.artemiyandarina.blps_lab2.schemas.petition.PetitionBase;
import ru.artemiyandarina.blps_lab2.schemas.petition.PetitionCreate;
import ru.artemiyandarina.blps_lab2.schemas.petition.PetitionRead;

public class PetitionMapper {
    private final UserMapper userMapper;

    public PetitionMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    protected Petition mapPetitionBaseToEntity(PetitionBase schema, Petition entity) {
        entity.setTitle(schema.getTitle());
        entity.setDescription(schema.getDescription());
        return entity;
    }

    protected Petition mapPetitionBaseToEntity(PetitionBase schema) {
        return mapPetitionBaseToEntity(schema, new Petition());
    }

    public Petition mapPetitionCreateToEntity(PetitionCreate schema) {
        return mapPetitionBaseToEntity(schema);
    }

    public PetitionRead mapEntityToPetitionRead(Petition entity) {
        PetitionRead schema = new PetitionRead();
        schema.setId(entity.getId());
        schema.setOwner(
                userMapper.mapEntityToUserRead(entity.getOwner())
        );
        schema.setTitle(entity.getTitle());
        schema.setDescription(entity.getDescription());
        schema.setCreationDate(entity.getCreationDate());
        return schema;
    }
}
