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


package com.xylocore.copybook.generator.emit.pic;

import com.xylocore.copybook.generator.domain.AccessorMethodInfo;
import com.xylocore.copybook.generator.domain.DataElement;
import com.xylocore.copybook.generator.domain.Element;
import com.xylocore.copybook.generator.domain.Level88Element;
import com.xylocore.copybook.generator.domain.Value;
import com.xylocore.copybook.generator.domain.ValueRange;
import com.xylocore.copybook.generator.emit.BufferEmitter;
import com.xylocore.copybook.generator.emit.IndexOffsets;
import com.xylocore.copybook.generator.emit.value.ConstantValueEmitter;
import com.xylocore.copybook.generator.emit.value.ConstantValueEmitterFactory;
import com.xylocore.copybook.runtime.ConstantValue;
import com.xylocore.copybook.runtime.DataType;
import com.xylocore.copybook.runtime.marshallers.PICMarshaller;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public abstract class AbstractPICMarshallerEmitter
    implements
        PICMarshallerEmitter
{
    //
    // Members
    //
    
    
    private String nativeType;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * Constructor.
     */
    public AbstractPICMarshallerEmitter( String aNativeType )
    {
        assert aNativeType != null && aNativeType.length() != 0;
        
        nativeType = aNativeType;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDataType
     * 
     * @return
     */
    protected String getDelegateDecodeMethodName( DataType aDataType )
    {
        return getDelegateAccessorMethodName( aDataType, "decode" );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDataType
     * 
     * @return
     */
    protected String getDelegateEncodeMethodName( DataType aDataType )
    {
        return getDelegateAccessorMethodName( aDataType, "encode" );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDataType
     * 
     * @return
     */
    protected String getDelegateAccessorMethodName( DataType   aDataType,
                                                    String     aDirection )
    {
        assert aDataType != null;
        
        String mySuffix = aDataType.getAsSuffix();

        return aDirection + getNativeType() + "As" + mySuffix;
    }

    
    /**
     * FILLIN
     * 
     * @return
     */
    protected String getDelegateIsBlankMethodName()
    {
        return "is" + getNativeType() + "Blank";
    }

    
    /**
     * FILLIN
     * 
     * @return
     */
    protected String getDelegateIsNullMethodName()
    {
        return "isNull";
    }

    
    /**
     * FILLIN
     * 
     * @param       aDataType
     * 
     * @return
     */
    protected String getDelegateIsValidMethodName( DataType aDataType )
    {
        assert aDataType != null;
        
        String mySuffix = aDataType.getAsSuffix();

        return "is" + getNativeType() + "As" + mySuffix + "Valid";
    }
    

    /**
     * FILLIN
     *
     * @return
     */
    protected String getDelegateConditionNameMethodName()
    {
        return "is" + getNativeType() + "ConditionNameValid";
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    protected String getNativeType()
    {
        return nativeType;
    }
    

    /**
     * FILLIN
     * 
     * @param       aEmitter
     * @param       aElement
     */
    protected void emitIndexOffsets( BufferEmitter   aEmitter,
                                     Element         aElement  )
    {
        IndexOffsets myIndexOffset = new IndexOffsets();
        myIndexOffset.emit( aEmitter, aElement );
    }

    
    /**
     * FILLIN
     * 
     * @param       aEmitter
     * @param       aElement
     * @param       aAccessorMethodInfo
     */
    protected void emitVariableReaderMethodParts( BufferEmitter        aEmitter,
                                                  Element              aElement,
                                                  AccessorMethodInfo   aAccessorMethodInfo )
    {
        assert aEmitter            != null;
        assert aElement            != null;
        assert aAccessorMethodInfo != null;
        
        emitVariableMethodParts( aEmitter, aElement, aAccessorMethodInfo );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aEmitter
     * @param       aElement
     * @param       aAccessorMethodInfo
     */
    protected void emitVariableWriterMethodParts( BufferEmitter        aEmitter,
                                                  Element              aElement,
                                                  AccessorMethodInfo   aAccessorMethodInfo )
    {
        assert aEmitter            != null;
        assert aElement            != null;
        assert aAccessorMethodInfo != null;
        
        emitVariableMethodParts( aEmitter, aElement, aAccessorMethodInfo );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aEmitter
     * @param       aElement
     * @param       aAccessorMethodInfo
     */
    protected void emitVariableMethodParts( BufferEmitter        aEmitter,
                                            Element              aElement,
                                            AccessorMethodInfo   aAccessorMethodInfo )
    {
        //  By default assume that no additional parts are needed
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aEmitter
     * @param       aElement
     */
    protected void emitVariableIsBlankMethodParts( BufferEmitter   aEmitter,
                                                   Element         aElement  )
    {
        //  By default assume that no additional parts are needed
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aEmitter
     * @param       aElement
     */
    protected void emitVariableIsNullMethodParts( BufferEmitter   aEmitter,
                                                  Element         aElement  )
    {
        //  By default assume that no additional parts are needed
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aEmitter
     * @param       aElement
     * @param       aAccessorMethodInfo
     */
    protected void emitVariableIsValidMethodParts( BufferEmitter        aEmitter,
                                                   Element              aElement,
                                                   AccessorMethodInfo   aAccessorMethodInfo )
    {
        //  By default assume that no additional parts are needed
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aEmitter
     * @param       aElement
     * @param       aAccessorMethodInfo
     */
    protected void emitVariableConverterParts( BufferEmitter        aEmitter,
                                               Element              aElement,
                                               AccessorMethodInfo   aAccessorMethodInfo )
    {
    }
    
    
    /**
     * FILLIN
     *
     * @param       aEmitter
     * @param       aElement
     */
    protected void emitVariableNullEquivalentStrategiesParts( BufferEmitter   aEmitter,
                                                              DataElement     aElement  )
    {
    }
    
    
    /**
     * FILLIN
     *
     * @param       aEmitter
     * @param       aConditionalVariableElement
     */
    protected abstract void emitVariableConditionNameMethodParts( BufferEmitter   aEmitter,
                                                                  Element         aConditionalVariableElement );
    
    
    
    /**
     * FILLIN
     *
     * @param       aElement
     * @param       aValueRange
     * 
     * @return
     */
    protected ConstantValue convertValueRange( Element      aElement,
                                               ValueRange   aValueRange )
    {
        Comparable<?> myConvertedValue1 = null;
        Comparable<?> myConvertedValue2 = null;
        
        if ( aValueRange.isSingleValue() )
        {
            myConvertedValue1 = convertValue( aElement, aValueRange.getValue1() );
        }
        else
        {
            myConvertedValue1 = convertValue( aElement, aValueRange.getValue1() );
            myConvertedValue2 = convertValue( aElement, aValueRange.getValue2() );
        }
        
        return createConstantValue( myConvertedValue1, myConvertedValue2 );
    }
    
    
    /**
     * FILLIN
     *
     * @param       aElement
     * @param       aValue
     * 
     * @return
     */
    protected abstract Comparable<?> convertValue( Element   aElement,
                                                   Value     aValue    );
    
    
    /**
     * FILLIN
     *
     * @param       aValue1
     * @param       aValue2
     * 
     * @return
     */
    protected ConstantValue createConstantValue( Comparable<?>   aValue1,
                                                 Comparable<?>   aValue2  )
    {
        return new ConstantValue( aValue1, aValue2 );
    }
    
    
    
    
    //
    // PICMarshallerEmitter interface implementation
    //
    
    
    public void emitMarshaller( BufferEmitter   aEmitter,
                                PICMarshaller   aMarshaller,
                                String          aInstanceName )
    {
        throw new UnsupportedOperationException( "the marshaller emitter "                +
                                                 getClass().getName()                     +
                                                 " does not support emitting marshallers"   );
    }
    
    
    public void emitValueRange( BufferEmitter   aEmitter,
                                Element         aElement,
                                ValueRange      aValueRange )
    {
        ConstantValue        myConstantValue = convertValueRange( aElement, aValueRange );
        ConstantValueEmitter myValueEmitter  = ConstantValueEmitterFactory.getInstance().newInstance( myConstantValue );

        myValueEmitter.emit( aEmitter, myConstantValue );
    }
    
    
    public void emitReader( BufferEmitter        aEmitter,
                            PICMarshaller        aMarshaller,
                            Element              aElement,
                            AccessorMethodInfo   aAccessorMethodInfo )
    {
        assert aEmitter            != null;
        assert aMarshaller         == null;
        assert aElement            != null;
        assert aAccessorMethodInfo != null;
        
        DataType myDataType   = aAccessorMethodInfo.getDataType();
        String   myMethodName = getDelegateDecodeMethodName( myDataType );
        
        aEmitter.append( "return "      )
                .append( myMethodName   )
                .append( "( aContext, " )
                ;
        
        emitIndexOffsets( aEmitter, aElement );
        
        emitVariableReaderMethodParts( aEmitter, aElement, aAccessorMethodInfo );
        
        aEmitter.append( " );" );
    }


    public void emitWriter( BufferEmitter        aEmitter,
                            PICMarshaller        aMarshaller,
                            Element              aElement,
                            AccessorMethodInfo   aAccessorMethodInfo )
    {
        assert aEmitter            != null;
        assert aMarshaller         == null;
        assert aElement            != null;
        assert aAccessorMethodInfo != null;
        
        DataType myDataType   = aAccessorMethodInfo.getDataType();
        String   myMethodName = getDelegateEncodeMethodName( myDataType );
        
        aEmitter.append( myMethodName   )
                .append( "( aContext, " )
                ;

        emitIndexOffsets( aEmitter, aElement );
        
        aEmitter.append( ", aValue" );
        
        emitVariableWriterMethodParts( aEmitter, aElement, aAccessorMethodInfo );
        
        aEmitter.append( " );" );
    }
    
    
    public boolean isBlankMethodNeeded( Element aElement )
    {
        return false;
    }
    
    
    public void emitBlankMethodCall( BufferEmitter   aEmitter,
                                     PICMarshaller   aMarshaller,
                                     Element         aElement     )
    {
        assert aEmitter            != null;
        assert aMarshaller         == null;
        assert aElement            != null;
        assert isBlankMethodNeeded( aElement );
        
        String myMethodName = getDelegateIsBlankMethodName();
        
        aEmitter.append( "return "      )
                .append( myMethodName   )
                .append( "( aContext, " )
                ;

        emitIndexOffsets              ( aEmitter, aElement );
        emitVariableIsBlankMethodParts( aEmitter, aElement );
        
        aEmitter.append( " );" );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     * 
     * @return
     */
    public boolean isNullMethodNeeded( DataElement aElement )
    {
        return ( ! aElement.getNullEquivalentStrategies().isEmpty() );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aEmitter
     * @param       aMarshaller
     * @param       aElement
     */
    public void emitNullMethodCall( BufferEmitter   aEmitter,
                                    PICMarshaller   aMarshaller,
                                    DataElement     aElement     )
    {
        assert aEmitter    != null;
        assert aMarshaller == null;
        assert aElement    != null;
        assert isNullMethodNeeded( aElement );
        
        String myMethodName = getDelegateIsNullMethodName();
        
        aEmitter.append( "return "      )
                .append( myMethodName   )
                .append( "( aContext, " )
                ;

        emitIndexOffsets             ( aEmitter, aElement );
        emitVariableIsNullMethodParts( aEmitter, aElement );
        
        aEmitter.append( " );" );
    }
    
    
    public boolean isValidMethodNeeded( Element              aElement,
                                        AccessorMethodInfo   aAccessorMethodInfo )
    {
        return true;
    }
    
    
    public void emitValidMethodCall( BufferEmitter        aEmitter,
                                     PICMarshaller        aMarshaller,
                                     Element              aElement,
                                     AccessorMethodInfo   aAccessorMethodInfo )
    {
        assert aEmitter            != null;
        assert aMarshaller         == null;
        assert aElement            != null;
        assert aAccessorMethodInfo != null;
        assert isValidMethodNeeded( aElement, aAccessorMethodInfo );
        
        DataType myDataType   = aAccessorMethodInfo.getDataType();
        String   myMethodName = getDelegateIsValidMethodName( myDataType );
        
        aEmitter.append( "return "      )
                .append( myMethodName   )
                .append( "( aContext, " )
                ;

        emitIndexOffsets              ( aEmitter, aElement                      );
        emitVariableIsValidMethodParts( aEmitter, aElement, aAccessorMethodInfo );

        aEmitter.append( " );" );
    }
    
    
    public void emitConditionNameMethodCall( BufferEmitter    aEmitter,
                                             Element          aConditionalVariableElement,
                                             Level88Element   aConditionNameElement,
                                             String           aConditionNameValueMapName   )
    
    {
        assert aEmitter                    != null;
        assert aConditionalVariableElement != null;
        assert aConditionNameElement       != null;
        
        String myMethodName = getDelegateConditionNameMethodName();
        
        aEmitter.append( "return "      )
                .append( myMethodName   )
                .append( "( aContext, " )
                ;
        
        emitIndexOffsets( aEmitter, aConditionalVariableElement );
        
        aEmitter.append( ", \""                          )
                .append( aConditionNameElement.getName() )
                .append( "\", "                          )
                .append( aConditionNameValueMapName      )
                ;
        
        emitVariableConditionNameMethodParts( aEmitter, aConditionalVariableElement );
        
        aEmitter.append( " );" );
    }
}
