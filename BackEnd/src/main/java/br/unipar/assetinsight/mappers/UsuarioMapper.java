package br.unipar.assetinsight.mappers;

import br.unipar.assetinsight.dtos.responses.principal.UsuarioResponse;
import br.unipar.assetinsight.entities.UsuarioEntity;
import br.unipar.assetinsight.utils.DataUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(uses = RoleMapper.class)
public interface UsuarioMapper {
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    @AfterMapping
    default void setDtRecord(UsuarioResponse request, @MappingTarget UsuarioEntity entity) {
        entity.setDtRecord(DataUtils.getNow());
    }

    @Mapping(target = "listRoles", source = "permissoes")
    @Mapping(target = "dtRecord", source = "dtCriacao")
    @Mapping(target = "dtLogin", source = "lastLogin")
    UsuarioEntity toEntity(UsuarioResponse request);

    @Mapping(target = "permissoes", source = "listRoles")
    @Mapping(target = "dtCriacao", source = "dtRecord")
    @Mapping(target = "lastLogin", source = "dtLogin")
    UsuarioResponse toResponse(UsuarioEntity entity);


    UsuarioEntity updateEntity(UsuarioResponse request, @MappingTarget UsuarioEntity entity);

    List<UsuarioEntity> toEntityList(List<UsuarioResponse> request);
    List<UsuarioResponse> toResponseList(List<UsuarioEntity> entity);

    default Page<UsuarioResponse> toResponsePage(Page<UsuarioEntity> entityPage) {
        List<UsuarioEntity> entities = entityPage.getContent();
        List<UsuarioResponse> responses = toResponseList(entities);
        return new PageImpl<>(responses, entityPage.getPageable(), entityPage.getTotalElements());
    }
}
