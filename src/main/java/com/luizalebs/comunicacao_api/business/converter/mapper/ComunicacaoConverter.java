package com.luizalebs.comunicacao_api.business.converter.mapper;


import com.luizalebs.comunicacao_api.business.dto.ComunicacaoInDTO;
import com.luizalebs.comunicacao_api.business.dto.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.infraestructure.entities.ComunicacaoEntity;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper (componentModel = "spring")
public interface ComunicacaoConverter {


    ComunicacaoEntity paraComunicacaoEntity(ComunicacaoInDTO comunicacaoInDTO);
    ComunicacaoOutDTO paraComunicacaoDTO(ComunicacaoEntity comunicacaoEntity);

    List<ComunicacaoOutDTO> paraListaComunicacaoOutDTO(List<ComunicacaoEntity> comunicacaoEntity);


}


