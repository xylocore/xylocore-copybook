package com.xylocore.commons.data.copybook.parser.pic;

import java.util.List;

import com.xylocore.commons.data.copybook.domain.Element;
import com.xylocore.commons.data.copybook.domain.PICSlice;
import com.xylocore.commons.data.copybook.domain.PICSymbolType;
import com.xylocore.commons.data.copybook.runtime.DataType;
import com.xylocore.commons.data.copybook.runtime.SignPosition;
import com.xylocore.commons.data.copybook.runtime.SignType;
import com.xylocore.commons.data.copybook.runtime.UsageType;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class NumericPICProcessingStrategy
    implements
        PICProcessingStrategy
{
    //
    // PICConverterStrategy interface implementation
    //
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.parser.PICConverterStrategy#convert(com.xylocore.commons.data.copybook.domain.Element)
     */
    public void convert( Element aElement )
    {
        assert aElement.getPICSlices() != null;
        
        List<PICSlice> mySlices           = aElement.getPICSlices();
        boolean        myHasSign          = false;
        int            myScalingDirection = -1;
        int            myScalePosition    = 0;
        boolean        myScalingComplete  = false;
        int            myDigits           = 0;
        int            myDecimalPosition  = Integer.MIN_VALUE;
        
        for ( int i = 0, ci = mySlices.size() ; i < ci ; i++ )
        {
            PICSlice      mySlice      = mySlices.get( i );
            PICSymbolType mySymbolType = mySlice.getType();

            assert mySymbolType == PICSymbolType.Sign                   ||
                   mySymbolType == PICSymbolType.DecimalScalingPosition ||
                   mySymbolType == PICSymbolType.AssumedDecimalPoint    ||
                   mySymbolType == PICSymbolType.Numeric;
            
            if ( mySymbolType == PICSymbolType.Sign )
            {
                if ( i == 0 )
                {
                    myHasSign = true;
                }
                else
                {
                    // TODO: throw proper exception
                    throw new RuntimeException( "sign symbol (S) only allowed at beginning of the picture string" );
                }
            }
            else if ( mySymbolType == PICSymbolType.DecimalScalingPosition )
            {
                if ( myScalingComplete )
                {
                    // TODO: throw proper exception
                    throw new RuntimeException( "improper use of the decimal scaling position symbol (P)" );
                }
                
                myScalePosition += myScalingDirection*mySlice.getCount();
            }
            else
            {
                if ( ! myScalingComplete && myScalePosition != 0 )
                {
                    myScalingComplete = true;
                }
                else if ( myScalingComplete && myScalingDirection == 1 )
                {
                    // TODO: throw proper exception
                    throw new RuntimeException( "improper use of the decimal scaling position symbol (P)" );
                }
                
                myScalingDirection = 1;

                if ( mySymbolType == PICSymbolType.AssumedDecimalPoint )
                {
                    if ( myDecimalPosition != Integer.MIN_VALUE )
                    {
                        throw new RuntimeException( "the assumed decimal point symbol (V) can only occur once" );
                    }
                    
                    myDecimalPosition = myDigits;
                }
                else    // mySymbolType == PICSymbolType.Numeric
                {
                    myDigits += mySlice.getCount();
                }
            }
        }
        
        if ( aElement.getSignType() == null )
        {
            aElement.setSignType( myHasSign ? SignType.NotSeparate : SignType.None );
        }
        if ( aElement.getSignType() != SignType.None && aElement.getSignPosition() == null )
        {
            aElement.setSignPosition( SignPosition.Trailing );
        }
        
        UsageType myUsageType = aElement.getEffectiveUsageType();
        assert myUsageType == UsageType.Binary         ||
               myUsageType == UsageType.Computational3 ||
               myUsageType == UsageType.Computational5 ||
               myUsageType == UsageType.Display        ||
               myUsageType == UsageType.National;
        
        int mySize;
        if ( myUsageType.isComputational() )
        {
            if ( myUsageType == UsageType.Binary || myUsageType == UsageType.Computational5 )
            {
                if ( myDigits <= 4 )
                {
                    mySize = 2;
                }
                else if ( myDigits <= 9 )
                {
                    mySize = 4;
                }
                else
                {
                    mySize = 8;
                }
            }
            else // myUsageType == UsageType.Computational3
            {
                mySize = myDigits / 2 + 1;
            }
        }
        else
        {
            mySize = myDigits;
            
            if ( aElement.getSignType() == SignType.Separate )
            {
                mySize++;
            }
            if ( myUsageType == UsageType.National )
            {
                // TODO: support for NSYMBOL(NATIONAL) needed
                mySize *= 2;
            }
        }
        
        int myPrecision = ( myDecimalPosition != Integer.MIN_VALUE ) ? myDigits - myDecimalPosition : 0;

        DataType myDataType;
        if ( aElement.getDatePattern() != null )
        {
            myDataType = DataType.Date;
        }
        else if
        (
            myPrecision == 0                        ||
            myUsageType == UsageType.Binary         ||
            myUsageType == UsageType.Computational5
        )
        {
            if ( myDigits <= 4 )
            {
                myDataType = DataType.Short;
            }
            else if ( myDigits <= 9 )
            {
                myDataType = DataType.Integer;
            }
            else
            {
                myDataType = DataType.Long;
            }
        }
        else    // myPrecision != 0
        {
            myDataType = DataType.BigDecimal;
        }
        
        aElement.setDigits         ( myDigits        );
        aElement.setScalingPosition( myScalePosition );
        aElement.setPrecision      ( myPrecision     );
        aElement.setSize           ( mySize          );
        aElement.setNonIndexedSize ( mySize          );
        aElement.setDataType       ( myDataType      );
    }
}
