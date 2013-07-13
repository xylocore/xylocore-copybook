package com.xylocore.commons.data.copybook.runtime.marshallers.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Map;

import com.xylocore.commons.data.copybook.runtime.ConstantValue;
import com.xylocore.commons.data.copybook.runtime.CopybookContext;
import com.xylocore.commons.data.copybook.runtime.marshallers.Computational1PICMarshaller;


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
    public byte decodeAsByte( CopybookContext   aContext,
                              int               aOffset   )
    {
        return (byte) decodeAsFloat( aContext, aOffset );
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
        encodeAsFloat( aContext, aOffset, aValue );
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
        return (char) decodeAsFloat( aContext, aOffset );
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
        encodeAsFloat( aContext, aOffset, aValue );
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
        return (short) decodeAsFloat( aContext, aOffset );
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
        encodeAsFloat( aContext, aOffset, aValue );
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
        return (int) decodeAsFloat( aContext, aOffset );
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
        encodeAsFloat( aContext, aOffset, aValue );
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
        return (long) decodeAsFloat( aContext, aOffset );
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
        encodeAsFloat( aContext, aOffset, aValue );
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
    public void encodeAsFloat( CopybookContext   aContext,
                               int               aOffset,
                               float             aValue    )
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
    public double decodeAsDouble( CopybookContext   aContext,
                                  int               aOffset   )
    {
        return decodeAsFloat( aContext, aOffset );
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
        encodeAsFloat( aContext, aOffset, (float) aValue );
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
        return Float.toString( decodeAsFloat( aContext, aOffset ) );
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
        encodeAsFloat( aContext, aOffset, Float.parseFloat( aValue ) );
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
