package com.musala.droneservice.utils.enums;

public enum State {

    IDLE("Idle"),
    LOADING("Loading"),
    LOADED("Loaded"),
    DELIVERING("Delivering"),
    DELIVERED("Delivered"),
    RETURNING("Returning");

    private String droneState;

    State(String droneState) {this.droneState = droneState; }

    public String getDroneState() {
        return droneState;
    }
}
