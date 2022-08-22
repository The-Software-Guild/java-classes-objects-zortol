package com.sg.DVDlibrary.dao;

import com.sg.DVDlibrary.dto.DVD;

import java.io.IOException;
import java.util.List;

public interface DVDdao {
    DVD addDVD(String dvdName, DVD dvd) throws IOException;

    List<DVD> getAllDVDs();

    DVD getDVD(String dvdName);

    DVD removeDVD(String dvdName) throws IOException;

    DVD editDVD(String dvdName,int featureChosen,String editedString) throws IOException;
}
