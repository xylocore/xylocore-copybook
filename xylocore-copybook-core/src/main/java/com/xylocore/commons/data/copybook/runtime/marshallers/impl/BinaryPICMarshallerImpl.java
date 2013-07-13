package com.xylocore.commons.data.copybook.runtime.marshallers.impl;

import com.xylocore.commons.data.copybook.runtime.CopybookContext;
import com.xylocore.commons.data.copybook.runtime.SignType;
import com.xylocore.commons.data.copybook.runtime.marshallers.BinaryPICMarshaller;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class BinaryPICMarshallerImpl
    extends
        AbstractNumericPICMarshaller
    implements
        BinaryPICMarshaller
{
    //
    // Members
    //
    
    
    private static final BinaryPICMarshallerImpl instance = new BinaryPICMarshallerImpl();
    
    
    
    
    //
    // Class implementation
    //
    

    /**
     * Private constructor as per the singleton pattern.
     */
    private BinaryPICMarshallerImpl()
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public static BinaryPICMarshallerImpl getInstance()
    {
        return instance;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AbstractNumericPICMarshaller#validateRange(long, long, long)
     */
    protected void validateRange( long   aValue,
                                  long   aMinValue,
                                  long   aMaxValue  )
    {
        // TODO - implement
    }

    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AbstractNumericPICMarshaller#decodeAsLong(com.xylocore.commons.data.copybook.spi.Context, int, int, com.xylocore.commons.data.copybook.runtime.SignType, int, int)
     */
    public long decodeAsLong( CopybookContext    aContext,
                              int        aOffset,
                              int        aDigits,
                              SignType   aSignType,
                              int        aPrecision,
                              int        aScalingPosition )
    {
        long myValue =
                aContext.getDataBehavior()
                        .decodeBinary( aContext,
                                       aOffset,
                                       aDigits,
                                       aSignType != SignType.None );
        
        // TODO: do range check against PIC info
        
        return myValue;
    }
    
 
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AbstractNumericPICMarshaller#encodeAsLong(com.xylocore.commons.data.copybook.spi.Context, int, long, int, com.xylocore.commons.data.copybook.runtime.SignType, int, int)
     */
    public void encodeAsLong( CopybookContext    aContext,
                              int        aOffset,
                              long       aValue,
                              int        aDigits,
                              SignType   aSignType,
                              int        aPrecision,
                              int        aScalingPosition )
    {
        aContext.getDataBehavior()
                .encodeBinary( aContext,
                               aOffset,
                               aValue,
                               aDigits,
                               aSignType != SignType.None );
    }
}
