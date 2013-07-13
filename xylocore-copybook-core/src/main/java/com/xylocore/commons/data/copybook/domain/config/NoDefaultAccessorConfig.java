package com.xylocore.commons.data.copybook.domain.config;



/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class NoDefaultAccessorConfig
    extends
        AccessorConfig
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
        
        aVisitor.visitNoDefaultAccessor( this );
        
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
        
        aVisitor.leaveNoDefaultAccessor( this );
    }
}
