package com.luizalebs.comunicacao_api.business.service;

import com.luizalebs.comunicacao_api.business.converter.mapper.ComunicacaoConverter;
import com.luizalebs.comunicacao_api.infraestructure.client.NotificacaoEmailCliente;
import com.luizalebs.comunicacao_api.infraestructure.exceptions.ConflictException;
import com.luizalebs.comunicacao_api.infraestructure.exceptions.IllegalArgumentException;
import com.luizalebs.comunicacao_api.infraestructure.repositories.ComunicacaoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@Nested
@ExtendWith(MockitoExtension.class)
class ComunicacaoServiceTest {

    // Obs. Testes realizados na ComunicacaoService com Agendar e Deletar comunicado.
    // Obs2. Mais testes serão realizados a medidade que a aplicação for ganhando novos recursos.

    @Mock
    private ComunicacaoRepository comunicacaoRepository;
    @Mock
    private ComunicacaoConverter comunicacaoConverter;
    @Mock
    private NotificacaoEmailCliente notificacaoEmailCliente;
    @InjectMocks
    private ComunicacaoService service;

    @Test
    @DisplayName("Deve lançar uma IllegalArgumentException quando DTO for nulo")
    void agendarComunicacao() {

        assertThatThrownBy(() -> service.agendarComunicacao(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("O argumento fornecido é inválido: comunicado não pode ser nulo!");

        verifyNoInteractions(comunicacaoRepository, comunicacaoConverter, notificacaoEmailCliente);


    }

    @Test
    void buscaComunicacaoPendentes() {
    }

    @Test
    void enviaComunicadoEAtualizaStatus() {
    }

    @Test
    void buscarStatusComunicacao() {
    }

    @Test
    void alterarStatusComunicado() {
    }

    @Test
    @DisplayName("Deve deletar comunicado por e-mail com sucesso")
    void deletaComunicadoPorEmailDestinatario() {

        String email = "teste@teste.com.br";
        doNothing().when(comunicacaoRepository).deleteByEmailDestinatario(email);

        service.deletaComunicadoPorEmailDestinatario(email);

        verify(comunicacaoRepository, times(1)).deleteByEmailDestinatario(email);


    }

    void deveLancarExcecaoQuandoEmailForNulo() {

        assertThatThrownBy(() -> service.deletaComunicadoPorEmailDestinatario(null))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining("O comunicado não existe ou já foi excluido do sistema!");

        verifyNoInteractions(comunicacaoRepository);


    }
}