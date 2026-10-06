package com.luizalebs.comunicacao_api.business.converter.mapper;


import com.luizalebs.comunicacao_api.business.dto.ComunicacaoInDTO;
import com.luizalebs.comunicacao_api.business.dto.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.infraestructure.entities.ComunicacaoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "spring")
public interface ComunicacaoConverter {

    @Mapping(source = "id", target = "id")
    ComunicacaoEntity paraComunicacaoEntity(ComunicacaoInDTO comunicacaoInDTO);
    ComunicacaoOutDTO paraComunicacaoDTO(ComunicacaoEntity comunicacaoEntity);


}


//PR para ajuste do Java with Maven