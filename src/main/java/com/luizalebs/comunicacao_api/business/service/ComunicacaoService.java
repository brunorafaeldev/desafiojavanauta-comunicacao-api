package com.luizalebs.comunicacao_api.business.service;

import com.luizalebs.comunicacao_api.business.converter.mapper.ComunicacaoConverter;
import com.luizalebs.comunicacao_api.business.dto.ComunicacaoInDTO;
import com.luizalebs.comunicacao_api.business.dto.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.infraestructure.client.NotificacaoEmailCliente;
import com.luizalebs.comunicacao_api.infraestructure.entities.ComunicacaoEntity;
import com.luizalebs.comunicacao_api.infraestructure.enums.ModoEnvioEnum;
import com.luizalebs.comunicacao_api.infraestructure.enums.StatusEnvioEnum;
import com.luizalebs.comunicacao_api.infraestructure.exceptions.ConflictException;
import com.luizalebs.comunicacao_api.infraestructure.exceptions.IllegalArgumentException;
import com.luizalebs.comunicacao_api.infraestructure.exceptions.ResourceNotFoundException;
import com.luizalebs.comunicacao_api.infraestructure.exceptions.UnsupportedOperationException;
import com.luizalebs.comunicacao_api.infraestructure.repositories.ComunicacaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
@Slf4j
public class ComunicacaoService {

    private final ComunicacaoRepository repository;
    private final ComunicacaoConverter converter;
    private final NotificacaoEmailCliente notificacaoEmailCliente;


    public ComunicacaoOutDTO agendarComunicacao(ComunicacaoInDTO dto) {

        if (Objects.isNull(dto)) {
            throw new IllegalArgumentException("O argumento fornecido é inválido: comunicado não pode ser nulo!");
        }

        validarModoEnvio(dto.getModoDeEnvio());
        dto.setStatusEnvio(StatusEnvioEnum.PENDENTE);
        ComunicacaoEntity entity = converter.paraComunicacaoEntity(dto);
        repository.save(entity);
        ComunicacaoOutDTO outDTO = converter.paraComunicacaoDTO(entity);
        return outDTO;
    }

    //Obs. O Método abaixo restringe o uso de outros modosDeEnvioEnum enquanto não forem implementados

    private void validarModoEnvio(ModoEnvioEnum modoEnvioEnum) {
        if (modoEnvioEnum == null) {
            throw new IllegalArgumentException("O modo de envio não pode ser nulo.");
        }

        switch (modoEnvioEnum) {
            case SMS, PUSH, WHATSAPP -> throw new UnsupportedOperationException(
                    "O modo de envio '" + modoEnvioEnum + "' ainda não é suportado pelo sistema."
            );


            default -> throw new IllegalArgumentException("Modo de envio inválido: " + modoEnvioEnum);
        }
    }

    public List<ComunicacaoOutDTO> buscaComunicacaoPendentes(LocalDateTime dataLimite) {

        List<ComunicacaoEntity> entities = repository.findByStatusEnvioAndDataHoraEnvioLessThanEqual(
                StatusEnvioEnum.PENDENTE, dataLimite);

        return converter.paraListaComunicacaoOutDTO(entities);


    }

    @Transactional
    public void enviaComunicadoEAtualizaStatus(ComunicacaoOutDTO comunicacaoOutDTO) {

        try {

            ModoEnvioEnum modoEnvioEnum = comunicacaoOutDTO.getModoDeEnvio();
            processaEnvioPorModo(comunicacaoOutDTO, modoEnvioEnum);
            repository.atualizarStatus(comunicacaoOutDTO.getId(), StatusEnvioEnum.ENVIADO);


        } catch (UnsupportedOperationException e) {
            log.warn("Canal não suportado para o ID {}: {}", comunicacaoOutDTO.getId(), e.getMessage());

        } catch (Exception e) {

            repository.atualizarStatus(comunicacaoOutDTO.getId(), StatusEnvioEnum.ERRO);
            log.error("Erro ao enviar notificação para ID {}: {}", comunicacaoOutDTO.getId(), e.getMessage());

            throw new RuntimeException("Falha no processo de envio da comunicação", e);

        }
    }


    private void processaEnvioPorModo(ComunicacaoOutDTO comunicacaoOutDTO, ModoEnvioEnum modoEnvioEnum) {


        switch (modoEnvioEnum) {
            case EMAIL:
                notificacaoEmailCliente.enviarEmail(comunicacaoOutDTO);
                break;
            case SMS:
            case PUSH:
            case WHATSAPP:



                    log.warn("Modo de envio {} ainda não suportado para o ID {}", modoEnvioEnum, comunicacaoOutDTO.getId());
                    throw new UnsupportedOperationException("Modo de envio não suportado: " + modoEnvioEnum);




            default:
                throw new IllegalArgumentException("Modo de envio inválido" + modoEnvioEnum);
        }


    }


    public ComunicacaoOutDTO buscarStatusComunicacao(String emailDestinatario) {
        ComunicacaoEntity entity = repository.findByEmailDestinatario(emailDestinatario);
        if (Objects.isNull(entity)) {
            throw new ResourceNotFoundException("O comunicado não existe ou foi deletado do sistema");
        }
        return converter.paraComunicacaoDTO(entity);

    }


    public ComunicacaoOutDTO alterarStatusComunicado(String emailDestinatario) {
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
