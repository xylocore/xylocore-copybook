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


package com.xylocore.copybook.generator.parser;

import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class WildcardElementFilter
    extends
        ElementFilter
{
    //
    // Members
    //
    
    
    private String pattern;
    private String compiledPattern;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @param       aInclusive
     */
    public WildcardElementFilter( String    aPattern,
                                  boolean   aInclusive )
    {
        super( aInclusive );
        
        assert StringUtils.isNotBlank( aPattern );

        pattern = aPattern;
        
        compilePattern();
    }
    
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return pattern;
    }
    
    
    /**
     * FILLIN
     */
    private void compilePattern()
    {
        String myPattern = pattern;
        
        if ( StringUtils.indexOfAny( myPattern, "*?/" ) == -1 )
        {
            myPattern = "**/" + myPattern;
        }
        else if ( myPattern.endsWith( "/" ) )
        {
            myPattern += "**";
        }
        
        int myIndex = 0;
        while ( (myIndex = myPattern.indexOf( '*', myIndex )) != -1 )
        {
            String myRemainder = myPattern.substring( myIndex+1 );
            if ( myRemainder.startsWith( "*" ) )
            {
                if
                (
                    (
                        myIndex                       != 0   &&
                        myPattern.charAt( myIndex-1 ) != '/'
                    ) ||
                    (
                        myIndex+2 < myPattern.length()       &&
                        myPattern.charAt( myIndex+2 ) != '/'
                    )
                )
                {
                    // TODO: invalid pattern - better exception
                    throw new RuntimeException( "invalid element filter pattern: " + pattern );
                }
                
                int myLast = StringUtils.indexOfAnyBut( myRemainder, "*" );
                myRemainder = myRemainder.substring( ( myLast != -1 ) ? myLast : myRemainder.length() );
                myPattern = myPattern.substring( 0, myIndex ) + "\t" + myRemainder;
            }

            myIndex++;
        }
        
        compiledPattern = myPattern;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.parser.ElementFilter#filter(java.util.Set)
     */
    public void filter( Set<String>   aSource,
                        Set<String>   aResults )
    {
        if ( isInclusive() )
        {
            for ( String myName : aSource )
            {
                if ( match( myName ) )
                {
                    aResults.add( myName );
                }
            }
        }
        else
        {
            for ( Iterator<String> myIterator = aResults.iterator() ; myIterator.hasNext() ; )
            {
                String myName  = myIterator.next();

                if ( match( myName ) )
                {
                    myIterator.remove();
                }
            }
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aName
     * 
     * @return
     */
    private boolean match( String aName )
    {
        int   myPatternLength  = compiledPattern.length();
        int[] myLastResults    = new int[myPatternLength+1];
        int[] myCurrentResults = new int[myPatternLength+1];

        // Initialize the DP buffers
        for ( int i = 1, ci = myLastResults.length ; i < ci ; i++ )
        {
            myLastResults[i] = 0;
        }
        myLastResults[0]    = 1;
        myCurrentResults[0] = 0;

        for ( int myElementPathCharIndex = 0, myElementPathLength = aName.length() ; myElementPathCharIndex < myElementPathLength ; myElementPathCharIndex++ )
        {
            // Get the current element path character
            char myElementPathChar = aName.charAt( myElementPathCharIndex );

            // Process each character in the pattern
            for ( int i = 0 ; i < myPatternLength ; i++ )
            {
                char myPatternChar = compiledPattern.charAt( i );
                if ( myPatternChar == '\t' )
                {
                    myCurrentResults[i+1] = ( myLastResults[i] == 1 || myLastResults[i+1] == 1 ) ? 1 : 0;
                }
                else if ( myPatternChar == '*' )
                {
                    myCurrentResults[i+1] = ( myElementPathChar != '/' && ( myLastResults[i] == 1 || myLastResults[i+1] == 1 ) ) ? 1 : 0;
                }
                else if ( myPatternChar == '?'  )
                {
                    myCurrentResults[i+1] = ( myElementPathChar != '/' ) ? myLastResults[i] : 0;
                }
                else if ( myElementPathChar == myPatternChar )
                {
                    myCurrentResults[i+1] = myLastResults[i];
                }
                else
                {
                    myCurrentResults[i+1] = 0;
                }
            }

            // Switch to the other dp result buffer
            int[] myTmpArray = myLastResults;
            myLastResults     = myCurrentResults;
            myCurrentResults  = myTmpArray;
        }

        return ( myLastResults[myPatternLength] == 1 );
    }
}
