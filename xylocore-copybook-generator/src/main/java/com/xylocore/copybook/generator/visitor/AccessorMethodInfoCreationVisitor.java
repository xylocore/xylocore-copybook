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


package com.xylocore.copybook.generator.visitor;

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
    
 
    @Override
    public void visitDefaultAccessor( DefaultAccessorConfig aAccessor )
    {
        skipAccessor = true;
    }
    
    
    @Override
    public void leaveDefaultAccessor( DefaultAccessorConfig aAccessor )
    {
        skipAccessor = false;
    }
    
 
    @Override
    public void visitNoDefaultAccessor( NoDefaultAccessorConfig aAccessor )
    {
        skipAccessor = true;
    }
    
    
    @Override
    public void leaveNoDefaultAccessor( NoDefaultAccessorConfig aAccessor )
    {
        skipAccessor = false;
    }
    
    
    @Override
    public void visitByteAccessor( ByteAccessorConfig aAccessor )
    {
        accessorMethodInfo = new ByteAccessorMethodInfo();
    }


    @Override
    public void visitCharAccessor( CharAccessorConfig aAccessor )
    {
        accessorMethodInfo = new CharAccessorMethodInfo();
    }


    @Override
    public void visitShortAccessor( ShortAccessorConfig aAccessor )
    {
        accessorMethodInfo = new ShortAccessorMethodInfo();
    }


    @Override
    public void visitIntegerAccessor( IntegerAccessorConfig aAccessor )
    {
        accessorMethodInfo = new IntegerAccessorMethodInfo();
    }


    @Override
    public void visitLongAccessor( LongAccessorConfig aAccessor )
    {
        accessorMethodInfo = new LongAccessorMethodInfo();
    }


    @Override
    public void visitFloatAccessor( FloatAccessorConfig aAccessor )
    {
        accessorMethodInfo = new FloatAccessorMethodInfo();
    }


    @Override
    public void visitDoubleAccessor( DoubleAccessorConfig aAccessor )
    {
        accessorMethodInfo = new DoubleAccessorMethodInfo();
    }


    @Override
    public void visitBigIntegerAccessor( BigIntegerAccessorConfig aAccessor )
    {
        accessorMethodInfo = new BigIntegerAccessorMethodInfo();
    }


    @Override
    public void visitBigDecimalAccessor( BigDecimalAccessorConfig aAccessor )
    {
        BigDecimalAccessorMethodInfo myAccessorMethodInfo = new BigDecimalAccessorMethodInfo();
        myAccessorMethodInfo.setScale( aAccessor.getScale() );
        
        accessorMethodInfo = myAccessorMethodInfo;        
    }


    @Override
    public void visitStringAccessor( StringAccessorConfig aAccessor )
    {
        accessorMethodInfo = new StringAccessorMethodInfo();
    }


    @Override
    public void visitDateAccessor( DateAccessorConfig aAccessor )
    {
        DateAccessorMethodInfo myAccessorMethodInfo = new DateAccessorMethodInfo();
        myAccessorMethodInfo.setPattern( aAccessor.getPattern() );
        
        accessorMethodInfo = myAccessorMethodInfo;        
    }
    
    
    @Override
    public void visitAccessor( AccessorConfig aAccessor )
    {
        if ( ! skipAccessor )
        {
            accessorMethodInfo.setDefault( aAccessor.isDefault() );
        }
    }
}
