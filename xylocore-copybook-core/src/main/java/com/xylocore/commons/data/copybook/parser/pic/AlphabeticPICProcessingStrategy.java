package com.xylocore.commons.data.copybook.parser.pic;

import com.xylocore.commons.data.copybook.domain.PICSymbolType;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class AlphabeticPICProcessingStrategy
    extends
        AlphanumericPICProcessingStrategy
{
    //
    // Class implementation
    //
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.parser.AlphanumericPICProcessingStrategy#getSymbolType()
     */
    protected PICSymbolType getSymbolType()
    {
        return PICSymbolType.Alphanumeric;
    }
}
