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


package com.xylocore.copybook.generator.parser.visitor;

import java.util.List;

import com.xylocore.copybook.generator.domain.Copybook;
import com.xylocore.copybook.generator.domain.Element;
import com.xylocore.copybook.generator.domain.config.ConfigVisitor;
import com.xylocore.copybook.generator.domain.config.DataElementConfig;
import com.xylocore.copybook.generator.domain.config.Environment;
import com.xylocore.copybook.generator.domain.config.GroupElementConfig;
import com.xylocore.copybook.generator.parser.ElementFilter;


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
    
    
    private FilterCollectionVisitor     filterCollectionVisitor     = new FilterCollectionVisitor();
    private Copybook                    copybook;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @param       aCopybook
     */
    public ElementMetadataAssignmentVisitor( Copybook aCopybook )
    {
        assert aCopybook != null;
        
        copybook = aCopybook;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aEnvironment
     */
    public void assign( Environment aEnvironment )
    {
        assert aEnvironment != null;
        
        aEnvironment.accept( this );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#visitGroupElement(com.xylocore.commons.data.copybook.domain.config.GroupElementConfig)
     */
    public void visitGroupElement( GroupElementConfig aGroupElement )
    {
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#leaveGroupElement(com.xylocore.commons.data.copybook.domain.config.GroupElementConfig)
     */
    public void leaveGroupElement( GroupElementConfig aGroupElement )
    {
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#visitDataElement(com.xylocore.commons.data.copybook.domain.config.DataElementConfig)
     */
    public void visitDataElement( DataElementConfig aDataElement )
    {
        ElementFilter[] myFilters = filterCollectionVisitor.collectFilters( aDataElement );
        List<Element>   myMatches = copybook.getElementDictionary().getMatchingElements( myFilters );

        for ( Element myElement : myMatches )
        {
//            if ( myElement.getElementType() == ElementType.ElementaryItem )
            {
                myElement.addMetadata( aDataElement );
            }
        }
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#leaveDataElement(com.xylocore.commons.data.copybook.domain.config.DataElementConfig)
     */
    public void leaveDataElement( DataElementConfig aDataElement )
    {
    }
}
