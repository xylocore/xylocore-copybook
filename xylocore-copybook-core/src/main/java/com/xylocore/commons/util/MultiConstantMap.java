package com.xylocore.commons.util;

import java.util.HashMap;
import java.util.Map;


/**
 * FILLIN
 *
 * @author      Eric R. Medley
 */

public class MultiConstantMap<K,V>
    implements
        ConstantMap<K,V>
{
    //
    // Members
    //
    
    
    private Map<K,V> mappings;
    
    
    
    
    //
    // Class implementation
    //
    

    /**
     * FILLIN
     *
     * @param       aMappings
     */
    public MultiConstantMap( Map<K,V> aMappings )
    {
        mappings = new HashMap<K,V>( aMappings );
    }
    
    
    
    
    //
    // ConstantMap interface implementation
    //
    
    
    public V get( K aKey )
    {
        return mappings.get( aKey );
    }
}
