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


package com.xylocore.copybook.generator.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;



/**
 * A collection of elements that form a dictionary for a copybook.
 * 
 * @author      Eric R. Medley
 * 
 * @see         Copybook
 */

public class ElementDictionary
{
    //
    // Members
    //
    
    
    private Map<String,List<Element>>   elementNameMappings     = new HashMap<>();
    private Map<String,Element>         elementPathMappings     = new HashMap<>();
    
    
    
    
    //
    // Class implementation
    //
    

    /**
     * Returns <tt>true</tt> if the dictionary contains an element with the
     * specified element name.
     * 
     * @param       aElementName
     *                  The element name of interest.
     * 
     * @return      <tt>true</tt> if the dictionary contains an element with
     *              the specified element name.
     */
    public boolean isElementNameAvailable( String aElementName )
    {
        assert StringUtils.isNotBlank( aElementName );
        
        return elementNameMappings.containsKey( aElementName );
    }
    
    
    /**
     * Returns <tt>true</tt> if the dictionary contains one and only one element with
     * the specified element name.
     * 
     * @param       aElementName
     *                  The element name of interest.
     * 
     * @return      <tt>true</tt> if the dictionary contains one and only one element with
     *              the specified element name.
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
    public List<Element> getMatchingElements( List<ElementFilter> aFilters )
    {
        Set<String> myNames = new HashSet<>();
        if ( aFilters != null )
        {
            for ( ElementFilter myFilter : aFilters )
            {
                myFilter.filter( elementPathMappings.keySet(), myNames );
            }
        }

        List<Element> myMatches = new ArrayList<>();
        for ( String myElementPath : myNames )
        {
            myMatches.add( elementPathMappings.get( myElementPath ) );
        }
        
        return myMatches;
    }
    
    
    /**
     * Adds an element to the dictionary.
     * 
     * @param       aElementPath
     *                  The fully qualified element path within the copybook.
     * @param       aElement
     *                  The element.
     */
    public void addElementPathMapping( String    aElementPath,
                                       Element   aElement      )
    {
        assert StringUtils.isNotBlank( aElementPath );
        assert aElement != null;
        
        elementPathMappings.put( aElementPath, aElement );
        
        String myElementName = aElement.getName();
        
        List<Element> myElementsPerName = elementNameMappings.get( myElementName );
        if ( myElementsPerName == null )
        {
            myElementsPerName = new ArrayList<>();
            elementNameMappings.put( myElementName, myElementsPerName );
        }
        myElementsPerName.add( aElement );
    }
}
