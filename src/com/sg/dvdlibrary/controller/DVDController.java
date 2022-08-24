package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DVDdaoException;
import com.sg.dvdlibrary.dao.DVDdaoFileImpl;
import com.sg.dvdlibrary.dto.DVD;
import com.sg.dvdlibrary.ui.DVDView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;


public class DVDController {

    private final UserIO io = new UserIOConsoleImpl();
    DVDView view = new DVDView(io);
    DVDdaoFileImpl dao = new DVDdaoFileImpl();

    public void run(){
        boolean keepGoing = true;
        int menuSelection;
        try {
            while (keepGoing) {

                menuSelection = this.view.printMenuAndGetSelection();

                switch (menuSelection) {
                    case 1 -> this.showAllDVD();
                    case 2 -> this.createDVD();
                    case 3 -> this.showAdvd();
                    case 4 -> this.deleteDVD();
                    case 5 -> this.editDVD();
                    case 6 -> keepGoing = false;
                    default -> io.print("UNKNOWN COMMAND");
                }

            }
            io.print("GOOD BYE");
        } catch(DVDdaoException e){
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void createDVD() throws DVDdaoException {
        this.view.displayCreateDVDBanner();
        DVD newDVD = this.view.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        this.view.displayCreateSuccessBanner();
    }

    private void showAllDVD() throws DVDdaoException {
        this.view.displayDisplayAllBanner();
        this.view.displaydvdList(dao.getAllDVDs());

    }

    private void showAdvd() throws DVDdaoException {
        view.displayDisplayADVDBanner();
        String dvdTitle = view.getDVDChoice();
        DVD aDVD = dao.getDVD(dvdTitle);
        view.displayDVD(aDVD);
    }

    private void deleteDVD() throws DVDdaoException {
        view.displayRemoveDVDBanner();
        String dvdTitle = view.getDVDChoice();
        view.displayRemoveResult(dao.getDVD(dvdTitle));
        dao.removeDVD(dvdTitle);
    }

    private void editDVD() throws DVDdaoException {
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
