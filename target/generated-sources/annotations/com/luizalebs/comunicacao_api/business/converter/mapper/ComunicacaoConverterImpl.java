package com.luizalebs.comunicacao_api.business.converter.mapper;

import com.luizalebs.comunicacao_api.business.dto.ComunicacaoInDTO;
import com.luizalebs.comunicacao_api.business.dto.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.infraestructure.entities.ComunicacaoEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-06T16:18:50-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class ComunicacaoConverterImpl implements ComunicacaoConverter {

    @Override
    public ComunicacaoEntity paraComunicacaoEntity(ComunicacaoInDTO comunicacaoInDTO) {
        if ( comunicacaoInDTO == null ) {
            return null;
        }

        ComunicacaoEntity.ComunicacaoEntityBuilder comunicacaoEntity = ComunicacaoEntity.builder();

        comunicacaoEntity.id( comunicacaoInDTO.getId() );
        comunicacaoEntity.dataHoraEnvio( comunicacaoInDTO.getDataHoraEnvio() );
        comunicacaoEntity.nomeDestinatario( comunicacaoInDTO.getNomeDestinatario() );
        comunicacaoEntity.emailDestinatario( comunicacaoInDTO.getEmailDestinatario() );
        comunicacaoEntity.telefoneDestinatario( comunicacaoInDTO.getTelefoneDestinatario() );
        comunicacaoEntity.mensagem( comunicacaoInDTO.getMensagem() );
        comunicacaoEntity.modoDeEnvio( comunicacaoInDTO.getModoDeEnvio() );
        comunicacaoEntity.statusEnvio( comunicacaoInDTO.getStatusEnvio() );

        return comunicacaoEntity.build();
    }

    @Override
    public ComunicacaoOutDTO paraComunicacaoDTO(ComunicacaoEntity comunicacaoEntity) {
        if ( comunicacaoEntity == null ) {
            return null;
        }

        ComunicacaoOutDTO.ComunicacaoOutDTOBuilder comunicacaoOutDTO = ComunicacaoOutDTO.builder();

        comunicacaoOutDTO.dataHoraEnvio( comunicacaoEntity.getDataHoraEnvio() );
        comunicacaoOutDTO.nomeDestinatario( comunicacaoEntity.getNomeDestinatario() );
        comunicacaoOutDTO.emailDestinatario( comunicacaoEntity.getEmailDestinatario() );
        comunicacaoOutDTO.telefoneDestinatario( comunicacaoEntity.getTelefoneDestinatario() );
        comunicacaoOutDTO.mensagem( comunicacaoEntity.getMensagem() );
        comunicacaoOutDTO.modoDeEnvio( comunicacaoEntity.getModoDeEnvio() );
        comunicacaoOutDTO.statusEnvio( comunicacaoEntity.getStatusEnvio() );

        return comunicacaoOutDTO.build();
    }
}
