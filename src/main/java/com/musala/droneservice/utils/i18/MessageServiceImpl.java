package com.musala.droneservice.utils.i18;

import org.springframework.context.MessageSource;

import java.util.Locale;

public class MessageServiceImpl implements MessageService {
    private MessageSource messageSource;

    public MessageServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String propertyName, String[] placeholders, Locale locale) {
        return messageSource.getMessage(propertyName, placeholders, locale);
    }

    @Override
    public String getMessage(String propertyName, Locale locale) {
        return messageSource.getMessage(propertyName, new String[] {}, locale);
    }
}
