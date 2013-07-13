package com.xylocore.commons.data.copybook.runtime.marshallers.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import com.xylocore.commons.data.copybook.runtime.AlphanumericPICFlags;
import com.xylocore.commons.data.copybook.runtime.ConstantValue;
import com.xylocore.commons.data.copybook.runtime.CopybookContext;
import com.xylocore.commons.data.copybook.runtime.CopybookError;
import com.xylocore.commons.data.copybook.runtime.converters.Converter;
import com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller;
import com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class AlphanumericPICMarshallerImpl
    extends
        AbstractPICMarshaller
    implements
        AlphanumericPICMarshaller
{
    //
    // Members
    //
    
    
    private static final AlphanumericPICMarshallerImpl instance = new AlphanumericPICMarshallerImpl();
    
    
    
    
    //
    // Class implementation
    //
    

    /**
     * Private constructor as per the singleton pattern.
     */
    private AlphanumericPICMarshallerImpl()
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public static AlphanumericPICMarshallerImpl getInstance()
    {
        return instance;
    }

    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * 
     * @return
     */
    public void decodeStringIntoWorkBuffer( CopybookContext   aContext,
                                            int               aOffset,
                                            int               aSize,
                                            int               aFlags    )
    {
        CharBuffer myBuffer = aContext.getWorkBuffer( aSize );
        int        myStart  = 0;
        int        myEnd    = aSize;
        
        // Decode the character data using the correct decoder technique
        if ( AlphanumericPICFlags.isDisplay( aFlags ) )
        {
            aContext.getDataBehavior().decodeDisplayChars( aContext, aOffset, aSize, myBuffer );
        }
        else if ( AlphanumericPICFlags.isNational( aFlags ) )
        {
            aContext.getDataBehavior().decodeNationalChars( aContext, aOffset, aSize, myBuffer );
        }
        else // AlphanumericPICFlags.isDbcs( aFlags )
        {
            aContext.getDataBehavior().decodeDbcsChars( aContext, aOffset, aSize, myBuffer );
        }
        
        if ( ! aContext.isError() )
        {
            if ( AlphanumericPICFlags.isRightJustify( aFlags ) )
            {
                if ( AlphanumericPICFlags.isTrimWhitespace( aFlags ) )
                {
                    while ( myStart < myEnd && myBuffer.get( myStart ) == ' ' )
                    {
                        myStart++;
                    }
                }
            }
            else
            {
                if ( AlphanumericPICFlags.isTrimWhitespace( aFlags ) )
                {
                    while ( myEnd > myStart && myBuffer.get( myEnd-1 ) == ' ' )
                    {
                        myEnd--;
                    }
                }
            }
            
            aContext.setWorkBufferOffsets( myStart, myEnd );
        }
        else
        {
            aContext.setWorkBufferOffsets( 0, 0 );
        }
    }
    

    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * @param       aMinimumValue
     * @param       aMaximumValue
     * 
     * @return
     */
    private boolean isValidLong( CopybookContext   aContext,
                                 int               aOffset,
                                 int               aSize,
                                 int               aFlags,
                                 long              aMinimumValue,
                                 long              aMaximumValue  )
    {
        CopybookError myStandardError = aContext.getStandardError();
        Object        myPlatformError = aContext.getPlatformError();
        
        try
        {
            getLongValue( aContext, aOffset, aSize, aFlags, aMinimumValue, aMaximumValue );
            return ! aContext.isError();
        }
        finally
        {
            aContext.setError( myStandardError, myPlatformError );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * @param       aMinimumValue
     * @param       aMaximumValue
     * 
     * @return
     */
    private long getLongValue( CopybookContext   aContext,
                               int               aOffset,
                               int               aSize,
                               int               aFlags,
                               long              aMinimumValue,
                               long              aMaximumValue  )
    {
        long myValue  = 0;
        
        decodeStringIntoWorkBuffer( aContext, aOffset, aSize, aFlags );

        CharBuffer myBuffer = aContext.getWorkBuffer( aSize );
        int        myStart  = aContext.getWorkBufferStartOffset();
        int        myEnd    = aContext.getWorkBufferEndOffset();
        
        if ( ! aContext.isError() )
        {
            long mySign       = 0;
            int  myDigitCount = 0;
            
            while ( myStart < myEnd && ! aContext.isError() )
            {
                char myChar = myBuffer.get( myStart );
                
                switch ( myChar )
                {
                    case '-':
                        
                        if ( myDigitCount == 0 && mySign == 0 )
                        {
                            mySign = -1;
                        }
                        else
                        {
                            aContext.setError( CopybookError.InvalidNumberFormat, null );
                        }
                        break;
                        
                    case '+':
                    
                        if ( myDigitCount == 0 && mySign == 0 )
                        {
                            mySign = 1;
                        }
                        else
                        {
                            aContext.setError( CopybookError.InvalidNumberFormat, null );
                        }
                        break;
                        
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        
                        long myDigit = Character.digit( myChar, 10 );
                        long myNewValue = myValue * 10 + myDigit;
                        if ( myValue != (myNewValue - myDigit) / 10 )
                        {
                            // Overflow occurred
                            aContext.setError( CopybookError.NumericOverflow, null );
                        }
                        
                        myValue = myNewValue;
                        myDigitCount++;
                        
                        break;
                        
                    default:
                        
                        aContext.setError( CopybookError.InvalidNumberFormat, null );
                        break;
                }
                
                myStart++;
            }
            
            if ( ! aContext.isError() )
            {
                if ( myDigitCount == 0 )
                {
                    aContext.setError( CopybookError.InvalidNumberFormat, null );
                }
                else
                {
                    myValue = myValue * ( mySign < 0 ? -1 : 1 );
                    
                    if ( myValue < aMinimumValue || myValue > aMaximumValue )
                    {
                        aContext.setError( CopybookError.NumberOutsideOfConstraints, null );
                    }
                }
            }
        }
        
        return ( ! aContext.isError() ? myValue : 0 );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aSize
     * @param       aFlags
     * @param       aMinimumValue
     * @param       aMaximumValue
     * 
     * @return
     */
    private void setLongValue( CopybookContext   aContext,
                               int               aOffset,
                               long              aValue,
                               int               aSize,
                               int               aFlags,
                               long              aMinimumValue,
                               long              aMaximumValue  )
    {
        if ( aValue < aMinimumValue || aValue > aMaximumValue )
        {
            aContext.setError( CopybookError.NumberOutsideOfConstraints, null );
        }
        else
        {
            encodeAsString( aContext, aOffset, Long.toString( aValue ), aSize, aFlags, null );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    private boolean checkDecodeNullEquivalents( CopybookContext            aContext,
                                                int                        aOffset,
                                                int                        aSize,
                                                int                        aFlags,
                                                NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        boolean myIsNull = false;
        
        if ( aNullEquivalentStrategies != null )
        {
            for ( int i = 0, ci = aNullEquivalentStrategies.length ; i < ci && ! myIsNull && ! aContext.isError() ; i++ )
            {
                NullEquivalentStrategy myNullEquivalentStrategy = aNullEquivalentStrategies[i];
                
                if ( ! myNullEquivalentStrategy.isDirect() && ! aContext.isWorkBufferOffsetsSet() )
                {
                    decodeStringIntoWorkBuffer( aContext, aOffset, aSize, aFlags );
                }
                
                myIsNull = myNullEquivalentStrategy.isAlphanumericNull( this, aContext, aOffset, aSize, aFlags );
            }
        }
        
        return myIsNull;
    }

    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    private boolean encodeUsingNullEquivalent( CopybookContext            aContext,
                                               int                        aOffset,
                                               int                        aSize,
                                               int                        aFlags,
                                               NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        boolean myStrategyAvailable = ( aNullEquivalentStrategies != null && aNullEquivalentStrategies.length != 0 );
        
        if ( myStrategyAvailable )
        {
            aNullEquivalentStrategies[0].setAlphanumericNull( this, aContext, aOffset, aSize, aFlags );
        }
        
        return myStrategyAvailable;
    }
    
    
    
    
    //
    // AlphanumericPICMarshaller interface implementation
    //
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#isBlank(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, int)
     */
    public boolean isBlank( CopybookContext   aContext,
                            int               aOffset,
                            int               aSize,
                            int               aFlags    )
    {
        boolean blank;
        
        // Decode the character data using the correct decoder technique
        if ( AlphanumericPICFlags.isDisplay( aFlags ) )
        {
            blank = aContext.getDataBehavior().isDisplayCharsBlank( aContext, aOffset, aSize );
        }
        else if ( AlphanumericPICFlags.isNational( aFlags ) )
        {
            blank = aContext.getDataBehavior().isNationalCharsBlank( aContext, aOffset, aSize );
        }
        else // AlphanumericPICFlags.isDbcs( aFlags )
        {
            blank = aContext.getDataBehavior().isDbcsCharsBlank( aContext, aOffset, aSize );
        }
        
        return blank;
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#isValidAsByte(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, int)
     */
    public boolean isValidAsByte( CopybookContext   aContext,
                                  int               aOffset,
                                  int               aSize,
                                  int               aFlags    )
    {
        return isValidLong( aContext, aOffset, aSize, aFlags, ((long) Byte.MIN_VALUE) & 0xff, ((long) Byte.MAX_VALUE) & 0xff );
    }
    

    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#decodeAsByte(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, int)
     */
    public byte decodeAsByte( CopybookContext   aContext,
                              int               aOffset,
                              int               aSize,
                              int               aFlags    )
    {
        long myValue = getLongValue( aContext, aOffset, aSize, aFlags, ((long) Byte.MIN_VALUE) & 0xff, ((long) Byte.MAX_VALUE) & 0xff );
        return ( (byte) myValue );
    }
    

    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#encodeAsByte(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, byte, int, int)
     */
    public void encodeAsByte( CopybookContext   aContext,
                              int               aOffset,
                              byte              aValue,
                              int               aSize,
                              int               aFlags    )
    {
        setLongValue( aContext, aOffset, ((long) aValue) & 0xff, aSize, aFlags, ((long) Byte.MIN_VALUE) & 0xff, ((long) Byte.MAX_VALUE) & 0xff );
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#isValidAsChar(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, int)
     */
    public boolean isValidAsChar( CopybookContext   aContext,
                                  int               aOffset,
                                  int               aSize,
                                  int               aFlags    )
    {
        CopybookError myStandardError = aContext.getStandardError();
        Object        myPlatformError = aContext.getPlatformError();
        
        try
        {
            decodeAsString( aContext, aOffset, aSize, aFlags, null );
            return ! aContext.isError();
        }
        finally
        {
            aContext.setError( myStandardError, myPlatformError );
        }
    }
    

    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#decodeAsChar(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, int)
     */
    public char decodeAsChar( CopybookContext   aContext,
                              int               aOffset,
                              int               aSize,
                              int               aFlags    )
    {
        String myValue = decodeAsString( aContext, aOffset, aSize, aFlags, null );

        return myValue.charAt( 0 );
    }
    

    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#encodeAsChar(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, char, int, int)
     */
    public void encodeAsChar( CopybookContext   aContext,
                              int               aOffset,
                              char              aValue,
                              int               aSize,
                              int               aFlags    )
    {
        String myString = Character.toString( aValue );
        
        encodeAsString( aContext, aOffset, myString, aSize, aFlags, null );
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#isValidAsShort(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, int)
     */
    public boolean isValidAsShort( CopybookContext   aContext,
                                   int               aOffset,
                                   int               aSize,
                                   int               aFlags    )
    {
        return isValidLong( aContext, aOffset, aSize, aFlags, Short.MIN_VALUE, Short.MAX_VALUE );
    }

    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#decodeAsShort(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, int)
     */
    public short decodeAsShort( CopybookContext   aContext,
                                int               aOffset,
                                int               aSize,
                                int               aFlags    )
    {
        long myValue = getLongValue( aContext, aOffset, aSize, aFlags, Short.MIN_VALUE, Short.MAX_VALUE );
        return ( (short) myValue );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#encodeAsShort(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, short, int, int)
     */
    public void encodeAsShort( CopybookContext   aContext,
                               int               aOffset,
                               short             aValue,
                               int               aSize,
                               int               aFlags    )
    {
        setLongValue( aContext, aOffset, aValue, aSize, aFlags, Short.MIN_VALUE, Short.MAX_VALUE );
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#isValidAsInteger(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, int)
     */
    public boolean isValidAsInteger( CopybookContext   aContext,
                                     int               aOffset,
                                     int               aSize,
                                     int               aFlags    )
    {
        return isValidLong( aContext, aOffset, aSize, aFlags, Integer.MIN_VALUE, Integer.MAX_VALUE );
    }

    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#decodeAsInteger(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, int)
     */
    public int decodeAsInteger( CopybookContext   aContext,
                                int               aOffset,
                                int               aSize,
                                int               aFlags    )
    {
        long myValue = getLongValue( aContext, aOffset, aSize, aFlags, Integer.MIN_VALUE, Integer.MAX_VALUE );
        return ( (int) myValue );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#encodeAsInteger(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, int, int)
     */
    public void encodeAsInteger( CopybookContext   aContext,
                                 int               aOffset,
                                 int               aValue,
                                 int               aSize,
                                 int               aFlags    )
    {
        setLongValue( aContext, aOffset, aValue, aSize, aFlags, Integer.MIN_VALUE, Integer.MAX_VALUE );
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#isValidAsLong(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, int)
     */
    public boolean isValidAsLong( CopybookContext   aContext,
                                  int               aOffset,
                                  int               aSize,
                                  int               aFlags    )
    {
        return isValidLong( aContext, aOffset, aSize, aFlags, Long.MIN_VALUE, Long.MAX_VALUE );
    }

    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#decodeAsLong(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, int)
     */
    public long decodeAsLong( CopybookContext   aContext,
                              int               aOffset,
                              int               aSize,
                              int               aFlags    )
    {
        return getLongValue( aContext, aOffset, aSize, aFlags, Long.MIN_VALUE, Long.MAX_VALUE );
    }
    
 
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#encodeAsLong(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, long, int, int)
     */
    public void encodeAsLong( CopybookContext   aContext,
                              int               aOffset,
                              long              aValue,
                              int               aSize,
                              int               aFlags    )
    {
        setLongValue( aContext, aOffset, aValue, aSize, aFlags, Long.MIN_VALUE, Long.MAX_VALUE );
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#isValidAsFloat(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, int)
     */
    public boolean isValidAsFloat( CopybookContext   aContext,
                                   int               aOffset,
                                   int               aSize,
                                   int               aFlags    )
    {
        // TODO: implement
        throw new UnsupportedOperationException( "not yet implemented" );
    }

    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#decodeAsFloat(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, int)
     */
    public float decodeAsFloat( CopybookContext   aContext,
                                int               aOffset,
                                int               aSize,
                                int               aFlags    )
    {
        String myValue = decodeAsString( aContext, aOffset, aSize, aFlags, null );

        return Float.parseFloat( myValue );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#encodeAsFloat(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, float, int, int)
     */
    public void encodeAsFloat( CopybookContext   aContext,
                               int               aOffset,
                               float             aValue,
                               int               aSize,
                               int               aFlags    )
    {
        encodeAsDouble( aContext, aOffset, aValue, aSize, aFlags );
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#isValidAsDouble(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, int)
     */
    public boolean isValidAsDouble( CopybookContext   aContext,
                                    int               aOffset,
                                    int               aSize,
                                    int               aFlags    )
    {
        // TODO: implement
        throw new UnsupportedOperationException( "not yet implemented" );
    }

    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#decodeAsDouble(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, int)
     */
    public double decodeAsDouble( CopybookContext   aContext,
                                  int               aOffset,
                                  int               aSize,
                                  int               aFlags    )
    {
        String myValue = decodeAsString( aContext, aOffset, aSize, aFlags, null );

        return Double.parseDouble( myValue );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#encodeAsDouble(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, double, int, int)
     */
    public void encodeAsDouble( CopybookContext   aContext,
                                int               aOffset,
                                double            aValue,
                                int               aSize,
                                int               aFlags    )
    {
        String myString = Double.toString( aValue );
        
        encodeAsString( aContext, aOffset, myString, aSize, aFlags, null );
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#isValidAsBigInteger(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, int, com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy[])
     */
    public boolean isValidAsBigInteger( CopybookContext            aContext,
                                        int                        aOffset,
                                        int                        aSize,
                                        int                        aFlags,
                                        NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        // TODO: implement
        throw new UnsupportedOperationException( "not yet implemented" );
    }

    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#decodeAsBigInteger(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, int, com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy[])
     */
    public BigInteger decodeAsBigInteger( CopybookContext            aContext,
                                          int                        aOffset,
                                          int                        aSize,
                                          int                        aFlags,
                                          NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        // TODO: implement
        throw new UnsupportedOperationException( "not yet implemented" );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#encodeAsBigInteger(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, java.math.BigInteger, int, int, com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy[])
     */
    public void encodeAsBigInteger( CopybookContext            aContext,
                                    int                        aOffset,
                                    BigInteger                 aValue,
                                    int                        aSize,
                                    int                        aFlags,
                                    NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        // TODO: implement
        throw new UnsupportedOperationException( "not yet implemented" );
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#isValidAsBigDecimal(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, int, com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy[])
     */
    public boolean isValidAsBigDecimal( CopybookContext            aContext,
                                        int                        aOffset,
                                        int                        aSize,
                                        int                        aFlags,
                                        NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        // TODO: implement
        throw new UnsupportedOperationException( "not yet implemented" );
    }

    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#decodeAsBigDecimal(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, int, com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy[])
     */
    public BigDecimal decodeAsBigDecimal( CopybookContext            aContext,
                                          int                        aOffset,
                                          int                        aSize,
                                          int                        aFlags,
                                          NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        // TODO: implement
        throw new UnsupportedOperationException( "not yet implemented" );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#encodeAsBigDecimal(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, java.math.BigDecimal, int, int, com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy[])
     */
    public void encodeAsBigDecimal( CopybookContext            aContext,
                                    int                        aOffset,
                                    BigDecimal                 aValue,
                                    int                        aSize,
                                    int                        aFlags,
                                    NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        // TODO: implement
        throw new UnsupportedOperationException( "not yet implemented" );
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#isValidAsString(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, int, com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy[])
     */
    public boolean isValidAsString( CopybookContext            aContext,
                                    int                        aOffset,
                                    int                        aSize,
                                    int                        aFlags,
                                    NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        CopybookError myStandardError = aContext.getStandardError();
        Object        myPlatformError = aContext.getPlatformError();
        
        try
        {
            boolean myValid = false;
            
            if
            (
                checkDecodeNullEquivalents( aContext,
                                            aOffset,
                                            aSize,
                                            aFlags,
                                            aNullEquivalentStrategies )
            )
            {
                myValid = true;
            }
            else if ( ! aContext.isError() )
            {
                CharBuffer myBuffer = aContext.getWorkBuffer( aSize );
                
                // Decode the character data using the correct decoder technique
                if ( AlphanumericPICFlags.isDisplay( aFlags ) )
                {
                    aContext.getDataBehavior().decodeDisplayChars( aContext, aOffset, aSize, myBuffer );
                }
                else if ( AlphanumericPICFlags.isNational( aFlags ) )
                {
                    aContext.getDataBehavior().decodeNationalChars( aContext, aOffset, aSize, myBuffer );
                }
                else // AlphanumericPICFlags.isDbcs( aFlags )
                {
                    aContext.getDataBehavior().decodeDbcsChars( aContext, aOffset, aSize, myBuffer );
                }
                
                myValid = ( ! aContext.isError() );
            }

            return myValid;
        }
        finally
        {
            aContext.setError( myStandardError, myPlatformError );
        }
    }

    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#decodeAsString(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, int, com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy[])
     */
    public String decodeAsString( CopybookContext            aContext,
                                  int                        aOffset,
                                  int                        aSize,
                                  int                        aFlags,
                                  NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        String myString = null;
        
        if
        (
            ! checkDecodeNullEquivalents( aContext,
                                          aOffset,
                                          aSize,
                                          aFlags,
                                          aNullEquivalentStrategies ) &&
            ! aContext.isError()
        )
        {
            CharBuffer myBuffer  = aContext.getWorkBuffer( aSize );
            
            decodeStringIntoWorkBuffer( aContext, aOffset, aSize, aFlags );
            
            if ( ! aContext.isError() )
            {
                int myStart = aContext.getWorkBufferStartOffset();
                int myEnd   = aContext.getWorkBufferEndOffset();
                
                StringBuilder myTempBuffer = aContext.getWorkStringBuilder( myEnd - myStart );
        
                while ( myStart < myEnd )
                {
                    myTempBuffer.append( myBuffer.get( myStart++ ) );
                }
                
                myString = myTempBuffer.toString();
            }
            else
            {
                myString = null;
            }
        }
        
        return myString;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#encodeAsString(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, java.lang.String, int, int, com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy[])
     */
    public void encodeAsString( CopybookContext            aContext,
                                int                        aOffset,
                                String                     aValue,
                                int                        aSize,
                                int                        aFlags,
                                NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        boolean myNullEquivalentUsed = false;
        
        if ( aValue == null )
        {
            myNullEquivalentUsed =
                    encodeUsingNullEquivalent( aContext,
                                               aOffset,
                                               aSize,
                                               aFlags,
                                               aNullEquivalentStrategies );
        }
        
        if ( ! myNullEquivalentUsed && ! aContext.isError() )
        {
            if ( aValue == null )
            {
                if ( AlphanumericPICFlags.isNullAllowed( aFlags ) )
                {
                    aValue = "";
                }
                else
                {
                    aContext.setError( CopybookError.NullNotAllowed, null );
                }
            }

            if ( ! aContext.isError() )
            {
                CharBuffer myBuffer       = aContext.getWorkBuffer( aSize );
                int        myValueLength  = aValue.length();
                int        myBufferOffset = 0;
                int        myStart;
                int        myEnd;
        
                if ( AlphanumericPICFlags.isRightJustify( aFlags ) )
                {
                    if ( myValueLength < aSize )
                    {
                        myBufferOffset = aSize - myValueLength;
                        
                        for ( int i = 0, ci = myBufferOffset ; i < ci ; i++ )
                        {
                            myBuffer.put( i, ' ' );
                        }
        
                        myStart = 0;
                    }
                    else
                    {
                        myStart = myValueLength - aSize;
                    }
        
                    myEnd = myValueLength;
                }
                else
                {
                    if ( myValueLength < aSize )
                    {
                        for ( int i = myValueLength ; i < aSize ; i++ )
                        {
                            myBuffer.put( i, ' ' );
                        }
                        
                        myEnd = myValueLength;
                    }
                    else
                    {
                        myEnd = aSize;
                    }
                    
                    myStart = 0;
                }
                
                while ( myStart < myEnd )
                {
                    myBuffer.put( myBufferOffset++, aValue.charAt( myStart++ ) );
                }
                
                myBuffer.limit   ( aSize );
                myBuffer.position( aSize );
                myBuffer.flip();
                
                // Encode the character data using the correct encoder technique
                if ( AlphanumericPICFlags.isDisplay( aFlags ) )
                {
                    aContext.getDataBehavior().encodeDisplayChars( aContext, aOffset, aSize, myBuffer );
                }
                else if ( AlphanumericPICFlags.isNational( aFlags ) )
                {
                    aContext.getDataBehavior().encodeNationalChars( aContext, aOffset, aSize, myBuffer );
                }
                else // AlphanumericPICFlags.isDbcs( aFlags )
                {
                    aContext.getDataBehavior().encodeDbcsChars( aContext, aOffset, aSize, myBuffer );
                }
            }
        }
    }

    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#isValidAsDate(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, int, com.xylocore.commons.data.copybook.runtime.converters.Converter, com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy[])
     */
    public boolean isValidAsDate( CopybookContext            aContext,
                                  int                        aOffset,
                                  int                        aSize,
                                  int                        aFlags,
                                  Converter                  aConverter,
                                  NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        CopybookError myStandardError = aContext.getStandardError();
        Object        myPlatformError = aContext.getPlatformError();
        
        try
        {
            boolean myValid = false;
            
            if ( checkDecodeNullEquivalents( aContext, aOffset, aSize, aFlags, aNullEquivalentStrategies ) )
            {
                myValid = true;
            }
            else if ( ! aContext.isError() )
            {
                myValid = aConverter.isAlphanumericValid( this, aContext, aOffset, aSize, aFlags );
            }

            return myValid;
        }
        finally
        {
            aContext.setError( myStandardError, myPlatformError );
        }
    }
    

    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#decodeAsDate(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, int, com.xylocore.commons.data.copybook.runtime.converters.Converter, com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy[])
     */
    public Date decodeAsDate( CopybookContext            aContext,
                              int                        aOffset,
                              int                        aSize,
                              int                        aFlags,
                              Converter                  aConverter,
                              NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        Date myDate = null;

        aContext.clearWorkBufferOffsets();
        
        if
        (
            ! checkDecodeNullEquivalents( aContext, aOffset, aSize, aFlags, aNullEquivalentStrategies ) &&
            ! aContext.isError()
        )
        {
            myDate = (Date) aConverter.decodeAlphanumeric( this, aContext, aOffset, aSize, aFlags );
        }
        
        return myDate;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#encodeAsDate(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, java.util.Date, int, int, com.xylocore.commons.data.copybook.runtime.converters.Converter, com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy[])
     */
    public void encodeAsDate( CopybookContext            aContext,
                              int                        aOffset,
                              Date                       aValue,
                              int                        aSize,
                              int                        aFlags,
                              Converter                  aConverter,
                              NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        boolean myNullEquivalentUsed = false;
        
        if ( aValue == null )
        {
            myNullEquivalentUsed = encodeUsingNullEquivalent( aContext, aOffset, aSize, aFlags, aNullEquivalentStrategies );
        }
        
        if ( ! myNullEquivalentUsed && ! aContext.isError() )
        {
            aConverter.encodeAlphanumeric( this, aContext, aOffset, aValue, aSize, aFlags );
        }
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller#isConditionNameValid(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, java.lang.String, java.util.Map, int, int)
     */
    public boolean isConditionNameValid( CopybookContext               aContext,                   
                                         int                           aOffset,                    
                                         String                        aConditionName,             
                                         Map<String,ConstantValue[]>   aConditionNameValueMappings,
                                         int                           aSize,                      
                                         int                           aFlags                       )
    {
        boolean myValid = false;
        
        String myValue = decodeAsString( aContext, aOffset, aSize, aFlags, null );
        if ( ! aContext.isError() )
        {
            ConstantValue[] myConstantValues = aConditionNameValueMappings.get( aConditionName );
            if ( myConstantValues != null )
            {
                myValid = ( Arrays.binarySearch( myConstantValues, myValue ) >= 0 );
            }
        }
        
        return myValid;
    }
}
