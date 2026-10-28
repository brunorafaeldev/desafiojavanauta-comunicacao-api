package com.luizalebs.comunicacao_api.infraestructure.repositories;

import com.luizalebs.comunicacao_api.infraestructure.entities.ComunicacaoEntity;
import com.luizalebs.comunicacao_api.infraestructure.enums.StatusEnvioEnum;
import feign.Param;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ComunicacaoRepository extends CrudRepository<ComunicacaoEntity, Long> {

    ComunicacaoEntity findByEmailDestinatario(String nomeDestinatario);

    @Transactional
    void deleteByEmailDestinatario(String emailDestinatario);

    @Transactional
    @Modifying
    @Query("UPDATE ComunicacaoEntity c SET c.statusEnvio = :statusEnvio WHERE c.id = :id")
    void atualizarStatus(@Param("id") Long id, @Param("statusEnvio") StatusEnvioEnum statusEnvio);

    List<ComunicacaoEntity>  findByStatusEnvioAndDataHoraEnvioLessThanEqual (StatusEnvioEnum statusEnvio, LocalDateTime dataHoraEnvio);

}
