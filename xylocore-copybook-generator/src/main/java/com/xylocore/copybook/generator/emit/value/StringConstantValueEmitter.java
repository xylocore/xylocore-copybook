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

import com.xylocore.commons.util.BufferEmitter;
import com.xylocore.copybook.runtime.ConstantCharacterString;
import com.xylocore.copybook.runtime.ConstantValue;


/**
 * FILLIN
 *
 * @author      Eric R. Medley
 */

public class StringConstantValueEmitter
    extends
        AbstractConstantValueEmitter
{
    //
    // Class implementation
    //
    
    
    @Override
    protected void emitInstantiationParameters( BufferEmitter   aEmitter,
                                                ConstantValue   aValue    )
    {
        if ( aValue.getValue1() instanceof ConstantCharacterString )
        {
            ConstantCharacterString myConstantCharacterString = (ConstantCharacterString) aValue.getValue1();
            
            aEmitter.append( " '" );
            
            appendEscapedChar( aEmitter, myConstantCharacterString.getConstantChar() );

            aEmitter.append( "', "                              )
                    .append( myConstantCharacterString.length() )
                    .append( " "                                )
                    ;
        }
        else
        {
            super.emitInstantiationParameters( aEmitter, aValue );
        }
    }
}
