package com.proyecto.Academic0.service.impl;

import com.proyecto.Academic0.service.NotificationService;
import org.springframework.stereotype.Service;

/**
 *
 * @author trabajo
 */
@Service
public class EmailNotification implements NotificationService{
    @Override
    public void enviar(String destinatario, String mensaje){
        System.out.println("Email enviado a " + destinatario);
    }
    
}
