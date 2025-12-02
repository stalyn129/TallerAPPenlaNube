package com.services;


public interface FirestoreService {
    void guardarLog(String accion, String usuario);
    void guardarAlerta(String producto, Integer stock);
}
