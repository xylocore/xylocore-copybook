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

import com.xylocore.copybook.generator.domain.Element;
import com.xylocore.copybook.generator.domain.ElementType;
import com.xylocore.copybook.generator.domain.Visitor;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class DetermineElementTypeVisitor
    extends
        Visitor
{
    //
    // Class implementation
    //
    
    
    @Override
    public boolean shouldVisitChildren( Element aParent )
    {
        return true;
    }

    
    @Override
    public void visitElement( Element aElement )
    {
        assert aElement != null;
        
        Element     myParentElement = aElement.getParent();
        ElementType myElementType;
        
        if ( aElement.getFirstChild() == null )
        {
            myElementType = ElementType.ElementaryItem;
        }
        else if
        (
            aElement.isNationalGroupUsage() ||
            (
                myParentElement                  != null                          &&
                myParentElement.getElementType() == ElementType.NationalGroupItem
            )
        )
        {
            myElementType = ElementType.NationalGroupItem;
        }
        else
        {
            myElementType = ElementType.AlphanumericGroupItem;
        }
        
        aElement.setElementType( myElementType );
    }
}
