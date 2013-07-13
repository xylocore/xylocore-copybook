package com.xylocore.commons.data.copybook.domain.config;

/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class BlankNullEquivalentConfig
    extends
        NullEquivalentConfig
{
    //
    // Class implementation
    //
    
    
    /**
     * Default constructor.
     */
    public BlankNullEquivalentConfig()
    {
        super( "BlankNullEquivalent" );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aVisitor
     */
    protected void acceptVisit( ConfigVisitor aVisitor )
    {
        assert aVisitor != null;
        
        aVisitor.visitBlankNullEquivalent( this );
        
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
        
        aVisitor.leaveBlankNullEquivalent( this );
    }
}
