package com.xylocore.commons.data.copybook.domain;


/**
 * FILLIN
 *
 * @author      Eric R. Medley
 */

public class ValueRange
{
    //
    // Members
    //
    
    
    private Value value1;
    private Value value2;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     *
     * @param       aValue1
     */
    public ValueRange( Value aValue1 )
    {
        this( aValue1, aValue1 );
    }
    
    
    /**
     * FILLIN
     *
     * @param       aValue1
     * @param       aValue2
     */
    public ValueRange( Value   aValue1,
                       Value   aValue2  )
    {
        value1 = aValue1;
        value2 = aValue2;
    }
    
    
    /**
     * FILLIN
     *
     * @return
     */
    public boolean isSingleValue()
    {
        return ( value1 == value2 );
    }
    
    
    /**
     * FILLIN
     *
     * @return
     */
    public Value getValue1()
    {
        return value1;
    }
    
    
    /**
     * FILLIN
     *
     * @return
     */
    public Value getValue2()
    {
        return value2;
    }
}
