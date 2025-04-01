package com.mio.todosimple.Selic.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record SelicResponse(
        @JsonProperty("data") String data,
        @JsonProperty("valor") String valor
) {
    public LocalDate getDataAsLocalDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(data, formatter);
    }

    public Double getValorAsDouble() {
        return Double.parseDouble(valor.replace(",", "."));
    }
}