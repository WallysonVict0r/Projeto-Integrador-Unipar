package br.unipar.assetinsight.mappers;

import br.unipar.assetinsight.dtos.requests.CadastroRequest;
import br.unipar.assetinsight.dtos.requests.LoginRequest;
import br.unipar.assetinsight.dtos.responses.principal.CadastroResponse;
import br.unipar.assetinsight.dtos.responses.principal.LoginResponse;
import br.unipar.assetinsight.entities.UsuarioEntity;
import br.unipar.assetinsight.utils.DataUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.sql.Timestamp;
import java.time.Instant;

@Mapper(uses = RoleMapper.class)
public interface AuthenticationMapper {
    AuthenticationMapper INSTANCE = Mappers.getMapper(AuthenticationMapper.class);

    @AfterMapping
    default void setDtRecord(CadastroRequest cadastroRequest, @MappingTarget UsuarioEntity usuarioEntity) {
        usuarioEntity.setDtRecord(DataUtils.getNow());
    }

    UsuarioEntity toUsuarioEntity(CadastroRequest cadastroRequest);

    UsuarioEntity toUsuarioEntity(LoginRequest loginRequest);

    @Mapping(source = "permissoes", target = "listRoles")
    @Mapping(source = "createdAt", target = "dtRecord")
    UsuarioEntity toUsuarioEntity(CadastroResponse cadastroResponse);


    @Mapping(source = "permissoes", target = "listRoles")
    @Mapping(source = "createdAt", target = "dtRecord")
    UsuarioEntity toUsuarioEntity(LoginResponse loginResponse);



    @Mapping(source = "listRoles", target = "permissoes")
    @Mapping(source = "dtRecord", target = "createdAt")
    LoginResponse toLoginResponse(UsuarioEntity usuarioEntity);

    LoginResponse toLoginResponse(CadastroRequest cadastroRequest);

    LoginResponse toLoginResponse(CadastroResponse cadastroResponse);

    LoginResponse toLoginResponse(LoginRequest loginRequest);



    @Mapping(source = "listRoles", target = "permissoes")
    @Mapping(source = "dtRecord", target = "createdAt")
    CadastroResponse toCadastroResponse(UsuarioEntity usuarioEntity);

    CadastroResponse toCadastroResponse(LoginRequest loginRequest);

    CadastroResponse toCadastroResponse(LoginResponse loginResponse);

    CadastroResponse toCadastroResponse(CadastroRequest cadastroRequest);


    default Instant map(Timestamp value){
        return value.toInstant();
    };

    default Timestamp map(Instant value){
        return Timestamp.from(value);
    };
}
