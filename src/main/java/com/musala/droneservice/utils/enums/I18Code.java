package com.musala.droneservice.utils.enums;

public enum I18Code {

    MESSAGE_INVALID_REQUEST("messages.invalid.request.supplied"),

    MESSAGE_UNEXPECTED_ERROR("messages.unexpected.error.occurred"),
    MESSAGE_RECORD_ALREADY_EXIST("messages.record.already.exists"),
    MESSAGE_RECORD_CREATED_SUCCESSFULLY("messages.record.created.successfully"),
    MESSAGE_RECORD_NOT_FOUND("messages.record.not.found"),
    MESSAGE_RECORD_UPDATED_SUCCESSFULLY("messages.record.updated.successfully"),
    MESSAGE_RECORD_DELETED_SUCCESSFULLY("messages.record.deleted.successfully"),
    MESSAGE_RECORDS_FETCHED_SUCCESSFULLY("messages.records.fetched.successfully");

    private String code;

    I18Code(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
