package com.quadrolord.ejge.utils;

/**
 * Created by Goorus on 16.09.2016.
 */
abstract public class AbstractAuthService {

    abstract public void auth();

    abstract public void goOffline();

    abstract public void addValueListener(Object owner, String key, final StorageValueListener listener);

    abstract public void removeValueListener(Object owner);

    abstract public void saveValue(String key, Object value);

}
