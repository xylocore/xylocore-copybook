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

import com.xylocore.commons.util.BufferEmitter;
import com.xylocore.copybook.runtime.converters.BigDecimalConverter;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class BigDecimalConverterEmitter
    implements
        ConverterEmitter<BigDecimalConverter>
{
    //
    // ConverterEmitter interface implementation
    //
    

    @Override
    public void emitDeclaration( BufferEmitter         aEmitter,
                                 BigDecimalConverter   aConverter )
    {
        aEmitter.append( "new "                )
                .append( getClass().getName()  )
                .append( "( "                  )
                .append( aConverter.getScale() )
                .append( " )"                  )
                ;
    }
}
