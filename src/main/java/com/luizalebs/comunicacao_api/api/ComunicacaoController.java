package com.luizalebs.comunicacao_api.api;

import com.luizalebs.comunicacao_api.business.dto.ComunicacaoInDTO;
import com.luizalebs.comunicacao_api.business.dto.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.business.service.ComunicacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comunicacao")
@RequiredArgsConstructor
@Tag(name = "Comunicação", description = "Cadastro, Busca e Status de Comunicado")
public class ComunicacaoController {

    private final ComunicacaoService service;

    @PostMapping("/agendar")
    @Operation(summary = "Agenda Comunicado", description = "Cria e agenda comunicado")
    @ApiResponse(responseCode = "200", description = "Comunicado agendado com sucesso")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ComunicacaoOutDTO> agendar(@RequestBody ComunicacaoInDTO dto)  {
        return ResponseEntity.ok(service.agendarComunicacao(dto));
    }

    @Operation(summary = "Busca Status Comunicado", description = "Mostra Status do comunicado")
    @ApiResponse(responseCode = "200", description = "Status informado com sucesso")
    @ApiResponse(responseCode = "404", description = "Comunicado não encontrado por e-mail do destinatário" )
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @GetMapping()
    public ResponseEntity<ComunicacaoOutDTO> buscarStatus(@RequestParam String emailDestinatario) {
        return ResponseEntity.ok(service.buscarStatusComunicacao(emailDestinatario));
    }

    @Operation(summary = "Altera status comunicado", description = "Altera status comunicado para CANCELADO")
    @ApiResponse(responseCode = "200", description = "Status mudou para CANCELADO")
    @ApiResponse(responseCode = "404", description = "Comunicado não encontrado por e-mail do destinatário" )
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @PatchMapping("/cancelar")
    public ResponseEntity<ComunicacaoOutDTO> cancelarStatus(@RequestParam String emailDestinatario) {
        return ResponseEntity.ok(service.alterarStatusComunicacao(emailDestinatario));
    }
}
