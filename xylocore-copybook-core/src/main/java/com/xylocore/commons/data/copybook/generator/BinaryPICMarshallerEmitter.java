package com.xylocore.commons.data.copybook.generator;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class BinaryPICMarshallerEmitter
    extends
        AbstractNumericPICMarshallerEmitter
{
    //
    // Members
    //
    
    
    private static final BinaryPICMarshallerEmitter instance = new BinaryPICMarshallerEmitter();
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * Private constructor as per the singleton pattern.
     */
    private BinaryPICMarshallerEmitter()
    {
        super( "Binary" );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public static BinaryPICMarshallerEmitter getInstance()
    {
        return instance;
    }
}
