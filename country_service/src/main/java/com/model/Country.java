package com.model;

//import java.util.Arrays;

import javax.xml.bind.annotation.XmlRootElement;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
    String[] capital;

    public String[] getCapital() {
        return this.capital;
    }

    public void setCapital(String[] capital) {
        this.capital = capital;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);//Being nice to the next guy, heck we're already using gson.
        // return "{" + "\"capital\"=" + Arrays.toString(getCapital()) + "}";//Not quite JSON
    }

}