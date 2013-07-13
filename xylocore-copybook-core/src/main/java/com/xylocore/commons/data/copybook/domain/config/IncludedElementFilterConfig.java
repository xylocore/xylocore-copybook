package com.xylocore.commons.data.copybook.domain.config;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class IncludedElementFilterConfig
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
        
        aVisitor.visitIncludedElementFilter( this );
        
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
        
        aVisitor.leaveIncludedElementFilter( this );
    }
}
