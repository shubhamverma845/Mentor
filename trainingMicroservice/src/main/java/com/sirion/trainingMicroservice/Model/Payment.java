package com.sirion.trainingMicroservice.Model;

public class Payment {

    String id;
    String userid;
    String mentorid;
    String tid;
    String amount;

    public Payment() {
    }

    public Payment(String id, String userid, String mentorid, String tid, String amount) {
        this.id = id;
        this.userid = userid;
        this.mentorid = mentorid;
        this.tid = tid;
        this.amount = amount;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMentorid() {
        return mentorid;
    }

    public void setMentorid(String mentorid) {
        this.mentorid = mentorid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
