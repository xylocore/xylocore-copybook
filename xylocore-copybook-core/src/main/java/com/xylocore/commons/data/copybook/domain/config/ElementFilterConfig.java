package com.xylocore.commons.data.copybook.domain.config;

import java.util.Map;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class ElementFilterConfig
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
     * @param       aName
     */
    public void setName( String aName )
    {
        name = aName;
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
        
        aVisitor.visitElementFilter( this );
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
        
        aVisitor.leaveElementFilter( this );
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
        aLabelValueMap.put( "Name", name );
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
