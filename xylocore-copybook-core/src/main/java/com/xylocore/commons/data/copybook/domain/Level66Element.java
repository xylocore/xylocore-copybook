package com.xylocore.commons.data.copybook.domain;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class Level66Element
    extends
        DataElement
{
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @param       aName
     */
    public Level66Element( String aName )
    {
        super( aName, 66 );
    }
    
    
    protected void acceptVisit( Visitor aVisitor )
    {
        aVisitor.visitLevel66Element( this );
        
        super.acceptVisit( aVisitor );
    }
    
    
    
    protected void acceptLeave( Visitor aVisitor )
    {
        super.acceptLeave( aVisitor );
        
        aVisitor.leaveLevel66Element( this );
    }
}
