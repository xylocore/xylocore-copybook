package com.xylocore.commons.data.copybook.generator;

import java.util.HashMap;
import java.util.Map;

import com.xylocore.commons.data.copybook.runtime.ConstantValue;
import com.xylocore.commons.data.copybook.runtime.StringConstantValue;


/**
 * FILLIN
 *
 * @author      Eric R. Medley
 */

public class ConstantValueEmitterFactory
{
    //
    // Members
    //
    
    
    private static final ConstantValueEmitterFactory    instance            = new ConstantValueEmitterFactory();
    
    private Map<String,StringConstantValueEmitter>      emitterMappings;
    private ConstantValueEmitter                        defaultEmitter;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * Private constructor as per singleton pattern.
     */
    private ConstantValueEmitterFactory()
    {
        emitterMappings = new HashMap<String,StringConstantValueEmitter>();
        defaultEmitter  = new DefaultConstantValueEmitter();
        
        emitterMappings.put( StringConstantValue.class.getName(), new StringConstantValueEmitter() );
    }
    
    
    /**
     * FILLIN
     *
     * @return
     */
    public static ConstantValueEmitterFactory getInstance()
    {
        return instance;
    }
    
    
    /**
     * FILLIN
     *
     * @param       aValue
     * 
     * @return
     */
    public ConstantValueEmitter newInstance( ConstantValue aValue )
    {
        assert aValue != null;
        
        ConstantValueEmitter myEmitter = emitterMappings.get( aValue.getClass().getName() );
        if ( myEmitter == null )
        {
            myEmitter = defaultEmitter;
        }
        
        return myEmitter;
    }
}
