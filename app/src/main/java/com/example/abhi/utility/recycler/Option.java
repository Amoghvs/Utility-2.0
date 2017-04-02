package com.example.abhi.utility.recycler;

/**
 * Created by abhi on 26/2/17.
 */

public class Option {


    private int optionimg;
    private String optionname;

    public Option(int optionimg, String optionname) {
        this.optionimg = optionimg;
        this.optionname = optionname;
    }

    public int getOptionimg() {
        return optionimg;
    }

    public void setOptionimg(int optionimg) {
        this.optionimg = optionimg;
    }

    public String getOptionname() {
        return optionname;
    }

    public void setOptionname(String optionname) {
        this.optionname = optionname;
    }
}
