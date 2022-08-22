package com.sg.DVDlibrary.controller;

import com.sg.DVDlibrary.dao.DVDdaoFileImpl;
import com.sg.DVDlibrary.dto.DVD;
import com.sg.DVDlibrary.ui.DVDView;
import com.sg.DVDlibrary.ui.UserIO;
import com.sg.DVDlibrary.ui.UserIOConsoleImpl;

import java.io.IOException;

public class DVDController {

    private final UserIO io = new UserIOConsoleImpl();
    DVDView view = new DVDView(io);
    DVDdaoFileImpl dao = new DVDdaoFileImpl();

    public void run() throws IOException {
        boolean keepGoing = true;
        int menuSelection;
        while (keepGoing) {

            menuSelection = this.view.printMenuAndGetSelection();

            switch (menuSelection) {
                case 1:
                    this.showAllDVD();
                    break;
                case 2:
                    this.createDVD();
                    break;
                case 3:
                    this.showAdvd();
                    break;
                case 4:
                    this.deleteDVD();
                    break;
                case 5:
                    this.editDVD();
                    break;
                case 6:
                    keepGoing = false;
                    break;
                default:
                    io.print("UNKNOWN COMMAND");
            }

        }
        io.print("GOOD BYE");
    }

    private void createDVD() throws IOException {
        this.view.displayCreateDVDBanner();
        DVD newDVD = this.view.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        this.view.displayCreateSuccessBanner();
    }

    private void showAllDVD() {
        this.view.displayDisplayAllBanner();
        this.view.displaydvdList(dao.getAllDVDs());

    }

    private void showAdvd() {
        view.displayDisplayADVDBanner();
        String dvdTitle = view.getDVDChoice();
        DVD aDVD = dao.getDVD(dvdTitle);
        view.displayDVD(aDVD);
    }

    private void deleteDVD() throws IOException {
        view.displayRemoveDVDBanner();
        String dvdTitle = view.getDVDChoice();
        view.displayRemoveResult(dao.getDVD(dvdTitle));
        dao.removeDVD(dvdTitle);
    }

    private void editDVD() throws IOException {
        view.displayEditDVDBanner();
        String dvdTitle = view.getDVDChoice();

        if (dao.getDVD(dvdTitle) != null) {

            int editFeatureNum = view.chooseEditFeature();
            String editValue = view.giveEditValue();
            dao.editDVD(dvdTitle, editFeatureNum, editValue);
            view.displayDVD(dao.getDVD(dvdTitle));
        } else {
            view.editDVDnoExist();
        }

    }
}
