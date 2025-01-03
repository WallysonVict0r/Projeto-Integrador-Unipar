package br.unipar.assetinsight.repositories;

import br.unipar.assetinsight.entities.TarefaEntity;
import br.unipar.assetinsight.enums.StatusTarefaEnum;
import br.unipar.assetinsight.repositories.custom.interfaces.ICustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface TarefaRepository extends JpaRepository<TarefaEntity, Long>, ICustomRepository<TarefaEntity> {
    Optional<List<TarefaEntity>> findAllByAmbienteEntity_Id(long id);

    Optional<List<TarefaEntity>> findAllByCategoriaEntity_Id(long id);

    Page<TarefaEntity> findAll(Pageable pageable);

    Optional<List<TarefaEntity>> findAllByDtPrevisaoBefore(Timestamp dataHora);

    long countByCategoriaEntity_Id(long id);

    @Query("SELECT COUNT(t) FROM TarefaEntity t WHERE t.status = :status")
    long countTarefasByStatus(@Param("status") StatusTarefaEnum status);
}