package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DVDdaoFileImpl implements DVDdao {

    private Map<String, DVD> dvds = new HashMap<>();
    public static final String DVDFILE = "dvd.txt";
    public static final String DELIMITER = "::";



    @Override
    public DVD addDVD(String dvdName, DVD dvd) throws DVDdaoException{
        this.loadFromFile();
        DVD thisDVD = dvds.put(dvdName,dvd);
        this.writeToFile();
        return thisDVD;
    }

    @Override
    public List<DVD> getAllDVDs() throws DVDdaoException {
        this.loadFromFile();
        return new ArrayList<DVD>(dvds.values());
    }

    @Override
    public DVD getDVD(String dvdName) throws DVDdaoException {
        this.loadFromFile();
        return this.dvds.get(dvdName);
    }

    @Override
    public DVD removeDVD(String dvdName) throws DVDdaoException{
        this.loadFromFile();
        DVD dvd = this.dvds.remove(dvdName);
        this.writeToFile();
        return dvd;
    }

    @Override
    public DVD editDVD(String dvdName,int featureChosen,String editedString) throws DVDdaoException {
        /**
         * 1. Title 2. Release date
         * 3. MPAA rating 4. Director name
         * 5. Studio 6. User Rating
         */
        this.loadFromFile();
        DVD editDVD = this.dvds.get(dvdName);
        switch(featureChosen){
            case 1: // title
                editDVD.setTitle(editedString);
                break;
            case 2: // Release date
                editDVD.setReleaseDate(LocalDate.parse(editedString));
                break;
            case 3: // MPAA rating
                editDVD.setMPAA_rating(Integer.parseInt(editedString));
                break;
            case 4: // director's name
                editDVD.setDirectorName(editedString);
                break;
            case 5: // studio
                editDVD.setStudio(editedString);
                break;
            case 6: // user rating
                editDVD.setUser_rating(editedString);
                break;
            default:
                System.out.println("Default here");

        }
        this.writeToFile();
        return editDVD;
    }

    /**
     * The method here turns text data from a file source into a DVD object
     * Text from the file source should look like
     * "Title::Release_Date::MPAA_rating::Director_name::Studio::User_rating"
     * @param DVD
     * @return
     */
    private DVD unmarshallingDVD(String DVD){
        String[] DVDtoken = DVD.split(DELIMITER);

        DVD dvd = new DVD();

        // Assign the string array tokens to their respective fields
        String title = DVDtoken[0];
        LocalDate release_Date = LocalDate.parse(DVDtoken[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int MPAA_rating = Integer.parseInt(DVDtoken[2]);
        String director_name = DVDtoken[3];
        String studio = DVDtoken[4];
        String user_rating = DVDtoken[5];

        // Set the fields to the object
        dvd.setTitle(title);
        dvd.setReleaseDate(release_Date);
        dvd.setMPAA_rating(MPAA_rating);
        dvd.setDirectorName(director_name);
        dvd.setStudio(studio);
        dvd.setUser_rating(user_rating);

        return dvd;
    }

    /**
     * It loads from a file in order to add objects into the hashmap
     * This method calls the private unmarshallingDVD() to convert the lines into objects
     */
    private void loadFromFile() throws DVDdaoException{
        Scanner scan;
        try{
            scan = new Scanner(new BufferedReader(new FileReader(DVDFILE)));


        } catch (FileNotFoundException e) {
            throw new DVDdaoException("Could not load data from the file");
        }

        String currentLine;
        DVD currentDVD;

        while(scan.hasNextLine()){
            currentLine = scan.nextLine();

            // To prevent empty lines to be skipped so that the program does not crash
            if(currentLine.isBlank()){
                continue;
            }
            currentDVD = this.unmarshallingDVD(currentLine);
            this.dvds.put(currentDVD.getTitle(),currentDVD);

        }

        scan.close();
    }

    /**
     * Convert DVD object into string
     * String to be written in this format:
     * "Title::Release_Date::MPAA_rating::Director_name::Studio::User_rating"
     * @param dvd
     * @return
     */
    private String marshallDVD(DVD dvd){
        StringBuilder builder = new StringBuilder();

        builder.append(dvd.getTitle());
        builder.append(DELIMITER);
        builder.append(dvd.getReleaseDate().toString());
        builder.append(DELIMITER);
        builder.append(String.valueOf(dvd.getMPAA_rating()));
        builder.append(DELIMITER);
        builder.append(dvd.getDirectorName());
        builder.append(DELIMITER);
        builder.append(dvd.getStudio());
        builder.append(DELIMITER);
        builder.append(dvd.getUser_rating());

        return builder.toString();

    }

    /**
     * Writes all the dvd to the DVDFILE
     */
    private void writeToFile() throws DVDdaoException {

        PrintWriter out;

        try{
            out = new PrintWriter(new FileWriter(DVDFILE));
        }catch(IOException e){
            throw new DVDdaoException("Could not write to file");
        }

        String dvdText;

        List<DVD> dvdList = this.getAllDVDs();
        for(DVD dvd : dvdList){
            // first convert dvd into string
            dvdText = this.marshallDVD(dvd);
            // write to file
            out.println(dvdText);

            out.flush();

        }

        out.close();
    }
}
