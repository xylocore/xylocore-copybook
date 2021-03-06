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


package com.xylocore.copybook.generator.pic;

import com.xylocore.copybook.generator.domain.Element;
import com.xylocore.copybook.runtime.DataType;
import com.xylocore.copybook.runtime.UsageType;


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
    // PICProcessingStrategy interface implementation
    //
    

    @Override
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
