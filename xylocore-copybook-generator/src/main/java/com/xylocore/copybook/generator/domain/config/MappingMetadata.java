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
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

@XmlAccessorType( XmlAccessType.PROPERTY     )
@XmlType        ( propOrder = { "elements" } )
public class MappingMetadata
    implements
        ConfigEntityDescribable,
        ConfigVisitable
{
    //
    // Members
    //
    
    
    private List<ElementConfig> elements = new ArrayList<>();
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    @XmlElementRef
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
        elements.clear();
        
        if ( aElements != null )
        {
            elements.addAll( aElements );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     */
    public void addElement( ElementConfig aElement )
    {
        assert aElement != null;
        
        elements.add( aElement );
    }
    
    
    @Override
    public String toString()
    {
        return ConfigEntityDescriber.simpleDescribe( this );
    }
    
    
    

    //
    // ConfigEntityDescribable interface implementation
    //
    
    
    @Override
    public void buildDescribableLabelValuePairs( Map<String,String> aLabelValueMap )
    {
    }


    @Override
    public void buildDescribableCollections( Map<String, Object> aCollectionsMap )
    {
        aCollectionsMap.put( "Elements", elements );
    }



    
    //
    // ConfigVisitable interface implementation
    //
    

    @Override
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
