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

public class GroupElementConfig
    extends
        ElementConfig
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
    
    
    /**
     * FILLIN
     * 
     * @param       aVisitor
     */
    protected void acceptVisit( ConfigVisitor aVisitor )
    {
        assert aVisitor != null;
        
        aVisitor.visitGroupElement( this );
        
        super.acceptVisit( aVisitor );
    }


    /**
     * FILLIN
     * 
     * @param       aVisitor
     */
    protected void acceptChildren( ConfigVisitor aVisitor )
    {
        assert aVisitor != null;
        
        super.acceptChildren( aVisitor );
        
        for ( ElementConfig myElement : elements )
        {
            myElement.accept( aVisitor );
        }
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
        
        aVisitor.leaveGroupElement( this );
    }
    
    
    

    //
    // ConfigEntityDescribable interface implementation
    //
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigEntityDescribable#buildDescribableCollections(java.util.Map)
     */
    public void buildDescribableCollections( Map<String, Object> aCollectionsMap )
    {
        super.buildDescribableCollections( aCollectionsMap );
        
        aCollectionsMap.put( "Elements", elements );
    }
}
