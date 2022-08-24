package com.sg.dvdlibrary;

import com.sg.dvdlibrary.controller.DVDController;
import com.sg.dvdlibrary.dao.DVDdaoException;

public class App {

    public static void main(String[] args) throws DVDdaoException {
        DVDController app = new DVDController();
        app.run();
    }
}
