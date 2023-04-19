package com.musala.droneservice.utils.enums;

public enum DroneStateTransition {

    IDLE {
        @Override
        public DroneStateTransition nextState() {return LOADING;}

        @Override
        public String action() {return ""; }
    },
    LOADING {
        @Override
        public DroneStateTransition nextState() {
            return LOADED;
        }

        @Override
        public String action() {
            return "";
        }


    },
    LOADED {
        @Override
        public DroneStateTransition nextState() {
            return DELIVERING;
        }

        @Override
        public String action() {
            return "";
        }


    },
    DELIVERING {
        @Override
        public DroneStateTransition nextState() {
            return DELIVERED;
        }

        @Override
        public String action() {
            return "";
        }


    },
    DELIVERED {
        @Override
        public DroneStateTransition nextState() {
            return RETURNING;
        }

        @Override
        public String action() {
            return "";
        }


    },
    RETURNING {
        @Override
        public DroneStateTransition nextState() {
            return this;
        }

        @Override
        public String action() {
            return "";
        }
    };

    public abstract DroneStateTransition nextState();

    public abstract String action();
}


