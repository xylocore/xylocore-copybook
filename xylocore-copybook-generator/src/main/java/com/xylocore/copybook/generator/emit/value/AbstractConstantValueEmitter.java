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

import com.xylocore.commons.util.BufferEmitter;
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
        Set<String> mySet = new HashSet<>();
        
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
     * @param       aEmitter
     * @param       aValue
     */
    protected void emitInstantiationParameters( BufferEmitter   aEmitter,
                                                ConstantValue   aValue    )
    {
        assert aEmitter != null;
        assert aValue   != null;
        
        aEmitter.append( ' ' );
        
        if ( aValue.getValue2() == null )
        {
            emitValue( aEmitter, aValue.getValue1() );
        }
        else
        {
            emitValue( aEmitter, aValue.getValue1() );
            aEmitter.append( ", " );
            emitValue( aEmitter, aValue.getValue2() );
        }
        
        aEmitter.append( ' ' );
    }
    
    
    /**
     * FILLIN
     *
     * @param       aEmitter
     * @param       aValue
     */
    protected void emitValue( BufferEmitter   aEmitter,
                              Comparable<?>   aValue    )
    {
        assert aEmitter != null;
        assert aValue   != null;
        
        if ( aValue instanceof String )
        {
            aEmitter.append( "\"" );
            
            appendEscapedString( aEmitter, aValue.toString() );
            
            aEmitter.append( "\"" );
        }
        else
        {
            String myQuoteString = ( isQuoteNeeded( aValue ) ) ? "\"" : "";
            
            aEmitter.append( "new "                      )
                    .append( aValue.getClass().getName() )
                    .append( "( "                        )
                    .append( myQuoteString               )
                    ;
            
            appendEscapedString( aEmitter, aValue.toString() );
            
            aEmitter.append( myQuoteString )
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
     * @param       aEmitter
     * @param       aString
     */
    protected void appendEscapedString( BufferEmitter   aEmitter,
                                        String          aString   )
    {
        assert aEmitter != null;
        assert aString  != null;
        
        for ( int i = 0, ci = aString.length() ; i < ci ; i++ )
        {
            appendEscapedChar( aEmitter, aString.charAt( i ) );
        }
    }
    
    
    /**
     * FILLIN
     *
     * @param       aEmitter
     * @param       aChar
     */
    protected void appendEscapedChar( BufferEmitter   aEmitter,
                                      char            aChar     )
    {
        assert aEmitter != null;

        switch ( aChar )
        {
            case '\b':
                
                aEmitter.append( "\\b" );
                break;
                
            case '\t':
                
                aEmitter.append( "\\t" );
                break;
                
            case '\n':
                
                aEmitter.append( "\\n" );
                break;
                
            case '\f':
                
                aEmitter.append( "\\f" );
                break;
                
            case '\r':
                
                aEmitter.append( "\\r" );
                break;
                
            case '"':
                
                aEmitter.append( "\\\"" );
                break;
                
            case '\'':
                
                aEmitter.append( "\\'" );
                break;
                
            case '\\':
                
                aEmitter.append( "\\\\" );
                break;
                
            default:
                        
                if ( aChar >= ' ' && aChar <= 0x7e )
                {
                    aEmitter.append( aChar );
                }
                else
                {
                    aEmitter.append( "\\u" );
                    FormatHelper.formatNumber( aEmitter.getBuffer(),
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
    
    
    @Override
    public void emit( BufferEmitter   aEmitter,
                      ConstantValue   aValue    )
    {
        assert aEmitter != null;
        assert aValue   != null;
        
        aEmitter.append( "new "                      )
                .append( getConstantValueClassName() )
                .append( "("                         )
                ;

        emitInstantiationParameters( aEmitter, aValue );
        
        aEmitter.append( ")" );
    }
}
