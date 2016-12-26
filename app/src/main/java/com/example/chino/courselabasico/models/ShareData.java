package com.example.chino.courselabasico.models;

import java.security.Key;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by FAMILY on 26/12/2016.
 */

public class ShareData
{
    private static HashMap<String, Object> _data;

    public static HashMap<String, Object> data()
    {
        if(_data == null) _data = new HashMap<>();

        return  _data;
    }

    public static void put(String key, Object value)
    {
        _data = data();

        _data.put( key, value );

    }

    public static Object get(String key)
    {
        _data = data();

        return _data.get( key );
    }
}
