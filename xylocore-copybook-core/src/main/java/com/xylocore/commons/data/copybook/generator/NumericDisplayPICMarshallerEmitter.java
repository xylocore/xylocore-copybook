package com.xylocore.commons.data.copybook.generator;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class NumericDisplayPICMarshallerEmitter
    extends
        AbstractNumericDisplayPICMarshallerEmitter
{
    //
    // Members
    //
    
    
    private static final NumericDisplayPICMarshallerEmitter instance = new NumericDisplayPICMarshallerEmitter();
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * Private constructor as per the singleton pattern.
     */
    private NumericDisplayPICMarshallerEmitter()
    {
        super( "NumericDisplay" );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public static NumericDisplayPICMarshallerEmitter getInstance()
    {
        return instance;
    }
}
