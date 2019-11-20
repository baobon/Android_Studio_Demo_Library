package com.example.demo_mpandroidchart;

public class Wethear {
    private int temp;
    private  int humi;
    private  int press;
    private  int clound;

    public Wethear(int temp, int humi, int press, int clound) {
        this.temp = temp;
        this.humi = humi;
        this.press = press;
        this.clound = clound;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getHumi() {
        return humi;
    }

    public void setHumi(int humi) {
        this.humi = humi;
    }

    public int getPress() {
        return press;
    }

    public void setPress(int press) {
        this.press = press;
    }

    public int getClound() {
        return clound;
    }

    public void setClound(int clound) {
        this.clound = clound;
    }
}
