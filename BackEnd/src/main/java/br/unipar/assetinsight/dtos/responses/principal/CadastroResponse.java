package br.unipar.assetinsight.dtos.responses.principal;

import br.unipar.assetinsight.enums.PermissoesEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.List;

/**
 * DTO de {@link br.unipar.assetinsight.entities.UsuarioEntity}
 */
public record CadastroResponse(
        @Schema(description = "Id do cadastro.", example = "1")
        String username,

        @Schema(description = "Nome do usuário.", example = "Usuário Exemplo")
        Instant createdAt,

        @Schema(description = "Permissões do usuário.")
        List<PermissoesEnum> permissoes
) { }
