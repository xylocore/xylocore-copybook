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

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.xylocore.copybook.generator.domain.Copybook;
import com.xylocore.copybook.generator.domain.DataElement;
import com.xylocore.copybook.generator.domain.Element;
import com.xylocore.copybook.generator.domain.ElementNotFoundException;
import com.xylocore.copybook.generator.domain.ElementProxy;
import com.xylocore.copybook.generator.domain.ElementType;
import com.xylocore.copybook.generator.domain.PICSlice;
import com.xylocore.copybook.generator.domain.PICSymbolType;
import com.xylocore.copybook.generator.domain.StringAccessorMethodInfo;
import com.xylocore.copybook.generator.domain.Visitor;
import com.xylocore.copybook.generator.domain.config.Environment;
import com.xylocore.copybook.generator.pic.AlphabeticPICProcessingStrategy;
import com.xylocore.copybook.generator.pic.AlphanumericEditedPICProcessingStrategy;
import com.xylocore.copybook.generator.pic.AlphanumericPICProcessingStrategy;
import com.xylocore.copybook.generator.pic.DbcsPICProcessingStrategy;
import com.xylocore.copybook.generator.pic.ExternalFloatingPointPICProcessingStrategy;
import com.xylocore.copybook.generator.pic.InternalFloatingPointPICProcessingStrategy;
import com.xylocore.copybook.generator.pic.NationalEditedPICProcessingStrategy;
import com.xylocore.copybook.generator.pic.NationalPICProcessingStrategy;
import com.xylocore.copybook.generator.pic.NumericEditedPICProcessingStrategy;
import com.xylocore.copybook.generator.pic.NumericPICProcessingStrategy;
import com.xylocore.copybook.generator.pic.PICProcessingStrategy;
import com.xylocore.copybook.runtime.DataCategory;
import com.xylocore.copybook.runtime.DataType;
import com.xylocore.copybook.runtime.SignType;
import com.xylocore.copybook.runtime.UsageType;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class CopybookNormalizationVisitor
    extends
        Visitor
{
    //
    // Members
    //
    
    
    private static final PICProcessingStrategy                      alphabeticProcessingStrategy                = new AlphabeticPICProcessingStrategy();
    private static final PICProcessingStrategy                      numericProcessingStrategy                   = new NumericPICProcessingStrategy();
    private static final PICProcessingStrategy                      numericEditedProcessingStrategy             = new NumericEditedPICProcessingStrategy();
    private static final PICProcessingStrategy                      alphanumericProcessingStrategy              = new AlphanumericPICProcessingStrategy();
    private static final PICProcessingStrategy                      alphanumericEditedProcessingStrategy        = new AlphanumericEditedPICProcessingStrategy();
    private static final PICProcessingStrategy                      dbcsProcessingStrategy                      = new DbcsPICProcessingStrategy();
    private static final PICProcessingStrategy                      externalFloatingPointProcessingStrategy     = new ExternalFloatingPointPICProcessingStrategy();
    private static final PICProcessingStrategy                      internalFloatingPointProcessingStrategy     = new InternalFloatingPointPICProcessingStrategy();
    private static final PICProcessingStrategy                      nationalProcessingStrategy                  = new NationalPICProcessingStrategy();
    private static final PICProcessingStrategy                      nationalEditedProcessingStrategy            = new NationalEditedPICProcessingStrategy();
    private static final Map<UsageType,PICProcessingStrategy>       usageTypeProcessingStrategyMap              = new HashMap<UsageType,PICProcessingStrategy>();
    private static final Map<DataCategory,PICProcessingStrategy>    dataCategoryProcessingStrategyMap           = new HashMap<DataCategory,PICProcessingStrategy>();
    private static final Map<PICSymbolType,Set<DataCategory>>       symbolTypeToDataCategoryMap                 = new HashMap<PICSymbolType,Set<DataCategory>>();
    private static final Set<DataCategory>                          dataCategoryWorkingSet                      = new HashSet<DataCategory>();
    private static final Set<DataCategory>                          stringBasedDataCategories                   = new HashSet<DataCategory>();
    
    private Environment                                             environment;
    private ValidateElementMetadataVisitor                          validateElementMetadataVisitor;
    private ApplyMetadataToElementVisitor                           applyMetadataVisitor;
    private Visitor                                                 determineElementTypeVisitor;
    private AssignConvertersVisitor                                 assignConvertersVisitor;
    
    
    
    
    //
    // Static initialization
    //
    
    
    static
    {
        usageTypeProcessingStrategyMap.put( UsageType.Binary        , numericProcessingStrategy               );
        usageTypeProcessingStrategyMap.put( UsageType.Computational1, internalFloatingPointProcessingStrategy );
        usageTypeProcessingStrategyMap.put( UsageType.Computational2, internalFloatingPointProcessingStrategy );
        usageTypeProcessingStrategyMap.put( UsageType.Computational3, numericProcessingStrategy               );
        usageTypeProcessingStrategyMap.put( UsageType.Computational5, numericProcessingStrategy               );
        
        dataCategoryProcessingStrategyMap.put( DataCategory.Alphabetic           , alphabeticProcessingStrategy            );
        dataCategoryProcessingStrategyMap.put( DataCategory.Numeric              , numericProcessingStrategy               );
        dataCategoryProcessingStrategyMap.put( DataCategory.NumericEdited        , numericEditedProcessingStrategy         );
        dataCategoryProcessingStrategyMap.put( DataCategory.Alphanumeric         , alphanumericProcessingStrategy          );
        dataCategoryProcessingStrategyMap.put( DataCategory.AlphanumericEdited   , alphanumericEditedProcessingStrategy    );
        dataCategoryProcessingStrategyMap.put( DataCategory.DBCS                 , dbcsProcessingStrategy                  );
        dataCategoryProcessingStrategyMap.put( DataCategory.ExternalFloatingPoint, externalFloatingPointProcessingStrategy );
        dataCategoryProcessingStrategyMap.put( DataCategory.InternalFloatingPoint, internalFloatingPointProcessingStrategy );
        dataCategoryProcessingStrategyMap.put( DataCategory.National             , nationalProcessingStrategy              );
        dataCategoryProcessingStrategyMap.put( DataCategory.NationalEdited       , nationalEditedProcessingStrategy        );

        Set<DataCategory> mySet;
        
        mySet = new HashSet<DataCategory>();
        mySet.add( DataCategory.Alphabetic         );
        mySet.add( DataCategory.Alphanumeric       );
        mySet.add( DataCategory.AlphanumericEdited );
        symbolTypeToDataCategoryMap.put( PICSymbolType.Alphabetic, mySet );
        
        mySet = new HashSet<DataCategory>();
        mySet.add( DataCategory.Alphanumeric       );
        mySet.add( DataCategory.AlphanumericEdited );
        symbolTypeToDataCategoryMap.put( PICSymbolType.Alphanumeric, mySet );
        
        mySet = new HashSet<DataCategory>();
        mySet.add( DataCategory.NumericEdited      );
        mySet.add( DataCategory.AlphanumericEdited );
        mySet.add( DataCategory.DBCS               );
        mySet.add( DataCategory.NationalEdited     );
        symbolTypeToDataCategoryMap.put( PICSymbolType.Blank, mySet );
        
        mySet = new HashSet<DataCategory>();
        mySet.add( DataCategory.ExternalFloatingPoint );
        symbolTypeToDataCategoryMap.put( PICSymbolType.Exponent, mySet );
        
        mySet = new HashSet<DataCategory>();
        mySet.add( DataCategory.DBCS ); // valid
        symbolTypeToDataCategoryMap.put( PICSymbolType.DbcsCharacter, mySet );
        
        mySet = new HashSet<DataCategory>();
        // TODO: add check for NSYMBOL(DBCS) compiler option
//        mySet.add( DataCategory.DBCS           );
        mySet.add( DataCategory.National       );
        mySet.add( DataCategory.NationalEdited );
        symbolTypeToDataCategoryMap.put( PICSymbolType.NationalCharacter, mySet );
        
        mySet = new HashSet<DataCategory>();
        mySet.add( DataCategory.Numeric       );
        mySet.add( DataCategory.NumericEdited );
        symbolTypeToDataCategoryMap.put( PICSymbolType.DecimalScalingPosition, mySet );
        
        mySet = new HashSet<DataCategory>();
        mySet.add( DataCategory.Numeric );
        symbolTypeToDataCategoryMap.put( PICSymbolType.Sign, mySet );
        
        mySet = new HashSet<DataCategory>();
        mySet.add( DataCategory.Numeric               );
        mySet.add( DataCategory.NumericEdited         );
        mySet.add( DataCategory.ExternalFloatingPoint );
        symbolTypeToDataCategoryMap.put( PICSymbolType.AssumedDecimalPoint, mySet );
        
        mySet = new HashSet<DataCategory>();
        mySet.add( DataCategory.NumericEdited );
        symbolTypeToDataCategoryMap.put( PICSymbolType.LeadingBlankZero, mySet );
        
        mySet = new HashSet<DataCategory>();
        mySet.add( DataCategory.Numeric               );
        mySet.add( DataCategory.NumericEdited         );
        mySet.add( DataCategory.Alphanumeric          );
        mySet.add( DataCategory.AlphanumericEdited    );
        mySet.add( DataCategory.ExternalFloatingPoint );
        symbolTypeToDataCategoryMap.put( PICSymbolType.Numeric, mySet );
        
        mySet = new HashSet<DataCategory>();
        mySet.add( DataCategory.NumericEdited  );
        mySet.add( DataCategory.Alphanumeric   );
        mySet.add( DataCategory.NationalEdited );
        symbolTypeToDataCategoryMap.put( PICSymbolType.Zero, mySet );
        
        mySet = new HashSet<DataCategory>();
        mySet.add( DataCategory.NumericEdited      );
        mySet.add( DataCategory.AlphanumericEdited );
        mySet.add( DataCategory.NationalEdited     );
        symbolTypeToDataCategoryMap.put( PICSymbolType.Slash, mySet );
        
        mySet = new HashSet<DataCategory>();
        mySet.add( DataCategory.NumericEdited );
        symbolTypeToDataCategoryMap.put( PICSymbolType.Comma, mySet );
        
        mySet = new HashSet<DataCategory>();
        mySet.add( DataCategory.NumericEdited         );
        mySet.add( DataCategory.ExternalFloatingPoint );
        symbolTypeToDataCategoryMap.put( PICSymbolType.Period, mySet );
        
        mySet = new HashSet<DataCategory>();
        mySet.add( DataCategory.NumericEdited         );
        mySet.add( DataCategory.ExternalFloatingPoint );
        symbolTypeToDataCategoryMap.put( PICSymbolType.Plus, mySet );
        
        mySet = new HashSet<DataCategory>();
        mySet.add( DataCategory.NumericEdited         );
        mySet.add( DataCategory.ExternalFloatingPoint );
        symbolTypeToDataCategoryMap.put( PICSymbolType.Minus, mySet );
        
        mySet = new HashSet<DataCategory>();
        mySet.add( DataCategory.NumericEdited );
        symbolTypeToDataCategoryMap.put( PICSymbolType.Credit, mySet );
        
        mySet = new HashSet<DataCategory>();
        mySet.add( DataCategory.NumericEdited );
        symbolTypeToDataCategoryMap.put( PICSymbolType.Debit, mySet );
        
        mySet = new HashSet<DataCategory>();
        mySet.add( DataCategory.NumericEdited );
        symbolTypeToDataCategoryMap.put( PICSymbolType.Asterisk, mySet );
        
        mySet = new HashSet<DataCategory>();
        mySet.add( DataCategory.NumericEdited );
        symbolTypeToDataCategoryMap.put( PICSymbolType.Currency, mySet );
        
        dataCategoryWorkingSet.add( DataCategory.Alphabetic            );
        dataCategoryWorkingSet.add( DataCategory.Numeric               );
        dataCategoryWorkingSet.add( DataCategory.NumericEdited         );
        dataCategoryWorkingSet.add( DataCategory.Alphanumeric          );
        dataCategoryWorkingSet.add( DataCategory.AlphanumericEdited    );
        dataCategoryWorkingSet.add( DataCategory.DBCS                  );
        dataCategoryWorkingSet.add( DataCategory.ExternalFloatingPoint );
        dataCategoryWorkingSet.add( DataCategory.InternalFloatingPoint );
        dataCategoryWorkingSet.add( DataCategory.National              );
        dataCategoryWorkingSet.add( DataCategory.NationalEdited        );

        stringBasedDataCategories.add( DataCategory.Alphabetic            );
        stringBasedDataCategories.add( DataCategory.NumericEdited         );
        stringBasedDataCategories.add( DataCategory.Alphanumeric          );
        stringBasedDataCategories.add( DataCategory.AlphanumericEdited    );
        stringBasedDataCategories.add( DataCategory.DBCS                  );
        stringBasedDataCategories.add( DataCategory.ExternalFloatingPoint );
        stringBasedDataCategories.add( DataCategory.National              );
        stringBasedDataCategories.add( DataCategory.NationalEdited        );
    }
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @param       aEnvironment
     */
    public CopybookNormalizationVisitor( Environment aEnvironment )
    {
        assert aEnvironment != null;
        
        environment                    = aEnvironment;
        validateElementMetadataVisitor = new ValidateElementMetadataVisitor();
        applyMetadataVisitor           = new ApplyMetadataToElementVisitor( aEnvironment );
        assignConvertersVisitor        = new AssignConvertersVisitor();
        
        determineElementTypeVisitor =
                new Visitor()
                    {
                        public boolean shouldVisitChildren( Element aParent )
                        {
                            return true;
                        }
                        
                        public void visitElement( Element aElement )
                        {
                            determineElementType( aElement );
                        }
                    };
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aCopybook
     */
    public void normalize( Copybook aCopybook )
    {
        assert aCopybook != null;
        
        aCopybook.accept( this );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.Visitor#visitCopybook(com.xylocore.commons.data.copybook.domain.Copybook)
     */
    public void visitCopybook( Copybook aCopybook )
    {
        assert aCopybook != null;

        aCopybook.accept( determineElementTypeVisitor );
        
        assignElementMetadata( aCopybook );
        validateElementMetadataVisitor.validate( aCopybook );
    }
    

    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.Visitor#leaveCopybook(com.xylocore.commons.data.copybook.domain.Copybook)
     */
    public void leaveCopybook( Copybook aCopybook )
    {
        assert aCopybook != null;
        
        int myMaximumRecordLength = 0;
        
        for ( Element myChild = aCopybook.getFirstChild() ; myChild != null ; myChild = myChild.getNextSibling() )
        {
            calculateSizeAndOffset( myChild, 0 );
            
            myChild.setRecordLengthMethodName( environment.getNameConverter().generateRecordLengthMethodName( myChild.getName() ) );

            myMaximumRecordLength = Math.max( myMaximumRecordLength, myChild.getSize() );
        }
        
        aCopybook.setSize          ( myMaximumRecordLength );
        aCopybook.setNonIndexedSize( myMaximumRecordLength );
    
        aCopybook.accept( assignConvertersVisitor );
        
//        aCopybook.accept( new DebugVisitor() );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.Visitor#shouldVisitChildren(com.xylocore.commons.data.copybook.domain.Element)
     */
    public boolean shouldVisitChildren( Element aParent )
    {
        return true;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.Visitor#visitElement(com.xylocore.commons.data.copybook.domain.Element)
     */
    public void visitElement( Element aElement )
    {
        assert aElement != null;

        preprocessPIC               ( aElement );
        determineElementUsage       ( aElement );
        determineDataCategory       ( aElement );
        resolveElementProxies       ( aElement );
        processPIC                  ( aElement );
        assignedOuterIndexedElement ( aElement );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.Visitor#leaveDataElement(com.xylocore.commons.data.copybook.domain.DataElement)
     */
    public void leaveDataElement( DataElement aElement )
    {
        applyMetadataVisitor.apply( aElement );
        
        determineStringBasedElements    ( aElement );
        determineAccessorsForDataElement( aElement );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     * 
     * @return
     */
    private Element getParentElement( Element aElement )
    {
        assert aElement != null;
        
        return aElement.getParent();
    }

    
    /**
     * FILLIN
     * 
     * @param       aCopybook
     */
    private void assignElementMetadata( Copybook aCopybook )
    {
        ElementDictionaryBuilderVisitor myBuilder = new ElementDictionaryBuilderVisitor();
        myBuilder.build( aCopybook );
        
        ElementMetadataAssignmentVisitor myAssignmentVisitor = new ElementMetadataAssignmentVisitor( aCopybook );
        myAssignmentVisitor.assign( environment );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     */
    private void determineElementType( Element aElement )
    {
        assert aElement != null;
        
        Element     myParentElement = getParentElement( aElement );
        ElementType myElementType;
        
        if ( aElement.getFirstChild() == null )
        {
            myElementType = ElementType.ElementaryItem;
        }
        else if
        (
            aElement.isNationalGroupUsage() ||
            (
                myParentElement                  != null                          &&
                myParentElement.getElementType() == ElementType.NationalGroupItem
            )
        )
        {
            myElementType = ElementType.NationalGroupItem;
        }
        else
        {
            myElementType = ElementType.AlphanumericGroupItem;
        }
        
        aElement.setElementType( myElementType );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     */
    private void determineElementUsage( Element aElement )
    {
        assert aElement != null;
        
        ElementType myElementType               = aElement.getElementType();
        Element     myParentElement             = getParentElement( aElement );
        UsageType   myUsageType                 = aElement.getUsageType();
        boolean     myGroupUsageNationalImplied = false;
        UsageType   myEffectiveUsageType;
        
        if ( myElementType == ElementType.NationalGroupItem )
        {
            if ( ! aElement.isNationalGroupUsage() )
            {
                aElement.setNationalGroupUsage( true );
                myGroupUsageNationalImplied = true;
            }
        
            if ( myUsageType != null )
            {
                // TODO: throw proper exception
                throw new RuntimeException( "element "                                        +
                                            aElement.getName()                                +
                                            " specified usage ("                              +
                                            myUsageType                                       +
                                            ") and "                                          +
                                            ( myGroupUsageNationalImplied ? "implied " : "" ) +
                                            "Group-Usage National, which is not allowed"        );
            }
            
            if ( aElement.isJustified() )
            {
                // TODO: throw proper exception
                throw new RuntimeException( "element "                                                   +
                                            aElement.getName()                                           +
                                            " specified justified clause for a"                          +
                                            ( myGroupUsageNationalImplied ? "n implied" : "" )           +
                                            " Group-Usage Nationial group element, which is not allowed"   );
            }
            
            aElement.setEffectiveUsageType( UsageType.National );
        }
        else
        {
            if ( myParentElement != null )
            {
                UsageType myParentUsageType = myParentElement.getEffectiveUsageType();
                if ( myParentUsageType != null )
                {
                    if ( myUsageType == null )
                    {
                        aElement.setEffectiveUsageType( myParentUsageType );
                    }
                    else if ( aElement.getUsageType() != myParentUsageType )
                    {
                        // TODO: throw proper exception
                        throw new RuntimeException( "element "                             +
                                                    aElement.getName()                     +
                                                    " usage ("                             +
                                                    myUsageType                            +
                                                    ") does not match parent usage type (" +
                                                    myParentUsageType                      +
                                                    ")"                                      );
                    }
                }
            }
            
            if ( aElement.getEffectiveUsageType() == null )
            {
                if ( myElementType == ElementType.ElementaryItem )
                {
                    if ( myUsageType != null )
                    {
                        myEffectiveUsageType = myUsageType;
                    }
                    else if ( aElement.getSymbolTypes().contains( PICSymbolType.NationalCharacter ) )
                    {
                        myEffectiveUsageType = UsageType.National;
                    }
                    else
                    {
                        myEffectiveUsageType = UsageType.Display;
                    }
                    
                    aElement.setEffectiveUsageType( myEffectiveUsageType );
                }
                else if ( myElementType == ElementType.AlphanumericGroupItem )
                {
                    aElement.setEffectiveUsageType( myUsageType );
                }
            }
        }

        myEffectiveUsageType = aElement.getEffectiveUsageType();
        if
        (
            aElement.getDatePattern() != null                     &&
            myEffectiveUsageType      != UsageType.Binary         &&
            myEffectiveUsageType      != UsageType.Computational3 &&
            myEffectiveUsageType      != UsageType.Display
        )
        {
            // TODO: throw proper exception
            throw new RuntimeException( "element "                                                   +
                                        aElement.getName()                                           +
                                        " has a"                                                     +
                                        ( aElement.getUsageType() == null ? "n implied" : "" )       +
                                        " usage of "                                                 +
                                        myEffectiveUsageType                                         +
                                        " which does not allow a DATE FORMAT clause to be specified"   );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     */
    private void resolveElementProxies( Element aElement )
    {
        assert aElement != null;

        ElementProxy myProxy;
        
        myProxy = (ElementProxy) aElement.getRedefinedElement();
        if ( myProxy != null )
        {
            Element myReferencedElement = aElement.getParent().getChildByName( myProxy.getName() );
            if ( myReferencedElement == null )
            {
                throw new ElementNotFoundException( myProxy.getName() );
            }
            
            aElement.setRedefinedElement( myReferencedElement );
        }
        
        myProxy = (ElementProxy) aElement.getOccursDependingOnElement();
        if ( myProxy != null )
        {
            Element myReferencedElement = aElement.getParent().getChildByName( myProxy.getName() );
            if ( myReferencedElement == null )
            {
                throw new ElementNotFoundException( myProxy.getName() );
            }
            
            aElement.setOccursDependingOnElement( myReferencedElement );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     */
    private void preprocessPIC( Element aElement )
    {
        assert aElement != null;
        
        List<PICSlice> mySlices = aElement.getPICSlices();
        if ( mySlices != null )
        {
            Set<PICSymbolType> mySymbolTypes            = new HashSet<PICSymbolType>();
            Set<DataCategory>  myPossibleDataCategories = new HashSet<DataCategory>( dataCategoryWorkingSet );
            boolean            myIsAlphabeticOnly       = true;
            boolean            myIsNumericOnly          = true;
            int                myPlusMinusCount         = 0;
            
            for ( PICSlice mySlice : mySlices )
            {
                PICSymbolType mySymbolType = mySlice.getType();
                
                myPossibleDataCategories.retainAll( symbolTypeToDataCategoryMap.get( mySymbolType ) );
                
                myIsAlphabeticOnly &= ( mySymbolType == PICSymbolType.Alphabetic );
                myIsNumericOnly    &= ( mySymbolType == PICSymbolType.Numeric    );
                
                if ( mySymbolType == PICSymbolType.Plus || mySymbolType == PICSymbolType.Minus )
                {
                    myPlusMinusCount++;
                }
    
                mySymbolTypes.add( mySymbolType );
            }
            
            if ( myPossibleDataCategories.contains( DataCategory.AlphanumericEdited ) )
            {
                if
                (
                    !
                    (
                        (
                            mySymbolTypes.contains( PICSymbolType.Alphabetic   ) ||
                            mySymbolTypes.contains( PICSymbolType.Alphanumeric )
                        ) &&
                        (
                            mySymbolTypes.contains( PICSymbolType.Blank ) ||
                            mySymbolTypes.contains( PICSymbolType.Zero  ) ||
                            mySymbolTypes.contains( PICSymbolType.Slash )
                        )
                    )
                )
                {
                    myPossibleDataCategories.remove( DataCategory.AlphanumericEdited );
                }
            }
            
            if ( myPossibleDataCategories.contains( DataCategory.Numeric ) )
            {
                if ( aElement.isBlankWhenZero() )
                {
                    myPossibleDataCategories.remove( DataCategory.Numeric );
                }
            }
            
            if ( myPossibleDataCategories.contains( DataCategory.NumericEdited ) )
            {
                if
                (
                    !
                    (
                        mySymbolTypes.contains( PICSymbolType.Blank            ) ||
                        mySymbolTypes.contains( PICSymbolType.Slash            ) ||
                        mySymbolTypes.contains( PICSymbolType.LeadingBlankZero ) ||
                        mySymbolTypes.contains( PICSymbolType.Zero             ) ||
                        mySymbolTypes.contains( PICSymbolType.Comma            ) ||
                        mySymbolTypes.contains( PICSymbolType.Period           ) ||
                        mySymbolTypes.contains( PICSymbolType.Asterisk         ) ||
                        mySymbolTypes.contains( PICSymbolType.Plus             ) ||
                        mySymbolTypes.contains( PICSymbolType.Minus            ) ||
                        mySymbolTypes.contains( PICSymbolType.Credit           ) ||
                        mySymbolTypes.contains( PICSymbolType.Debit            ) ||
                        mySymbolTypes.contains( PICSymbolType.Currency         )
                    )
                )
                {
                    myPossibleDataCategories.remove( DataCategory.NumericEdited );
                }
            }
            
            if ( myPossibleDataCategories.contains( DataCategory.DBCS ) )
            {
                if
                (
                    !
                    (
                        (
                            mySymbolTypes.size() == 1 &&
                            (
                                mySymbolTypes.contains( PICSymbolType.DbcsCharacter     ) // ||
                                // mySymbolTypes.contains( PICSymbolType.NationalCharacter )
                                // TODO: add check for NSYMBOL(DBCS) compiler option
                            )
                        ) ||
                        (
                            mySymbolTypes.size() == 2 &&
                            mySymbolTypes.contains( PICSymbolType.DbcsCharacter ) &&
                            mySymbolTypes.contains( PICSymbolType.Blank         )
                        )
                    )
                )
                {
                    myPossibleDataCategories.remove( DataCategory.DBCS );
                }
            }
            
            if ( myPossibleDataCategories.contains( DataCategory.National ) )
            {
                if
                (
                    !
                    (
                        mySymbolTypes.size() == 1 &&
                        mySymbolTypes.contains( PICSymbolType.NationalCharacter ) // &&
                        // TODO: add check for NSYMBOL(NATIONAL) compiler option
                    )
                )
                {
                    myPossibleDataCategories.remove( DataCategory.National );
                }
            }
            
            if ( myPossibleDataCategories.contains( DataCategory.NationalEdited ) )
            {
                if
                (
                    !
                    (
                        mySymbolTypes.contains( PICSymbolType.NationalCharacter ) &&
                        (
                            mySymbolTypes.contains( PICSymbolType.Blank ) ||
                            mySymbolTypes.contains( PICSymbolType.Zero  ) ||
                            mySymbolTypes.contains( PICSymbolType.Slash )
                        )
                    )
                )
                {
                    myPossibleDataCategories.remove( DataCategory.NationalEdited );
                }
            }
            
            if ( myPossibleDataCategories.contains( DataCategory.ExternalFloatingPoint ) )
            {
                if
                (
                    !
                    (
                        mySymbolTypes.contains( PICSymbolType.Exponent ) &&
                        mySymbolTypes.contains( PICSymbolType.Numeric  ) &&
                        myPlusMinusCount == 2                            &&
                        (
                            mySymbolTypes.contains( PICSymbolType.Period              ) ||
                            mySymbolTypes.contains( PICSymbolType.AssumedDecimalPoint )
                        )
                    )
                )
                {
                    myPossibleDataCategories.remove( DataCategory.ExternalFloatingPoint );
                }
            }
            
            if ( myPossibleDataCategories.contains( DataCategory.Alphanumeric ) )
            {
                if ( myIsAlphabeticOnly )
                {
                    myPossibleDataCategories.clear();
                    myPossibleDataCategories.add( DataCategory.Alphabetic );
                }
                else
                {
                    myPossibleDataCategories.remove( DataCategory.Alphabetic );
                }
                
                if ( myIsNumericOnly )
                {
                    myPossibleDataCategories.clear();
                    myPossibleDataCategories.add( DataCategory.Numeric );
                }
                else
                {
                    myPossibleDataCategories.remove( DataCategory.Numeric );
                }
            }
            
            aElement.setSymbolTypes           ( Collections.unmodifiableSet( mySymbolTypes            ) );
            aElement.setPossibleDataCategories( Collections.unmodifiableSet( myPossibleDataCategories ) );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     */
    private void processPIC( Element aElement )
    {
        assert aElement != null;

        PICProcessingStrategy myConverterStrategy = selectPICConverterStrategy( aElement );
        if ( myConverterStrategy != null )
        {
            myConverterStrategy.convert( aElement );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     */
    private void determineDataCategory( Element aElement )
    {
        assert aElement != null;

        Set<DataCategory> myPossibleDataCategories = aElement.getPossibleDataCategories();
        if ( myPossibleDataCategories != null )
        {
            if ( myPossibleDataCategories.size() != 1 )
            {
                // TODO: throw proper exception
                throw new RuntimeException( "a data category cannot be determined for the element " + aElement.getName() );
            }
            
            aElement.setDataCategory( myPossibleDataCategories.iterator().next() );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     * 
     * @return
     */
    private PICProcessingStrategy selectPICConverterStrategy( Element aElement )
    {
        PICProcessingStrategy myStrategy = null;
        
        // TODO: revisit code
        
        if ( aElement.getElementType() == ElementType.ElementaryItem )
        {
            UsageType myUsageType = aElement.getEffectiveUsageType();
            
            myStrategy = usageTypeProcessingStrategyMap.get( myUsageType );
            if ( myStrategy == null )
            {
                Set<DataCategory> myPossibleDataCategories = aElement.getPossibleDataCategories();
                if ( myPossibleDataCategories != null )
                {
                    if ( myPossibleDataCategories.size() != 1 )
                    {
                        // TODO: throw proper exception
                        throw new RuntimeException( "a data category cannot be determined from the picture for the element " + aElement.getName() );
                    }
                    
                    DataCategory myDataCategory = myPossibleDataCategories.iterator().next();
                    
                    myStrategy = dataCategoryProcessingStrategyMap.get( myDataCategory );
                }
            }
            
            if ( myStrategy == null )
            {
                if
                (
                    myUsageType != UsageType.Pointer          &&
                    myUsageType != UsageType.FunctionPointer  &&
                    myUsageType != UsageType.ProcedurePointer &&
                    myUsageType != UsageType.ObjectReference  &&
                    myUsageType != UsageType.Computational1   &&
                    myUsageType != UsageType.Computational2
                )
                {
                    // TODO: throw proper exception
                    throw new RuntimeException( "a picture clause must be specified for the element " + aElement.getName() );
                }
            }
        }
        
        return myStrategy;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDataElement
     */
    private void determineStringBasedElements( DataElement aElement )
    {
        if ( aElement.getElementType() == ElementType.ElementaryItem )
        {
            DataCategory myDataCategory = aElement.getDataCategory();
            UsageType    myUsageType    = aElement.getEffectiveUsageType();

            if
            (
                myDataCategory == DataCategory.Alphabetic         ||
                myDataCategory == DataCategory.Alphanumeric       ||
                myDataCategory == DataCategory.AlphanumericEdited
            )
            {
                aElement.setDisplayPresent( true );
            }
            else if ( myDataCategory == DataCategory.Numeric )
            {
                if ( myUsageType == UsageType.Display && aElement.getSignType() != SignType.NotSeparate )
                {
                    aElement.setDisplayPresent( true );
                }
                else if ( myUsageType == UsageType.National )
                {
                    aElement.setNationalPresent( true );
                }
            }
            else if
            (
                myDataCategory == DataCategory.NumericEdited         ||
                myDataCategory == DataCategory.ExternalFloatingPoint
            )
            {
                if ( myUsageType == UsageType.Display )
                {
                    aElement.setDisplayPresent( true );
                }
                else
                {
                    aElement.setNationalPresent( true );
                }
            }
            else if ( myDataCategory == DataCategory.National )
            {
                aElement.setNationalPresent( true );
            }
            else if ( myDataCategory == DataCategory.NationalEdited )
            {
                aElement.setNationalPresent( true );
            }
            else if ( myDataCategory == DataCategory.DBCS )
            {
                aElement.setDbcsPresent( true );
            }
        }
        else
        {
            int myPossibleStringTypesPresent = 0;
            
            for ( Element myChild = aElement.getFirstChild() ; myChild != null ; myChild = myChild.getNextSibling() )
            {
                Element myRedefinedElement = myChild.getRedefinedElement();
                
                if ( myRedefinedElement != null )
                {
                    int myRedefinedPossibleStringTypes = myRedefinedElement.getPossibleStringTypesPresent();
                    int myChildPossibleStringTypes     = myChild.getPossibleStringTypesPresent();
                
                    if
                    (
                        myRedefinedElement.getTotalPossibleStringTypesPresent()       != 1                          ||
                        myChild.getTotalPossibleStringTypesPresent()                  != 1                          ||
                        (myRedefinedPossibleStringTypes | myChildPossibleStringTypes) != myChildPossibleStringTypes
                    )
                    {
                        myPossibleStringTypesPresent = 0;
                        break;
                    }
                }

                if ( myChild.getPossibleStringTypesPresent() == 0 )
                {
                    myPossibleStringTypesPresent = 0;
                    break;
                }
                
                myPossibleStringTypesPresent |= myChild.getPossibleStringTypesPresent();
            }
                
            if ( aElement.getAccessorMethodInfos().containsKey( DataType.String ) )
            {
                myPossibleStringTypesPresent |= Element.DISPLAY_PRESENT_FLAG;
            }

            aElement.setPossibleStringTypesPresent( myPossibleStringTypesPresent );
        }
    }
    
    
    /**
     * FILLIN
     *
     * @param       aElement
     */
    private void determineAccessorsForDataElement( DataElement aElement )
    {
        assert aElement != null;

        if
        (
            aElement.getElementType()                     != ElementType.ElementaryItem &&
            aElement.getTotalPossibleStringTypesPresent() != 0                          &&
            ! aElement.isNoDefaultAccessor()
        )
        {
            StringAccessorMethodInfo myAccessorMethodInfo = new StringAccessorMethodInfo();
            myAccessorMethodInfo.setDefault( true );
            aElement.addAccessorMethodInfo( myAccessorMethodInfo );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     * @param       aOffset
     */
    private void calculateSizeAndOffset( Element   aElement,
                                         int       aOffset   )
    {
        assert aElement != null;
        assert aOffset  >= 0;

        aElement.setOffset( aOffset );

        int myCurrentOffset = aOffset;
        int myCurrentSize   = 0;
        int myTotalSize     = 0;
        
        if ( aElement.isOccurs() )
        {
            myCurrentOffset = 0;
        }
        
        if ( aElement.getElementType() != ElementType.ElementaryItem )
        {
            for ( Element myChild = aElement.getFirstChild() ; myChild != null ; myChild = myChild.getNextSibling() )
            {
                if ( myChild.getRedefinedElement() != null )
                {
                    calculateSizeAndOffset( myChild, myCurrentOffset );
                    myCurrentSize = Math.max( myCurrentSize, myChild.getSize() );
                }
                else
                {
                    myCurrentOffset += myCurrentSize;
                    myTotalSize     += myCurrentSize;
                    
                    calculateSizeAndOffset( myChild, myCurrentOffset );
                    myCurrentSize = myChild.getSize();
                }
            }
            
            myTotalSize += myCurrentSize;
        }
        else
        {
            myTotalSize = aElement.getSize();
        }
        
        aElement.setSize          ( myTotalSize*aElement.getOccursMaxValue() );
        aElement.setNonIndexedSize( myTotalSize                              );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     */
    private void assignedOuterIndexedElement( Element aElement )
    {
        assert aElement != null;
        
        aElement.setOuterIndexedElement( findOuterIndexedElement( aElement ) );
    }


    /**
     * FILLIN
     * 
     * @param       aElement
     * 
     * @return
     */
    private Element findOuterIndexedElement( Element aElement )
    {
        assert aElement != null;
        
        Element myOuterIndexedElement = null;
        Element myParent              = aElement.getParent();
        
        if ( myParent != null )
        {
            if ( myParent.isOccurs() )
            {
                myOuterIndexedElement = myParent;
            }
            else
            {
                myOuterIndexedElement = myParent.getOuterIndexedElement();
            }
        }
        
        return myOuterIndexedElement;
    }
}
