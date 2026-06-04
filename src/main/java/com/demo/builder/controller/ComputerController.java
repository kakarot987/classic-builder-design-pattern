package com.demo.builder.controller;

import com.demo.builder.model.Computer;
import com.demo.builder.model.ComputerRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * DIRECTOR role in the Builder Pattern.
 *
 * The Director knows HOW to use the Builder to construct
 * specific, pre-defined configurations (presets), or it
 * can delegate full control to the client (custom build).
 */
@RestController
@RequestMapping("/api/computers")
@CrossOrigin(origins = "*")
public class ComputerController {

    /**
     * POST /api/computers/build
     * Custom build – client supplies all fields.
     * The controller uses Builder step-by-step.
     */
    @PostMapping("/build")
    public ResponseEntity<?> buildCustom(@RequestBody ComputerRequest req) {
        try {
            // ── Core Builder Pattern usage ──────────────────────────────
            Computer computer = new Computer.Builder(
                    req.getCpu(),
                    req.getRamGB(),
                    req.getStorageGB()
            )
            .gpu(req.getGpu() != null ? req.getGpu() : "Integrated")
            .bluetooth(req.isHasBluetooth())
            .wifi(req.isHasWifi())
            .os(req.getOs() != null ? req.getOs() : "None")
            .color(req.getColor() != null ? req.getColor() : "Black")
            .build();   // ← final construction happens here

            return ResponseEntity.ok(toMap(computer, "Custom Build"));

        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
        }
    }

    /**
     * GET /api/computers/presets
     * Director presets – common configurations built with the Builder.
     */
    @GetMapping("/presets")
    public ResponseEntity<List<Map<String, Object>>> getPresets() {

        // ── Preset 1 : Office PC ────────────────────────────────────────
        Computer officePC = new Computer.Builder("Intel Core i5-13400", 16, 512)
                .os("Windows 11")
                .wifi(true)
                .bluetooth(true)
                .color("Silver")
                .build();

        // ── Preset 2 : Gaming Rig ───────────────────────────────────────
        Computer gamingRig = new Computer.Builder("Intel Core i9-14900K", 64, 2000)
                .gpu("NVIDIA RTX 4090")
                .os("Windows 11")
                .wifi(true)
                .bluetooth(true)
                .color("RGB Black")
                .build();

        // ── Preset 3 : Dev Workstation ──────────────────────────────────
        Computer devBox = new Computer.Builder("AMD Ryzen 9 7950X", 128, 4000)
                .gpu("AMD Radeon RX 7900 XTX")
                .os("Ubuntu 24.04 LTS")
                .wifi(true)
                .bluetooth(false)
                .color("Matte Black")
                .build();

        // ── Preset 4 : Budget PC ────────────────────────────────────────
        Computer budgetPC = new Computer.Builder("Intel Core i3-13100", 8, 256)
                .os("Windows 11 Home")
                .wifi(true)
                .color("White")
                .build();

        return ResponseEntity.ok(List.of(
                toMap(officePC, "Office PC"),
                toMap(gamingRig, "Gaming Rig"),
                toMap(devBox, "Dev Workstation"),
                toMap(budgetPC, "Budget PC")
        ));
    }

    // ── Helper: Computer → Map (JSON-friendly) ───────────────────────────
    private Map<String, Object> toMap(Computer c, String label) {
        return Map.of(
            "label",        label,
            "cpu",          c.getCpu(),
            "ramGB",        c.getRamGB(),
            "storageGB",    c.getStorageGB(),
            "gpu",          c.getGpu(),
            "hasBluetooth", c.isHasBluetooth(),
            "hasWifi",      c.isHasWifi(),
            "os",           c.getOs() != null ? c.getOs() : "None",
            "color",        c.getColor() != null ? c.getColor() : "Black"
        );
    }
}
