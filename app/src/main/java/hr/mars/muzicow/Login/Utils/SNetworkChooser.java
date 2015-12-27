package hr.mars.muzicow.Login.Utils;

import hr.mars.muzicow.Login.Services.SNetwork;

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
