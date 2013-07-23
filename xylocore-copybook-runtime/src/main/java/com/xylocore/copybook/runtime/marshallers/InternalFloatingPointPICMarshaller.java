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


package com.xylocore.copybook.runtime.marshallers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

import com.xylocore.copybook.runtime.ConstantValue;
import com.xylocore.copybook.runtime.CopybookContext;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public interface InternalFloatingPointPICMarshaller
    extends
        PICMarshaller
{
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public boolean isValid( CopybookContext   aContext,
                            int               aOffset   );


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public byte decodeAsByte( CopybookContext   aContext,
                              int               aOffset   );
    

    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeAsByte( CopybookContext   aContext,
                              int               aOffset,
                              byte              aValue    );
    

    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public char decodeAsChar( CopybookContext   aContext,
                              int               aOffset   );
    

    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeAsChar( CopybookContext   aContext,
                              int               aOffset,
                              char              aValue    );

    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public short decodeAsShort( CopybookContext   aContext,
                                int               aOffset   );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeAsShort( CopybookContext   aContext,
                               int               aOffset,
                               short             aValue    );

    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public int decodeAsInteger( CopybookContext   aContext,
                                int               aOffset   );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeAsInteger( CopybookContext   aContext,
                                 int               aOffset,
                                 int               aValue    );

    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public long decodeAsLong( CopybookContext   aContext,
                              int               aOffset   );
    
 
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeAsLong( CopybookContext   aContext,
                              int               aOffset,
                              long              aValue    );

    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public float decodeAsFloat( CopybookContext   aContext,
                                int               aOffset   );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeAsFloat( CopybookContext   aContext,
                               int               aOffset,
                               float             aValue    );

    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public double decodeAsDouble( CopybookContext   aContext,
                                  int               aOffset   );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeAsDouble( CopybookContext   aContext,
                                int               aOffset,
                                double            aValue    );

    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public BigInteger decodeAsBigInteger( CopybookContext   aContext,
                                          int               aOffset   );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeAsBigInteger( CopybookContext   aContext,
                                    int               aOffset,
                                    BigInteger        aValue    );

    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public BigDecimal decodeAsBigDecimal( CopybookContext   aContext,
                                          int               aOffset   );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeAsBigDecimal( CopybookContext   aContext,
                                    int               aOffset,
                                    BigDecimal        aValue    );

    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public String decodeAsString( CopybookContext   aContext,
                                  int               aOffset   );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeAsString( CopybookContext   aContext,
                                int               aOffset,
                                String            aValue    );

    
    /**
     * FILLIN
     *
     * @param       aContext
     * @param       aOffset
     * @param       aConditionName
     * @param       aConditionNameValueMappings
     * @return
     */
    public boolean isConditionNameValid( CopybookContext               aContext,                   
                                         int                           aOffset,                    
                                         String                        aConditionName,             
                                         Map<String,ConstantValue[]>   aConditionNameValueMappings );
}
