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

import java.util.HashMap;
import java.util.Map;

import com.xylocore.commons.util.ConstantMap;
import com.xylocore.commons.util.MultiConstantMap;
import com.xylocore.commons.util.SingleConstantMap;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public enum DataUsageCategory
{
    //
    // Enumerated constants
    //
    
    
    NumericDisplay,
    NumericNational,
    Binary,
    Computational1,
    Computational2,
    Computational3,
    Computational5,
    Alphanumeric;



    
    //
    // Members
    //
    
    
    private static final Map<DataCategory,ConstantMap<UsageType,DataUsageCategory>> categoryUsageMap = new HashMap<>();
    
    
    
    
    //
    // Static initializer
    //
    
    
    static
    {
        ConstantMap<UsageType,DataUsageCategory> myAlphanumericSingleConstantMap = new SingleConstantMap<>( Alphanumeric );
        
        categoryUsageMap.put( DataCategory.Alphabetic  , myAlphanumericSingleConstantMap );
        categoryUsageMap.put( DataCategory.Alphanumeric, myAlphanumericSingleConstantMap );
        categoryUsageMap.put( DataCategory.DBCS        , myAlphanumericSingleConstantMap );
        categoryUsageMap.put( DataCategory.National    , myAlphanumericSingleConstantMap );
        
        HashMap<UsageType,DataUsageCategory> myMap = new HashMap<>();
        myMap.put( UsageType.Binary        , Binary          );
        myMap.put( UsageType.Computational1, Computational1  );
        myMap.put( UsageType.Computational2, Computational2  );
        myMap.put( UsageType.Computational3, Computational3  );
        myMap.put( UsageType.Computational5, Computational5  );
        myMap.put( UsageType.Display       , NumericDisplay  );
        myMap.put( UsageType.National      , NumericNational );
        categoryUsageMap.put( DataCategory.Numeric, new MultiConstantMap<>( myMap ) );
    }
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * Returns the DataUsageCategory the is mapped to the specified data category and usage type.
     * 
     * @param       aDataCategory
     * @param       aUsageType
     * 
     * @return
     */
    public static DataUsageCategory getUsingDataCategoryAndUsageType( DataCategory   aDataCategory,
                                                                      UsageType      aUsageType     )
    {
        DataUsageCategory myDataUsageCategory = null;

        ConstantMap<UsageType,DataUsageCategory> myConstantMap = categoryUsageMap.get( aDataCategory );
        if ( myConstantMap != null )
        {
            myDataUsageCategory = myConstantMap.get( aUsageType );
        }
        
        return myDataUsageCategory;
    }
}
