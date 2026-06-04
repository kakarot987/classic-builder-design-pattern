package com.demo.builder.model;

/**
 * BUILDER DESIGN PATTERN DEMO
 *
 * Problem: Constructing a complex object (Computer) with many optional fields.
 * Using telescoping constructors or setters leads to messy, error-prone code.
 *
 * Solution: A static nested Builder class collects all params step-by-step,
 * then creates the final immutable object in one clean build() call.
 *
 * Pattern Roles:
 *  - Product      : Computer (the complex object being built)
 *  - Builder      : Computer.Builder (inner static class)
 *  - Director     : ComputerController (orchestrates common builds)
 */
public class Computer {

    // ── Product fields – all final (immutable after construction) ──────────
    private final String cpu;           // required
    private final int ramGB;            // required
    private final int storageGB;        // required
    private final String gpu;           // optional
    private final boolean hasBluetooth; // optional
    private final boolean hasWifi;      // optional
    private final String os;            // optional
    private final String color;         // optional

    // Private constructor – only the Builder can call this
    private Computer(Builder builder) {
        this.cpu           = builder.cpu;
        this.ramGB         = builder.ramGB;
        this.storageGB     = builder.storageGB;
        this.gpu           = builder.gpu;
        this.hasBluetooth  = builder.hasBluetooth;
        this.hasWifi       = builder.hasWifi;
        this.os            = builder.os;
        this.color         = builder.color;
    }

    // ── Getters ──────────────────────────────────────────────────────────
    public String getCpu()          { return cpu; }
    public int getRamGB()           { return ramGB; }
    public int getStorageGB()       { return storageGB; }
    public String getGpu()          { return gpu; }
    public boolean isHasBluetooth() { return hasBluetooth; }
    public boolean isHasWifi()      { return hasWifi; }
    public String getOs()           { return os; }
    public String getColor()        { return color; }

    @Override
    public String toString() {
        return "Computer{cpu='" + cpu + "', ramGB=" + ramGB +
               ", storageGB=" + storageGB + ", gpu='" + gpu +
               "', bluetooth=" + hasBluetooth + ", wifi=" + hasWifi +
               ", os='" + os + "', color='" + color + "'}";
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  BUILDER  –  static nested class
    // ═══════════════════════════════════════════════════════════════════════
    public static class Builder {

        // Required parameters
        private final String cpu;
        private final int    ramGB;
        private final int    storageGB;

        // Optional parameters – sensible defaults
        private String  gpu           = "Integrated";
        private boolean hasBluetooth  = false;
        private boolean hasWifi       = false;
        private String  os            = "None";
        private String  color         = "Black";

        /**
         * Constructor takes only the REQUIRED fields.
         * Everything else is set via fluent setter methods.
         */
        public Builder(String cpu, int ramGB, int storageGB) {
            if (cpu == null || cpu.isBlank())
                throw new IllegalArgumentException("CPU is required");
            if (ramGB <= 0)
                throw new IllegalArgumentException("RAM must be > 0 GB");
            if (storageGB <= 0)
                throw new IllegalArgumentException("Storage must be > 0 GB");

            this.cpu       = cpu;
            this.ramGB     = ramGB;
            this.storageGB = storageGB;
        }

        // ── Optional field setters – each returns `this` for chaining ───
        public Builder gpu(String gpu)              { this.gpu = gpu;           return this; }
        public Builder bluetooth(boolean val)       { this.hasBluetooth = val;  return this; }
        public Builder wifi(boolean val)            { this.hasWifi = val;       return this; }
        public Builder os(String os)                { this.os = os;             return this; }
        public Builder color(String color)          { this.color = color;       return this; }

        /**
         * Terminal method – validates & constructs the final Computer object.
         */
        public Computer build() {
            return new Computer(this);
        }
    }
}
