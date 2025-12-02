package com.services.impl;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.services.FirestoreService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class FirestoreServiceImpl implements FirestoreService {

    @Override
    public void guardarLog(String accion, String usuario) {

        Firestore db = FirestoreClient.getFirestore();

        Map<String, Object> data = new HashMap<>();
        data.put("accion", accion);
        data.put("usuario", usuario);
        data.put("fecha", LocalDateTime.now().toString());

        db.collection("logsActividad").add(data);
    }

    @Override
    public void guardarAlerta(String producto, Integer stock) {

        Firestore db = FirestoreClient.getFirestore();

        Map<String, Object> alerta = new HashMap<>();
        alerta.put("producto", producto);
        alerta.put("stock", stock);
        alerta.put("tipo", "critico");
        alerta.put("fecha", LocalDateTime.now().toString());

        db.collection("alertasStock").add(alerta);
    }
}

