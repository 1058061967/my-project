package com.manage.utils;


public class Constant {


    public enum MenuType {

    	CATALOG(0),MENU(1),BUTTON(2);

        private int value;

        private MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    

    public enum ScheduleStatus {

    	NORMAL(0),PAUSE(1);

        private int value;

        private ScheduleStatus(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    }
}
