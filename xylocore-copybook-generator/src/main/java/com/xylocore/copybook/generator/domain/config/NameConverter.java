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

import com.xylocore.copybook.runtime.DataType;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public interface NameConverter
{
    /**
     * FILLIN
     * 
     * @param       aSource
     * 
     * @return
     */
    public String convertToBeanName( String aSource );
    
    
    /**
     * FILLIN
     *
     * @param       aSource
     * 
     * @return
     */
    public String generateRecordLengthMethodName( String aSource );
    
    
    /**
     * FILLIN
     *
     * @param       aSource
     * 
     * @return
     */
    public String generateOffsetMethodName( String aSource );
    
    
    /**
     * FILLIN
     *
     * @param       aSource
     * 
     * @return
     */
    public String generateLengthMethodName( String aSource );
    
    
    /**
     * FILLIN
     *
     * @param       aSource
     * 
     * @return
     */
    public String generateTotalLengthMethodName( String aSource );
    
    
    /**
     * FILLIN
     *
     * @param       aSource
     * 
     * @return
     */
    public String generateSingleElementLengthMethodName( String aSource );
    
    
    /**
     * FILLIN
     *
     * @param       aSource
     * 
     * @return
     */
    public String generateBlankMethodName( String aSource );
    
    
    /**
     * FILLIN
     *
     * @param       aSource
     * 
     * @return
     */
    public String generateNullMethodName( String aSource );
    
    
    /**
     * FILLIN
     *
     * @param       aSource
     * @param       aDataType
     * @param       aIsDefault
     * @param       aHasMultipleDatatypes
     * 
     * @return
     */
    public String generateValidMethodName( String     aSource,
                                           DataType   aDataType,
                                           boolean    aIsDefault,
                                           boolean    aHasMultipleDatatypes );
    
    
    /**
     * FILLIN
     *
     * @param       aSource
     * @param       aDataType
     * @param       aIsDefault
     * @param       aHasMultipleDatatypes
     * 
     * @return
     */
    public String generateGetterMethodName( String     aSource,
                                            DataType   aDataType,
                                            boolean    aIsDefault,
                                            boolean    aHasMultipleDatatypes );
    
    
    /**
     * FILLIN
     *
     * @param       aSource
     * @param       aDataType
     * @param       aIsDefault
     * @param       aHasMultipleDatatypes
     * 
     * @return
     */
    public String generateSetterMethodName( String     aSource,
                                            DataType   aDataType,
                                            boolean    aIsDefault,
                                            boolean    aHasMultipleDatatypes );
    
    
    /**
     * FILLIN
     *
     * @param       aSource
     * 
     * @return
     */
    public String generateConditionNameMethodName( String aSource );
}
