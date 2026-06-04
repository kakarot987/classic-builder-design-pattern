package com.demo.builder.model;

public class ComputerRequest {
    // Required
    private String cpu;
    private int ramGB;
    private int storageGB;

    // Optional
    private String  gpu;
    private boolean hasBluetooth;
    private boolean hasWifi;
    private String  os;
    private String  color;

    // Getters & Setters
    public String getCpu()                  { return cpu; }
    public void setCpu(String cpu)          { this.cpu = cpu; }

    public int getRamGB()                   { return ramGB; }
    public void setRamGB(int ramGB)         { this.ramGB = ramGB; }

    public int getStorageGB()               { return storageGB; }
    public void setStorageGB(int s)         { this.storageGB = s; }

    public String getGpu()                  { return gpu; }
    public void setGpu(String gpu)          { this.gpu = gpu; }

    public boolean isHasBluetooth()         { return hasBluetooth; }
    public void setHasBluetooth(boolean b)  { this.hasBluetooth = b; }

    public boolean isHasWifi()              { return hasWifi; }
    public void setHasWifi(boolean w)       { this.hasWifi = w; }

    public String getOs()                   { return os; }
    public void setOs(String os)            { this.os = os; }

    public String getColor()                { return color; }
    public void setColor(String color)      { this.color = color; }
}
