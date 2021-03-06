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


package com.xylocore.copybook.generator.emit.value;

import java.util.HashMap;
import java.util.Map;

import com.xylocore.copybook.runtime.ConstantValue;
import com.xylocore.copybook.runtime.StringConstantValue;


/**
 * FILLIN
 *
 * @author      Eric R. Medley
 */

public class ConstantValueEmitterFactory
{
    //
    // Members
    //
    
    
    private static final ConstantValueEmitterFactory            instance            = new ConstantValueEmitterFactory();
    
    private static final Map<String,StringConstantValueEmitter> emitterMappings;
    private static final ConstantValueEmitter                   defaultEmitter;
    
    
    

    //
    // Static initialization
    //
    
    
    static
    {
        emitterMappings = new HashMap<>();
        defaultEmitter  = new DefaultConstantValueEmitter();
        
        emitterMappings.put( StringConstantValue.class.getName(), new StringConstantValueEmitter() );
    }
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * Private constructor as per singleton pattern.
     */
    private ConstantValueEmitterFactory()
    {
    }
    
    
    /**
     * FILLIN
     *
     * @return
     */
    public static ConstantValueEmitterFactory getInstance()
    {
        return instance;
    }
    
    
    /**
     * FILLIN
     *
     * @param       aValue
     * 
     * @return
     */
    public ConstantValueEmitter newInstance( ConstantValue aValue )
    {
        assert aValue != null;
        
        ConstantValueEmitter myEmitter = emitterMappings.get( aValue.getClass().getName() );
        if ( myEmitter == null )
        {
            myEmitter = defaultEmitter;
        }
        
        return myEmitter;
    }
}
