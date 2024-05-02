package com.doctor.model;

import jdk.jfr.DataAmount;


public class SlotPeriod {
    String id;
    String doctorId;
    String date;
    String fromTime;
    String toTime;
    String status;

    public SlotPeriod(String id, String doctorId, String date, String fromTime, String toTime, String status) {
        this.id = id;
        this.doctorId = doctorId;
        this.date = date;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
