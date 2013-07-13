package com.xylocore.commons.data.copybook.domain;

import com.xylocore.commons.data.copybook.runtime.DataType;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class BigDecimalAccessorMethodInfo
    extends
        AccessorMethodInfo
{
    //
    // Members
    //
    
    
    private int scale;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     */
    public BigDecimalAccessorMethodInfo()
    {
        super( DataType.BigDecimal );
        
        scale = 0;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public int getScale()
    {
        return scale;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aScale
     */
    public void setScale( int aScale )
    {
        scale = aScale;
    }
    

    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.AccessorMethodInfo#acceptVisit(com.xylocore.commons.data.copybook.domain.Visitor)
     */
    protected void acceptVisit( Visitor aVisitor )
    {
        assert aVisitor != null;
        
        aVisitor.visitBigDecimalAccessorMethodInfo( this );
        
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
        
        aVisitor.leaveBigDecimalAccessorMethodInfo( this );
    }
}
