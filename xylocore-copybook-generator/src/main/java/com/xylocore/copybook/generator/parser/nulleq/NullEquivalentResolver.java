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


package com.xylocore.copybook.generator.parser.nulleq;

import com.xylocore.copybook.generator.domain.DataElement;
import com.xylocore.copybook.generator.domain.config.BlankNullEquivalentConfig;
import com.xylocore.copybook.generator.domain.config.ConfigVisitor;
import com.xylocore.copybook.generator.domain.config.ConstantNullEquivalentConfig;
import com.xylocore.copybook.generator.domain.config.NullEquivalentConfig;
import com.xylocore.copybook.runtime.DataUsageCategory;
import com.xylocore.copybook.runtime.nulleq.BlankNullEquivalentStrategy;
import com.xylocore.copybook.runtime.nulleq.ConstantNullEquivalentStrategy;
import com.xylocore.copybook.runtime.nulleq.NullEquivalentStrategy;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

class NullEquivalentResolver
    extends
        ConfigVisitor
{
    //
    // Members
    //
    
    
    protected   DataElement             dataElement;
    protected   NullEquivalentStrategy  nullEquivalentStrategy;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.parser.NullEquivalentResolver#resolve(com.xylocore.commons.data.copybook.domain.DataElement, com.xylocore.commons.data.copybook.domain.config.NullEquivalentConfig)
     */
    public NullEquivalentStrategy resolve( DataElement            aDataElement,
                                           NullEquivalentConfig   aNullEquivalentConfig )
    {
        dataElement = aDataElement;
        
        aNullEquivalentConfig.accept( this );
        
        return nullEquivalentStrategy;
    }
    

    /**
     * FILLIN
     * 
     * @return
     */
    protected DataUsageCategory getDataUsageCategory()
    {
        return DataUsageCategory.getUsingDataCategoryAndUsageType( dataElement.getDataCategory(), dataElement.getEffectiveUsageType() );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#visitNullEquivalent(com.xylocore.commons.data.copybook.domain.config.NullEquivalentConfig)
     */
    public void visitNullEquivalent( NullEquivalentConfig aNullEquivalent )
    {
        if ( nullEquivalentStrategy == null )
        {
            // TODO: implement better error notification
            throw new RuntimeException( "null equivalent strategy not found for null equivalent " + aNullEquivalent.getClass().getName() );
        }
    }
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#visitBlankNullEquivalent(com.xylocore.commons.data.copybook.domain.config.BlankNullEquivalentConfig)
     */
    public void visitBlankNullEquivalent( BlankNullEquivalentConfig aNullEquivalent )
    {
        nullEquivalentStrategy = BlankNullEquivalentStrategy.getInstance();
    }

    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#visitConstantNullEquivalent(com.xylocore.commons.data.copybook.domain.config.ConstantNullEquivalentConfig)
     */
    public void visitConstantNullEquivalent( ConstantNullEquivalentConfig aNullEquivalent )
    {
        nullEquivalentStrategy =
                new ConstantNullEquivalentStrategy( getDataUsageCategory(),
                                                    aNullEquivalent.getDataUsageCategory(),
                                                    aNullEquivalent.getValue()              );
    }
}
