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
import com.xylocore.copybook.runtime.marshallers.Computational1PICMarshaller;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class Computational1PICMarshallerImpl
    extends
        AbstractPICMarshaller
    implements
        Computational1PICMarshaller
{
    //
    // Members
    //


    private static final Computational1PICMarshallerImpl instance = new Computational1PICMarshallerImpl();




    //
    // Class implementation
    //


    /**
     * Private constructor as per the singleton pattern.
     */
    private Computational1PICMarshallerImpl()
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public static Computational1PICMarshallerImpl getInstance()
    {
        return instance;
    }

    
    @Override
    public boolean isValid( CopybookContext   aContext,
                            int               aOffset   )
    {
        throw new UnsupportedOperationException( "not yet implemented" );
    }
    

    @Override
    public byte decodeAsByte( CopybookContext   aContext,
                              int               aOffset   )
    {
        return (byte) decodeAsFloat( aContext, aOffset );
    }
    

    @Override
    public void encodeAsByte( CopybookContext   aContext,
                              int               aOffset,
                              byte              aValue    )
    {
        encodeAsFloat( aContext, aOffset, aValue );
    }


    @Override
    public char decodeAsChar( CopybookContext   aContext,
                              int               aOffset   )
    {
        return (char) decodeAsFloat( aContext, aOffset );
    }
    

    @Override
    public void encodeAsChar( CopybookContext   aContext,
                              int               aOffset,
                              char              aValue    )
    {
        encodeAsFloat( aContext, aOffset, aValue );
    }

    
    @Override
    public short decodeAsShort( CopybookContext   aContext,
                                int               aOffset   )
    {
        return (short) decodeAsFloat( aContext, aOffset );
    }
    
    
    @Override
    public void encodeAsShort( CopybookContext   aContext,
                               int               aOffset,
                               short             aValue    )
    {
        encodeAsFloat( aContext, aOffset, aValue );
    }

    
    @Override
    public int decodeAsInteger( CopybookContext   aContext,
                                int               aOffset   )
    {
        return (int) decodeAsFloat( aContext, aOffset );
    }
    
    
    @Override
    public void encodeAsInteger( CopybookContext   aContext,
                                 int               aOffset,
                                 int               aValue    )
    {
        encodeAsFloat( aContext, aOffset, aValue );
    }

    
    @Override
    public long decodeAsLong( CopybookContext   aContext,
                              int               aOffset   )
    {
        return (long) decodeAsFloat( aContext, aOffset );
    }
    
 
    @Override
    public void encodeAsLong( CopybookContext   aContext,
                              int               aOffset,
                              long              aValue    )
    {
        encodeAsFloat( aContext, aOffset, aValue );
    }

    
    @Override
    public float decodeAsFloat( CopybookContext   aContext,
                                int               aOffset   )
    {
        // TODO: implement
        throw new UnsupportedOperationException( "not yet implemented" );
    }
    
    
    @Override
    public void encodeAsFloat( CopybookContext   aContext,
                               int               aOffset,
                               float             aValue    )
    {
        // TODO: implement
        throw new UnsupportedOperationException( "not yet implemented" );
    }

    
    @Override
    public double decodeAsDouble( CopybookContext   aContext,
                                  int               aOffset   )
    {
        return decodeAsFloat( aContext, aOffset );
    }
    
    
    @Override
    public void encodeAsDouble( CopybookContext   aContext,
                                int               aOffset,
                                double            aValue    )
    {
        encodeAsFloat( aContext, aOffset, (float) aValue );
    }

    
    @Override
    public BigInteger decodeAsBigInteger( CopybookContext   aContext,
                                          int               aOffset   )
    {
        // TODO: implement
        throw new UnsupportedOperationException( "not yet implemented" );
    }
    
    
    @Override
    public void encodeAsBigInteger( CopybookContext   aContext,
                                    int               aOffset,
                                    BigInteger        aValue    )
    {
        // TODO: implement
        throw new UnsupportedOperationException( "not yet implemented" );
    }

    
    @Override
    public BigDecimal decodeAsBigDecimal( CopybookContext   aContext,
                                          int               aOffset   )
    {
        // TODO: implement
        throw new UnsupportedOperationException( "not yet implemented" );
    }
    
    
    @Override
    public void encodeAsBigDecimal( CopybookContext   aContext,
                                    int               aOffset,
                                    BigDecimal        aValue    )
    {
        // TODO: implement
        throw new UnsupportedOperationException( "not yet implemented" );
    }

    
    @Override
    public String decodeAsString( CopybookContext   aContext,
                                  int               aOffset   )
    {
        return Float.toString( decodeAsFloat( aContext, aOffset ) );
    }
    
    
    @Override
    public void encodeAsString( CopybookContext   aContext,
                                int               aOffset,
                                String            aValue    )
    {
        encodeAsFloat( aContext, aOffset, Float.parseFloat( aValue ) );
    }

    
    @Override
    public boolean isConditionNameValid( CopybookContext               aContext,                   
                                         int                           aOffset,                    
                                         String                        aConditionName,             
                                         Map<String,ConstantValue[]>   aConditionNameValueMappings )
    {
        boolean myValid = false;
        
        float myFloatValue = decodeAsFloat( aContext, aOffset );
        
        if ( ! aContext.isError() )
        {
            Comparable<?> myValue = new Float( myFloatValue );
            
            ConstantValue[] myConstantValues = aConditionNameValueMappings.get( aConditionName );
            if ( myConstantValues != null )
            {
                myValid = ( Arrays.binarySearch( myConstantValues, myValue ) >= 0 );
            }
        }
        
        return myValid;
    }
}
