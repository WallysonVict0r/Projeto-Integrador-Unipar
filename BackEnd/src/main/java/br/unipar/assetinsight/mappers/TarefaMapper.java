package br.unipar.assetinsight.mappers;

import br.unipar.assetinsight.dtos.requests.TarefaRequest;
import br.unipar.assetinsight.dtos.responses.principal.TarefaResponse;
import br.unipar.assetinsight.entities.TarefaEntity;
import br.unipar.assetinsight.utils.DataUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(uses = {CategoriaMapper.class, AmbienteMapper.class, UsuarioMapper.class})
public interface TarefaMapper {
    TarefaMapper INSTANCE = Mappers.getMapper(TarefaMapper.class);

    @AfterMapping
    default void setDtRecord(TarefaRequest request, @MappingTarget TarefaEntity entity) {
        entity.setDtRecord(DataUtils.getNow());
    }

    @Mapping(source = "categoria", target = "categoriaEntity")
    @Mapping(source = "ambiente", target = "ambienteEntity", qualifiedByName = "mapLongToAmbienteEntity")
    @Mapping(source = "previsao", target = "dtPrevisao")
    TarefaEntity toEntity(TarefaRequest request);

    @Mapping(source = "categoria", target = "categoriaEntity")
    @Mapping(source = "ambiente", target = "ambienteEntity")
    @Mapping(source = "lastChangedBy", target = "usuarioEntityCriador")
    @Mapping(source = "lastChange", target = "dtRecord")
    @Mapping(source = "previsao", target = "dtPrevisao")
    TarefaEntity toEntity(TarefaResponse response);


    @Mapping(source = "categoriaEntity", target = "categoria")
    @Mapping(source = "ambienteEntity", target = "ambiente")
    @Mapping(source = "dtPrevisao", target = "previsao")
    TarefaRequest toRequest(TarefaEntity entity);

    TarefaRequest toRequest(TarefaResponse response);


    @Mapping(source = "categoriaEntity", target = "categoria")
    @Mapping(source = "ambienteEntity", target = "ambiente")
    @Mapping(source = "usuarioEntityCriador", target = "lastChangedBy")
    @Mapping(source = "dtRecord", target = "lastChange")
    @Mapping(source = "dtPrevisao", target = "previsao")
    TarefaResponse toResponse(TarefaEntity entity);

    @Mapping(source = "ambiente", target = "ambiente", qualifiedByName = "mapLongToAmbienteEntity")
    TarefaResponse toResponse(TarefaRequest request);


    TarefaEntity updateEntity(TarefaRequest request, @MappingTarget TarefaEntity entity);

    List<TarefaEntity> toEntityList(List<TarefaRequest> request);
    List<TarefaRequest> toRequestList(List<TarefaEntity> entity);
    List<TarefaResponse> toResponseList(List<TarefaEntity> entity);

    default Page<TarefaResponse> toResponsePage(Page<TarefaEntity> entityPage) {
        List<TarefaEntity> entities = entityPage.getContent();
        List<TarefaResponse> responses = toResponseList(entities);
        return new PageImpl<>(responses, entityPage.getPageable(), entityPage.getTotalElements());
    }



    default TarefaEntity mapLongToEntity(Long id) {
        if (id == null) {
            return null;
        }
        TarefaEntity entity = new TarefaEntity();
        entity.setId(id);
        return entity;
    }

    default Long mapEntityToLong(TarefaEntity entity) {
        if (entity == null) {
            return null;
        }
        return entity.getId();
    }
}