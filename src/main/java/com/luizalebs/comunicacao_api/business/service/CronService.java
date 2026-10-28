package com.luizalebs.comunicacao_api.business.service;


import com.luizalebs.comunicacao_api.business.dto.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.infraestructure.entities.ComunicacaoEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Component
public class CronService {


    private final ComunicacaoService comunicacaoService;


    @Scheduled(cron = "${cron.horario}")

    public void buscaProcessaComunicadosAgendados() {

        log.info("Buscando comunicações pendentes...");

        LocalDateTime dataHoraAtual = LocalDateTime.now();


        List<ComunicacaoOutDTO> comunicadosPendentes = comunicacaoService.buscaComunicacaoPendentes(dataHoraAtual);

        log.info("Quantidade de comunicados encontrados: {}", comunicadosPendentes.size());

        comunicadosPendentes.forEach(comunicacao -> {
            try {
                comunicacaoService.enviaComunicadoEAtualizaStatus(comunicacao);
                log.info("Comunicado enviado com sucesso para o e-mail: {}", comunicacao.getEmailDestinatario());
            } catch (IllegalArgumentException e) {
                log.error("Erro ao processar o envio do comunicado para o e-mail: {}", comunicacao.getEmailDestinatario(), e);
            }
        });

        log.info("Finalizado processamento das comunicações");

    }


}
