/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Academic0.service.impl;

import com.proyecto.Academic0.service.NotificationService;
import org.springframework.stereotype.Service;

/**
 *
 * @author trabajo
 */
@Service
public class PushNotification implements NotificationService{
    @Override
    public void enviar(String destinatario, String mensaje){
        System.out.println("Push enviado a " + destinatario);
    }
}
