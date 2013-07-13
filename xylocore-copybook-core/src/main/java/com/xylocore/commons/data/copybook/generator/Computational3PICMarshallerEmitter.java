package com.xylocore.commons.data.copybook.generator;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class Computational3PICMarshallerEmitter
    extends
        AbstractNumericPICMarshallerEmitter
{
    //
    // Members
    //
    
    
    private static final Computational3PICMarshallerEmitter instance = new Computational3PICMarshallerEmitter();
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * Private constructor as per the singleton pattern.
     */
    private Computational3PICMarshallerEmitter()
    {
        super( "Comp3" );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public static Computational3PICMarshallerEmitter getInstance()
    {
        return instance;
    }
}
