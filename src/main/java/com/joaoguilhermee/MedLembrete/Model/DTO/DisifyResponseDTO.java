package com.joaoguilhermee.MedLembrete.Model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DisifyResponseDTO {
    private boolean format;
    private boolean disposable;
    private boolean dns;

    public boolean isFormat() { return format; }
    public void setFormat(boolean format) { this.format = format; }
    public boolean isDisposable() { return disposable; }
    public void setDisposable(boolean disposable) { this.disposable = disposable; }
    public boolean isDns() { return dns; }
    public void setDns(boolean dns) { this.dns = dns; }
}