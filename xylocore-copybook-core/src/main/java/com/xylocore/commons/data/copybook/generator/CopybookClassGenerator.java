/*
 * CopybookClassGenerator.java
 *
 * Copyright 2006 The Palantir Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xylocore.commons.data.copybook.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

import com.xylocore.commons.data.copybook.domain.AccessorMethodInfo;
import com.xylocore.commons.data.copybook.domain.Copybook;
import com.xylocore.commons.data.copybook.domain.DataElement;
import com.xylocore.commons.data.copybook.domain.Element;
import com.xylocore.commons.data.copybook.domain.ElementType;
import com.xylocore.commons.data.copybook.domain.Level88Element;
import com.xylocore.commons.data.copybook.domain.ValueRange;
import com.xylocore.commons.data.copybook.domain.config.Environment;
import com.xylocore.commons.data.copybook.domain.config.EnvironmentConfigurationException;
import com.xylocore.commons.data.copybook.parser.CopybookProcessor;
import com.xylocore.commons.data.copybook.runtime.AbstractCopybook;
import com.xylocore.commons.data.copybook.runtime.ConstantValue;
import com.xylocore.commons.data.copybook.runtime.DataCategory;
import com.xylocore.commons.data.copybook.runtime.DataType;
import com.xylocore.commons.data.copybook.runtime.DataUsageCategory;
import com.xylocore.commons.data.copybook.runtime.UsageType;
import com.xylocore.commons.data.copybook.runtime.converters.Converter;
import com.xylocore.commons.data.copybook.runtime.marshallers.PICMarshaller;
import com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy;
import com.xylocore.commons.util.FormatHelper;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class CopybookClassGenerator
{
    //
    // Members
    //
    
    
    private static final int                                            DEFAULT_INDENT_WIDTH                            = 4;
    private static final Map<DataUsageCategory,PICMarshallerEmitter>    dataUsageCategoryEmittersMap                    = new HashMap<DataUsageCategory,PICMarshallerEmitter>();
    private static final Set<String>                                    importedClasses                                 = new TreeSet<String>();
    
    private Environment                                                 environment;
    private Copybook                                                    copybook;
    private PICMarshallerCollectionVisitor                              picMarshallerCollectionVisitor                  = new PICMarshallerCollectionVisitor();
    private ConditionNameValueMapVariableCollectionVisitor              conditionNameValueMapVariableCollectionVisitor  = new ConditionNameValueMapVariableCollectionVisitor();
    private ConverterCollectionVisitor                                  converterCollectionVisitor                      = new ConverterCollectionVisitor();
    private List<Element>                                               elementsOfInterest                              = new ArrayList<Element>();
    private Set<Element>                                                excludedElements                                = new HashSet<Element>();
    private StringBuilder                                               buffer                                          = new StringBuilder();
    private int                                                         indentLevel                                     = 0;
    private int                                                         indentWidth                                     = DEFAULT_INDENT_WIDTH;
    private Map<DataElement,PICMarshaller>                              elementMarshallerMap;
    private Map<Converter,String>                                       converterInstanceNameMappings                   = new TreeMap<Converter,String>();
    private Map<List<NullEquivalentStrategy>,String>                    nullEquivalentStrategySetInstanceNameMappings   = new HashMap<List<NullEquivalentStrategy>,String>();
    private List<ConditionNameValueRanges>                              conditionNameValueRangesList;

    
    
    
    //
    // Static initialization
    //
    
    
    static
    {
        dataUsageCategoryEmittersMap.put( DataUsageCategory.Alphanumeric   , AlphanumericPICMarshallerEmitter.getInstance()    );
        dataUsageCategoryEmittersMap.put( DataUsageCategory.Binary         , BinaryPICMarshallerEmitter.getInstance()          );
        dataUsageCategoryEmittersMap.put( DataUsageCategory.Computational1 , Computational1PICMarshallerEmitter.getInstance()  );
        dataUsageCategoryEmittersMap.put( DataUsageCategory.Computational2 , Computational2PICMarshallerEmitter.getInstance()  );
        dataUsageCategoryEmittersMap.put( DataUsageCategory.Computational3 , Computational3PICMarshallerEmitter.getInstance()  );
        dataUsageCategoryEmittersMap.put( DataUsageCategory.Computational5 , Computational5PICMarshallerEmitter.getInstance()  );
        dataUsageCategoryEmittersMap.put( DataUsageCategory.NumericDisplay , NumericDisplayPICMarshallerEmitter.getInstance()  );
        dataUsageCategoryEmittersMap.put( DataUsageCategory.NumericNational, NumericNationalPICMarshallerEmitter.getInstance() );
        
//        mySingleLookup = new SingleEmitterLookup( AlphanumericPICMarshallerEmitter.getInstance() );
//        dataUsageCategoryEmittersMap.put( DataCategory.Alphabetic  , mySingleLookup );
//        dataUsageCategoryEmittersMap.put( DataCategory.Alphanumeric, mySingleLookup );
//        dataUsageCategoryEmittersMap.put( DataCategory.DBCS        , mySingleLookup );
//        dataUsageCategoryEmittersMap.put( DataCategory.National    , mySingleLookup );
//        
//        myCollectionLookup = new EmitterCollectionLookup();
//        myCollectionLookup.addEmitter( UsageType.Binary        , BinaryPICMarshallerEmitter.getInstance()          );
//        myCollectionLookup.addEmitter( UsageType.Computational1, Computational1PICMarshallerEmitter.getInstance()  );
//        myCollectionLookup.addEmitter( UsageType.Computational2, Computational2PICMarshallerEmitter.getInstance()  );
//        myCollectionLookup.addEmitter( UsageType.Computational3, Computational3PICMarshallerEmitter.getInstance()  );
//        myCollectionLookup.addEmitter( UsageType.Computational5, Computational5PICMarshallerEmitter.getInstance()  );
//        myCollectionLookup.addEmitter( UsageType.Display       , NumericDisplayPICMarshallerEmitter.getInstance()  );
//        myCollectionLookup.addEmitter( UsageType.Display1      , NumericPICMarshallerEmitter.getInstance()        );
//        myCollectionLookup.addEmitter( UsageType.National      , NumericNationalPICMarshallerEmitter.getInstance() );
//        dataUsageCategoryEmittersMap.put( DataCategory.Numeric, myCollectionLookup );
        
//        importedClasses.add( com.xylocore.commons.data.copybook.runtime.AbstractCopybook.class.getName()              );
//        importedClasses.add( com.xylocore.commons.data.copybook.runtime.ConstantValue.class.getName()                 );
//        importedClasses.add( com.xylocore.commons.data.copybook.runtime.SignPosition.class.getName()                  );
//        importedClasses.add( com.xylocore.commons.data.copybook.runtime.SignType.class.getName()                      );
        importedClasses.add( com.xylocore.commons.data.copybook.runtime.AlphanumericPICFlags.class.getName()          );
//        importedClasses.add( com.xylocore.commons.data.copybook.runtime.CopybookContext.class.getName()               );
//        importedClasses.add( com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy.class.getName() );
    }
    
    
    
    
    //
    // Class implementation
    //
    

    /**
     * FILLIN
     * 
     * @param       aEnvironment
     */
    public CopybookClassGenerator( Environment aEnvironment )
    {
        if ( aEnvironment == null )
        {
            throw new IllegalArgumentException( "a environment must be specified" );
        }
        
        environment = aEnvironment;
    }
    
    
    /**
     * FILLIN
     */
    public void generate()
    {
        validateEnvironment();

        try
        {
            String   myPackageName             = environment.getPackageName();
            String   myClassName               = environment.getClassName();
            File     myGenerationRootDirectory = new File( environment.getGenerationRootDirectory() );
            
            copybook = parseCopybook();
            
            File myOutputDirectory =
                    StringUtils.isNotEmpty( myPackageName )
                        ? new File( myGenerationRootDirectory, myPackageName.replace( '.', File.separatorChar ) )
                        : myGenerationRootDirectory;
            myOutputDirectory.mkdirs();
            
            File myOutputFile = new File( myOutputDirectory, myClassName + ".java" );
    
            indentLevel = 0;
            indentWidth = 4;
    
            try ( FileWriter myWriter = new FileWriter( myOutputFile ) )
            {
                selectElementsOfInterest();
                generateSourceFile( myWriter );
            }
            catch ( IOException myIOException )
            {
                // TODO: throw an appropriate exception
            }
        }
        catch ( Exception myException )
        {
            // TODO: throw an appropriate exception
            throw new RuntimeException( myException.getMessage(), myException );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    private Copybook parseCopybook()
    {
        try
        {
            CopybookProcessor myProcessor = new CopybookProcessor();
            return myProcessor.process( environment );
        }
        catch ( Exception myException )
        {
            // TODO: throw an appropriate exception
            throw new RuntimeException( myException );
        }
    }
    
    
    /**
     * FILLIN
     */
    private void validateEnvironment()
    {
        assert environment != null;
        
        String myGenerationRootDirectoryName = environment.getGenerationRootDirectory();
        if ( myGenerationRootDirectoryName == null )
        {
            throw new EnvironmentConfigurationException( "a generation root directory cannot be null" );
        }
        
        File myGenerationRootDirectory = new File( myGenerationRootDirectoryName );
        
        if
        (
            myGenerationRootDirectory != null         &&
            myGenerationRootDirectory.exists()        &&
            ! myGenerationRootDirectory.isDirectory()
        )
        {
            throw new EnvironmentConfigurationException( "the specified generation root directory (" +
                                                         myGenerationRootDirectory.getPath()         +
                                                         ") exists but is not a directory"             );
        }
        
        // TODO: implement validation
    }
    
    
    /**
     * FILLIN
     */
    private void selectElementsOfInterest()
    {
        elementsOfInterest.clear();
        excludedElements  .clear();
        
        checkElement( copybook );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     */
    private void checkElement( Element aElement )
    {
        if
        (
            aElement instanceof DataElement                               &&
            ! excludedElements.contains( aElement )                       &&
            ! ((DataElement) aElement).getAccessorMethodInfos().isEmpty()
        )
        {
            elementsOfInterest.add( aElement );
        }

        if ( aElement.getFirstChild() != null )
        {
            for ( Element myChild = aElement.getFirstChild() ; myChild != null ; myChild = myChild.getNextSibling() )
            {
                checkElement( myChild );
            }
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aWriter
     * 
     * @exception   IOException
     */
    private void generateSourceFile( Writer aWriter )
            throws IOException
    {
        assert aWriter != null;
        
        generateSourceFileHeader      ( aWriter );
        generatePICMarshallers        ( aWriter );
        generateConditionNameVariables( aWriter );
        generateConverterMembers      ( aWriter );
        generateElementsSource        ( aWriter );
        generateSourceFileFooter      ( aWriter );
    }

    
    /**
     * FILLIN
     * 
     * @param       aWriter
     * 
     * @exception   IOException
     */
    private void generateSourceFileHeader( Writer aWriter )
            throws IOException
    {
        assert aWriter != null;
        
        buffer.setLength( 0 );
        
        buffer.append( "package "                   )
              .append( environment.getPackageName() )
              .append( ";\n"                        )
              .append( "\n"                         )
              ;
        
        for ( String myImportedClassName : importedClasses )
        {
            buffer.append( "import "           )
                  .append( myImportedClassName )
                  .append( ";\n"               )
                  ;
        }
        
        buffer.append( "\n"                       )
              .append( "\n"                       )
              .append( "public class "            )
              .append( environment.getClassName() )
              .append( "\n"                       )
              ;
        
        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent( 1 ) );
        
        buffer.append( "extends\n" );
        
        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent( 2 ) );
        
        buffer.append( AbstractCopybook.class.getName() )
              .append( "\n"                             )
              .append( "{\n"                            )
              ;

        aWriter.append( buffer );
        
        indentLevel++;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aWriter
     * 
     * @exception   IOException
     */
    private void generatePICMarshallers( Writer aWriter )
            throws IOException
    {
        assert aWriter != null;
        
        elementMarshallerMap = picMarshallerCollectionVisitor.collect( elementsOfInterest );
        
        if ( ! elementMarshallerMap.isEmpty() )
        {
            int myIndex = 1;
            
            buffer.setLength( 0 );
            
            FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
            
            buffer.append( "//\n" );
            
            FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
            
            buffer.append( "// PIC marshallers\n" );
            
            FormatHelper.stringOfCharacters( ' ', calculateIndent() );
            
            buffer.append( "//\n" )
                  .append( "\n"   )
                  .append( "\n"   )
                  ;

            for ( Map.Entry<DataElement,PICMarshaller> myEntry : elementMarshallerMap.entrySet() )
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
                        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
                        buffer.append( "private static final " );
                        
                        myEmitter.emitMarshaller( buffer, myMarshaller, myMarshallerInstanceName );
                        
                        buffer.append( '\n' );
                    }
                }
            }
            
            buffer.append( "\n" )
                  .append( "\n" )
                  .append( "\n" )
                  .append( "\n" )
                  ;

            aWriter.append( buffer );
        }
    }
    
    
    /**
     * FILLIN
     *
     * @param       aWriter
     * 
     * @exception   IOException
     */
    private void generateConditionNameVariables( Writer aWriter )
            throws IOException
    {
        assert aWriter != null;
        
        conditionNameValueRangesList = conditionNameValueMapVariableCollectionVisitor.collect( elementsOfInterest );
        
        if ( ! conditionNameValueRangesList.isEmpty() )
        {
            String myMapSuffix = "Map<String," + ConstantValue.class.getName() + "[]>";
            
            buffer.setLength( 0 );
            
            FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
            
            buffer.append( "//\n" );
            
            FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
            
            buffer.append( "// Condition name value mappings\n" );
            
            FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
            
            buffer.append( "//\n" )
                  .append( "\n"   )
                  .append( "\n"   )
                  ;
            
            FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
            
            buffer.append( "private static final java.util." )
                  .append( myMapSuffix                       )
                  .append( " conditionNameValueMappings;\n"  )
                  .append( "\n"                              )
                  ;
            
            FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
            
            buffer.append( "static\n" );

            FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
            
            buffer.append( "{\n" );
            
            FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent( 1 ) );
            
            buffer.append( "java.util."                       )
                  .append( myMapSuffix                        )
                  .append( " myMappings = new java.util.Hash" )
                  .append( myMapSuffix                        )
                  .append( "();\n"                            )
                  .append( "\n"                               )
                  ;

            indentLevel++;
            
            for ( ConditionNameValueRanges myConditionNameValueRanges : conditionNameValueRangesList )
            {
                Level88Element       myElement     = myConditionNameValueRanges.getConditionNameElement();
                List<ValueRange>     myValueRanges = myConditionNameValueRanges.getValueRanges();
                Element              myParent      = myElement.getParent();
                PICMarshallerEmitter myEmitter     = getMarshallerEmitter( myParent );
                
                FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
                
                buffer.append( "addConditionNameValueMapping\n" );
                
                FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
                
                buffer.append( "(\n" );
                
                FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent( 1 ) );
                
                buffer.append( "myMappings,\n" );
                
                FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent( 1 ) );
                
                buffer.append( "\""                )
                      .append( myElement.getName() )
                      .append( "\",\n"             )
                      ;
                
                FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent( 1 ) );
                
                buffer.append( "new "                        )
                      .append( ConstantValue.class.getName() )
                      .append( "[]\n"                        )
                      ;
                
                FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent( 2 ) );
                
                buffer.append( "{\n" );
                
                indentLevel += 2;
                
                for ( Iterator<ValueRange> myValueRangeIterator = myValueRanges.iterator() ; myValueRangeIterator.hasNext() ; )
                {
                    ValueRange myValueRange = myValueRangeIterator.next();
                    
                    FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent( 1 ) );
                    
                    myEmitter.emitValueRange( buffer, myParent, myValueRange );
                    
                    buffer.append( myValueRangeIterator.hasNext() ? "," : "" )
                          .append( "\n"                                      )
                          ;
                }
                
                indentLevel -= 2;
                
                FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent( 2 ) );
                
                buffer.append( "}\n" );
                
                FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
                
                buffer.append( ");\n" );
            }
            
            indentLevel--;
            
            buffer.append( "\n" );
            
            FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent( 1 ) );
            
            buffer.append( "conditionNameValueMappings = java.util.Collections.unmodifiableMap( myMappings );\n" );
            
            FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
            
            buffer.append( "}\n" )
                  .append( "\n"  )
                  .append( "\n"  )
                  .append( "\n"  )
                  .append( "\n"  )
                  ;

            aWriter.append( buffer );
        }
    }

    
    
    /**
     * FILLIN
     * 
     * @param       aWriter
     * 
     * @exception   IOException
     */
    private void generateConverterMembers( Writer aWriter )
            throws IOException
    {
        assert aWriter != null;
        
        converterCollectionVisitor.collect( elementsOfInterest );
        Set<Converter>                    myConverters                 = converterCollectionVisitor.getConverters();
        Set<List<NullEquivalentStrategy>> myNullEquivalentStrategySets = converterCollectionVisitor.getNullEquivalentStrategySets();
        
        if ( ! myConverters.isEmpty() )
        {
            int myIndex = 1;
            
            buffer.setLength( 0 );
            
            FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
            
            buffer.append( "//\n" );
            
            FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
            
            buffer.append( "// Converters\n" );
            
            FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
            
            buffer.append( "//\n" )
                  .append( "\n"   )
                  .append( "\n"   )
                  ;

            for ( Converter myConverter : myConverters )
            {
                String myConverterInstanceName = "converter" + myIndex;
                
                myIndex++;
                
                FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
                
                buffer.append( "private static final "          )
                      .append( myConverter.getClass().getName() )
                      .append( " "                              )
                      .append( myConverterInstanceName          )
                      .append( " = "                            )
                      ;
                
                myConverter.emitDeclaration( buffer );
                
                buffer.append( ";\n" );
                
                converterInstanceNameMappings.put( myConverter, myConverterInstanceName );
            }
            
            buffer.append( "\n" )
                  .append( "\n" )
                  .append( "\n" )
                  .append( "\n" )
                  ;

            aWriter.append( buffer );
        }
        
        if ( ! myNullEquivalentStrategySets.isEmpty() )
        {
            int myIndex = 1;
            
            buffer.setLength( 0 );
            
            FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() )
            ;
            buffer.append( "//\n" );
            
            FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
            
            buffer.append( "// Null equivalent strategy sets\n" );
            
            FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
            
            buffer.append( "//\n" )
                  .append( "\n"   )
                  .append( "\n"   )
                  ;

            for ( List<NullEquivalentStrategy> myNullEquivalentStrategySet : myNullEquivalentStrategySets )
            {
                String myNullEquivalentStrategySetInstanceName = "nullEquivalentStrategySet" + myIndex;
                
                myIndex++;
                
                boolean myFirst           = true;
                int     myExtraLineIndent = buffer.length();

                for ( NullEquivalentStrategy myNullEquivalentStrategy : myNullEquivalentStrategySet )
                {
                    if ( myFirst )
                    {
                        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
                        
                        buffer.append( "private static final "                 )
                              .append( NullEquivalentStrategy.class.getName()  )
                              .append( "[] "                                   )
                              .append( myNullEquivalentStrategySetInstanceName )
                              .append( " = { "                                 )
                              ;

                        myExtraLineIndent = buffer.length() - myExtraLineIndent;
                        myFirst           = false;
                    }
                    else
                    {
                        buffer.append( ",\n" );
                        
                        FormatHelper.stringOfCharacters( buffer, ' ', myExtraLineIndent );
                    }
                    
                    myNullEquivalentStrategy.emitDeclaration( buffer );
                }
                
                buffer.append( " };\n" );
                
                nullEquivalentStrategySetInstanceNameMappings.put( myNullEquivalentStrategySet,
                                                                   myNullEquivalentStrategySetInstanceName );
            }
            
            buffer.append( "\n" )
                  .append( "\n" )
                  .append( "\n" )
                  .append( "\n" )
                  ;

            aWriter.append( buffer );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aWriter
     * 
     * @exception   IOException
     */
    private void generateSourceFileFooter( Writer aWriter )
            throws IOException
    {
        assert aWriter != null;
        
        indentLevel--;
        
        buffer.setLength( 0 );
        
        buffer.append( "}\n" );

        aWriter.append( buffer );
    }
    
   
    /**
     * FILLIN
     * 
     * @param       aWriter
     * 
     * @exception   IOException
     */
    private void generateElementsSource( Writer aWriter )
            throws IOException
    {
        assert aWriter != null;
        
        String myClassName = environment.getClassName();
        
        buffer.setLength( 0 );
        
        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
        
        buffer.append( "//\n" );
        
        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
        
        buffer.append( "// Members\n" );
        
        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
        
        buffer.append( "//\n" )
              .append( "\n"   )
              .append( "\n"   )
              ;
        
        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
        
        buffer.append( "private static final " )
              .append( myClassName             )
              .append( " instance = new "      )
              .append( myClassName             )
              .append( "();\n"                 )
              .append( "\n"                    )
              .append( "\n"                    )
              .append( "\n"                    )
              .append( "\n"                    )
              ;
        
        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
        
        buffer.append( "//\n" );
        
        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
        
        buffer.append( "// Class implementation\n" );
        
        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
        
        buffer.append( "//\n" )
              .append( "\n"   )
              .append( "\n"   )
              ;
        
        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
        
        buffer.append( "public static "   )
              .append( myClassName        )
              .append( " getInstance()\n" )
              ;
        
        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
        
        buffer.append( "{\n" );
        
        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent( 1 ) );
        
        buffer.append( "return instance;\n" );
        
        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
        
        buffer.append( "}\n" )
              .append( "\n"  )
              .append( "\n"  )
              ;
        
        aWriter.append( buffer );
        
        generateRecordLengthMethods( aWriter );

        for ( Iterator<Element> myElementIterator  = elementsOfInterest.iterator() ; myElementIterator.hasNext() ; )
        {
            generateElementSource( aWriter, (DataElement) myElementIterator.next(), ! myElementIterator.hasNext() );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aWriter
     * 
     * @exception   IOException
     */
    private void generateRecordLengthMethods( Writer aWriter )
            throws IOException
    {
        assert aWriter != null;
        
        buffer.setLength( 0 );
        
        generateLengthMethod( "getMaximumRecordLength", copybook.getSize() );
        
        for ( Element myChild = copybook.getFirstChild() ; myChild != null ; myChild = myChild.getNextSibling() )
        {
            generateLengthMethod( myChild.getRecordLengthMethodName(), myChild.getSize() );
        }

        aWriter.append( buffer );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aWriter
     * @param       aElement
     * @param       aLastElement
     * 
     * @exception   IOException
     */
    private void generateElementSource( Writer        aWriter,
                                        DataElement   aElement,
                                        boolean       aLastElement )
            throws IOException
    {
        assert aWriter  != null;
        assert aElement != null;

        // Set the null equivalent strategy instance variable name
        List<NullEquivalentStrategy> myNullEquivalentStrategies                   = aElement.getNullEquivalentStrategies();
        String                       myNullEquivalentStrategyInstanceVariableName = nullEquivalentStrategySetInstanceNameMappings.get( myNullEquivalentStrategies );
        if ( myNullEquivalentStrategyInstanceVariableName != null )
        {
            aElement.setNullEquivalentStrategySetInstanceVariableName( myNullEquivalentStrategyInstanceVariableName );
        }

        buffer.setLength( 0 );
        generateOffsetMethodCall( aElement );
        generateLengthMethodCall( aElement );
        generateBlankMethodCall ( aElement );
        generateNullMethodCall  ( aElement );
        
        aWriter.append( buffer );
            
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
            
            buffer.setLength( 0 );

            generateValidMethodCall( aElement, myAccessorMethodInfo, myIsDefaultAccessor, myHasMultipleAccessors             );
            generateGetMethodCall  ( aElement, myAccessorMethodInfo, myIsDefaultAccessor, myHasMultipleAccessors, myJavaType );
            generateSetMethodCall  ( aElement, myAccessorMethodInfo, myIsDefaultAccessor, myHasMultipleAccessors, myJavaType );
    
            if ( aLastElement )
            {
                buffer.setLength( buffer.length()-2 );
            }

            aWriter.append( buffer );
        }
        
        for ( Level88Element myLevel88Element : aElement.getLevel88Elements() )
        {
            buffer.setLength( 0 );
            
            generateConditionNameMethodCall( aElement, myLevel88Element );

            aWriter.append( buffer );
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
        
        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
        
        buffer.append( "public int "                  )
              .append( myOffsetMethodName             )
              .append( "("                            )
              .append( aElement.isOccurs() ? " " : "" )
              ;
            
        appendIndexParameterDeclarations( aElement, false );
        
        buffer.append( aElement.isOccurs() ? " " : "" )
              .append( ")\n"                          )
              ;
        
        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
        
        buffer.append( "{\n" );
        
        indentLevel++;
        
        try
        {
            FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
            
            buffer.append( "return " );
            
            IndexOffsets myIndexOffset = new IndexOffsets();
            myIndexOffset.emit( buffer, aElement );

            buffer.append( ";\n" );
        }
        finally
        {
            indentLevel--;
        }
        
        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
        
        buffer.append( "}\n" )
              .append( "\n"  )
              .append( "\n"  )
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
        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
        
        buffer.append( "public int " )
              .append( aMethodName   )
              .append( "()\n"        )
              ;
        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
        
        buffer.append( "{\n" );
        
        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent( 1 ) );
        
        buffer.append( "return " )
              .append( aLength   )
              .append( ";\n"     )
              ;
        
        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
        
        buffer.append( "}\n" )
              .append( "\n"  )
              .append( "\n"  )
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
        
        PICMarshallerEmitter myEmitter = getMarshallerEmitter( aElement );

        if ( myEmitter.isBlankMethodNeeded( aElement ) )
        {
            String myBlankMethodName =
                    environment.getNameConverter()
                               .generateBlankMethodName( aElement.getName() );
            
            FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
            
            buffer.append( "public boolean "                         )
                  .append( myBlankMethodName                         )
                  .append( "( "                                      )
                  .append( environment.getCopybookContextClassName() )
                  .append( " aContext"                               )
                  ;
            
            appendIndexParameterDeclarations( aElement, true );
            
            buffer.append( " )\n" );
            
            FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
            
            buffer.append( "{\n" );
            
            indentLevel++;
            
            try
            {
                FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
                
                myEmitter.emitBlankMethodCall( buffer, null, aElement );

                buffer.append( "\n" );
            }
            finally
            {
                indentLevel--;
            }
            
            FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
            
            buffer.append( "}\n" )
                  .append( "\n"  )
                  .append( "\n"  )
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
        
        PICMarshallerEmitter myEmitter = getMarshallerEmitter( aElement );

        if ( myEmitter.isNullMethodNeeded( aElement ) )
        {
            String myNullMethodName =
                    environment.getNameConverter()
                               .generateNullMethodName( aElement.getName() );
            
            FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
            
            buffer.append( "public boolean "                         )
                  .append( myNullMethodName                          )
                  .append( "( "                                      )
                  .append( environment.getCopybookContextClassName() )
                  .append( " aContext"                               )
                  ;
            
            appendIndexParameterDeclarations( aElement, true );
            
            buffer.append( " )\n" );
            
            FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
            
            buffer.append( "{\n" );
            
            indentLevel++;
            
            try
            {
                FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
                
                myEmitter.emitNullMethodCall( buffer, null, aElement );

                buffer.append( "\n" );
            }
            finally
            {
                indentLevel--;
            }
            
            FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
            
            buffer.append( "}\n" )
                  .append( "\n"  )
                  .append( "\n"  )
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
        
        PICMarshallerEmitter myEmitter = getMarshallerEmitter( aElement );

        if ( myEmitter.isValidMethodNeeded( aElement, aAccessorMethodInfo ) )
        {
            String myValidMethodName =
                    environment.getNameConverter()
                               .generateValidMethodName( aElement.getName(),
                                                         aAccessorMethodInfo.getDataType(),
                                                         aIsDefaultAccessor,
                                                         aHasMultipleDatatypes              );
            
            FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
            
            buffer.append( "public boolean "                         )
                  .append( myValidMethodName                         )
                  .append( "( "                                      )
                  .append( environment.getCopybookContextClassName() )
                  .append( " aContext"                               )
                  ;
            
            appendIndexParameterDeclarations( aElement, true );
            
            buffer.append( " )\n" );
            
            FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
            
            buffer.append( "{\n" );
            
            indentLevel++;
            
            try
            {
                FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
                
                myEmitter.emitValidMethodCall( buffer, null, aElement, aAccessorMethodInfo );

                buffer.append( "\n" );
            }
            finally
            {
                indentLevel--;
            }
            
            FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
            
            buffer.append( "}\n" )
                  .append( "\n"  )
                  .append( "\n"  )
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
        
        PICMarshallerEmitter myEmitter = getMarshallerEmitter( aElement );

        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
        
        buffer.append( "public "                                 )
              .append( aJavaType                                 )
              .append( " "                                       )
              .append( myGetterMethodName                        )
              .append( "( "                                      )
              .append( environment.getCopybookContextClassName() )
              .append( " aContext"                               )
              ;
  
        appendIndexParameterDeclarations( aElement, true );
  
        buffer.append( " )\n" );
        
        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
        
        buffer.append( "{\n" );

        indentLevel++;
        
        try
        {
            FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
            
            myEmitter.emitReader( buffer, null, aElement, aAccessorMethodInfo );

            buffer.append( "\n" );
        }
        finally
        {
            indentLevel--;
        }

        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
        
        buffer.append( "}\n" )
              .append( "\n"  )
              .append( "\n"  )
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
        
        PICMarshallerEmitter myEmitter = getMarshallerEmitter( aElement );
        
        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
        
        buffer.append( "public void "                            )
              .append( mySetterMethodName                        )
              .append( "( "                                      )
              .append( environment.getCopybookContextClassName() )
              .append( " aContext"                               )
              ;
  
        appendIndexParameterDeclarations( aElement, true );
  
        buffer.append            ( ", "                   )
              .append            ( aJavaType              )
              .append            ( " aValue )\n"          )
              ;
        
        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
        
        buffer.append( "{\n" );
  
        indentLevel++;
        
        try
        {
            FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
            
            myEmitter.emitWriter( buffer, null, aElement, aAccessorMethodInfo );

            buffer.append( "\n" );
        }
        finally
        {
            indentLevel--;
        }

        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
        
        buffer.append( "}\n" )
              .append( "\n"  )
              .append( "\n"  )
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
        
        PICMarshallerEmitter myEmitter = getMarshallerEmitter( aElement );
        
        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
        
        buffer.append( "public boolean "                         )
              .append( myConditionNameMethodName                 )
              .append( "( "                                      )
              .append( environment.getCopybookContextClassName() )
              .append( " aContext"                               )
              ;
  
        appendIndexParameterDeclarations( aElement, true );
  
        buffer.append( " )\n" );
        
        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
        
        buffer.append( "{\n" );
  
        indentLevel++;
        
        try
        {
            FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
            
            myEmitter.emitConditionNameMethodCall( buffer,
                                                   aElement,
                                                   aLevel88Element,
                                                   "conditionNameValueMappings" );

            buffer.append( "\n" );
        }
        finally
        {
            indentLevel--;
        }

        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
        
        buffer.append( "}\n" )
              .append( "\n"  )
              .append( "\n"  )
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
            buffer.append( aLeadingComma ? ", " : "" )
                  .append( "int aIndex"              )
                  .append( myIndexPosition           )
                  ;
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
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    private int calculateIndent()
    {
        return calculateIndent( 0 );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aLevelDelta
     * 
     * @return
     */
    private int calculateIndent( int aLevelDelta )
    {
        return (indentLevel + aLevelDelta) * indentWidth;
    }
}
