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

import java.util.ArrayList;
import java.util.List;

import com.xylocore.copybook.generator.domain.Copybook;
import com.xylocore.copybook.generator.domain.DataElement;
import com.xylocore.copybook.generator.domain.Element;
import com.xylocore.copybook.generator.domain.Visitor;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

class ElementDictionaryBuilderVisitor
    extends
        Visitor
{
    //
    // Nested classes
    //
    

    /**
     * FILLIN
     */
    private static class StackEntry
    {
        //
        // Members
        //
        
        private Element element;
        private String  elementPath;
        
        
        //
        // Class implementation
        //
        
        /**
         * FILLIN
         * 
         * @param       aElement
         * @param       aElementPath
         */
        public StackEntry( Element   aElement,
                           String    aElementPath )
        {
            element     = aElement;
            elementPath = aElementPath;
        }
        
        /**
         * FILLIN
         * 
         * @return
         */
        public Element getElement()
        {
            return element;
        }
        
        
        /**
         * FILLIN
         * 
         * @return
         */
        public String getElementPath()
        {
            return elementPath;
        }
    }
    
    
    
    
    //
    // Members
    //
    
    
    private List<StackEntry>    elementStack    = new ArrayList<>();
    private Copybook            copybook;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @param       aCopybook
     */
    public void build( Copybook aCopybook )
    {
        copybook = aCopybook;
        
        aCopybook.accept( this );
    }
    
    
    @Override
    public boolean shouldVisitChildren( Element aParent )
    {
        return true;
    }
    
    
    @Override
    public void visitDataElement( DataElement aElement )
    {
        String myElementName  = aElement.getName();
        int    myElementLevel = aElement.getLevel();
        
        if ( myElementName != null && myElementLevel != 66 && myElementLevel != 88 )
        {
            StackEntry myPreviousStackEntry = ( ! elementStack.isEmpty() ) ? elementStack.get( elementStack.size()-1 ) : null;
            String     myElementPath;
            
            if ( myPreviousStackEntry != null )
            {
                myElementPath = myPreviousStackEntry.getElementPath() + "/" + myElementName; 
            }
            else
            {
                myElementPath = myElementName;
            }
            
            copybook.getElementDictionary().addElementPathMapping( myElementPath, aElement );
            
            StackEntry myStackEntry = new StackEntry( aElement, myElementPath );
            elementStack.add( myStackEntry );
        }
    }
    
    
    @Override
    public void leaveDataElement( DataElement aElement )
    {
        if ( ! elementStack.isEmpty() )
        {
            int        myLastEntryIndex = elementStack.size()-1;
            StackEntry myStackEntry     = elementStack.get( myLastEntryIndex );
            
            if ( myStackEntry.getElement() == aElement )
            {
                elementStack.remove( myLastEntryIndex );
            }
        }
    }
}
