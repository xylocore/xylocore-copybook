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


package com.xylocore.commons.data.copybook.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.xylocore.commons.data.copybook.parser.ElementFilter;


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
    
    
    private Map<String,List<Element>>   elementNameMappings     = new HashMap<>();
    private Map<String,Element>         elementPathMappings     = new HashMap<>();
    
    
    
    
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
     * FILLIN
     * 
     * @param       aElementPath
     * @param       aElement
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
