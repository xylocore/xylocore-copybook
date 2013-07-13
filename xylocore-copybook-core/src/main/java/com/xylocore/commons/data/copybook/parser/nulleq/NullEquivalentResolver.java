package com.xylocore.commons.data.copybook.parser.nulleq;

import com.xylocore.commons.data.copybook.domain.DataElement;
import com.xylocore.commons.data.copybook.domain.config.BlankNullEquivalentConfig;
import com.xylocore.commons.data.copybook.domain.config.ConfigVisitor;
import com.xylocore.commons.data.copybook.domain.config.ConstantNullEquivalentConfig;
import com.xylocore.commons.data.copybook.domain.config.NullEquivalentConfig;
import com.xylocore.commons.data.copybook.runtime.DataUsageCategory;
import com.xylocore.commons.data.copybook.runtime.nulleq.BlankNullEquivalentStrategy;
import com.xylocore.commons.data.copybook.runtime.nulleq.ConstantNullEquivalentStrategy;
import com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy;


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
// TODO: remove
if ( "IPPFX7-MASTER-CO-NUMBER".equals( aDataElement.getName() ) )
{
    System.out.println();
}
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
