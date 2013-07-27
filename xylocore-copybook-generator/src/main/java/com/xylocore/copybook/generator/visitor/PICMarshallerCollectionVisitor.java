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


package com.xylocore.copybook.generator.visitor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xylocore.copybook.generator.domain.DataElement;
import com.xylocore.copybook.generator.domain.Element;
import com.xylocore.copybook.generator.domain.Visitor;
import com.xylocore.copybook.runtime.DataCategory;
import com.xylocore.copybook.runtime.marshallers.PICMarshaller;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class PICMarshallerCollectionVisitor
    extends
        Visitor
{
    //
    // Nested classes and interfaces
    //
    
    
    /**
     * FILLIN
     */
    interface PICMarshallerBuilder
    {
        public PICMarshaller build( PICMarshallerCollectionVisitor   aDelegate,
                                    Element                          aElement   );
    }
    
    
    
    
    //
    // Members
    //
    
    
    private static final Map<DataCategory,PICMarshallerBuilder> marshallerBuilders      = new HashMap<>();
    private Map<DataElement,PICMarshaller>                      elementMarshallerMap;
    
    
    
    
    //
    // Static intialization
    //
    
    
    static
    {
        PICMarshallerBuilder myBuilder;
        
        myBuilder =
                new PICMarshallerBuilder()
                    {
                        public PICMarshaller build( PICMarshallerCollectionVisitor   aDelegate,
                                                    Element                          aElement   )
                        {
                            return aDelegate.buildNumericEditedMarshaller( aElement );
                        }
                    };
        marshallerBuilders.put( DataCategory.NumericEdited, myBuilder );
        
        myBuilder =
                new PICMarshallerBuilder()
                    {
                        public PICMarshaller build( PICMarshallerCollectionVisitor   aDelegate,
                                                    Element                          aElement   )
                        {
                            return aDelegate.buildAlphanumericEditedMarshaller( aElement );
                        }
                    };
        marshallerBuilders.put( DataCategory.AlphanumericEdited, myBuilder );
    
        myBuilder =
                new PICMarshallerBuilder()
                    {
                        public PICMarshaller build( PICMarshallerCollectionVisitor   aDelegate,
                                                    Element                          aElement   )
                        {
                            return aDelegate.buildExternalFloatingPointMarshaller( aElement );
                        }
                    };
        marshallerBuilders.put( DataCategory.ExternalFloatingPoint, myBuilder );
        
        myBuilder =
                new PICMarshallerBuilder()
                    {
                        public PICMarshaller build( PICMarshallerCollectionVisitor   aDelegate,
                                                    Element                          aElement   )
                        {
                            return aDelegate.buildNationalEditedMarshaller( aElement );
                        }
                    };
        marshallerBuilders.put( DataCategory.NationalEdited, myBuilder );
    }
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     */
    public PICMarshallerCollectionVisitor()
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElements
     * 
     * @return
     */
    public Map<DataElement,PICMarshaller> collect( List<Element> aElements )
    {
        try
        {
            elementMarshallerMap = new HashMap<>();
            
            for ( Element myElement : aElements )
            {
                myElement.accept( this );
            }
            
            return elementMarshallerMap;
        }
        finally
        {
            elementMarshallerMap = null;
        }
    }


    @Override
    public void visitDataElement( DataElement aElement )
    {
        DataCategory         myDataCategory = aElement.getDataCategory();
        PICMarshallerBuilder myBuilder      = marshallerBuilders.get( myDataCategory );
        if ( myBuilder != null )
        {
            elementMarshallerMap.put( aElement, myBuilder.build( this, aElement ) );
        }
    }


    /**
     * FILLIN
     * 
     * @param       aElement
     * 
     * @return
     */
    PICMarshaller buildNumericEditedMarshaller( Element aElement )
    {
        return null;
    }


    /**
     * FILLIN
     * 
     * @param       aElement
     * 
     * @return
     */
    PICMarshaller buildAlphanumericEditedMarshaller( Element aElement )
    {
        return null;
    }


    /**
     * FILLIN
     * 
     * @param       aElement
     * 
     * @return
     */
    PICMarshaller buildExternalFloatingPointMarshaller( Element aElement )
    {
        return null;
    }


    /**
     * FILLIN
     * 
     * @param       aElement
     * 
     * @return
     */
    PICMarshaller buildNationalEditedMarshaller( Element aElement )
    {
        return null;
    }
}
