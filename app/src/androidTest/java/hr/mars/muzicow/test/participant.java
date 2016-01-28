
package hr.mars.muzicow.test;

import hr.mars.muzicow.activities.MainActivity;

import com.robotium.solo.*;

import android.test.ActivityInstrumentationTestCase2;


public class participant extends ActivityInstrumentationTestCase2<MainActivity> {
    private Solo solo;

    public participant() {
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

        //Click on Participant
        solo.clickOnView(solo.getView(hr.mars.muzicow.R.id.spinner));
        //Wait for spinner to open
        solo.waitForDialogToOpen(5000);
        //Sleep for 1366 milliseconds
        solo.sleep(1366);
        //Click on Artist
        solo.clickOnView(solo.getView(android.R.id.text1, 0));
        //Sleep for 2242 milliseconds
        solo.sleep(2242);
        //Click on Log in with Twitter
        solo.clickOnView(solo.getView(hr.mars.muzicow.R.id.twitter));


    }
}
