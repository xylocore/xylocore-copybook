package com.xylocore.commons.data.copybook.runtime.marshallers.impl;

import com.xylocore.commons.data.copybook.runtime.marshallers.PICMarshaller;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public abstract class AbstractPICMarshaller
    implements
        PICMarshaller
{
    //
    // Comparable interface implementation
    //
    

    /*
     * (non-Javadoc)
     * @see java.lang.Comparable#compareTo(T)
     */
    public int compareTo( PICMarshaller aOpaqueRhs )
    {
        return getClass().getName().compareTo( aOpaqueRhs.getClass().getName() );
    }
}
