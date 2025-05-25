package org.wanderers;

import java.util.ArrayList;

public interface GeneralService  {
    
    public void save(ArrayList<Object>  object); //save related object to DataStorage 
    
    public void load(ArrayList<Object> object); //load related object to class to DataStorage
    

}
