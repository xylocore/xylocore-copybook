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
    
    
    private List<ElementConfig> elements = new ArrayList<ElementConfig>();
    
    
    
    
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
    protected void acceptVisit( ConfigVisitor aVisitor )
    {
        assert aVisitor != null;
        
        aVisitor.visitGroupElement( this );
        
        super.acceptVisit( aVisitor );
    }


    @Override
    protected void acceptChildren( ConfigVisitor aVisitor )
    {
        assert aVisitor != null;
        
        super.acceptChildren( aVisitor );
        
        for ( ElementConfig myElement : elements )
        {
            myElement.accept( aVisitor );
        }
    }
    
    
    @Override
    protected void acceptLeave( ConfigVisitor aVisitor )
    {
        assert aVisitor != null;
        
        super.acceptLeave( aVisitor );
        
        aVisitor.leaveGroupElement( this );
    }
    
    
    

    //
    // ConfigEntityDescribable interface implementation
    //
    
    
    @Override
    public void buildDescribableCollections( Map<String,Object> aCollectionsMap )
    {
        super.buildDescribableCollections( aCollectionsMap );
        
        aCollectionsMap.put( "Elements", elements );
    }
}
