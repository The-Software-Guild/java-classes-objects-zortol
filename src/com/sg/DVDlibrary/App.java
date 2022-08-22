package com.sg.DVDlibrary;

import com.sg.DVDlibrary.controller.DVDController;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        DVDController app = new DVDController();
        app.run();
    }
}
