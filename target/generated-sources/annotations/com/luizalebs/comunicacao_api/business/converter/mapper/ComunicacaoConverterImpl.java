package com.luizalebs.comunicacao_api.business.converter.mapper;

import com.luizalebs.comunicacao_api.business.dto.ComunicacaoInDTO;
import com.luizalebs.comunicacao_api.business.dto.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.infraestructure.entities.ComunicacaoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-20T11:51:26-0300",
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

        comunicacaoOutDTO.id( comunicacaoEntity.getId() );
        comunicacaoOutDTO.dataHoraEnvio( comunicacaoEntity.getDataHoraEnvio() );
        comunicacaoOutDTO.nomeDestinatario( comunicacaoEntity.getNomeDestinatario() );
        comunicacaoOutDTO.emailDestinatario( comunicacaoEntity.getEmailDestinatario() );
        comunicacaoOutDTO.telefoneDestinatario( comunicacaoEntity.getTelefoneDestinatario() );
        comunicacaoOutDTO.mensagem( comunicacaoEntity.getMensagem() );
        comunicacaoOutDTO.modoDeEnvio( comunicacaoEntity.getModoDeEnvio() );
        comunicacaoOutDTO.statusEnvio( comunicacaoEntity.getStatusEnvio() );

        return comunicacaoOutDTO.build();
    }

    @Override
    public List<ComunicacaoOutDTO> paraListaComunicacaoOutDTO(List<ComunicacaoEntity> comunicacaoEntity) {
        if ( comunicacaoEntity == null ) {
            return null;
        }

        List<ComunicacaoOutDTO> list = new ArrayList<ComunicacaoOutDTO>( comunicacaoEntity.size() );
        for ( ComunicacaoEntity comunicacaoEntity1 : comunicacaoEntity ) {
            list.add( paraComunicacaoDTO( comunicacaoEntity1 ) );
        }

        return list;
    }
}
