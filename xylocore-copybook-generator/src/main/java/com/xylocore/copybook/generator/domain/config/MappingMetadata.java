//
//   Copyright 2013 The Palantir Corporation
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.
//

package com.xylocore.copybook.generator.domain.config;

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
