/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author eliot
 */
interface ChartConstInterface {

    final String[] typesoflocations = {"City", "Town", "Suburbs", "Country", "Sea side", "Mountain"};
    final String[] typesofproperties = {"Single family detached house", "House", "Appartment", "Commercial Estate", "Industrial Estate", "land", "Agricol Land", "Building Land", "Cottage", "Castle", "Mansion", "Mas", "Ranch", "Farm", "Stud", "Barge", "Bastide", "Windmill", "Barn"};
    final String[] typesofproximities = {"Far", "Intermediate", "Close", "Very close"};
    final String[] minrooms = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};
    final String[] maxrooms = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};
    final String[] typesrenovation = {"No renovation", "Very little renovation", "Some renovation", "Important renovation", "Complete renovation"};
    final String[] propertystate = {"Brand new", "Recent", "Old", "Very old"};

}
