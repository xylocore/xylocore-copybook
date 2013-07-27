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


package com.xylocore.copybook.runtime;


/**
 * FILLIN
 *
 * @author      Eric R. Medley
 */

public class ConstantCharacterString
    implements
        Comparable<ConstantCharacterString>,
        CharSequence
{
    //
    // Members
    //
    
    
    private char    constantChar;
    private int     length;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     *
     * @param       aConstantChar
     * @param       aLength
     */
    public ConstantCharacterString( char   aConstantChar,
                                    int    aLength        )
    {
        if ( aLength < 0 )
        {
            throw new IllegalArgumentException( "length cannot be less than zero" );
        }
        
        constantChar = aConstantChar;
        length       = aLength;
    }
    
    
    /**
     * FILLIN
     *
     * @return
     */
    public char getConstantChar()
    {
        return constantChar;
    }
    
    
    
    
    //
    // CharSequence interface implementation
    //
    
    
    @Override
    public char charAt( int aIndex )
    {
        if ( aIndex < 0 || aIndex >= length )
        {
            throw new IndexOutOfBoundsException();
        }
        
        return constantChar;
    }
    
    
    @Override
    public int length()
    {
        return length;
    }
    
    
    @Override
    public CharSequence subSequence( int   aStart,
                                     int   aEnd    )
    {
        if ( aStart < 0 || aEnd < 0 || aEnd > length || aStart > aEnd )
        {
            throw new IndexOutOfBoundsException();
        }
        
        return toString().subSequence( aStart, aEnd );
    }
    

    @Override
    public String toString()
    {
        StringBuffer myBuffer = new StringBuffer();
        for ( int i = 0, ci = length ; i < ci ; i++ )
        {
            myBuffer.append( constantChar );
        }
        
        return myBuffer.toString();
    }
    
    
    
    
    //
    // Comparable interface implementation
    //
    

    @Override
    public int compareTo( ConstantCharacterString aRhs )
    {
        int myCmp = ( constantChar < aRhs.constantChar ) ? -1 : (( constantChar > aRhs.constantChar ) ? 1 : 0);
        if ( myCmp == 0 )
        {
            myCmp = ( length < aRhs.length ) ? -1 : (( length > aRhs.length ) ? 1 : 0);
        }
        
        return myCmp;
    }
}
