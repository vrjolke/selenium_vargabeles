package com.codecool.vargabeles;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PermissionsWithGlassTest {

    private PermissionsWithGlass permissionsWithGlass;

    @BeforeEach
    void newDriver(){
        permissionsWithGlass = new PermissionsWithGlass();
        permissionsWithGlass.login();
    }

    @AfterEach
    void finish() {
        permissionsWithGlass.getDriver().close();

    }

    @Test
    void checkPermissions() throws InterruptedException {
        assertEquals(permissionsWithGlass.checkPermissions(), 3);
    }

    @Test
    void checkPermissionsWithGlass() throws InterruptedException {
        assertEquals(permissionsWithGlass.checkPermissions(), 3);

    }

    @Test
    void comparePermissionsWithGlass() throws InterruptedException  {
        assertEquals(permissionsWithGlass.checkPermissions(), permissionsWithGlass.checkPermissionsWithGlass());
    }
}