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


package com.xylocore.copybook.generator.parser.pic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.xylocore.copybook.generator.domain.PICSlice;
import com.xylocore.copybook.generator.domain.PICSymbolType;


/**
 * FILLIN
 *
 * @author      Eric R. Medley
 */

public class PICLexer
{
    //
    // Members
    //
    
    
    private static final int    EOF_CHAR        = '\uffff';
    
    private String              source;
    private int                 sourceOffset;
    
    
    
    
    //
    // Class implementation
    //


    /**
     * FILLIN
     * 
     * @param       aSource
     * 
     * @return
     * 
     * @throws      ICException
     */
    public List<PICSlice> parse( String aSource )
            throws PICException
    {
        List<PICSlice>     mySlices     = new ArrayList<PICSlice>();
        Set<PICSymbolType> myTypesFound = new HashSet<>();
        char               myChar;
        
        source       = aSource;
        sourceOffset = 0;

        ScanLoop:
        while ( true )
        {
            boolean  myAllowParens = false;
            PICSlice mySlice       = null;
            
            myChar = peek(1);
            switch ( myChar )
            {
                case 'A':  case 'a':
                {
                    mySlice       = new PICSlice( PICSymbolType.Alphabetic );
                    myAllowParens = true;

                    myTypesFound.add( PICSymbolType.Alphabetic );
                    
                    pop();
                    
                    break;
                }
                case 'B':  case 'b':
                {
                    mySlice       = new PICSlice( PICSymbolType.Blank );
                    myAllowParens = true;

                    myTypesFound.add( PICSymbolType.Blank );
                    
                    pop();
                    
                    break;
                }
                case 'G':  case 'g':
                {
                    mySlice       = new PICSlice( PICSymbolType.DbcsCharacter );
                    myAllowParens = true;

                    myTypesFound.add( PICSymbolType.DbcsCharacter );
                    
                    pop();
                    
                    break;
                }
                case 'N':  case 'n':
                {
                    mySlice       = new PICSlice( PICSymbolType.NationalCharacter );
                    myAllowParens = true;

                    myTypesFound.add( PICSymbolType.NationalCharacter );
                    
                    pop();
                    
                    break;
                }
                case 'P':  case 'p':
                {
                    mySlice       = new PICSlice( PICSymbolType.DecimalScalingPosition );
                    myAllowParens = true;

                    myTypesFound.add( PICSymbolType.DecimalScalingPosition );
                    
                    pop();
                    
                    break;
                }
                case 'X':  case 'x':
                {
                    mySlice       = new PICSlice( PICSymbolType.Alphanumeric );
                    myAllowParens = true;

                    myTypesFound.add( PICSymbolType.Alphanumeric );
                    
                    pop();
                    
                    break;
                }
                case 'Z':  case 'z':
                {
                    mySlice       = new PICSlice( PICSymbolType.LeadingBlankZero );
                    myAllowParens = true;

                    myTypesFound.add( PICSymbolType.LeadingBlankZero );
                    
                    pop();
                    
                    break;
                }
                case '0':
                {
                    mySlice       = new PICSlice( PICSymbolType.Zero );
                    myAllowParens = true;

                    myTypesFound.add( PICSymbolType.Zero );
                    
                    pop();
                    
                    break;
                }
                case '9':
                {
                    mySlice       = new PICSlice( PICSymbolType.Numeric );
                    myAllowParens = true;

                    myTypesFound.add( PICSymbolType.Numeric );
                    
                    pop();
                    
                    break;
                }
                case '/':
                {
                    mySlice       = new PICSlice( PICSymbolType.Slash );
                    myAllowParens = true;

                    myTypesFound.add( PICSymbolType.Slash );
                    
                    pop();
                    
                    break;
                }
                case ',':
                {
                    mySlice       = new PICSlice( PICSymbolType.Comma );
                    myAllowParens = true;

                    myTypesFound.add( PICSymbolType.Comma );
                    
                    pop();
                    
                    break;
                }
                case '+':
                {
                    mySlice       = new PICSlice( PICSymbolType.Plus );
                    myAllowParens = true;
                    
                    myTypesFound.add( PICSymbolType.Plus );
                    
                    pop();
                    
                    break;
                }
                case '-':
                {
                    mySlice       = new PICSlice( PICSymbolType.Minus );
                    myAllowParens = true;

                    myTypesFound.add( PICSymbolType.Minus );
                    
                    pop();
                    
                    break;
                }
                case '*':
                {
                    mySlice       = new PICSlice( PICSymbolType.Asterisk );
                    myAllowParens = true;

                    myTypesFound.add( PICSymbolType.Asterisk );
                    
                    pop();
                    
                    break;
                }
                case '$':
                {
                    mySlice       = new PICSlice( PICSymbolType.Currency );
                    myAllowParens = true;
                    
                    myTypesFound.add( PICSymbolType.Currency );
                    
                    pop();
                    
                    break;
                }
                case 'E':  case 'e':
                {
                    if ( myTypesFound.contains( PICSymbolType.Exponent ) )
                    {
                        throw new PICSymbolOnlyAllowedOnceException( "E", sourceOffset );
                    }
                    
                    myTypesFound.add( PICSymbolType.Exponent );
                    
                    mySlice = new PICSlice( PICSymbolType.Exponent );

                    pop();
                    
                    break;
                }
                case 'S':  case 's':
                {
                    if ( myTypesFound.contains( PICSymbolType.Sign ) )
                    {
                        throw new PICSymbolOnlyAllowedOnceException( "S", sourceOffset );
                    }
                    
                    myTypesFound.add( PICSymbolType.Sign );
                    
                    mySlice = new PICSlice( PICSymbolType.Sign );
                    
                    pop();

                    break;
                }
                case 'V':  case 'v':
                {
                    if ( myTypesFound.contains( PICSymbolType.AssumedDecimalPoint ) )
                    {
                        throw new PICSymbolOnlyAllowedOnceException( "V", sourceOffset );
                    }
                    
                    myTypesFound.add( PICSymbolType.AssumedDecimalPoint );
    
                    mySlice = new PICSlice( PICSymbolType.AssumedDecimalPoint );
                    
                    pop();
                    
                    break;
                }
                case 'C':  case 'c':
                {
                    myChar = peek(2);
                    if ( myChar != 'R' && myChar != 'r' )
                    {
                        throw new IncompletePICSymbolException( "CR", sourceOffset );
                    }
                    
                    if ( myTypesFound.contains( PICSymbolType.Credit ) )
                    {
                        throw new PICSymbolOnlyAllowedOnceException( "CR", sourceOffset );
                    }
                    
                    if ( myTypesFound.contains( PICSymbolType.Debit ) )
                    {
                        throw new MutuallyExclusivePICSymbolsUsedException( "CR", "DB", sourceOffset );
                    }
                    
                    myTypesFound.add( PICSymbolType.Credit );
                    
                    mySlice = new PICSlice( PICSymbolType.Credit );
                    
                    pop();
                    pop();
    
                    break;
                }
                case 'D':  case 'd':
                {
                    myChar = peek(2);
                    if ( myChar != 'B' && myChar != 'b' )
                    {
                        throw new IncompletePICSymbolException( "DB", sourceOffset );
                    }
                    
                    if ( myTypesFound.contains( PICSymbolType.Debit ) )
                    {
                        throw new PICSymbolOnlyAllowedOnceException( "DB", sourceOffset );
                    }
                    
                    if ( myTypesFound.contains( PICSymbolType.Credit ) )
                    {
                        throw new MutuallyExclusivePICSymbolsUsedException( "DB", "CR", sourceOffset );
                    }
                    
                    myTypesFound.add( PICSymbolType.Debit );

                    mySlice = new PICSlice( PICSymbolType.Debit );
                    
                    pop();
                    pop();
    
                    break;
                }
                case '.':
                {
                    if ( myTypesFound.contains( PICSymbolType.Period ) )
                    {
                        throw new PICSymbolOnlyAllowedOnceException( ".", sourceOffset );
                    }
                    
                    myTypesFound.add( PICSymbolType.Period );
                    
                    mySlice = new PICSlice( PICSymbolType.Period );
                    
                    pop();
                    
                    break;
                }
                default:
                {
                    if ( myChar == EOF_CHAR )
                    {
                        break ScanLoop;
                    }

                    throw new IllegalPICCharacterException( myChar, sourceOffset );
                }
            }
            
            if ( myAllowParens )
            {
                if ( peek(1) == '(' )
                {
                    mySlice.setCount( matchParens() );
                }
            }

            if ( mySlices.size() != 0 )
            {
                PICSlice myLastSlice = mySlices.get( mySlices.size()-1 );
                if ( myLastSlice.getType() == mySlice.getType() )
                {
                    myLastSlice.setCount( myLastSlice.getCount() + mySlice.getCount() );
                    mySlice = null;
                }
            }
            
            if ( mySlice != null )
            {
                mySlices.add( mySlice );
            }
        }

        return mySlices;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     * 
     * @exception   PICException
     */
    private int matchParens()
            throws PICException
    {
        char myChar = pop();
        assert myChar == '(';
        
        int myValue  = 0;
        int myDigits = 0;
        
        myChar = peek(1);
        while ( Character.isDigit( myChar ) )
        {
            myDigits++;
            
            myValue = myValue*10 + Character.digit( myChar, 10 );
            
            pop();
            myChar = peek(1);
        }
        
        if ( myChar != ')' )
        {
            throw new IllegalPICOccurranceCountCharacterException( myChar, sourceOffset );
        }
        
        if ( myDigits == 0 )
        {
            throw new MissingPICOccurranceCountException( sourceOffset );
        }

        pop();
        
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aIndex
     * 
     * @return
     */
    private char peek( int aIndex )
    {
        char myChar;
        
        int myOffset = sourceOffset + aIndex - 1;
        if ( myOffset < source.length() )
        {
            myChar = source.charAt( myOffset );
        }
        else
        {
            myChar = EOF_CHAR;
        }
        
        return myChar;
    }
    

    /**
     * FILLIN
     * 
     * @return
     */
    private char pop()
    {
        char myChar = peek( 1 );
        if ( sourceOffset < source.length() )
        {
            sourceOffset++;
        }
        
        return myChar;
    }
}
