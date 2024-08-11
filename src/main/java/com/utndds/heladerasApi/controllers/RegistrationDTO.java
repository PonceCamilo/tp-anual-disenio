package com.utndds.heladerasApi.controllers;

public class RegistrationDTO {
    private String email;
    private String whatsapp;
    private String telefono;
    private String password;
    private String rol;
    private String name;
    private String type;
    private String lastName;
    private String birthdate;
    private String address;
    private boolean donationChecked;
    private boolean foodDonationChecked;
    private boolean mealDistributionChecked;

    // Getters y Setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isDonationChecked() {
        return donationChecked;
    }

    public void setDonationChecked(boolean donationChecked) {
        this.donationChecked = donationChecked;
    }

    public boolean isFoodDonationChecked() {
        return foodDonationChecked;
    }

    public void setFoodDonationChecked(boolean foodDonationChecked) {
        this.foodDonationChecked = foodDonationChecked;
    }

    public boolean isMealDistributionChecked() {
        return mealDistributionChecked;
    }

    public void setMealDistributionChecked(boolean mealDistributionChecked) {
        this.mealDistributionChecked = mealDistributionChecked;
    }
}

