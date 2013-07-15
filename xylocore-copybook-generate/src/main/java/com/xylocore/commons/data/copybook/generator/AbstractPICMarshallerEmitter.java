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


package com.xylocore.commons.data.copybook.generator;

import com.xylocore.commons.data.copybook.domain.AccessorMethodInfo;
import com.xylocore.commons.data.copybook.domain.DataElement;
import com.xylocore.commons.data.copybook.domain.Element;
import com.xylocore.commons.data.copybook.domain.Level88Element;
import com.xylocore.commons.data.copybook.domain.Value;
import com.xylocore.commons.data.copybook.domain.ValueRange;
import com.xylocore.commons.data.copybook.runtime.ConstantValue;
import com.xylocore.commons.data.copybook.runtime.DataType;
import com.xylocore.commons.data.copybook.runtime.marshallers.PICMarshaller;


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
     * @param       aBuffer
     * @param       aElement
     */
    protected void emitIndexOffsets( StringBuilder   aBuffer,
                                     Element         aElement )
    {
        IndexOffsets myIndexOffset = new IndexOffsets();
        myIndexOffset.emit( aBuffer, aElement );
    }

    
    /**
     * FILLIN
     * 
     * @param       aBuffer
     * @param       aElement
     * @param       aAccessorMethodInfo
     */
    protected void emitVariableReaderMethodParts( StringBuilder        aBuffer,
                                                  Element              aElement,
                                                  AccessorMethodInfo   aAccessorMethodInfo )
    {
        assert aBuffer             != null;
        assert aElement            != null;
        assert aAccessorMethodInfo != null;
        
        emitVariableMethodParts( aBuffer, aElement, aAccessorMethodInfo );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aBuffer
     * @param       aElement
     * @param       aAccessorMethodInfo
     */
    protected void emitVariableWriterMethodParts( StringBuilder        aBuffer,
                                                  Element              aElement,
                                                  AccessorMethodInfo   aAccessorMethodInfo )
    {
        assert aBuffer             != null;
        assert aElement            != null;
        assert aAccessorMethodInfo != null;
        
        emitVariableMethodParts( aBuffer, aElement, aAccessorMethodInfo );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aBuffer
     * @param       aElement
     * @param       aAccessorMethodInfo
     */
    protected void emitVariableMethodParts( StringBuilder        aBuffer,
                                            Element              aElement,
                                            AccessorMethodInfo   aAccessorMethodInfo )
    {
        //  By default assume that no additional parts are needed
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aBuffer
     * @param       aElement
     */
    protected void emitVariableIsBlankMethodParts( StringBuilder   aBuffer,
                                                   Element         aElement )
    {
        //  By default assume that no additional parts are needed
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aBuffer
     * @param       aElement
     */
    protected void emitVariableIsNullMethodParts( StringBuilder   aBuffer,
                                                  Element         aElement )
    {
        //  By default assume that no additional parts are needed
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aBuffer
     * @param       aElement
     * @param       aAccessorMethodInfo
     */
    protected void emitVariableIsValidMethodParts( StringBuilder        aBuffer,
                                                   Element              aElement,
                                                   AccessorMethodInfo   aAccessorMethodInfo )
    {
        //  By default assume that no additional parts are needed
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aBuffer
     * @param       aElement
     * @param       aAccessorMethodInfo
     */
    protected void emitVariableConverterParts( StringBuilder        aBuffer,
                                               Element              aElement,
                                               AccessorMethodInfo   aAccessorMethodInfo )
    {
    }
    
    
    /**
     * FILLIN
     *
     * @param       aBuffer
     * @param       aElement
     */
    protected void emitVariableNullEquivalentStrategiesParts( StringBuilder   aBuffer,
                                                              DataElement     aElement )
    {
    }
    
    
    /**
     * FILLIN
     *
     * @param       aBuffer
     * @param       aConditionalVariableElement
     */
    protected abstract void emitVariableConditionNameMethodParts( StringBuilder   aBuffer,
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
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.generator.PICMarshallerEmitter#emitMarshaller(com.xylocore.commons.util.XyStringBuffer, com.xylocore.commons.data.copybook.runtime.PICMarshaller, java.lang.String)
     */
    public void emitMarshaller( StringBuilder   aBuffer,
                                PICMarshaller   aMarshaller,
                                String          aInstanceName )
    {
        throw new UnsupportedOperationException( "the marshaller emitter "                +
                                                 getClass().getName()                     +
                                                 " does not support emitting marshallers"   );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.generator.PICMarshallerEmitter#emitValueRange(com.xylocore.commons.util.XyStringBuffer, com.xylocore.commons.data.copybook.domain.Element, com.xylocore.commons.data.copybook.domain.ValueRange)
     */
    public void emitValueRange( StringBuilder   aBuffer,
                                Element         aElement,
                                ValueRange      aValueRange )
    {
        ConstantValue        myConstantValue = convertValueRange( aElement, aValueRange );
        ConstantValueEmitter myEmitter       = ConstantValueEmitterFactory.getInstance().newInstance( myConstantValue );

        myEmitter.emit( aBuffer, myConstantValue );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.generator.PICMarshallerEmitter#emitReader(com.xylocore.commons.util.XyStringBuffer, com.xylocore.commons.data.copybook.runtime.PICMarshaller, com.xylocore.commons.data.copybook.domain.Element, com.xylocore.commons.data.copybook.domain.AccessorMethodInfo)
     */
    public void emitReader( StringBuilder        aBuffer,
                            PICMarshaller        aMarshaller,
                            Element              aElement,
                            AccessorMethodInfo   aAccessorMethodInfo )
    {
        assert aBuffer             != null;
        assert aMarshaller         == null;
        assert aElement            != null;
        assert aAccessorMethodInfo != null;
        
        DataType myDataType   = aAccessorMethodInfo.getDataType();
        String   myMethodName = getDelegateDecodeMethodName( myDataType );
        
        aBuffer.append( "return "      )
               .append( myMethodName   )
               .append( "( aContext, " )
               ;
        
        emitIndexOffsets( aBuffer, aElement );
        
        emitVariableReaderMethodParts( aBuffer, aElement, aAccessorMethodInfo );
        
        aBuffer.append( " );" );
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.generator.PICMarshallerEmitter#emitWriter(com.xylocore.commons.util.XyStringBuffer, com.xylocore.commons.data.copybook.runtime.PICMarshaller, com.xylocore.commons.data.copybook.domain.Element, com.xylocore.commons.data.copybook.domain.AccessorMethodInfo)
     */
    public void emitWriter( StringBuilder        aBuffer,
                            PICMarshaller        aMarshaller,
                            Element              aElement,
                            AccessorMethodInfo   aAccessorMethodInfo )
    {
        assert aBuffer             != null;
        assert aMarshaller         == null;
        assert aElement            != null;
        assert aAccessorMethodInfo != null;
        
        DataType myDataType   = aAccessorMethodInfo.getDataType();
        String   myMethodName = getDelegateEncodeMethodName( myDataType );
        
        aBuffer.append( myMethodName   )
               .append( "( aContext, " )
               ;

        emitIndexOffsets( aBuffer, aElement );
        
        aBuffer.append( ", aValue" );
        
        emitVariableWriterMethodParts( aBuffer, aElement, aAccessorMethodInfo );
        
        aBuffer.append( " );" );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.generator.PICMarshallerEmitter#isBlankMethodNeeded(com.xylocore.commons.data.copybook.domain.Element)
     */
    public boolean isBlankMethodNeeded( Element aElement )
    {
        return false;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.generator.PICMarshallerEmitter#emitBlankMethodCall(com.xylocore.commons.util.XyStringBuffer, com.xylocore.commons.data.copybook.runtime.marshallers.PICMarshaller, com.xylocore.commons.data.copybook.domain.Element)
     */
    public void emitBlankMethodCall( StringBuilder   aBuffer,
                                     PICMarshaller   aMarshaller,
                                     Element         aElement     )
    {
        assert aBuffer             != null;
        assert aMarshaller         == null;
        assert aElement            != null;
        assert isBlankMethodNeeded( aElement );
        
        String myMethodName = getDelegateIsBlankMethodName();
        
        aBuffer.append( "return "      )
               .append( myMethodName   )
               .append( "( aContext, " )
               ;

        emitIndexOffsets              ( aBuffer, aElement );
        emitVariableIsBlankMethodParts( aBuffer, aElement );
        
        aBuffer.append( " );" );
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
     * @param       aBuffer
     * @param       aMarshaller
     * @param       aElement
     */
    public void emitNullMethodCall( StringBuilder   aBuffer,
                                    PICMarshaller   aMarshaller,
                                    DataElement     aElement     )
    {
        assert aBuffer     != null;
        assert aMarshaller == null;
        assert aElement    != null;
        assert isNullMethodNeeded( aElement );
        
        String myMethodName = getDelegateIsNullMethodName();
        
        aBuffer.append( "return "      )
               .append( myMethodName   )
               .append( "( aContext, " )
               ;

        emitIndexOffsets             ( aBuffer, aElement );
        emitVariableIsNullMethodParts( aBuffer, aElement );
        
        aBuffer.append( " );" );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.generator.PICMarshallerEmitter#isValidMethodNeeded(com.xylocore.commons.data.copybook.domain.Element, com.xylocore.commons.data.copybook.domain.AccessorMethodInfo)
     */
    public boolean isValidMethodNeeded( Element              aElement,
                                        AccessorMethodInfo   aAccessorMethodInfo )
    {
        return true;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.generator.PICMarshallerEmitter#emitValidMethodCall(com.xylocore.commons.util.XyStringBuffer, com.xylocore.commons.data.copybook.runtime.PICMarshaller, com.xylocore.commons.data.copybook.domain.Element, com.xylocore.commons.data.copybook.domain.AccessorMethodInfo)
     */
    public void emitValidMethodCall( StringBuilder        aBuffer,
                                     PICMarshaller        aMarshaller,
                                     Element              aElement,
                                     AccessorMethodInfo   aAccessorMethodInfo )
    {
        assert aBuffer             != null;
        assert aMarshaller         == null;
        assert aElement            != null;
        assert aAccessorMethodInfo != null;
        assert isValidMethodNeeded( aElement, aAccessorMethodInfo );
        
        DataType myDataType   = aAccessorMethodInfo.getDataType();
        String   myMethodName = getDelegateIsValidMethodName( myDataType );
        
        aBuffer.append( "return "      )
               .append( myMethodName   )
               .append( "( aContext, " )
               ;

        emitIndexOffsets              ( aBuffer, aElement                      );
        emitVariableIsValidMethodParts( aBuffer, aElement, aAccessorMethodInfo );

        aBuffer.append( " );" );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.generator.PICMarshallerEmitter#emitConditionNameMethodCall(com.xylocore.commons.util.XyStringBuffer, com.xylocore.commons.data.copybook.domain.Element, com.xylocore.commons.data.copybook.domain.Level88Element, java.lang.String)
     */
    public void emitConditionNameMethodCall( StringBuilder    aBuffer,
                                             Element          aConditionalVariableElement,
                                             Level88Element   aConditionNameElement,
                                             String           aConditionNameValueMapName   )
    
    {
        assert aBuffer                     != null;
        assert aConditionalVariableElement != null;
        assert aConditionNameElement       != null;
        
        String myMethodName = getDelegateConditionNameMethodName();
        
        aBuffer.append( "return "      )
               .append( myMethodName   )
               .append( "( aContext, " )
               ;
        
        emitIndexOffsets( aBuffer, aConditionalVariableElement );
        
        aBuffer.append( ", \""                          )
               .append( aConditionNameElement.getName() )
               .append( "\", "                          )
               .append( aConditionNameValueMapName      )
               ;
        
        emitVariableConditionNameMethodParts( aBuffer, aConditionalVariableElement );
        
        aBuffer.append( " );" );
    }
}
