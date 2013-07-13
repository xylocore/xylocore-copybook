package com.xylocore.commons.data.copybook.domain.config;

import java.util.Map;

import com.xylocore.commons.data.copybook.runtime.DataType;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class BigDecimalAccessorConfig
    extends
        AccessorConfig
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
    public BigDecimalAccessorConfig()
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
    
    
    /**
     * FILLIN
     * 
     * @param       aVisitor
     */
    protected void acceptVisit( ConfigVisitor aVisitor )
    {
        assert aVisitor != null;
        
        aVisitor.visitBigDecimalAccessor( this );
        
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
        
        aVisitor.leaveBigDecimalAccessor( this );
    }
    

    
    
    //
    // ConfigEntityDescribable interface implementation
    //
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.AccessorConfig#buildDescribableLabelValuePairs(java.util.Map)
     */
    public void buildDescribableLabelValuePairs( Map<String,String> aLabelValueMap )
    {
        super.buildDescribableLabelValuePairs( aLabelValueMap );
        
        aLabelValueMap.put( "Scale", Integer.toString( scale ) );
    }
}
