package com.xylocore.commons.data.copybook.domain;

import java.math.BigDecimal;


/**
 * FILLIN
 *
 * @author      Eric R. Medley
 */

public class NumericValue
    extends
        Value
{
    //
    // Members
    //
    
    
    private BigDecimal value;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @param       aValue
     */
    public NumericValue( BigDecimal aValue )
    {
        value = aValue;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public BigDecimal getValue()
    {
        return value;
    }
    
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return value.toString();
    }
    
    
    
    
    //
    // Comparable interface implementation
    //
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.Value#compareTo(java.lang.Object)
     */
    public int compareTo( NumericValue aRhs )
    {
        int myCmp = super.compareTo( aRhs );
        if ( myCmp == 0 )
        {
            myCmp = value.compareTo( aRhs.value );
        }
        
        return myCmp;
    }
}
