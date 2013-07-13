package com.xylocore.commons.data.copybook.domain.config;

import com.xylocore.commons.data.copybook.runtime.DataType;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class LongAccessorConfig
    extends
        AccessorConfig
{
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     */
    public LongAccessorConfig()
    {
        super( DataType.Long );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aVisitor
     */
    protected void acceptVisit( ConfigVisitor aVisitor )
    {
        assert aVisitor != null;
        
        aVisitor.visitLongAccessor( this );
        
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
        
        aVisitor.leaveLongAccessor( this );
    }
}
