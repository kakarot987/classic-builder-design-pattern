package com.demo.builder.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComputerBuilderTest {

    @Test
    void appliesDefaultsWhenNoOptionalValuesAreSet() {
        Computer pc = new Computer.Builder("Intel i5", 16, 512).build();
        assertEquals("Intel i5", pc.getCpu());
        assertEquals(16, pc.getRamGB());
        assertEquals(512, pc.getStorageGB());
        assertEquals("Integrated", pc.getGpu());
        assertEquals("None", pc.getOs());
        assertEquals("Black", pc.getColor());
        assertFalse(pc.isHasBluetooth());
        assertFalse(pc.isHasWifi());
    }

    @Test
    void fluentSettersConstructCustomizedComputer() {
        Computer pc = new Computer.Builder("Ryzen 9", 64, 2000)
                .gpu("RTX 4090").wifi(true).bluetooth(true)
                .os("Linux").color("Silver").build();
        assertEquals("RTX 4090", pc.getGpu());
        assertEquals("Linux", pc.getOs());
        assertEquals("Silver", pc.getColor());
        assertTrue(pc.isHasWifi());
        assertTrue(pc.isHasBluetooth());
    }

    @Test
    void rejectsInvalidRequiredValues() {
        assertThrows(IllegalArgumentException.class, () -> new Computer.Builder(null, 16, 512));
        assertThrows(IllegalArgumentException.class, () -> new Computer.Builder(" ", 16, 512));
        assertThrows(IllegalArgumentException.class, () -> new Computer.Builder("Intel", 0, 512));
        assertThrows(IllegalArgumentException.class, () -> new Computer.Builder("Intel", 16, -1));
    }

    @Test
    void rejectsExplicitlyBlankOptionalValues() {
        Computer.Builder builder = new Computer.Builder("Intel i7", 16, 1024);
        assertThrows(IllegalArgumentException.class, () -> builder.gpu(" "));
        assertThrows(IllegalArgumentException.class, () -> builder.os(null));
        assertThrows(IllegalArgumentException.class, () -> builder.color(""));
        assertEquals("Integrated", builder.build().getGpu());
    }

    @Test
    void builtObjectIsUnchangedWhenBuilderIsReused() {
        Computer.Builder builder = new Computer.Builder("Intel", 16, 512).gpu("Integrated");
        Computer original = builder.build();
        Computer upgraded = builder.gpu("Discrete GPU").build();

        assertEquals("Integrated", original.getGpu());
        assertEquals("Discrete GPU", upgraded.getGpu());
        assertNotSame(original, upgraded);
    }
}
