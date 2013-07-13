package com.xylocore.commons.data.copybook.generator;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class NumericNationalPICMarshallerEmitter
    extends
        AbstractNumericDisplayPICMarshallerEmitter
{
    //
    // Members
    //
    
    
    private static final NumericNationalPICMarshallerEmitter instance = new NumericNationalPICMarshallerEmitter();
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * Private constructor as per the singleton pattern.
     */
    private NumericNationalPICMarshallerEmitter()
    {
        super( "NumericNational" );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public static NumericNationalPICMarshallerEmitter getInstance()
    {
        return instance;
    }
}
