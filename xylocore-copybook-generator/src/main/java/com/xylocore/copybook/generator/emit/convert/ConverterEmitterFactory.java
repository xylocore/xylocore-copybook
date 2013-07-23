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


package com.xylocore.copybook.generator.emit.convert;

import java.util.HashMap;
import java.util.Map;

import com.xylocore.copybook.runtime.converters.BigDecimalConverter;
import com.xylocore.copybook.runtime.converters.Converter;
import com.xylocore.copybook.runtime.converters.SimpleDateFormatDateConverter;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */
public class ConverterEmitterFactory
{
    //
    // Members
    //
    
    
    private static final ConverterEmitterFactory            instance;
    private static final Map<Class<?>,ConverterEmitter<?>>  emitterMappings;

    
    
    
    //
    // Static initialization
    //
    
    
    static
    {
        emitterMappings = new HashMap<>();
        emitterMappings.put( SimpleDateFormatDateConverter.class, new SimpleDateFormatDateConverterEmitter() );
        emitterMappings.put( BigDecimalConverter.class,           new BigDecimalConverterEmitter()           );
        
        instance = new ConverterEmitterFactory();
    }
    
    
    
    //
    // Class implementation
    //
    

    /**
     * Private constructor as per the singleton pattern.
     */
    private ConverterEmitterFactory()
    {
    }


    /**
     * FILLIN
     * 
     * @return
     */
    public static ConverterEmitterFactory getInstance()
    {
        return instance;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aConverter
     * 
     * @return
     */
    public <T extends Converter> ConverterEmitter<T> getEmitter( Converter aConverter )
    {
        @SuppressWarnings( "unchecked" )
        ConverterEmitter<T> myEmitter = (ConverterEmitter<T>) emitterMappings.get( aConverter.getClass() );
        
        if ( myEmitter == null )
        {
            // TODO: better exception
            throw new RuntimeException( "cannot find emitter for " + aConverter.getClass().getName() );
        }
        
        return myEmitter;
    }
}
