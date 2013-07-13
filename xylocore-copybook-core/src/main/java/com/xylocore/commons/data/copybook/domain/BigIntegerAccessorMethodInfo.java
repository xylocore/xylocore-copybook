package com.xylocore.commons.data.copybook.domain;

import com.xylocore.commons.data.copybook.runtime.DataType;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class BigIntegerAccessorMethodInfo
    extends
        AccessorMethodInfo
{
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     */
    public BigIntegerAccessorMethodInfo()
    {
        super( DataType.BigInteger );
    }
    

    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.AccessorMethodInfo#acceptVisit(com.xylocore.commons.data.copybook.domain.Visitor)
     */
    protected void acceptVisit( Visitor aVisitor )
    {
        assert aVisitor != null;
        
        aVisitor.visitBigIntegerAccessorMethodInfo( this );
        
        super.acceptVisit( aVisitor );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.AccessorMethodInfo#acceptLeave(com.xylocore.commons.data.copybook.domain.Visitor)
     */
    protected void acceptLeave( Visitor aVisitor )
    {
        assert aVisitor != null;
        
        super.acceptLeave( aVisitor );
        
        aVisitor.leaveBigIntegerAccessorMethodInfo( this );
    }
}
