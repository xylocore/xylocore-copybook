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


package com.xylocore.copybook.runtime;


/**
 * FILLIN
 *
 * @author      Eric R. Medley
 */

public class NumericPICMarshallerHelper
{
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public static int calculateScale( int   aPrecision,
                                      int   aScalingPosition )
    {
        return ( aPrecision != 0 ) ? -aPrecision : aScalingPosition;
    }

    
    /**
     * FILLIN
     * 
     * @param       aValue
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aInvert
     * 
     * @return
     */
    public static double scaleValue( double    aValue,
                                     int       aPrecision,
                                     int       aScalingPosition,
                                     boolean   aInvert           )
    {
        int myScale = calculateScale( aPrecision, aScalingPosition ); 
        if ( myScale != 0 )
        {
            if ( aInvert )
            {
                myScale = -myScale;
            }
            
            aValue *= Math.pow( 10, myScale );
        }
        
        return aValue;
    }
}
