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


package com.xylocore.copybook.generator.emit.value;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.xylocore.commons.util.FormatHelper;
import com.xylocore.copybook.runtime.ConstantValue;


/**
 * FILLIN
 *
 * @author      Eric R. Medley
 */

public abstract class AbstractConstantValueEmitter
    implements
        ConstantValueEmitter
{
    //
    // Members
    //
    
    
    private static Set<String> quotableValueClasses;
    
    
    
    
    //
    //Static initializer
    //
    
    
    static
    {
        Set<String> mySet = new HashSet<String>();
        
        mySet.add( BigDecimal.class.getName() );
        mySet.add( BigInteger.class.getName() );
        mySet.add( String.class.getName()     );
        
        quotableValueClasses = Collections.unmodifiableSet( mySet );
    }
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     *
     * @return
     */
    protected String getConstantValueClassName()
    {
        return ConstantValue.class.getName();
    }
    

    /**
     * FILLIN
     *
     * @param       aBuffer
     * @param       aValue
     */
    protected void emitInstantiationParameters( StringBuilder   aBuffer,
                                                ConstantValue   aValue   )
    {
        assert aBuffer != null;
        assert aValue  != null;
        
        aBuffer.append( ' ' );
        
        if ( aValue.getValue2() == null )
        {
            emitValue( aBuffer, aValue.getValue1() );
        }
        else
        {
            emitValue( aBuffer, aValue.getValue1() );
            aBuffer.append( ", " );
            emitValue( aBuffer, aValue.getValue2() );
        }
        
        aBuffer.append( ' ' );
    }
    
    
    /**
     * FILLIN
     *
     * @param       aBuffer
     * @param       aValue
     */
    protected void emitValue( StringBuilder   aBuffer,
                              Comparable<?>   aValue   )
    {
        assert aBuffer != null;
        assert aValue  != null;
        
        if ( aValue instanceof String )
        {
            aBuffer.append( "\"" );
            
            appendEscapedString( aBuffer, aValue.toString() );
            
            aBuffer.append( "\"" );
        }
        else
        {
            String myQuoteString = ( isQuoteNeeded( aValue ) ) ? "\"" : "";
            
            aBuffer.append( "new "                      )
                   .append( aValue.getClass().getName() )
                   .append( "( "                        )
                   .append( myQuoteString               )
                   ;
            
            appendEscapedString( aBuffer, aValue.toString() );
            
            aBuffer.append( myQuoteString )
                   .append( " )"          )
                   ;
        }
    }
    
    
    /**
     * FILLIN
     *
     * @param       aValue
     * 
     * @return
     */
    protected boolean isQuoteNeeded( Comparable<?> aValue )
    {
        assert aValue != null;
        
        return quotableValueClasses.contains( aValue.getClass().getName() );
    }
    
    
    /**
     * FILLIN
     *
     * @param       aBuffer
     * @param       aString
     */
    protected void appendEscapedString( StringBuilder   aBuffer,
                                        String          aString  )
    {
        assert aBuffer != null;
        assert aString != null;
        
        for ( int i = 0, ci = aString.length() ; i < ci ; i++ )
        {
            appendEscapedChar( aBuffer, aString.charAt( i ) );
        }
    }
    
    
    /**
     * FILLIN
     *
     * @param       aBuffer
     * @param       aChar
     */
    protected void appendEscapedChar( StringBuilder   aBuffer,
                                      char            aChar    )
    {
        assert aBuffer != null;

        switch ( aChar )
        {
            case '\b':
                
                aBuffer.append( "\\b" );
                break;
                
            case '\t':
                
                aBuffer.append( "\\t" );
                break;
                
            case '\n':
                
                aBuffer.append( "\\n" );
                break;
                
            case '\f':
                
                aBuffer.append( "\\f" );
                break;
                
            case '\r':
                
                aBuffer.append( "\\r" );
                break;
                
            case '"':
                
                aBuffer.append( "\\\"" );
                break;
                
            case '\'':
                
                aBuffer.append( "\\'" );
                break;
                
            case '\\':
                
                aBuffer.append( "\\\\" );
                break;
                
            default:
                        
                if ( aChar >= ' ' && aChar <= 0x7e )
                {
                    aBuffer.append( aChar );
                }
                else
                {
                    aBuffer.append( "\\u" );
                    FormatHelper.formatNumber( aBuffer,
                                               aChar & 0x0000ffff,
                                               4,
                                               true,
                                               FormatHelper.Justify.Right,
                                               16                          );
                }
                break;
        }
    }
    
    
    
    
    //
    // ConstantValueEmitter interface implementation
    //
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.generator.ConstantValueEmitter#emit(com.xylocore.commons.util.XyStringBuffer, com.xylocore.commons.data.copybook.runtime.ConstantValue)
     */
    public void emit( StringBuilder   aBuffer,
                      ConstantValue   aValue   )
    {
        assert aBuffer != null;
        assert aValue  != null;
        
        aBuffer.append( "new "                      )
               .append( getConstantValueClassName() )
               .append( "("                         )
               ;

        emitInstantiationParameters( aBuffer, aValue );
        
        aBuffer.append( ")" );
    }
}
