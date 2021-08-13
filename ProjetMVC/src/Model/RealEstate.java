/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Estate;
import java.io.IOException;
import java.sql.Blob;

/**
 *
 * @author eliot
 */
public class RealEstate extends Estate {

    public RealEstate(int id, String location, String adress, int price, int area, String description, int nbrooms, int nbbthrooms, Blob image1, Blob image2, Blob image3, int factor) throws IOException {
        super(id, location, adress, price, area, description, nbrooms, nbbthrooms, image1, image2, image3, factor);
    }

}
