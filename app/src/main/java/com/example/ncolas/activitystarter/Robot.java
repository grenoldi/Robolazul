package com.example.ncolas.activitystarter;

/**
 * Created by bob on 3/7/18.
 */

public class Robot {
    private String rName;
    private String rCategory;

    private Robot(String _name, String _category){
        this.rName  = _name;
        this.rCategory = _category;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getrName() {
        return rName;
    }

    public void setrCategory(String rCategory) {
        this.rCategory = rCategory;
    }

    public String getrCathegory() {
        return rCategory;
    }
}
