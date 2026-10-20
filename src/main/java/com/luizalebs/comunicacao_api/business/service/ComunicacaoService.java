package com.luizalebs.comunicacao_api.business.service;

import com.luizalebs.comunicacao_api.business.converter.mapper.ComunicacaoConverter;
import com.luizalebs.comunicacao_api.business.dto.ComunicacaoInDTO;
import com.luizalebs.comunicacao_api.business.dto.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.infraestructure.client.NotificacaoEmailCliente;
import com.luizalebs.comunicacao_api.infraestructure.entities.ComunicacaoEntity;
import com.luizalebs.comunicacao_api.infraestructure.enums.StatusEnvioEnum;
import com.luizalebs.comunicacao_api.infraestructure.exceptions.ConflictException;
import com.luizalebs.comunicacao_api.infraestructure.exceptions.IllegalArgumentException;
import com.luizalebs.comunicacao_api.infraestructure.exceptions.ResourceNotFoundException;
import com.luizalebs.comunicacao_api.infraestructure.repositories.ComunicacaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ComunicacaoService {

    private final ComunicacaoRepository repository;
    private final ComunicacaoConverter converter;
    private final NotificacaoEmailCliente notificacaoEmailCliente;


    public ComunicacaoOutDTO agendarComunicacao(ComunicacaoInDTO dto) {

        if (Objects.isNull(dto)) {
            throw new IllegalArgumentException("O argumento fornecido é inválido: comunicado não pode ser nulo!");
        }
        dto.setStatusEnvio(StatusEnvioEnum.PENDENTE);
        ComunicacaoEntity entity = converter.paraComunicacaoEntity(dto);
        repository.save(entity);
        ComunicacaoOutDTO outDTO = converter.paraComunicacaoDTO(entity);
        return outDTO;
    }

    public List<ComunicacaoOutDTO> buscaComunicacaoPendentes(LocalDateTime dataLimite) {

        List<ComunicacaoEntity> entities = repository.findByStatusEnvioAndDataHoraEnvioLessThanEqual(
                StatusEnvioEnum.PENDENTE, dataLimite);

        return converter.paraListaComunicacaoOutDTO(entities);


    }

    @Transactional
    public void enviaComunicadoEAtualizaStatus(ComunicacaoOutDTO comunicacaoOutDTO) {
        try {

            notificacaoEmailCliente.enviarEmail(comunicacaoOutDTO);
            repository.atualizarStatus(comunicacaoOutDTO.getId(), StatusEnvioEnum.ENVIADO);

        } catch (IllegalArgumentException e) {

            repository.atualizarStatus(comunicacaoOutDTO.getId(), StatusEnvioEnum.ERRO);
            throw new IllegalArgumentException("Erro ao enviar e-mail para o id: " + comunicacaoOutDTO.getId());


        }
    }


    public ComunicacaoOutDTO buscarStatusComunicacao(String emailDestinatario) {
        ComunicacaoEntity entity = repository.findByEmailDestinatario(emailDestinatario);
        if (Objects.isNull(entity)) {
            throw new ResourceNotFoundException("O Status não pode ser alterado ou comunicado não existe");
        }
        return converter.paraComunicacaoDTO(entity);
    }

    public ComunicacaoOutDTO alterarStatusComunicacao(String emailDestinatario) {
        ComunicacaoEntity entity = repository.findByEmailDestinatario(emailDestinatario);
        if (Objects.isNull(entity)) {
            throw new ResourceNotFoundException("O Comunicado não existe ou não pode ser alterado!");
        }
        entity.setStatusEnvio(StatusEnvioEnum.CANCELADO);
        repository.save(entity);
        return (converter.paraComunicacaoDTO(entity));
    }

    public void deletaComunicadoPorEmailDestinatario(String emailDestinatario) {
        if (Objects.isNull(emailDestinatario)) {
            throw new ConflictException("O comunicado não existe ou já foi excluido do sistema!");
        }


        repository.deleteByEmailDestinatario(emailDestinatario);

    }


}
