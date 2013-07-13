package com.xylocore.commons.util;


/**
 * FILLIN
 *
 * @author      Eric R. Medley
 */

public class SingleConstantMap<K,V>
    implements
        ConstantMap<K,V>
{
    //
    // Members
    //
    
    
    private V value;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     *
     * @param       aValue
     */
    public SingleConstantMap( V aValue )
    {
        value = aValue;
    }
    
    
    
    
    //
    // ConstantMap interface implementation
    //
    
    
    public V get( K aKey )
    {
        return value;
    }
}
