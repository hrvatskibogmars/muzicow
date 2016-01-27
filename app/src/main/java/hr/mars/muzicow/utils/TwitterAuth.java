package hr.mars.muzicow.utils;

import hr.mars.muzicow.services.SocialAuth;
import hr.mars.muzicow.services.SocialnetworkManager;

/**
 * Created by mars on 27/01/16.
 */
public class TwitterAuth implements SocialAuth {

    @Override
    public void setProvider(String provider) {

    }

    @Override
    public String getProvider() {
        return null;
    }

    @Override
    public void setKey(String key) {

    }

    @Override
    public String getKey() {
        return null;
    }

    @Override
    public void setSecret() {

    }

    @Override
    public String getSecret() {
        return null;
    }

    @Override
    public boolean save(Object object_login) {
        return false;
    }

    @Override
    public Object load() {
        return null;
    }

    @Override
    public void setRole(String role) {

    }

    @Override
    public String getRole() {
        return null;
    }

    @Override
    public boolean login() {
        return false;
    }

    @Override
    public boolean logout() {
        return false;
    }

    @Override
    public boolean signup() {
        return false;
    }
}
