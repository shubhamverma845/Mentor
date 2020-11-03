package com.sirion.searchMicroservice.Model;

import java.util.List;

public class MentorDetails{

    long mid;
    String name;
    long yoe;
    List<String> skill;

    public MentorDetails(String name, long yoe, List<String> skill) {
        this.name = name;
        this.yoe = yoe;
        this.skill = skill;
    }

    public MentorDetails(long mid, String name, long yoe, List<String> skill) {
        this.mid = mid;
        this.name = name;
        this.yoe = yoe;
        this.skill = skill;
    }

    public long getMid() {
        return mid;
    }

    public void setMid(long mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getYoe() {
        return yoe;
    }

    public void setYoe(long yoe) {
        this.yoe = yoe;
    }

    public List<String> getSkill() {
        return skill;
    }

    public void setSkill(List<String> skill) {
        this.skill = skill;
    }
}
