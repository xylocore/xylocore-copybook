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

package com.xylocore.commons.data.copybook.domain.config.parser;

import org.exolab.castor.mapping.GeneralizedFieldHandler;

import com.xylocore.commons.data.copybook.runtime.DataUsageCategory;


/**
 * This class extends the <code>GeneralizedFieldHandler</code> and provides conversion
 * support for the data usage category enumerated type.
 * 
 * @author      Eric R. Medley
 */

public class DataUsageCategoryFieldHandler
    extends
        GeneralizedFieldHandler
{
    //
    // Class implementation
    //
    
    
    /* (non-Javadoc)
     * @see org.exolab.castor.mapping.GeneralizedFieldHandler#convertUponGet(java.lang.Object)
     */
    public Object convertUponGet( Object aValue )
    {
        return ( aValue != null ) ? aValue.toString() : null;
    }
    
    
    /* (non-Javadoc)
     * @see org.exolab.castor.mapping.GeneralizedFieldHandler#convertUponSet(java.lang.Object)
     */
    public Object convertUponSet( Object aValue )
    {
        return DataUsageCategory.valueOf( (String) aValue );
    }
    
    
    /* (non-Javadoc)
     * @see org.exolab.castor.mapping.GeneralizedFieldHandler#getFieldType()
     */
    public Class<?> getFieldType()
    {
        return DataUsageCategory.class;
    }
    
    
    /* (non-Javadoc)
     * @see org.exolab.castor.mapping.FieldHandler#newInstance(java.lang.Object)
     */
    public Object newInstance( Object aParent )
            throws IllegalStateException
    {
        return null;
    }
}
