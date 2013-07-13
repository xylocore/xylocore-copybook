package com.xylocore.commons.data.copybook.domain;

import java.util.Collections;
import java.util.List;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class Level88Element
    extends
        DataElement
{
    //
    // Members
    //
    
    
    private List<ValueRange> valueRanges;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @param       aName
     * @param       aValueRanges
     */
    public Level88Element( String             aName,
                           List<ValueRange>   aValueRanges )
    {
        super( aName, 88 );
        
        valueRanges = ( aValueRanges != null ) ? aValueRanges : Collections.<ValueRange>emptyList();
    }
    

    /**
     * FILLIN
     *
     * @return
     */
    public List<ValueRange> getValueRanges()
    {
        return valueRanges;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.DataElement#acceptVisit(com.xylocore.commons.data.copybook.domain.Visitor)
     */
    protected void acceptVisit( Visitor aVisitor )
    {
        aVisitor.visitLevel88Element( this );
        
        super.acceptVisit( aVisitor );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.DataElement#acceptLeave(com.xylocore.commons.data.copybook.domain.Visitor)
     */
    protected void acceptLeave( Visitor aVisitor )
    {
        super.acceptLeave( aVisitor );
        
        aVisitor.leaveLevel88Element( this );
    }
}
