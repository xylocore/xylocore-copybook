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


package com.xylocore.copybook.generator.parser.visitor;

import com.xylocore.copybook.generator.domain.AccessorMethodInfo;
import com.xylocore.copybook.generator.domain.BigDecimalAccessorMethodInfo;
import com.xylocore.copybook.generator.domain.BigIntegerAccessorMethodInfo;
import com.xylocore.copybook.generator.domain.ByteAccessorMethodInfo;
import com.xylocore.copybook.generator.domain.CharAccessorMethodInfo;
import com.xylocore.copybook.generator.domain.DateAccessorMethodInfo;
import com.xylocore.copybook.generator.domain.DoubleAccessorMethodInfo;
import com.xylocore.copybook.generator.domain.FloatAccessorMethodInfo;
import com.xylocore.copybook.generator.domain.IntegerAccessorMethodInfo;
import com.xylocore.copybook.generator.domain.LongAccessorMethodInfo;
import com.xylocore.copybook.generator.domain.ShortAccessorMethodInfo;
import com.xylocore.copybook.generator.domain.StringAccessorMethodInfo;
import com.xylocore.copybook.generator.domain.config.AccessorConfig;
import com.xylocore.copybook.generator.domain.config.BigDecimalAccessorConfig;
import com.xylocore.copybook.generator.domain.config.BigIntegerAccessorConfig;
import com.xylocore.copybook.generator.domain.config.ByteAccessorConfig;
import com.xylocore.copybook.generator.domain.config.CharAccessorConfig;
import com.xylocore.copybook.generator.domain.config.ConfigVisitor;
import com.xylocore.copybook.generator.domain.config.DateAccessorConfig;
import com.xylocore.copybook.generator.domain.config.DefaultAccessorConfig;
import com.xylocore.copybook.generator.domain.config.DoubleAccessorConfig;
import com.xylocore.copybook.generator.domain.config.FloatAccessorConfig;
import com.xylocore.copybook.generator.domain.config.IntegerAccessorConfig;
import com.xylocore.copybook.generator.domain.config.LongAccessorConfig;
import com.xylocore.copybook.generator.domain.config.NoDefaultAccessorConfig;
import com.xylocore.copybook.generator.domain.config.ShortAccessorConfig;
import com.xylocore.copybook.generator.domain.config.StringAccessorConfig;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class AccessorMethodInfoCreationVisitor
    extends
        ConfigVisitor
{
    //
    // Members
    //
    
    
    private AccessorMethodInfo  accessorMethodInfo;
    private boolean             skipAccessor;
    

    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessor
     * 
     * @return
     */
    public AccessorMethodInfo create( AccessorConfig aAccessor )
    {
        assert aAccessor != null;
        
        skipAccessor = false;
        
        aAccessor.accept( this );
        
        return accessorMethodInfo;
    }
    
 
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#visitDefaultAccessor(com.xylocore.commons.data.copybook.domain.config.DefaultAccessorConfig)
     */
    public void visitDefaultAccessor( DefaultAccessorConfig aAccessor )
    {
        skipAccessor = true;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#leaveDefaultAccessor(com.xylocore.commons.data.copybook.domain.config.DefaultAccessorConfig)
     */
    public void leaveDefaultAccessor( DefaultAccessorConfig aAccessor )
    {
        skipAccessor = false;
    }
    
 
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#visitNoDefaultAccessor(com.xylocore.commons.data.copybook.domain.config.NoDefaultAccessorConfig)
     */
    public void visitNoDefaultAccessor( NoDefaultAccessorConfig aAccessor )
    {
        skipAccessor = true;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#leaveNoDefaultAccessor(com.xylocore.commons.data.copybook.domain.config.NoDefaultAccessorConfig)
     */
    public void leaveNoDefaultAccessor( NoDefaultAccessorConfig aAccessor )
    {
        skipAccessor = false;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#visitByteAccessor(com.xylocore.commons.data.copybook.domain.config.ByteAccessorConfig)
     */
    public void visitByteAccessor( ByteAccessorConfig aAccessor )
    {
        ByteAccessorMethodInfo myAccessorMethodInfo = new ByteAccessorMethodInfo();
        accessorMethodInfo = myAccessorMethodInfo;        
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#visitCharAccessor(com.xylocore.commons.data.copybook.domain.config.CharAccessorConfig)
     */
    public void visitCharAccessor( CharAccessorConfig aAccessor )
    {
        CharAccessorMethodInfo myAccessorMethodInfo = new CharAccessorMethodInfo();
        accessorMethodInfo = myAccessorMethodInfo;        
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#visitShortAccessor(com.xylocore.commons.data.copybook.domain.config.ShortAccessorConfig)
     */
    public void visitShortAccessor( ShortAccessorConfig aAccessor )
    {
        ShortAccessorMethodInfo myAccessorMethodInfo = new ShortAccessorMethodInfo();
        accessorMethodInfo = myAccessorMethodInfo;        
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#visitIntegerAccessor(com.xylocore.commons.data.copybook.domain.config.IntegerAccessorConfig)
     */
    public void visitIntegerAccessor( IntegerAccessorConfig aAccessor )
    {
        IntegerAccessorMethodInfo myAccessorMethodInfo = new IntegerAccessorMethodInfo();
        accessorMethodInfo = myAccessorMethodInfo;        
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#visitLongAccessor(com.xylocore.commons.data.copybook.domain.config.LongAccessorConfig)
     */
    public void visitLongAccessor( LongAccessorConfig aAccessor )
    {
        LongAccessorMethodInfo myAccessorMethodInfo = new LongAccessorMethodInfo();
        accessorMethodInfo = myAccessorMethodInfo;        
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#visitFloatAccessor(com.xylocore.commons.data.copybook.domain.config.FloatAccessorConfig)
     */
    public void visitFloatAccessor( FloatAccessorConfig aAccessor )
    {
        FloatAccessorMethodInfo myAccessorMethodInfo = new FloatAccessorMethodInfo();
        accessorMethodInfo = myAccessorMethodInfo;        
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#visitDoubleAccessor(com.xylocore.commons.data.copybook.domain.config.DoubleAccessorConfig)
     */
    public void visitDoubleAccessor( DoubleAccessorConfig aAccessor )
    {
        DoubleAccessorMethodInfo myAccessorMethodInfo = new DoubleAccessorMethodInfo();
        accessorMethodInfo = myAccessorMethodInfo;        
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#visitBigIntegerAccessor(com.xylocore.commons.data.copybook.domain.config.BigIntegerAccessorConfig)
     */
    public void visitBigIntegerAccessor( BigIntegerAccessorConfig aAccessor )
    {
        BigIntegerAccessorMethodInfo myAccessorMethodInfo = new BigIntegerAccessorMethodInfo();
        accessorMethodInfo = myAccessorMethodInfo;        
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#visitBigDecimalAccessor(com.xylocore.commons.data.copybook.domain.config.BigDecimalAccessorConfig)
     */
    public void visitBigDecimalAccessor( BigDecimalAccessorConfig aAccessor )
    {
        BigDecimalAccessorMethodInfo myAccessorMethodInfo = new BigDecimalAccessorMethodInfo();
        myAccessorMethodInfo.setScale( aAccessor.getScale() );
        
        accessorMethodInfo = myAccessorMethodInfo;        
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#visitStringAccessor(com.xylocore.commons.data.copybook.domain.config.StringAccessorConfig)
     */
    public void visitStringAccessor( StringAccessorConfig aAccessor )
    {
        StringAccessorMethodInfo myAccessorMethodInfo = new StringAccessorMethodInfo();
        accessorMethodInfo = myAccessorMethodInfo;        
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#visitDateAccessor(com.xylocore.commons.data.copybook.domain.config.DateAccessorConfig)
     */
    public void visitDateAccessor( DateAccessorConfig aAccessor )
    {
        DateAccessorMethodInfo myAccessorMethodInfo = new DateAccessorMethodInfo();
        myAccessorMethodInfo.setPattern( aAccessor.getPattern() );
        
        accessorMethodInfo = myAccessorMethodInfo;        
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#visitAccessor(com.xylocore.commons.data.copybook.domain.config.AccessorConfig)
     */
    public void visitAccessor( AccessorConfig aAccessor )
    {
        if ( ! skipAccessor )
        {
            accessorMethodInfo.setDefault( aAccessor.isDefault() );
        }
    }
}
