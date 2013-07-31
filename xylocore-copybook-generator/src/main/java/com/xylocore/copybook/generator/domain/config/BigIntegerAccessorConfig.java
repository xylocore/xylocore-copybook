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


package com.xylocore.copybook.generator.domain.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.xylocore.copybook.runtime.DataType;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

@XmlAccessorType( XmlAccessType.PROPERTY        )
@XmlRootElement ( name = "big-integer-accessor" )
public class BigIntegerAccessorConfig
    extends
        AccessorConfig
{
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     */
    public BigIntegerAccessorConfig()
    {
        super( DataType.BigInteger );
    }
    
    
    @Override
    protected void acceptVisit( ConfigVisitor aVisitor )
    {
        assert aVisitor != null;
        
        aVisitor.visitBigIntegerAccessor( this );
        
        super.acceptVisit( aVisitor );
    }
    
    
    @Override
    protected void acceptLeave( ConfigVisitor aVisitor )
    {
        assert aVisitor != null;
        
        super.acceptLeave( aVisitor );
        
        aVisitor.leaveBigIntegerAccessor( this );
    }
}
