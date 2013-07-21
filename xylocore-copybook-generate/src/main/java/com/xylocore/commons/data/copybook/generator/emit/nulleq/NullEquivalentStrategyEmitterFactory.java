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


package com.xylocore.commons.data.copybook.generator.emit.nulleq;

import java.util.HashMap;
import java.util.Map;

import com.xylocore.commons.data.copybook.runtime.nulleq.BlankNullEquivalentStrategy;
import com.xylocore.commons.data.copybook.runtime.nulleq.ConstantNullEquivalentStrategy;
import com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */
public class NullEquivalentStrategyEmitterFactory
{
    //
    // Members
    //
    
    
    private static final NullEquivalentStrategyEmitterFactory           instance;
    private static final Map<Class<?>,NullEquivalentStrategyEmitter<?>> emitterMappings;

    
    
    
    //
    // Static initialization
    //
    
    
    static
    {
        emitterMappings = new HashMap<>();
        emitterMappings.put( ConstantNullEquivalentStrategy.class, new ConstantNullEquivalentStrategyEmitter() );
        emitterMappings.put( BlankNullEquivalentStrategy.class,    new BlankNullEquivalentStrategyEmitter()    );
        
        instance = new NullEquivalentStrategyEmitterFactory();
    }
    
    
    
    //
    // Class implementation
    //
    

    /**
     * Private constructor as per the singleton pattern.
     */
    private NullEquivalentStrategyEmitterFactory()
    {
    }


    /**
     * FILLIN
     * 
     * @return
     */
    public static NullEquivalentStrategyEmitterFactory getInstance()
    {
        return instance;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aNullEquivalentStrategy
     * 
     * @return
     */
    public <T extends NullEquivalentStrategy> NullEquivalentStrategyEmitter<T> getEmitter( NullEquivalentStrategy aNullEquivalentStrategy )
    {
        @SuppressWarnings( "unchecked" )
        NullEquivalentStrategyEmitter<T> myEmitter =
                (NullEquivalentStrategyEmitter<T>) emitterMappings.get( aNullEquivalentStrategy.getClass() );
        
        if ( myEmitter == null )
        {
            // TODO: better exception
            throw new RuntimeException( "cannot find emitter for " + aNullEquivalentStrategy.getClass().getName() );
        }
        
        return myEmitter;
    }
}
