package com.luizalebs.comunicacao_api.controller;

import com.luizalebs.comunicacao_api.business.dto.ComunicacaoInDTO;
import com.luizalebs.comunicacao_api.business.dto.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.business.service.ComunicacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comunicado")
@RequiredArgsConstructor
@Tag(name = "Comunicação", description = "Cadastro, Busca e Status de Comunicações")
public class ComunicacaoController {

    private final ComunicacaoService service;

    @PostMapping("/agendar")
    @Operation(summary = "Agenda Comunicacao", description = "Cria, agenda e envia comunicado")
    @ApiResponse(responseCode = "200", description = "Comunicado criado e agendado com sucesso")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ComunicacaoOutDTO> agendarComunicado(@Valid @RequestBody ComunicacaoInDTO dto)  {
        return ResponseEntity.ok(service.agendarComunicacao(dto));
    }

    @Operation(summary = "Busca Status Comunicado", description = "Mostra Status do comunicado")
    @ApiResponse(responseCode = "200", description = "Status informado com sucesso")
    @ApiResponse(responseCode = "404", description = "Comunicado não encontrado por e-mail do destinatário" )
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @GetMapping()
    public ResponseEntity<ComunicacaoOutDTO> buscarStatusComunicado(@Valid @RequestParam String emailDestinatario) {
        return ResponseEntity.ok(service.buscarStatusComunicacao(emailDestinatario));
    }

    @PatchMapping("/alterar")
    @Operation(summary = "Altera status comunicado", description = "Altera status do comunicado para CANCELADO")
    @ApiResponse(responseCode = "200", description = "Status mudou para CANCELADO")
    @ApiResponse(responseCode = "404", description = "Comunicado não encontrado por e-mail do destinatário" )
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")

    public ResponseEntity<ComunicacaoOutDTO> alteraStatusComunicado(@Valid @RequestParam String emailDestinatario) {
        return ResponseEntity.ok(service.alterarStatusComunicado(emailDestinatario));
    }


    @DeleteMapping("/{emailDestinatario}")
    @Operation(summary = "Deleta comunicado", description = "Deleta comunicado por e-mail")
    @ApiResponse(responseCode = "200", description = "Comunicado deletado")
    @ApiResponse(responseCode = "404", description = "Comunicado não encontrado por e-mail do destinatário" )
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletaComunicacaoPorEmailDestinatario(@Valid @PathVariable String emailDestinatario) {
       service.deletaComunicadoPorEmailDestinatario(emailDestinatario);
       return ResponseEntity.ok().build();

    }
}
