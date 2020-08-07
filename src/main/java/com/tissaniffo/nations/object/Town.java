package com.tissaniffo.nations.object;

import com.tissaniffo.nations.Nations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Town {

    private String name;
    private int residentAmount;
    private List<Resident> residents;
    private Resident major;

    public Town(String name, Resident major){
        this.name = name;
        this.residentAmount = 1;
        this.residents = Arrays.asList(major);
        this.major = major;
        Nations.getInstance().getCommonUtilities().getTownDatabase().createTown(this, name, major);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResidentAmount() {
        return residentAmount;
    }

    public void setResidentAmount(int residentAmount) {
        this.residentAmount = residentAmount;
    }

    public List<Resident> getResidents() {
        return residents;
    }

    public void addResident(Resident resident){
        residents.add(resident);
    }

    public Boolean hasResident(Resident resident){
        return residents.contains(resident);
    }

    public void setResidents(List<Resident> residents) {
        this.residents = residents;
    }

    public Resident getMajor() {
        return major;
    }

    public void setMajor(Resident major) {
        this.major = major;
    }
}
