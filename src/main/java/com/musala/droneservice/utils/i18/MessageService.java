package com.musala.droneservice.utils.i18;

import java.util.Locale;

public interface MessageService {
    String getMessage(String propertyName, String[] placeholders, Locale locale);
    String getMessage(String propertyName, Locale locale);
}
