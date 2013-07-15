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


package com.xylocore.commons.data.copybook.parser.pic;

import java.util.List;

import com.xylocore.commons.data.copybook.domain.Element;
import com.xylocore.commons.data.copybook.domain.PICSlice;
import com.xylocore.commons.data.copybook.domain.PICSymbolType;
import com.xylocore.commons.data.copybook.runtime.DataType;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class AlphanumericPICProcessingStrategy
    implements
        PICProcessingStrategy
{
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    protected PICSymbolType getSymbolType()
    {
        return PICSymbolType.Alphanumeric;
    }
    
    
    
    
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

        List<PICSlice> mySlices = aElement.getPICSlices();
        assert mySlices.size() == 1;

        PICSlice mySlice = mySlices.get( 0 );
        assert mySlice.getType() == getSymbolType();

        DataType myDataType = DataType.String;
        if ( aElement.getDatePattern() != null )
        {
            myDataType = DataType.Date;
        }
        
        aElement.setSize          ( mySlice.getCount() );
        aElement.setNonIndexedSize( mySlice.getCount() );
        aElement.setDigits        ( mySlice.getCount() );
        aElement.setDataType      ( myDataType         );
    }
}
