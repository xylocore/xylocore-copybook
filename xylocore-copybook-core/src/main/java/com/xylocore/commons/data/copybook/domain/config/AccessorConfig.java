package com.xylocore.commons.data.copybook.domain.config;

import java.util.Map;

import com.xylocore.commons.data.copybook.runtime.DataType;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public abstract class AccessorConfig
    implements
        ConfigEntityDescribable,
        ConfigVisitable
{
    //
    // Members
    //
    
    
    private DataType    dataType;
    private boolean     isDefault;
    
    
    
    
    //
    // Class implementation
    //


    /**
     * FILLIN
     */
    public AccessorConfig()
    {
        this( null );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDataType
     */
    public AccessorConfig( DataType aDataType )
    {
        dataType = aDataType;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public DataType getDataType()
    {
        return dataType;
    }
    
    
    /**
     * FILLIN
     *
     * @return
     */
    public boolean isDefault()
    {
        return isDefault;
    }
    
    
    /**
     * FILLIN
     *
     * @param       aIsDefault
     */
    public void setDefault( boolean aIsDefault )
    {
        isDefault = aIsDefault;
    }
    
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return ConfigEntityDescriber.simpleDescribe( this );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aVisitor
     */
    protected void acceptVisit( ConfigVisitor aVisitor )
    {
        assert aVisitor != null;
        
        aVisitor.visitAccessor( this );
    }


    /**
     * FILLIN
     * 
     * @param       aVisitor
     */
    protected void acceptChildren( ConfigVisitor aVisitor )
    {
        assert aVisitor != null;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aVisitor
     */
    protected void acceptLeave( ConfigVisitor aVisitor )
    {
        assert aVisitor != null;
        
        aVisitor.leaveAccessor( this );
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
        aLabelValueMap.put( "DataType"        , ( dataType != null ) ? dataType.toString() : null );
        aLabelValueMap.put( "IsDefault"       , isDefault ? "true" : "false"                      );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigEntityDescribable#buildDescribableCollections(java.util.Map)
     */
    public void buildDescribableCollections( Map<String, Object> aCollectionsMap )
    {
    }



    
    //
    // ConfigVisitable interface implementation
    //
    

    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitable#accept(com.xylocore.commons.data.copybook.domain.config.ConfigVisitor)
     */
    public void accept( ConfigVisitor aVisitor )
    {
        assert aVisitor != null;
        
        acceptVisit   ( aVisitor );
        acceptChildren( aVisitor );
        acceptLeave   ( aVisitor );
    }
}
