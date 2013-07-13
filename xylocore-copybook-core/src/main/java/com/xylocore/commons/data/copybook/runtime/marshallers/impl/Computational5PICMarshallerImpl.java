package com.xylocore.commons.data.copybook.runtime.marshallers.impl;

import com.xylocore.commons.data.copybook.runtime.CopybookContext;
import com.xylocore.commons.data.copybook.runtime.SignType;
import com.xylocore.commons.data.copybook.runtime.marshallers.Computational5PICMarshaller;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class Computational5PICMarshallerImpl
    extends
        AbstractNumericPICMarshaller
    implements
        Computational5PICMarshaller
{
    //
    // Members
    //


    private static final Computational5PICMarshallerImpl instance = new Computational5PICMarshallerImpl();




    //
    // Class implementation
    //


    /**
     * Private constructor as per the singleton pattern.
     */
    private Computational5PICMarshallerImpl()
    {
    }


    /**
     * FILLIN
     * 
     * @return
     */
    public static Computational5PICMarshallerImpl getInstance()
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
        assert aDigits > 0 && aDigits < 18;
        
        long myValue;
        
        switch ( aDigits )
        {
            case 1:
            case 2:
            case 3:
            case 4:
                
                myValue = aContext.getDataBehavior().decode2ByteComp5( aContext, aOffset );
                break;
                
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                
                myValue = aContext.getDataBehavior().decode4ByteComp5( aContext, aOffset );
                break;
                
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                
                myValue = aContext.getDataBehavior().decode8ByteComp5( aContext, aOffset );
                break;
                
            default:
                
                // TODO: handle error gracefully
                throw new IllegalStateException( "aDigits myst be between 1 and 18, actual value was " + aDigits );
        }
        
        // TODO: ensure correct behavior (sign, precision, scaling)
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
        assert aDigits > 0 && aDigits < 18;
        
        // TODO: ensure correct behavior (sign, precision, scaling)
        
        switch ( aDigits )
        {
            case 1:
            case 2:
            case 3:
            case 4:
                
                aContext.getDataBehavior().encode2ByteComp5( aContext, aOffset, aValue );
                break;
                
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                
                aContext.getDataBehavior().encode4ByteComp5( aContext, aOffset, aValue );
                break;
                
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                
                aContext.getDataBehavior().encode8ByteComp5( aContext, aOffset, aValue );
                break;
                
            default:
                
                // TODO: handle error gracefully
                throw new IllegalStateException( "aDigits myst be between 1 and 18, actual value was " + aDigits );
        }
    }
}
