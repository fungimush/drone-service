package com.musala.droneservice.utils.enums;

public enum Model {

    LIGHTWEIGHT("Lightweight"),
    MIDDLEWEIGHT("Middleweight"),
    CRUISERWEIGHT("Cruiserweight"),
    HEAVYWEIGHT("Heavyweight");

    private String droneModel;

    private Model(final String droneModel) { this.droneModel = droneModel; }

    public String getDroneModel() {
        return droneModel;
    }

}


