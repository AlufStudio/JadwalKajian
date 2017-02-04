package com.tolabulilm.jadwalkajian.setting.donasi;

public class Donasi {
    private String title;
    private int donated;
    private int needed;
    private String description;
    private String bankName;
    private String accName;
    private String bankNumber;

    public Donasi() {}

    public Donasi(String title, int donated, int needed, String description,
        String bankName, String accName, String bankNumber) {
        this.title = title;
        this.donated = donated;
        this.needed = needed;
        this.description = description;
        this.bankName = bankName;
        this.accName = accName;
        this.bankNumber = bankNumber;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDonated(int donated) {
        this.donated = donated;
    }

    public void setNeeded(int needed) {
        this.needed = needed;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getTitle() {
        return this.title;
    }

    public int getDonated() {
        return this.donated;
    }

    public int getNeeded() {
        return this.needed;
    }

    public String getDescription() {
        return this.description;
    }

    public String getBankName() {
        return this.bankName;
    }

    public String getAccName() {
        return this.accName;
    }

    public String getBankNumber() {
        return this.bankNumber;
    }
}
