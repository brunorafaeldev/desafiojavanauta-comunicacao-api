package com.luizalebs.comunicacao_api.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luizalebs.comunicacao_api.infraestructure.enums.ModoEnvioEnum;
import com.luizalebs.comunicacao_api.infraestructure.enums.StatusEnvioEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class  ComunicacaoInDTO implements Serializable {


    private Long id;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataHoraEnvio;
    @NotBlank (message = "O nome do destinatário é obrigatório")
    private String nomeDestinatario;
    @NotBlank (message = "O e-mail do destinatário é obrigatório")
    private String emailDestinatario;
    @NotBlank(message = "O telefone é obrigatório")
    private String telefoneDestinatario;
    @NotBlank(message = "A mensagem é obrigatória")
    private String mensagem;
    private ModoEnvioEnum modoDeEnvio;
    @JsonIgnore
    private StatusEnvioEnum statusEnvio;

}
