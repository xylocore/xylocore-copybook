package com.xylocore.commons.data.copybook.parser;

import java.util.ArrayList;
import java.util.List;

import com.xylocore.commons.data.copybook.domain.Copybook;
import com.xylocore.commons.data.copybook.domain.DataElement;
import com.xylocore.commons.data.copybook.domain.Element;
import com.xylocore.commons.data.copybook.domain.Visitor;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

class ElementDictionaryBuilder
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
//        public String getElementPath()
//        {
//            return elementPath;
//        }
    }
    
    
    
    
    //
    // Members
    //
    
    
    private List<StackEntry>    elementStack    = new ArrayList<StackEntry>();
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
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.Visitor#shouldVisitChildren(com.xylocore.commons.data.copybook.domain.Element)
     */
    public boolean shouldVisitChildren( Element aParent )
    {
        return true;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.Visitor#visitDataElement(com.xylocore.commons.data.copybook.domain.DataElement)
     */
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
                myElementPath = myPreviousStackEntry.elementPath + "/" + myElementName; 
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
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.Visitor#leaveDataElement(com.xylocore.commons.data.copybook.domain.DataElement)
     */
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
