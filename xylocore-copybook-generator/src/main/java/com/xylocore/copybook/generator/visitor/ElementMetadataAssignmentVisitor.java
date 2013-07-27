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


package com.xylocore.copybook.generator.visitor;

import java.util.List;

import com.xylocore.copybook.generator.domain.Copybook;
import com.xylocore.copybook.generator.domain.Element;
import com.xylocore.copybook.generator.domain.ElementFilter;
import com.xylocore.copybook.generator.domain.config.ConfigVisitor;
import com.xylocore.copybook.generator.domain.config.DataElementConfig;
import com.xylocore.copybook.generator.domain.config.Environment;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

class ElementMetadataAssignmentVisitor
    extends
        ConfigVisitor
{
    //
    // Members
    //
    
    
    private Copybook copybook;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @param       aEnvironment
     * @param       aCopybook
     */
    public void assign( Environment   aEnvironment,
                        Copybook      aCopybook     )
    {
        assert aEnvironment != null;
        assert aCopybook    != null;
        
        copybook = aCopybook;
        
        aEnvironment.accept( this );
    }
    
    
    @Override
    public void visitDataElement( DataElementConfig aDataElement )
    {
        FilterCollectionVisitor myFilterCollectionVisitor = new FilterCollectionVisitor();
        List<ElementFilter>     myFilters                 = myFilterCollectionVisitor.collectFilters( aDataElement );
        List<Element>           myMatches                 = copybook.getElementDictionary().getMatchingElements( myFilters );

        for ( Element myElement : myMatches )
        {
            myElement.addMetadata( aDataElement );
        }
    }
}
