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


package com.xylocore.copybook.generator.emit;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.xylocore.commons.util.FormatHelper;
import com.xylocore.copybook.generator.domain.AccessorMethodInfo;
import com.xylocore.copybook.generator.domain.ConditionNameValueRanges;
import com.xylocore.copybook.generator.domain.Copybook;
import com.xylocore.copybook.generator.domain.DataElement;
import com.xylocore.copybook.generator.domain.Element;
import com.xylocore.copybook.generator.domain.ElementType;
import com.xylocore.copybook.generator.domain.Level88Element;
import com.xylocore.copybook.generator.domain.ValueRange;
import com.xylocore.copybook.generator.domain.config.Environment;
import com.xylocore.copybook.generator.emit.convert.ConverterEmitterFactory;
import com.xylocore.copybook.generator.emit.nulleq.NullEquivalentStrategyEmitterFactory;
import com.xylocore.copybook.generator.emit.pic.AlphanumericPICMarshallerEmitter;
import com.xylocore.copybook.generator.emit.pic.BinaryPICMarshallerEmitter;
import com.xylocore.copybook.generator.emit.pic.Computational1PICMarshallerEmitter;
import com.xylocore.copybook.generator.emit.pic.Computational2PICMarshallerEmitter;
import com.xylocore.copybook.generator.emit.pic.Computational3PICMarshallerEmitter;
import com.xylocore.copybook.generator.emit.pic.Computational5PICMarshallerEmitter;
import com.xylocore.copybook.generator.emit.pic.NumericDisplayPICMarshallerEmitter;
import com.xylocore.copybook.generator.emit.pic.NumericNationalPICMarshallerEmitter;
import com.xylocore.copybook.generator.emit.pic.PICMarshallerEmitter;
import com.xylocore.copybook.generator.visitor.ConditionNameValueMapVariableCollectionVisitor;
import com.xylocore.copybook.generator.visitor.ConverterCollectionVisitor;
import com.xylocore.copybook.generator.visitor.PICMarshallerCollectionVisitor;
import com.xylocore.copybook.runtime.AbstractCopybook;
import com.xylocore.copybook.runtime.ConstantValue;
import com.xylocore.copybook.runtime.DataCategory;
import com.xylocore.copybook.runtime.DataType;
import com.xylocore.copybook.runtime.DataUsageCategory;
import com.xylocore.copybook.runtime.UsageType;
import com.xylocore.copybook.runtime.converters.Converter;
import com.xylocore.copybook.runtime.marshallers.PICMarshaller;
import com.xylocore.copybook.runtime.nulleq.NullEquivalentStrategy;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class CopybookClassEmitter
{
    //
    // Members
    //
    
    
    private static final int                                            DEFAULT_INDENT_WIDTH                            = 4;
    private static final Map<DataUsageCategory,PICMarshallerEmitter>    dataUsageCategoryEmittersMap;
    private static final Set<String>                                    importedClasses;
    
    private Environment                                                 environment;
    private Copybook                                                    copybook;
    private Writer                                                      writer;
    private List<Element>                                               elementsOfInterest;
    private Map<Converter,String>                                       converterInstanceNameMappings;
    private Map<List<NullEquivalentStrategy>,String>                    nullEquivalentStrategySetInstanceNameMappings;
    private BufferEmitter                                               emitter;
    
    
    
    
    //
    // Static initialization
    //
    
    
    static
    {
        dataUsageCategoryEmittersMap = new HashMap<>();
        dataUsageCategoryEmittersMap.put( DataUsageCategory.Alphanumeric   , AlphanumericPICMarshallerEmitter.getInstance()    );
        dataUsageCategoryEmittersMap.put( DataUsageCategory.Binary         , BinaryPICMarshallerEmitter.getInstance()          );
        dataUsageCategoryEmittersMap.put( DataUsageCategory.Computational1 , Computational1PICMarshallerEmitter.getInstance()  );
        dataUsageCategoryEmittersMap.put( DataUsageCategory.Computational2 , Computational2PICMarshallerEmitter.getInstance()  );
        dataUsageCategoryEmittersMap.put( DataUsageCategory.Computational3 , Computational3PICMarshallerEmitter.getInstance()  );
        dataUsageCategoryEmittersMap.put( DataUsageCategory.Computational5 , Computational5PICMarshallerEmitter.getInstance()  );
        dataUsageCategoryEmittersMap.put( DataUsageCategory.NumericDisplay , NumericDisplayPICMarshallerEmitter.getInstance()  );
        dataUsageCategoryEmittersMap.put( DataUsageCategory.NumericNational, NumericNationalPICMarshallerEmitter.getInstance() );
        
        importedClasses = new TreeSet<>();
        importedClasses.add( com.xylocore.copybook.runtime.AlphanumericPICFlags.class.getName() );
    }
    
    
    

    //
    // Instance initialization
    //
    
    
    {
        elementsOfInterest                            = new ArrayList<>();
        converterInstanceNameMappings                 = new TreeMap<>();
        nullEquivalentStrategySetInstanceNameMappings = new HashMap<>();
    }
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @param       aEnvironment
     * @param       aCopybook
     * @param       aElementsOfInterest
     * @param       aWriter
     * 
     * @throws      IOException
     */
    public synchronized void generate( Environment     aEnvironment,
                                       Copybook        aCopybook,
                                       List<Element>   aElementsOfInterest,
                                       Writer          aWriter              )
            throws IOException
    {
        assert aEnvironment        != null;
        assert aCopybook           != null;
        assert aElementsOfInterest != null;
        assert aWriter             != null;

        environment        = aEnvironment;
        copybook           = aCopybook;
        elementsOfInterest = aElementsOfInterest;
        writer             = aWriter;
        emitter            = new BufferEmitter( DEFAULT_INDENT_WIDTH );
        
        converterInstanceNameMappings.clear();
        nullEquivalentStrategySetInstanceNameMappings.clear();

        generateSourceFileHeader      ();
        generatePICMarshallers        ();
        generateConditionNameVariables();
        generateConverterMembers      ();
        generateElementsSource        ();
        generateSourceFileFooter      ();
    }

    
    /**
     * FILLIN
     * 
     * @exception   IOException
     */
    private void generateSourceFileHeader()
            throws IOException
    {
        emitter.clear(                                               )
               .line ( "package ", environment.getPackageName(), ";" )
               .line (                                               )
               ;
        
        for ( String myImportedClassName : importedClasses )
        {
            emitter.line( "import ", myImportedClassName, ";" );
        }
        
        emitter.line     (                                             )
               .line     (                                             )
               .line     ( "public class ", environment.getClassName() )
               .line1    ( "extends"                                   )
               .line2    ( AbstractCopybook.class.getName()            )
               .line     ( "{"                                         )
               .write    ( writer                                      )
               .increment(                                             )
               ;
    }
    
    
    /**
     * FILLIN
     * 
     * @exception   IOException
     */
    private void generatePICMarshallers()
            throws IOException
    {
        PICMarshallerCollectionVisitor myPicMarshallerCollectionVisitor = new PICMarshallerCollectionVisitor();
        Map<DataElement,PICMarshaller> myElementMarshallerMap           = myPicMarshallerCollectionVisitor.collect( elementsOfInterest );
        
        if ( ! myElementMarshallerMap.isEmpty() )
        {
            int myIndex = 1;

            emitter.clear(                      )
                   .line ( "//"                 )
                   .line ( "// PIC marshallers" )
                   .line ( "//"                 )
                   .line (                      )
                   .line (                      )
                   ;

            for ( Map.Entry<DataElement,PICMarshaller> myEntry : myElementMarshallerMap.entrySet() )
            {
                Element       myElement    = myEntry.getKey();
                PICMarshaller myMarshaller = myEntry.getValue();
                
                // TODO: remove null test once all pic marshallers have been implemented
                if ( myMarshaller != null )
                {
                    PICMarshallerEmitter myEmitter                = getMarshallerEmitter( myElement );
                    String               myMarshallerInstanceName = "picMarshaller" + myIndex;
                    
                    myIndex++;
                    
                    // TODO: remove null test once all emitters have been implemented
                    if ( myEmitter != null)
                    {
                        emitter.indent(                         )
                               .append( "private static final " )
                               ;
                        
                        myEmitter.emitMarshaller( emitter, myMarshaller, myMarshallerInstanceName );
                        
                        emitter.newline();
                    }
                }
            }

            emitter.line (        )
                   .line (        )
                   .line (        )
                   .line (        )
                   .write( writer )
                   ;
        }
    }
    
    
    /**
     * FILLIN
     *
     * @exception   IOException
     */
    private void generateConditionNameVariables()
            throws IOException
    {
        ConditionNameValueMapVariableCollectionVisitor myConditionNameValueMapVariableCollectionVisitor =
                new ConditionNameValueMapVariableCollectionVisitor();
        List<ConditionNameValueRanges> myConditionNameValueRangesList =
                myConditionNameValueMapVariableCollectionVisitor.collect( elementsOfInterest );
        
        if ( ! myConditionNameValueRangesList.isEmpty() )
        {
            String myMapSuffix = "Map<String," + ConstantValue.class.getName() + "[]>";
            
            emitter.clear    (                                                                                   )
                   .line     ( "//"                                                                              )
                   .line     ( "// Condition name value mappings"                                                )
                   .line     ( "//"                                                                              )
                   .line     (                                                                                   )
                   .line     (                                                                                   )
                   .line     ( "private static final java.util.", myMapSuffix, " conditionNameValueMappings;"    )
                   .line     (                                                                                   )
                   .line     ( "static"                                                                          )
                   .line     ( "{"                                                                               )
                   .line     ( "java.util.", myMapSuffix, " myMappings = new java.util.Hash", myMapSuffix, "();" )
                   .line     (                                                                                   )
                   .increment(                                                                                   )
                   ;

            for ( ConditionNameValueRanges myConditionNameValueRanges : myConditionNameValueRangesList )
            {
                Level88Element       myElement     = myConditionNameValueRanges.getConditionNameElement();
                List<ValueRange>     myValueRanges = myConditionNameValueRanges.getValueRanges();
                Element              myParent      = myElement.getParent();
                PICMarshallerEmitter myEmitter     = getMarshallerEmitter( myParent );

                emitter.line     ( "addConditionNameValueMapping\n"            )
                       .line     ( "("                                         )
                       .line1    ( "myMappings,"                               )
                       .line1    ( "\"", myElement.getName(), "\","            )
                       .line1    ( "new ", ConstantValue.class.getName(), "[]" )
                       .line2    ( "{"                                         )
                       .increment( 2                                           )
                       ;
                
                for ( Iterator<ValueRange> myValueRangeIterator = myValueRanges.iterator() ; myValueRangeIterator.hasNext() ; )
                {
                    ValueRange myValueRange = myValueRangeIterator.next();

                    emitter.indent( 1 );
                    
                    myEmitter.emitValueRange( emitter, myParent, myValueRange );
                    
                    emitter.append ( myValueRangeIterator.hasNext() ? "," : "" )
                           .newline(                                           )
                           ;
                }

                emitter.decrement( 2    )
                       .line     ( "}"  )
                       .line     ( ");" )
                       ;
            }

            emitter.decrement(                                                                                     )
                   .line     (                                                                                     )
                   .line1    ( "conditionNameValueMappings = java.util.Collections.unmodifiableMap( myMappings );" )
                   .line     ( "}"                                                                                 )
                   .line     (                                                                                     )
                   .line     (                                                                                     )
                   .line     (                                                                                     )
                   .line     (                                                                                     )
                   .write    ( writer                                                                              )
                   ;
        }
    }

    
    
    /**
     * FILLIN
     * 
     * @exception   IOException
     */
    private void generateConverterMembers()
            throws IOException
    {
        ConverterCollectionVisitor myConverterCollectionVisitor = new ConverterCollectionVisitor();
        myConverterCollectionVisitor.collect( elementsOfInterest );
        
        Set<Converter>                    myConverters                 = myConverterCollectionVisitor.getConverters();
        Set<List<NullEquivalentStrategy>> myNullEquivalentStrategySets = myConverterCollectionVisitor.getNullEquivalentStrategySets();
        
        if ( ! myConverters.isEmpty() )
        {
            int myIndex = 1;

            emitter.clear(                 )
                   .line ( "//"            )
                   .line ( "// Converters" )
                   .line ( "//"            )
                   .line (                 )
                   .line (                 )
                   ;

            for ( Converter myConverter : myConverters )
            {
                String myConverterInstanceName = "converter" + myIndex;
                
                myIndex++;

                emitter.indent()
                       .append( "private static final ", myConverter.getClass().getName(), " ", myConverterInstanceName, " = " )
                       ;
                
                ConverterEmitterFactory.getInstance()
                                       .getEmitter( myConverter )
                                       .emitDeclaration( emitter, myConverter );
                
                emitter.append ( ";" )
                       .newline(     )
                       ;
                
                converterInstanceNameMappings.put( myConverter, myConverterInstanceName );
            }
            
            emitter.line (        )
                   .line (        )
                   .line (        )
                   .line (        )
                   .write( writer )
                   ;
        }
        
        if ( ! myNullEquivalentStrategySets.isEmpty() )
        {
            int myIndex = 1;

            emitter.clear(                                    )
                   .line ( "//"                               )
                   .line ( "// Null equivalent strategy sets" )
                   .line ( "//"                               )
                   .line (                                    )
                   .line (                                    )
                   ;

            for ( List<NullEquivalentStrategy> myNullEquivalentStrategySet : myNullEquivalentStrategySets )
            {
                String myNullEquivalentStrategySetInstanceName = "nullEquivalentStrategySet" + myIndex;
                
                myIndex++;
                
                boolean myFirst           = true;
                int     myExtraLineIndent = emitter.getBuffer().length();

                for ( NullEquivalentStrategy myNullEquivalentStrategy : myNullEquivalentStrategySet )
                {
                    if ( myFirst )
                    {
                        emitter.indent()
                               .append( "private static final ", NullEquivalentStrategy.class.getName(), "[] ", myNullEquivalentStrategySetInstanceName, " = { " )
                               ;

                        myExtraLineIndent = emitter.getBuffer().length() - myExtraLineIndent;
                        myFirst           = false;
                    }
                    else
                    {
                        emitter.append ( "," )
                               .newline(     )
                               ;
                        
                        FormatHelper.stringOfCharacters( emitter.getBuffer(), ' ', myExtraLineIndent );
                    }
                    
                    NullEquivalentStrategyEmitterFactory.getInstance()
                                                        .getEmitter( myNullEquivalentStrategy )
                                                        .emitDeclaration( emitter, myNullEquivalentStrategy );
                }
                
                emitter.append ( " };" )
                       .newline(       )
                       ;
                
                nullEquivalentStrategySetInstanceNameMappings.put( myNullEquivalentStrategySet,
                                                                   myNullEquivalentStrategySetInstanceName );
            }

            emitter.line (        )
                   .line (        )
                   .line (        )
                   .line (        )
                   .write( writer )
                   ;
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @exception   IOException
     */
    private void generateSourceFileFooter()
            throws IOException
    {
        emitter.decrement(        )
               .clear    (        )
               .line     ( "}"    )
               .write    ( writer )
               ;
    }
    
   
    /**
     * FILLIN
     * 
     * @exception   IOException
     */
    private void generateElementsSource()
            throws IOException
    {
        String myClassName = environment.getClassName();

        emitter.clear(                                                                              )
               .line ( "//"                                                                         )
               .line ( "// Members"                                                                 )
               .line ( "//"                                                                         )
               .line (                                                                              )
               .line (                                                                              )
               .line ( "private static final ", myClassName, " instance = new ", myClassName, "();" )
               .line (                                                                              )
               .line (                                                                              )
               .line (                                                                              )
               .line (                                                                              )
               .line ( "//"                                                                         )
               .line ( "// Class implementation"                                                    )
               .line ( "//"                                                                         )
               .line (                                                                              )
               .line (                                                                              )
               .line ( "public static ", myClassName, " getInstance()"                              )
               .line ( "{"                                                                          )
               .line1( 1, "return instance;"                                                        )
               .line ( "}"                                                                          )
               .line (                                                                              )
               .line (                                                                              )
               .write( writer                                                                       )
               ;
        
        generateRecordLengthMethods();

        for ( Iterator<Element> myElementIterator  = elementsOfInterest.iterator() ; myElementIterator.hasNext() ; )
        {
            generateElementSource( (DataElement) myElementIterator.next(), ! myElementIterator.hasNext() );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @exception   IOException
     */
    private void generateRecordLengthMethods()
            throws IOException
    {
        emitter.clear();
        
        generateLengthMethod( "getMaximumRecordLength", copybook.getSize() );
        
        for ( Element myChild = copybook.getFirstChild() ; myChild != null ; myChild = myChild.getNextSibling() )
        {
            generateLengthMethod( myChild.getRecordLengthMethodName(), myChild.getSize() );
        }

        emitter.write( writer );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     * @param       aLastElement
     * 
     * @exception   IOException
     */
    private void generateElementSource( DataElement   aElement,
                                        boolean       aLastElement )
            throws IOException
    {
        assert aElement != null;

        // Set the null equivalent strategy instance variable name
        List<NullEquivalentStrategy> myNullEquivalentStrategies                   = aElement.getNullEquivalentStrategies();
        String                       myNullEquivalentStrategyInstanceVariableName = nullEquivalentStrategySetInstanceNameMappings.get( myNullEquivalentStrategies );
        if ( myNullEquivalentStrategyInstanceVariableName != null )
        {
            aElement.setNullEquivalentStrategySetInstanceVariableName( myNullEquivalentStrategyInstanceVariableName );
        }

        emitter.clear();

        generateOffsetMethodCall( aElement );
        generateLengthMethodCall( aElement );
        generateBlankMethodCall ( aElement );
        generateNullMethodCall  ( aElement );

        emitter.write( writer );
            
        for ( AccessorMethodInfo myAccessorMethodInfo : aElement.getAccessorMethodInfos().values() )
        {
            DataType myAccessorDataType     = myAccessorMethodInfo.getDataType();
            String   myJavaType             = myAccessorDataType.getJavaType();
            boolean  myIsDefaultAccessor    = myAccessorMethodInfo.isDefault();
            boolean  myHasMultipleAccessors = ( aElement.getAccessorMethodInfos().size() > 1 );
            
            // Set the converter instance variable name
            Converter myConverter = myAccessorMethodInfo.getConverter();
            if ( myConverter != null )
            {
                myAccessorMethodInfo.setConverterInstanceVariableName( converterInstanceNameMappings.get( myConverter ) );
            }

            emitter.clear();

            generateValidMethodCall( aElement, myAccessorMethodInfo, myIsDefaultAccessor, myHasMultipleAccessors             );
            generateGetMethodCall  ( aElement, myAccessorMethodInfo, myIsDefaultAccessor, myHasMultipleAccessors, myJavaType );
            generateSetMethodCall  ( aElement, myAccessorMethodInfo, myIsDefaultAccessor, myHasMultipleAccessors, myJavaType );
    
            if ( aLastElement )
            {
                emitter.getBuffer().setLength( emitter.getBuffer().length()-2 );
            }

            emitter.write( writer );
        }
        
        for ( Level88Element myLevel88Element : aElement.getLevel88Elements() )
        {
            emitter.clear();
            
            generateConditionNameMethodCall( aElement, myLevel88Element );

            emitter.write( writer );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     */
    private void generateOffsetMethodCall( DataElement aElement )
    {
        assert aElement != null;

        String myOffsetMethodName =
                environment.getNameConverter()
                           .generateOffsetMethodName( aElement.getName() );
        
        emitter.indent(                                                                        )
               .append( "public int ", myOffsetMethodName, "(", aElement.isOccurs() ? " " : "" )
               ;
            
        appendIndexParameterDeclarations( aElement, false );
        
        emitter.append   ( aElement.isOccurs() ? " " : "", ")" )
               .newline  (                                     )
               .line     ( "{"                                 )
               .increment(                                     )
               .indent   (                                     )
               .append   ( "return "                           )
               ;
        
        IndexOffsets myIndexOffset = new IndexOffsets();
        myIndexOffset.emit( emitter, aElement );

        emitter.append   ( ";" )
               .newline  (     )
               .decrement(     )
               .line     ( "}" )
               .line     (     )
               .line     (     )
               ;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     */
    private void generateLengthMethodCall( DataElement aElement )
    {
        assert aElement != null;
        
        if ( aElement.isOccurs() )
        {
            String myTotalLengthMethodName =
                    environment.getNameConverter()
                               .generateTotalLengthMethodName( aElement.getName() );

            generateLengthMethod( myTotalLengthMethodName, aElement.getSize() );
            
            String mySingleElementLengthMethodName =
                    environment.getNameConverter()
                               .generateSingleElementLengthMethodName( aElement.getName() );

            generateLengthMethod( mySingleElementLengthMethodName, aElement.getNonIndexedSize() );
        }
        else
        {
            String myLengthMethodName =
                    environment.getNameConverter()
                               .generateLengthMethodName( aElement.getName() );
            
            generateLengthMethod( myLengthMethodName, aElement.getSize() );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aMethodName
     * @param       aLength
     */
    private void generateLengthMethod( String   aMethodName,
                                       int      aLength      )
    {
        emitter.line ( "public int ", aMethodName, "()" )
               .line ( "{"                              )
               .line1( "return ", aLength, ";"          )
               .line ( "}"                              )
               .line (                                  )
               .line (                                  )
               ;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     */
    private void generateBlankMethodCall( DataElement aElement )
    {
        assert aElement != null;
        
        PICMarshallerEmitter myPICMarshallerEmitter = getMarshallerEmitter( aElement );

        if ( myPICMarshallerEmitter.isBlankMethodNeeded( aElement ) )
        {
            String myBlankMethodName =
                    environment.getNameConverter()
                               .generateBlankMethodName( aElement.getName() );

            emitter.indent()
                   .append( "public boolean ", myBlankMethodName, "( ", environment.getCopybookContextClassName(), " aContext" )
                   ;
            
            appendIndexParameterDeclarations( aElement, true );
            
            emitter.append   ( " )" )
                   .newline  (      )
                   .line     ( "{"  )
                   .increment(      )
                   .indent   (      )
                   ;
                
            myPICMarshallerEmitter.emitBlankMethodCall( emitter, null, aElement );

            emitter.newline  (     )
                   .decrement(     )
                   .line     ( "}" )
                   .newline  (     )
                   .newline  (     )
                   ;
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     */
    private void generateNullMethodCall( DataElement aElement )
    {
        assert aElement != null;
        
        PICMarshallerEmitter myPICMarshallerEmitter = getMarshallerEmitter( aElement );

        if ( myPICMarshallerEmitter.isNullMethodNeeded( aElement ) )
        {
            String myNullMethodName =
                    environment.getNameConverter()
                               .generateNullMethodName( aElement.getName() );

            emitter.indent()
                   .append( "public boolean ", myNullMethodName, "( ", environment.getCopybookContextClassName(), " aContext" )
                   ;
            
            appendIndexParameterDeclarations( aElement, true );
            
            emitter.append   ( " )" )
                   .newline  (      )
                   .line     ( "{"  )
                   .increment(      )
                   .indent   (      )
                   ;
                
            myPICMarshallerEmitter.emitNullMethodCall( emitter, null, aElement );

            emitter.newline  (     )
                   .decrement(     )
                   .line     ( "}" )
                   .newline  (     )
                   .newline  (     )
                   ;
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     * @param       aAccessorMethodInfo
     * @param       aIsDefaultAccessor
     * @param       aHasMultipleDatatypes
     */
    private void generateValidMethodCall( DataElement          aElement,
                                          AccessorMethodInfo   aAccessorMethodInfo,
                                          boolean              aIsDefaultAccessor,
                                          boolean              aHasMultipleDatatypes )
    {
        assert aElement            != null;
        assert aAccessorMethodInfo != null;
        
        PICMarshallerEmitter myPICMarshallerEmitter = getMarshallerEmitter( aElement );

        if ( myPICMarshallerEmitter.isValidMethodNeeded( aElement, aAccessorMethodInfo ) )
        {
            String myValidMethodName =
                    environment.getNameConverter()
                               .generateValidMethodName( aElement.getName(),
                                                         aAccessorMethodInfo.getDataType(),
                                                         aIsDefaultAccessor,
                                                         aHasMultipleDatatypes              );

            emitter.indent()
                   .append( "public boolean ", myValidMethodName, "( ", environment.getCopybookContextClassName(), " aContext" )
                   ;
            
            appendIndexParameterDeclarations( aElement, true );
            
            emitter.append   ( " )" )
                   .newline  (      )
                   .line     ( "{"  )
                   .increment(      )
                   .indent   (      )
                   ;
            
            myPICMarshallerEmitter.emitValidMethodCall( emitter, null, aElement, aAccessorMethodInfo );

            emitter.newline  (     )
                   .decrement(     )
                   .line     ( "}" )
                   .line     (     )
                   .line     (     )
                   ;
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     * @param       aAccessorMethodInfo
     * @param       aJavaType
     */
    private void generateGetMethodCall( DataElement          aElement,
                                        AccessorMethodInfo   aAccessorMethodInfo,
                                        boolean              aIsDefaultAccessor,
                                        boolean              aHasMultipleDatatypes,
                                        String               aJavaType            )
    {
        assert aElement            != null;
        assert aAccessorMethodInfo != null;
        assert aJavaType           != null;
        
        String myGetterMethodName =
                environment.getNameConverter()
                           .generateGetterMethodName( aElement.getName(),
                                                      aAccessorMethodInfo.getDataType(),
                                                      aIsDefaultAccessor,
                                                      aHasMultipleDatatypes              );
        
        PICMarshallerEmitter myPICMarshallerEmitter = getMarshallerEmitter( aElement );

        emitter.indent()
               .append( "public ", aJavaType, " ", myGetterMethodName, "( ", environment.getCopybookContextClassName(), " aContext" )
               ;
  
        appendIndexParameterDeclarations( aElement, true );
  
        emitter.append   ( " )" )
               .newline  (      )
               .line     ( "{"  )
               .increment(      )
               .indent   (      )
               ;
            
        myPICMarshallerEmitter.emitReader( emitter, null, aElement, aAccessorMethodInfo );

        emitter.newline  (     )
               .decrement(     )
               .line     ( "}" )
               .line     (     )
               .line     (     )
               ;
    }
    
    
    /**
     * FILLIN
     *
     * @param       aElement
     * @param       aAccessorMethodInfo
     * @param       aIsDefaultAccessor
     * @param       aHasMultipleDatatypes
     * @param       aJavaType
     */
    private void generateSetMethodCall( DataElement          aElement,
                                        AccessorMethodInfo   aAccessorMethodInfo,
                                        boolean              aIsDefaultAccessor,
                                        boolean              aHasMultipleDatatypes,
                                        String               aJavaType              )
    {
        assert aElement            != null;
        assert aAccessorMethodInfo != null;
        assert aJavaType           != null;
        
        String mySetterMethodName =
                environment.getNameConverter()
                           .generateSetterMethodName( aElement.getName(),
                                                      aAccessorMethodInfo.getDataType(),
                                                      aIsDefaultAccessor,
                                                      aHasMultipleDatatypes              );
        
        PICMarshallerEmitter myPICMarshallerEmitter = getMarshallerEmitter( aElement );
        
        emitter.indent()
               .append( "public void ", mySetterMethodName, "( ", environment.getCopybookContextClassName(), " aContext"                               )
               ;
  
        appendIndexParameterDeclarations( aElement, true );
  
        emitter.append   ( ", ", aJavaType, " aValue )" )
               .newline  (                              )
               .line     ( "{"                          )
               .increment(                              )
               .indent   (                              )
               ;
        
        myPICMarshallerEmitter.emitWriter( emitter, null, aElement, aAccessorMethodInfo );

        emitter.newline  (     )
               .decrement(     )
               .line     ( "}" )
               .newline  (     )
               .newline  (     )
              ;
    }
    
    
    /**
     * FILLIN
     *
     * @param       aElement
     * @param       aLevel88Element
     */
    private void generateConditionNameMethodCall( DataElement      aElement,
                                                  Level88Element   aLevel88Element )
    {
        assert aElement        != null;
        assert aLevel88Element != null;
        
        String myConditionNameMethodName =
                environment.getNameConverter()
                           .generateConditionNameMethodName( aLevel88Element.getName() );
        
        PICMarshallerEmitter myPICMarshallerEmitter = getMarshallerEmitter( aElement );

        emitter.indent()
               .append( "public boolean ", myConditionNameMethodName, "( ", environment.getCopybookContextClassName(), " aContext" )
               ;
  
        appendIndexParameterDeclarations( aElement, true );
  
        emitter.append   ( " )" )
               .newline  (      )
               .line     ( "{"  )
               .increment(      )
               .indent   (      )
               ;
            
        myPICMarshallerEmitter.emitConditionNameMethodCall( emitter,
                                                            aElement,
                                                            aLevel88Element,
                                                            "conditionNameValueMappings" );

        emitter.newline  (     )
               .decrement(     )
               .line     ( "}" )
               .newline  (     )
               .newline  (     )
               ;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     * @param       aLeadingComma
     * 
     * @return
     */
    private int appendIndexParameterDeclarations( Element   aElement,
                                                  boolean   aLeadingComma )
    {
        assert aElement != null;

        int myIndexPosition;
        
        Element myOuterIndexElement = aElement.getOuterIndexedElement();
        if ( myOuterIndexElement != null )
        {
            myIndexPosition = appendIndexParameterDeclarations( myOuterIndexElement, aLeadingComma ) + 1;
            aLeadingComma = true;
        }
        else
        {
            myIndexPosition = 1;
        }
        
        if ( aElement.isOccurs()  )
        {
            emitter.append( aLeadingComma ? ", " : "", "int aIndex", myIndexPosition );
        }
        
        return myIndexPosition;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     * 
     * @return
     */
    private PICMarshallerEmitter getMarshallerEmitter( Element aElement )
    {
        assert aElement != null;
        
        PICMarshallerEmitter myEmitter = null;
        
        if ( aElement.getElementType() == ElementType.ElementaryItem )
        {
            DataCategory      myDataCategory      = aElement.getDataCategory();
            UsageType         myUsageType         = aElement.getEffectiveUsageType();
            DataUsageCategory myDataUsageCategory = DataUsageCategory.getUsingDataCategoryAndUsageType( myDataCategory, myUsageType );
            
            assert myDataCategory != null;
            assert myUsageType    != null;
            
            myEmitter = dataUsageCategoryEmittersMap.get( myDataUsageCategory );
            assert myEmitter != null;
        }
        else if ( aElement.getTotalPossibleStringTypesPresent() != 0 )
        {
            myEmitter = AlphanumericPICMarshallerEmitter.getInstance();
        }
        
        assert myEmitter != null;
        
        return myEmitter;
    }
}
