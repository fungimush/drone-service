package com.musala.droneservice.utils.enums;

public enum I18Code {

    MESSAGE_INVALID_REQUEST("messages.invalid.request.supplied"),

    MESSAGE_INVALID_CODE("messages.invalid.code.supplied"),

    MESSAGE_UNEXPECTED_ERROR("messages.unexpected.error.occurred"),
    MESSAGE_RECORD_ALREADY_EXIST("messages.record.already.exists"),

    MESSAGE_RECORD_INVALID_SERIAL_NUMBER("messages.record.invalid.serial.number"),

    MESSAGE_RECORD_INVALID_WEIGHT("messages.record.invalid.weight"),
    MESSAGE_RECORD_CREATED_SUCCESSFULLY("messages.record.created.successfully"),
    MESSAGE_RECORD_NOT_FOUND("messages.record.not.found"),
    MESSAGE_RECORD_UPDATED_SUCCESSFULLY("messages.record.updated.successfully"),
    MESSAGE_RECORD_DELETED_SUCCESSFULLY("messages.record.deleted.successfully"),
    MESSAGE_RECORDS_FETCHED_SUCCESSFULLY("messages.records.fetched.successfully"),
    MESSAGE_RECORD_FETCHED_SUCCESSFULLY("messages.record.fetched.successfully"),
    MESSAGE_RECORD_LOADED_SUCCESSFULLY("messages.record.loaded.successfully"),
    MESSAGE_RECORD_NOT_LOADED("message.record.not.loaded"),
    MESSAGE_RECORD_INVALID_STATE("message.record.invalid.state"),
    MESSAGE_RECORDS_EXCEEDS_FLEET_LIMIT("message.records.exceeds.fleet.limit"),
    MESSAGE_RECORD_WEIGHT_EXCEEDS_LIMIT("message.record.weight.exceeds.limit"),

    MESSAGE_RECORD_BATTERY_LOW("message.record.battery.low");

    private String code;

    I18Code(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
