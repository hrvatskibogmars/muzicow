package hr.mars.muzicow.utils;

import hr.mars.muzicow.services.SNetwork;

/**
 * Created by Emil on 25.12.2015..
 */
public class SNetworkChooser  {
    SNetwork nObject;
    public void setSNetwork(SNetwork nObject){
        this.nObject = nObject;
    }

    public void loginChoice(){

        this.nObject.retSNetData();
    }
}
