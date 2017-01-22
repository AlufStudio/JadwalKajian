package com.tolabulilm.jadwalkajian.kajian;

/**
 * Created by fata on 1/18/2017.
 */

public class Kajian {
    private String id;
    private String ustadz;
    private String title;
    private long time;
    private String place;
    private String city;
    private String type;
    private String address;
    private String status;
    private String hijri;
    private String contactNumber;

    public Kajian() {
    }

    public Kajian(String id, String ustadz, String title, String place, long time,
                  String city, String type) {
        this.id = id;
        this.ustadz = ustadz;
        this.title = title;
        this.place = place;
        this.time = time;
        this.city = city;
        this.type = type;
    }

    public void setUstadz(String ustadz) {
        this.ustadz = ustadz;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setHijri(String hijri) {
        this.hijri = hijri;
    }

    public void setContactNumber(String number) {
        this.contactNumber = number;
    }

    public String getId() {
        return this.id;
    }

    public String getUstadz() {
        return this.ustadz;
    }

    public String getTitle() {
        return this.title;
    }

    public long getTime() {
        return this.time;
    }

    public String getPlace() {
        return this.place;
    }

    public String getCity() {
        return this.city;
    }

    public String getType() {
        return this.type;
    }

    public String getAddress() {
        return this.address;
    }

    public String getStatus() {
        return this.status;
    }

    public String getHijri() {
        return this.hijri;
    }

    public String getContactNumber() {
        return this.contactNumber;
    }
}
