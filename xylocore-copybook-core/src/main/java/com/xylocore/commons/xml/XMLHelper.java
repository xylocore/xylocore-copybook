/*
 * XMLHelper.java
 *
 * Copyright 2005 The Palantir Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xylocore.commons.xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class XMLHelper
{
    //
    // Members
    //


    public static final String NIL_ATTRIBUTE = "xsi:nil=\"true\"";
    
    private static char[] base64Alphabet = {
                                               'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
                                               'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                                               'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
                                               'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
                                               'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                                               'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
                                               'w', 'x', 'y', 'z', '0', '1', '2', '3',
                                               '4', '5', '6', '7', '8', '9', '+', '/'
                                           };




    //
    // Class implementation
    //


    /**
     * FILLIN
     */
    public static boolean isValidPCData( CharSequence aCharSequence )
    {
        boolean myIsValid = true;

        for ( int i = 0, c = aCharSequence.length() ; myIsValid && i < c ; i++ )
        {
            int myChar = aCharSequence.charAt( i ) & 0x0000ffff;
            myIsValid = isValidPCDataCharacter( myChar );
        }

        return myIsValid;
    }


    /**
     * FILLIN
     */
    public static boolean isValidPCData( InputStream aStream )
            throws java.io.IOException
    {
        boolean myIsValid = true;
        int     myChar;

        while ( myIsValid && (myChar = aStream.read()) != -1 )
        {
            myIsValid = isValidPCDataCharacter( myChar );
        }

        return myIsValid;
    }


    /**
     * FILLIN
     */
    public static boolean isValidPCData( Reader aReader )
            throws java.io.IOException
    {
        boolean myIsValid = true;
        int     myChar;

        while ( myIsValid && (myChar = aReader.read()) != -1 )
        {
            myIsValid = isValidPCDataCharacter( myChar );
        }

        return myIsValid;
    }


    /**
     * FILLIN
     */
    public static boolean isValidPCDataCharacter( int aChar )
    {
        return
        (
            aChar == 0x00000009                            ||
            aChar == 0x0000000a                            ||
            aChar == 0x0000000d                            ||
            ( aChar >= 0x00000020 && aChar <= 0x0000d7ff ) ||
            ( aChar >= 0x0000e000 && aChar <= 0x0000fffd ) ||
            ( aChar >= 0x00010000 && aChar <= 0x0010ffff )
        );
    }


    /**
     * FILLIN
     * 
     * @param       aBuffer
     * @param       aSource
     * 
     * @throws      IOException
     */
    public static void escapePCData( Appendable     aBuffer,
                                     CharSequence   aSource  )
            throws IOException
    {
        for ( int i = 0, c = aSource.length() ; i < c ; i++ )
        {
            escapePCData( aBuffer, aSource.charAt( i ) );
        }
    }


    /**
     * FILLIN
     * 
     * @param       aBuffer
     * @param       aChar
     * 
     * @throws      IOException
     */
    public static void escapePCData( Appendable   aBuffer,
                                     char         aChar    )
            throws IOException
    {
        switch ( aChar )
        {
            case '<':

                aBuffer.append( "&lt;" );
                break;

            case '>':

                aBuffer.append( "&gt;" );
                break;

            case '&':

                aBuffer.append( "&amp;" );
                break;

            case '\'':

                aBuffer.append( "&apos;" );
                break;

            case '"':

                aBuffer.append( "&quot;" );
                break;

            default:

                aBuffer.append( aChar );
                break;
        }
    }


    /**
     * FILLIN
     *
     * @param       aBuffer
     * @param       aSource
     * 
     * @throws      IOException
     */
    public static void base64Encode( Appendable     aBuffer,
                                     CharSequence   aSource  )
            throws IOException
    {
        int myIndex      = 0;
        int myLength     = aSource.length();
        int myGroup      = 0;
        int myLineLength = 0;

        while ( myIndex < myLength )
        {
            char myChar = aSource.charAt( myIndex++ );

            myGroup = (myGroup << 8) | (myChar & 0x000000ff);

            if ( myIndex % 3 == 0 )
            {
                base64EncodeOutput( aBuffer, myGroup, myLineLength, 3 );
                myLineLength = (myLineLength + 3) % 76;
                myGroup      = 0;
            }
        }

        if ( myIndex % 3 != 0 )
        {
            while ( myIndex % 3 != 0 )
            {
                char myChar = ( myIndex < myLength ) ? aSource.charAt( myIndex ) : 0;

                myGroup = (myGroup << 8) | (myChar & 0x000000ff);
                myIndex++;
            }

            base64EncodeOutput( aBuffer, myGroup, myLineLength, 3 - myIndex + myLength );
            myLineLength = (myLineLength + 3) % 76;
        }

        if ( myLineLength % 76 != 0 )
        {
            aBuffer.append( '\n' );
        }
    }


    /**
     * FILLIN
     *
     * @param       aBuffer
     * @param       aGroup
     * @param       aLineLength
     * @param       aSignificantChars
     * 
     * @throws      IOException
     */
    private static void base64EncodeOutput( Appendable   aBuffer,
                                            int          aGroup,
                                            int          aLineLength,
                                            int          aSignificantChars )
            throws IOException
    {
        aSignificantChars++;

        for ( int i = 0 ; i < aSignificantChars ; i++ )
        {
            int myOffset = (aGroup & 0x00fc0000) >> 18;
            aBuffer.append( base64Alphabet[myOffset] );

            aGroup = (aGroup & 0x0003ffff) << 6;

            aLineLength++;
            if ( aLineLength == 76 )
            {
                aBuffer.append( '\n' );
                aLineLength = 0;
            }
        }

        while ( aSignificantChars++ != 4 )
        {
            aBuffer.append( '=' );

            aLineLength++;
            if ( aLineLength == 76 )
            {
                aBuffer.append( '\n' );
                aLineLength = 0;
            }
        }
    }


    /**
     * FILLIN
     *
     * @param       aBuffer
     * @param       aSource
     * 
     * @throws      IOException
     */
    public static void base64Decode( Appendable     aBuffer,
                                     CharSequence   aSource  )
            throws IOException
    {
        int myChunkCount       = 0;
        int myGroup            = 0;
        int mySignificantChars = 0;

        for ( int i = 0, c = aSource.length() ; i < c ; i++ )
        {
            char myChar  = aSource.charAt( i );
            int  myValue = -1;

            if ( Character.isUpperCase( myChar ) )
            {
                myValue = myChar - 'A';
                mySignificantChars++;
            }
            else if ( Character.isLowerCase( myChar ) )
            {
                myValue = myChar - 'a' + 26;
                mySignificantChars++;
            }
            else if ( Character.isDigit( myChar ) )
            {
                myValue = myChar - '0' + 52;
                mySignificantChars++;
            }
            else if ( myChar == '+' )
            {
                myValue = 62;
                mySignificantChars++;
            }
            else if ( myChar == '/' )
            {
                myValue = 63;
                mySignificantChars++;
            }
            else if ( myChar == '=' )
            {
                myValue = 0;
            }

            if ( myValue != -1 )
            {
                myGroup = (myGroup << 6) | myValue;

                myChunkCount++;
                if ( myChunkCount == 4 )
                {
                    while ( --mySignificantChars != 0 )
                    {
                        aBuffer.append( (char) ((myGroup & 0x00ff0000) >> 16) );
                        myGroup = (myGroup << 8) & 0x00ffffff;
                    }
                    
                    myChunkCount = 0;
                }
            }
        }
    }
}
