package com.xylocore.commons.data.copybook.domain.config;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class ExcludedElementFilterConfig
    extends
        ElementFilterConfig
{
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @param       aVisitor
     */
    protected void acceptVisit( ConfigVisitor aVisitor )
    {
        assert aVisitor != null;
        
        aVisitor.visitExcludedElementFilter( this );
        
        super.acceptVisit( aVisitor );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aVisitor
     */
    protected void acceptLeave( ConfigVisitor aVisitor )
    {
        assert aVisitor != null;
        
        super.acceptLeave( aVisitor );
        
        aVisitor.leaveExcludedElementFilter( this );
    }
}
