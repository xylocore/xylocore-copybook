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


/**
 * FILLIN
 *
 * @author      Eric R. Medley
 */

public class StringValue
    extends
        Value
{
    //
    // Members
    //
    
    
    private String value;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @param       aValue
     */
    public StringValue( String aValue )
    {
        char myQuoteChar = aValue.charAt( aValue.length()-1 );
        assert myQuoteChar == '"' || myQuoteChar == '\'';
        
        int myIndex = aValue.indexOf( myQuoteChar );
        assert myIndex  >= 0;
        
//        String myPrefix = ( myIndex > 0 ) ? aValue.substring( 0, myIndex ) : null;
        
        aValue = aValue.substring( myIndex+1, aValue.length()-1 );
        if ( aValue.indexOf( myQuoteChar ) != -1 )
        {
            StringBuilder myBuffer = new StringBuilder( aValue.length() );
            
            for ( myIndex = 0 ; myIndex < aValue.length() ; myIndex++ )
            {
                char myChar = aValue.charAt( myIndex );
                if ( myChar == myQuoteChar )
                {
                    assert aValue.charAt( myIndex+1 ) == myQuoteChar;
                    myIndex++;
                }
                
                myBuffer.append( myChar );
            }
            
            aValue = myBuffer.toString();
        }
        
        // TODO: process prefix
        
        myIndex = aValue.length();
        while ( myIndex > 0 && aValue.charAt( myIndex-1 ) == ' ' )
        {
            myIndex--;
        }
        
        value = aValue.substring( 0, myIndex );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public String getValue()
    {
        return value;
    }
    
    
    @Override
    public String toString()
    {
        return value;
    }
    
    
    
    
    //
    // Comparable interface implementation
    //
    
    
    @Override
    public int compareTo( Value aOpaqueRhs )
    {
        int myCmp = super.compareTo( aOpaqueRhs );
        if ( myCmp == 0 )
        {
            StringValue myRhs = (StringValue) aOpaqueRhs;
            myCmp = value.compareTo( myRhs.value );
        }
        
        return myCmp;
    }
}
