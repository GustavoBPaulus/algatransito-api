package com.algaworks.algatransito.api.representationmodel;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.OffsetDateTime;
@Getter
@Setter
public class VeiculoRepresentationModel {

    private BigInteger id;
    private ProprietarioResumoModel proprietario;
    private String marca;
    private String modelo;
    private String numeroPlaca;
    private String status;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataApreensao;



}
