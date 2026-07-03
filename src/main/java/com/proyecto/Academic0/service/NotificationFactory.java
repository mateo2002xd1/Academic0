/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Academic0.service;

import com.proyecto.Academic0.enums.NotificationType;
import com.proyecto.Academic0.service.impl.EmailNotification;
import com.proyecto.Academic0.service.impl.PushNotification;
import com.proyecto.Academic0.service.impl.SmsNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author trabajo
 */
@Service
public class NotificationFactory {
    @Autowired
    public EmailNotification emailNotification;
    
    @Autowired
    public SmsNotification smsNotification;
    
    @Autowired
    public PushNotification pushNotification;
    
    public NotificationService getNotification(NotificationType tipo){
        switch(tipo){
            case NotificationType.EMAIL:
                return emailNotification;
            case NotificationType.SMS:
                return smsNotification;
            case NotificationType.PUSH:
                return pushNotification;
            default:
                throw new IllegalArgumentException("Tipo no soportado");
        }
    }
}
