package com.xylocore.commons.data.copybook.domain.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class MappingMetadata
    implements
        ConfigEntityDescribable,
        ConfigVisitable
{
    //
    // Members
    //
    
    
    private List<ElementConfig> elements = Collections.<ElementConfig>emptyList();
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public List<ElementConfig> getElements()
    {
        return elements;
    }
    

    /**
     * FILLIN
     * 
     * @param       aElements
     */
    public void setElements( List<ElementConfig> aElements )
    {
        elements =
                ( aElements != null && ! aElements.isEmpty() )
                        ? new ArrayList<ElementConfig>( aElements )
                        : Collections.<ElementConfig>emptyList();
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     */
    public void addElement( ElementConfig aElement )
    {
        assert aElement != null;
        
        if ( elements == Collections.<ElementConfig>emptyList() )
        {
            elements = new ArrayList<ElementConfig>();
        }
        
        elements.add( aElement );
    }
    
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return ConfigEntityDescriber.simpleDescribe( this );
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
        aCollectionsMap.put( "Elements", elements );
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
        
        aVisitor.visitMappingMetadata( this );

        for ( ElementConfig myElement : elements )
        {
            myElement.accept( aVisitor );
        }
        
        aVisitor.leaveMappingMetadata( this );
    }
}
