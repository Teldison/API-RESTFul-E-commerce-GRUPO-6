package org.serratec.bookshop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ViaCep(
    @SerializedName("logradouro") String rua,
    String bairro,
    @SerializedName("localidade") String cidade,
    String uf
) {}
