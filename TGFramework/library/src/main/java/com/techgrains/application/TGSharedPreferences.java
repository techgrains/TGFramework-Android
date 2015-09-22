package com.techgrains.application;

import android.content.SharedPreferences;

import com.techgrains.util.TGAndroidUtil;

import java.io.IOException;

/**
 * Wrapper class of android.content.SharedPreferences interface
 * @see SharedPreferences
 */
public class TGSharedPreferences {

    /**
     * Instance reference of SharedPreferences for given name and mode (MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITEABLE, MODE_MULTI_PROCESS)
     * @param name String
     * @param mode int
     * @return SharedPreferences
     * @see SharedPreferences
     */
    public static SharedPreferences instance(String name, int mode) {
        return TGApplication.getContext().getSharedPreferences(name, mode);
    }

    /**
     * Instance reference of SharedPreferences for package name with default mode as MODE_PRIVATE which is private to current app only.
     * @return SharedPreferences
     * @see SharedPreferences
     */
    public static SharedPreferences instance() {
        return TGApplication.getContext().getSharedPreferences(TGApplication.getContext().getPackageName(), TGApplication.MODE_PRIVATE);
    }

    /**
     * Editor of the SharedPreference created by default instance()
     * @return SharedPreferences
     * @see android.content.SharedPreferences.Editor
     */
    public static SharedPreferences.Editor editor() {
        return instance().edit();
    }

    /**
     * Editor of the SharedPreference created by give name and mode (MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITEABLE, MODE_MULTI_PROCESS)
     * @param name String
     * @param mode int
     * @return SharedPreferences
     * @see SharedPreferences
     */
    public static SharedPreferences.Editor editor(String name, int mode) {
        return instance(name, mode).edit();
    }

    /**
     * Put Object tied with provided key in default Shared Preference
     * @param key String
     * @param object Object
     * @throws IOException If unable to access Stream
     */
    public static void putObject(String key, Object object) throws IOException {
        putObject(key, object, editor());
    }

    /**
     * Put Object tied with provided key in specified Shared Preference
     * @param key String
     * @param object Object
     * @param name String
     * @param mode int
     * @throws IOException If unable to access Stream
     */
    public static void putObject(String key, Object object, String name, int mode) throws IOException {
        putObject(key, object, editor(name, mode));
    }

    private static void putObject(String key, Object object, SharedPreferences.Editor editor) throws IOException {
        if(object!=null)
            editor.putString(key, TGAndroidUtil.serialize(object)).commit();
    }

    /**
     * Get Object based on key from default Shared Preference
     * @param key String
     * @throws IOException If unable to access Stream
     * @throws ClassNotFoundException If unable to find class
     * @return Object
     */
    public static Object getObject(String key) throws IOException, ClassNotFoundException {
        return getObject(key, instance());
    }

    /**
     * Get Object based on key from specified Shared Preference
     * @param key String
     * @param name String
     * @param mode int
     * @throws IOException If unable to access Stream
     * @throws ClassNotFoundException If unable to find class
     * @return Object
     */
    public static Object getObject(String key, String name, int mode) throws IOException, ClassNotFoundException {
        return getObject(key, instance(name, mode));
    }

    private static Object getObject(String key, SharedPreferences sharedPreferences) throws IOException, ClassNotFoundException {
        String serialized = sharedPreferences.getString(key, null);
        return TGAndroidUtil.deserialize(serialized);
    }
}
