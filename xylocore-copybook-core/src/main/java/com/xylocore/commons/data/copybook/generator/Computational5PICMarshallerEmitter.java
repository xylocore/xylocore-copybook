package com.xylocore.commons.data.copybook.generator;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class Computational5PICMarshallerEmitter
    extends
        AbstractNumericPICMarshallerEmitter
{
    //
    // Members
    //
    
    
    private static final Computational5PICMarshallerEmitter instance = new Computational5PICMarshallerEmitter();
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * Private constructor as per the singleton pattern.
     */
    private Computational5PICMarshallerEmitter()
    {
        super( "Comp5" );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public static Computational5PICMarshallerEmitter getInstance()
    {
        return instance;
    }
}
