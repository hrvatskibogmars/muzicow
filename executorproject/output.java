package hr.mars.muzicow.test;

import hr.mars.muzicow.activities.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class bhjs extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;
  	
  	public bhjs() {
		super(MainActivity.class);
  	}

  	public void setUp() throws Exception {
        super.setUp();
		solo = new Solo(getInstrumentation());
		getActivity();
  	}
  
   	@Override
   	public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
  	}
  
	public void testRun() {
        //Wait for activity: 'hr.mars.muzicow.activities.MainActivity'
		solo.waitForActivity(hr.mars.muzicow.activities.MainActivity.class, 2000);
        //Sleep for 22278 milliseconds
		solo.sleep(22278);
        //Click on Log in with Facebook
		solo.clickOnView(solo.getView(hr.mars.muzicow.R.id.login_buttonFB));
        //Wait for activity: 'com.facebook.FacebookActivity'
		assertTrue("com.facebook.FacebookActivity is not found!", solo.waitForActivity(com.facebook.FacebookActivity.class));
	}
}
