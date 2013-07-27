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

public class StringConstantValue
    extends
        ConstantValue
{
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     *
     * @param       aValue
     */
    public StringConstantValue( Comparable<?> aValue )
    {
        super( stripTrailingSpaces( (String) aValue ) );
    }
    
    
    /**
     * FILLIN
     *
     * @param       aValue1
     * @param       aValue2
     */
    public StringConstantValue( Comparable<?>   aValue1,
                                Comparable<?>   aValue2  )
    {
        super( stripTrailingSpaces( (String) aValue1 ),
               stripTrailingSpaces( (String) aValue2 )  );
    }
    
    
    /**
     * FILLIN
     *
     * @param       aConstantChar
     * @param       aLength
     */
    public StringConstantValue( char   aConstantChar,
                                int    aLength        )
    {
        super( new ConstantCharacterString( aConstantChar, aLength ) );
    }
    
    
    @Override
    protected int compareValues( Comparable<?>   aValue1,
                                 Comparable<?>   aValue2  )
    {
        assert aValue1 != null;
        assert aValue2 != null;

        CharSequence myString1   = (CharSequence) aValue1;
        CharSequence myString2   = (CharSequence) aValue2;
        int          myCmp       = 0;
        int          myMinLength = Math.min( myString1.length(), myString2.length() );
        
        for ( int i = 0 ; i < myMinLength && myCmp == 0 ; i++ )
        {
            char myChar1 = myString1.charAt( i );
            char myChar2 = myString2.charAt( i );
            
            myCmp = ( myChar1 < myChar2 ) ? -1 : ( ( myChar1 > myChar2 ) ? 1 : 0 );
        }
        
        if ( myCmp == 0 )
        {
            CharSequence myTestString;
            int          myPossibleCmp;
            
            if ( myMinLength == myString1.length() )
            {
                myTestString  = myString2;
                myPossibleCmp = -1;
            }
            else
            {
                myTestString  = myString1;
                myPossibleCmp = 1;
            }
            
            for ( int i = myMinLength, ci = myTestString.length() ; i < ci ; i++ )
            {
                if ( myTestString.charAt( i ) != ' ' )
                {
                    myCmp = myPossibleCmp;
                    break;
                }
            }
        }
        
        return myCmp;
    }
    
    
    /**
     * FILLIN
     *
     * @param       aValue
     * 
     * @return
     */
    private static String stripTrailingSpaces( String aValue )
    {
        if ( aValue != null )
        {
            int myIndex = aValue.length();
            
            while ( myIndex > 0 && aValue.charAt( myIndex-1 ) == ' ' )
            {
                myIndex--;
            }
            
            aValue = aValue.substring( 0, myIndex );
        }
        
        return aValue;
    }
}
