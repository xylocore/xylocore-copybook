package com.xylocore.commons.data.copybook.domain.config;

import java.util.Map;

import com.xylocore.commons.data.copybook.runtime.DataUsageCategory;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class ConstantNullEquivalentConfig
    extends
        NullEquivalentConfig
{
    //
    // Members
    //
    
    
    private DataUsageCategory   dataUsageCategory;
    private String              value;
    
    
    
    
    //
    // Class implementation
    //
    

    /**
     * Default constructor.
     */
    public ConstantNullEquivalentConfig()
    {
        super( "ConstantNullEquivalent" );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public DataUsageCategory getDataUsageCategory()
    {
        return dataUsageCategory;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDataUsageCategory
     */
    public void setDataUsageCategory( DataUsageCategory aDataUsageCategory )
    {
        dataUsageCategory = aDataUsageCategory;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public String getValue()
    {
        return value;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aValue
     */
    public void setValue( String aValue )
    {
        value = aValue;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aVisitor
     */
    protected void acceptVisit( ConfigVisitor aVisitor )
    {
        assert aVisitor != null;
        
        aVisitor.visitConstantNullEquivalent( this );
        
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
        
        aVisitor.leaveConstantNullEquivalent( this );
    }
    
    
    

    //
    // ConfigEntityDescribable interface implementation
    //
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigEntityDescribable#buildDescribableLabelValuePairs(java.util.Map)
     */
    public void buildDescribableLabelValuePairs( Map<String,String> aLabelValueMap )
    {
        aLabelValueMap.put( "DataUsageCategory", dataUsageCategory.name() );
        aLabelValueMap.put( "Value"            , value                    );
    }
}
