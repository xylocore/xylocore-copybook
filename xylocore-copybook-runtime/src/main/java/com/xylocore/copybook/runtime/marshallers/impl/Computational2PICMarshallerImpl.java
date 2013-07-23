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


package com.xylocore.copybook.runtime.marshallers.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Map;

import com.xylocore.copybook.runtime.ConstantValue;
import com.xylocore.copybook.runtime.CopybookContext;
import com.xylocore.copybook.runtime.marshallers.Computational2PICMarshaller;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class Computational2PICMarshallerImpl
    extends
        AbstractPICMarshaller
    implements
        Computational2PICMarshaller
{
    //
    // Members
    //


    private static final Computational2PICMarshallerImpl instance = new Computational2PICMarshallerImpl();




    //
    // Class implementation
    //


    /**
     * Private constructor as per the singleton pattern.
     */
    private Computational2PICMarshallerImpl()
    {
    }


    /**
     * FILLIN
     * 
     * @return
     */
    public static Computational2PICMarshallerImpl getInstance()
    {
        return instance;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public boolean isValid( CopybookContext   aContext,
                            int               aOffset   )
    {
        throw new UnsupportedOperationException( "net yet implemented" );
    }

    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public byte decodeAsByte( CopybookContext   aContext,
                              int               aOffset   )
    {
        return (byte) decodeAsDouble( aContext, aOffset );
    }
    

    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeAsByte( CopybookContext   aContext,
                              int               aOffset,
                              byte              aValue    )
    {
        encodeAsDouble( aContext, aOffset, aValue );
    }
    

    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public char decodeAsChar( CopybookContext   aContext,
                              int               aOffset   )
    {
        return (char) decodeAsDouble( aContext, aOffset );
    }
    

    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeAsChar( CopybookContext   aContext,
                              int               aOffset,
                              char              aValue    )
    {
        encodeAsDouble( aContext, aOffset, aValue );
    }

    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public short decodeAsShort( CopybookContext   aContext,
                                int               aOffset   )
    {
        return (short) decodeAsDouble( aContext, aOffset );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeAsShort( CopybookContext   aContext,
                               int               aOffset,
                               short             aValue    )
    {
        encodeAsDouble( aContext, aOffset, aValue );
    }

    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public int decodeAsInteger( CopybookContext   aContext,
                                int               aOffset   )
    {
        return (int) decodeAsDouble( aContext, aOffset );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeAsInteger( CopybookContext   aContext,
                                 int               aOffset,
                                 int               aValue    )
    {
        encodeAsDouble( aContext, aOffset, aValue );
    }

    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public long decodeAsLong( CopybookContext   aContext,
                              int               aOffset   )
    {
        return (long) decodeAsDouble( aContext, aOffset );
    }
    
 
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeAsLong( CopybookContext   aContext,
                              int               aOffset,
                              long              aValue    )
    {
        encodeAsDouble( aContext, aOffset, aValue );
    }

    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public float decodeAsFloat( CopybookContext   aContext,
                                int               aOffset   )
    {
        return (float) decodeAsDouble( aContext, aOffset );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeAsFloat( CopybookContext   aContext,
                               int               aOffset,
                               float             aValue    )
    {
        encodeAsDouble( aContext, aOffset, aValue );
    }

    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public double decodeAsDouble( CopybookContext   aContext,
                                  int               aOffset   )
    {
        // TODO: implement
        throw new UnsupportedOperationException( "not yet implemented" );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeAsDouble( CopybookContext   aContext,
                                int               aOffset,
                                double            aValue    )
    {
        // TODO: implement
        throw new UnsupportedOperationException( "not yet implemented" );
    }

    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public BigInteger decodeAsBigInteger( CopybookContext   aContext,
                                          int               aOffset   )
    {
        // TODO: implement
        throw new UnsupportedOperationException( "not yet implemented" );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeAsBigInteger( CopybookContext   aContext,
                                    int               aOffset,
                                    BigInteger        aValue    )
    {
        // TODO: implement
        throw new UnsupportedOperationException( "not yet implemented" );
    }

    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public BigDecimal decodeAsBigDecimal( CopybookContext   aContext,
                                          int               aOffset   )
    {
        // TODO: implement
        throw new UnsupportedOperationException( "not yet implemented" );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeAsBigDecimal( CopybookContext   aContext,
                                    int               aOffset,
                                    BigDecimal        aValue    )
    {
        // TODO: implement
        throw new UnsupportedOperationException( "not yet implemented" );
    }

    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public String decodeAsString( CopybookContext   aContext,
                                  int               aOffset   )
    {
        return Double.toString( decodeAsDouble( aContext, aOffset ) );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeAsString( CopybookContext   aContext,
                                int               aOffset,
                                String            aValue    )
    {
        encodeAsDouble( aContext, aOffset, Double.parseDouble( aValue ) );
    }

    
    /**
     * FILLIN
     *
     * @param       aContext
     * @param       aOffset
     * @param       aConditionName
     * @param       aConditionNameValueMappings
     * @return
     */
    public boolean isConditionNameValid( CopybookContext               aContext,                   
                                         int                           aOffset,                    
                                         String                        aConditionName,             
                                         Map<String,ConstantValue[]>   aConditionNameValueMappings )
    {
        boolean myValid = false;
        
        double myFloatValue = decodeAsDouble( aContext, aOffset );
        
        if ( ! aContext.isError() )
        {
            Comparable<?> myValue = new Double( myFloatValue );
            
            ConstantValue[] myConstantValues = aConditionNameValueMappings.get( aConditionName );
            if ( myConstantValues != null )
            {
                myValid = ( Arrays.binarySearch( myConstantValues, myValue ) >= 0 );
            }
        }
        
        return myValid;
    }
}
