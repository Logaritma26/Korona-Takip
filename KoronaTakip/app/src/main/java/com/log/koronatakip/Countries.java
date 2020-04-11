package com.log.koronatakip;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Countries {

    @SerializedName("Country")
    @Expose
    private String Country;

    @SerializedName("Slug")
    @Expose
    private String Slug;

    @SerializedName("ISO2")
    @Expose
    private String ISO2;

    public void setCountry(String country) {
        Country = country;
    }

    public void setSlug(String slug) {
        Slug = slug;
    }

    public void setISO2(String ISO2) {
        this.ISO2 = ISO2;
    }

    public String getCountry() {
        return Country;
    }

    public String getSlug() {
        return Slug;
    }

    public String getISO2() {
        return ISO2;
    }
}
