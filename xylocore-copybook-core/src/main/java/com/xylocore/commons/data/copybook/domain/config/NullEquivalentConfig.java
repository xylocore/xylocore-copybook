package com.xylocore.commons.data.copybook.domain.config;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public abstract class NullEquivalentConfig
    implements
        ConfigEntityDescribable,
        ConfigVisitable
{
    //
    // Members
    //
    
    
    private String name;
    
    
    
    
    //
    // Class implementation
    //
    

    /**
     * Default constructor.
     */
    public NullEquivalentConfig()
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aName
     */
    public NullEquivalentConfig( String aName )
    {
        assert StringUtils.isNotBlank( aName );
        
        name = aName;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public String getName()
    {
        return name;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aVisitor
     */
    protected void acceptVisit( ConfigVisitor aVisitor )
    {
        assert aVisitor != null;
        
        aVisitor.visitNullEquivalent( this );
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
        
        aVisitor.leaveNullEquivalent( this );
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
