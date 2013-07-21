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


package com.xylocore.commons.data.copybook.generator.emit.pic;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class NumericNationalPICMarshallerEmitter
    extends
        AbstractNumericDisplayPICMarshallerEmitter
{
    //
    // Members
    //
    
    
    private static final NumericNationalPICMarshallerEmitter instance = new NumericNationalPICMarshallerEmitter();
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * Private constructor as per the singleton pattern.
     */
    private NumericNationalPICMarshallerEmitter()
    {
        super( "NumericNational" );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public static NumericNationalPICMarshallerEmitter getInstance()
    {
        return instance;
    }
}
