package com.luizalebs.comunicacao_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.luizalebs.comunicacao_api.business.dto.ComunicacaoInDTO;
import com.luizalebs.comunicacao_api.business.dto.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.business.service.ComunicacaoService;
import com.luizalebs.comunicacao_api.infraestructure.enums.ModoEnvioEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Nested
@ExtendWith(MockitoExtension.class)
class ComunicacaoControllerTest {


    // Obs. Testes realizados na ComunicacaoController esperando um 200 ok com AgendarComunicado.
    // Obs2. Mais testes serão realizados a medidade que a aplicação for ganhando novos recursos.

    @Mock
    private ComunicacaoService comunicacaoService;

    @InjectMocks
    private ComunicacaoController comunicacaoController;


    @Test
    @DisplayName("POST /agendar - Deve agendar comunicado com sucesso (200 ok)")
    void agendarComunicado()  {

        ComunicacaoInDTO inDTO = new ComunicacaoInDTO();
        inDTO.setModoDeEnvio(ModoEnvioEnum.EMAIL);
        inDTO.setDataHoraEnvio(LocalDateTime.now().plusDays(1));

        ComunicacaoOutDTO outDTO = new ComunicacaoOutDTO();
        outDTO.setId(1L);

        when(comunicacaoService.agendarComunicacao(any(ComunicacaoInDTO.class))).thenReturn(outDTO);
        var response = comunicacaoController.agendarComunicado(inDTO);

        assertNotNull(response);
        verify(comunicacaoService, times(1)).agendarComunicacao(any(ComunicacaoInDTO.class));
    }

    @Test
    void buscarStatusComunicado() {
    }

    @Test
    void alteraStatusComunicado() {
    }

    @Test
    void deletaComunicacaoPorEmailDestinatario() {
    }
}