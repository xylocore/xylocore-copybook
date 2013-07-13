package com.xylocore.commons.data.copybook.parser;

import java.util.Set;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public abstract class ElementFilter
{
    //
    // Members
    //
    
    
    private boolean inclusive;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @param       aInclusive
     */
    public ElementFilter( boolean aInclusive )
    {
        inclusive = aInclusive;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public boolean isInclusive()
    {
        return inclusive;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aSource
     * @param       aResults
     */
    public abstract void filter( Set<String>   aSource,
                                 Set<String>   aResults );
}
