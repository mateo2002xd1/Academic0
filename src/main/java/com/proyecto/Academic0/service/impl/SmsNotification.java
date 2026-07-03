package com.proyecto.Academic0.service.impl;

import com.proyecto.Academic0.service.NotificationService;
import org.springframework.stereotype.Service;

/**
 *
 * @author trabajo
 */
@Service
public class SmsNotification implements NotificationService{
    @Override
    public void enviar(String destinatario, String mensaje){
        System.out.println("Sms enviado a " + destinatario);
    }
}
