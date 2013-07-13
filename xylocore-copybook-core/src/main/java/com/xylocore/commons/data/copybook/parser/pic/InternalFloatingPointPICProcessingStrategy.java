package com.xylocore.commons.data.copybook.parser.pic;

import com.xylocore.commons.data.copybook.domain.Element;
import com.xylocore.commons.data.copybook.runtime.DataType;
import com.xylocore.commons.data.copybook.runtime.UsageType;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class InternalFloatingPointPICProcessingStrategy
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
        assert aElement != null;
        
        UsageType myUsageType = aElement.getEffectiveUsageType();
        DataType  myDataType  = null;
        
        if ( myUsageType == UsageType.Computational1 )
        {
            myDataType = DataType.Float;
        }
        else if ( myUsageType == UsageType.Computational2 )
        {
            myDataType = DataType.Double;
        }
        
        assert myDataType != null;
        
        if ( aElement.getPICSlices() != null )
        {
            // TODO: use proper exception
            throw new RuntimeException( "a usage type of " + myUsageType + " cannot be used with a picture clause" );
        }
        
        aElement.setDataType( myDataType );
    }
}
