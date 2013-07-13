package com.xylocore.commons.data.copybook.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.xylocore.commons.data.copybook.domain.Element;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class ElementDictionary
{
    //
    // Members
    //
    
    
    private Map<String,List<Element>>   elementNameMappings     = new HashMap<String,List<Element>>();
    private Map<String,Element>         elementPathMappings     = new HashMap<String,Element>();
    
    
    
    
    //
    // Class implementation
    //
    

    /**
     * FILLIN
     * 
     * @param       aElementName
     * 
     * @return
     */
    public boolean isElementNameAvailable( String aElementName )
    {
        assert StringUtils.isNotBlank( aElementName );
        
        return elementNameMappings.containsKey( aElementName );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElementName
     * 
     * @return
     */
    public boolean isUniqueElementName( String aElementName )
    {
        assert StringUtils.isNotBlank( aElementName );
        
        List<Element> myElements = elementNameMappings.get( aElementName );
        return ( myElements != null && myElements.size() == 1 );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aPattern
     * 
     * @return
     */
    public List<Element> getMatchingElements( ElementFilter[] aFilters )
    {
        Set<String> myNames = new HashSet<String>();
        if ( aFilters != null )
        {
            for ( ElementFilter myFilter : aFilters )
            {
                myFilter.filter( elementPathMappings.keySet(), myNames );
            }
        }

        List<Element> myMatches = new ArrayList<Element>();
        for ( String myElementPath : myNames )
        {
            myMatches.add( elementPathMappings.get( myElementPath ) );
        }
        
        return myMatches;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElementPath
     * @param       aElement
     */
    void addElementPathMapping( String    aElementPath,
                                Element   aElement      )
    {
        assert StringUtils.isNotBlank( aElementPath );
        assert aElement != null;
        
        elementPathMappings.put( aElementPath, aElement );
        
        String myElementName = aElement.getName();
        
        List<Element> myElementsPerName = elementNameMappings.get( myElementName );
        if ( myElementsPerName == null )
        {
            myElementsPerName = new ArrayList<Element>();
            elementNameMappings.put( myElementName, myElementsPerName );
        }
        myElementsPerName.add( aElement );
    }
}
