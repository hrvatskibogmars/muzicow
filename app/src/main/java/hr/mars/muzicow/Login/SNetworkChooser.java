package hr.mars.muzicow.Login;

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
