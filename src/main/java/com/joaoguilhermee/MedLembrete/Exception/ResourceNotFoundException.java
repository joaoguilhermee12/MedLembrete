package com.joaoguilhermee.MedLembrete.Exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, Long id) {
        super(resource + " não encontrado com id: " + id);
    }
}