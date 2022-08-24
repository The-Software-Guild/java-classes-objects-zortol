package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;

import java.io.IOException;
import java.util.List;

public interface DVDdao {
    DVD addDVD(String dvdName, DVD dvd) throws DVDdaoException;

    List<DVD> getAllDVDs() throws DVDdaoException;

    DVD getDVD(String dvdName) throws DVDdaoException;

    DVD removeDVD(String dvdName) throws DVDdaoException;

    DVD editDVD(String dvdName,int featureChosen,String editedString) throws DVDdaoException;
}
