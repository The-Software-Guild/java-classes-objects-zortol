package com.sg.dvdlibrary.ui;


import com.sg.dvdlibrary.dto.DVD;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

public class DVDView {

    private UserIO io;

    public DVDView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List DVDs");
        io.print("2. Create New DVD");
        io.print("3. View a DVD");
        io.print("4. Remove a DVD");
        io.print("5. Edit a DVD");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public DVD getNewDVDInfo() {
        // enter information by user
        try {

            String title = io.readString("Please enter DVD title");
            String SreleaseDate = io.readString("Please enter it's release date in the format: yyyy-MM-dd");
            LocalDate releaseDate = LocalDate.parse(SreleaseDate);
            String MPAA_rating = io.readString("Please enter the MPPA rating");
            String director_Name = io.readString("Please enter the director's name");
            String studio = io.readString("Please enter the studio's name");
            String user_rating = io.readString("Please enter the user rating");

            DVD currentDVD = new DVD();
            currentDVD.setTitle(title);
            currentDVD.setReleaseDate(releaseDate);
            currentDVD.setMPAA_rating(Integer.parseInt(MPAA_rating));
            currentDVD.setDirectorName(director_Name);
            currentDVD.setStudio(studio);
            currentDVD.setUser_rating(user_rating);
            return currentDVD;
        } catch (DateTimeException e) {
            System.out.println("There is a problem with the date format");
            return null;
        }


    }

    public void displayCreateDVDBanner() {
        io.print("=== Create DVD ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString(
                "DVD successfully created.  Please hit enter to continue");
    }

    public void displaydvdList(List<DVD> dvdList) {
        for (DVD currentDVD : dvdList) {
            String dvdInfo = String.format("#%s : %s %d %s %s %s",
                    currentDVD.getTitle(),
                    currentDVD.getReleaseDate().toString(),
                    currentDVD.getMPAA_rating(),
                    currentDVD.getDirectorName(),
                    currentDVD.getStudio(),
                    currentDVD.getUser_rating()
            );
            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
    }

    public void displayDisplayADVDBanner() {
        io.print("=== Display a DVD ===");
    }

    public String getDVDChoice() {
        return io.readString("Please enter the DVD title.");
    }

    public void displayDVD(DVD dvd) {
        if (dvd != null) {
            // if dvd is found
            String dvdInfo = String.format("#%s : %s %d %s %s %s",
                    dvd.getTitle(),
                    dvd.getReleaseDate().toString(),
                    dvd.getMPAA_rating(),
                    dvd.getDirectorName(),
                    dvd.getStudio(),
                    dvd.getUser_rating()
            );
            io.print(dvdInfo);
        } else {
            io.print("No such dvd.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayRemoveDVDBanner () {
        io.print("=== Remove DVD ===");
    }

    public void displayRemoveResult(DVD dvdRecord) {
        if(dvdRecord != null){
            io.print("DVD successfully removed.");
        }else{
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    // For Editing
    public void displayEditDVDBanner() {
        io.print("=== Edit DVD ===");
    }

    public int chooseEditFeature() {
        io.print("Which would you like to edit?\n" +
                "1. Title\n" +
                "2. Release Date\n" +
                "3. MPAA Rating\n" +
                "4. Director's name\n" +
                "5. Studio\n" +
                "6. User rating\n");

        return io.readInt("Select a number",1,6);

    }

    public String giveEditValue(){
        return io.readString("Type in the new value");
    }

    public void editDVDnoExist(){
        io.print("The given DVD does not exist\n");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
