package com.xylocore.commons.data.copybook.internal;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.xylocore.commons.util.FormatHelper;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class AbstractCopybookGenerator
{
    //
    // Nested classes
    //
    
    
    private static class Parameter
    {
        //
        // Members
        //
        
        private String dataType;
        private String name;
        
        
        //
        // Class implementation
        //
        
        /**
         * FILLIN
         * 
         * @param       aDataType
         * @param       aName
         */
        public Parameter( String   aDataType,
                          String   aName      )
        {
            assert StringUtils.isNotBlank( aDataType );
            assert StringUtils.isNotBlank( aName     );
            
            dataType = aDataType;
            name     = aName;
        }
        
        /**
         * FILLIN
         * 
         * @return
         */
        public String getDataType()
        {
            return dataType;
        }
        
        /**
         * FILLIN
         * 
         * @return
         */
        public String getName()
        {
            return name;
        }
    }
    
    
    
    
    //
    // Members
    //
    
    
    private static final String[][] allDataTypes =
    {
        { "byte"      , "Byte"       },
        { "char"      , "Char"       },
        { "short"     , "Short"      },
        { "int"       , "Integer"    },
        { "long"      , "Long"       },
        { "float"     , "Float"      },
        { "double"    , "Double"     },
        { "BigInteger", "BigInteger" },
        { "BigDecimal", "BigDecimal" },
        { "String"    , "String"     },
        { "Date"      , "Date"       }
    };
    private static final String[][] comp12DataTypes =
    {
        { "byte"      , "Byte"       },
        { "char"      , "Char"       },
        { "short"     , "Short"      },
        { "int"       , "Integer"    },
        { "long"      , "Long"       },
        { "float"     , "Float"      },
        { "double"    , "Double"     },
        { "BigInteger", "BigInteger" },
        { "BigDecimal", "BigDecimal" },
        { "String"    , "String"     }
    };
    
    
    
    
    //
    // Class implementation
    //
    
    
    public static void main( String[] args )
    {
        try
        {
            PrintStream myOutputStream = System.out;
            myOutputStream = new PrintStream( new FileOutputStream( "src/main/java/com/xylocore/commons/data/copybook/runtime/AbstractCopybook.java.new" ) );
            
            AbstractCopybookGenerator myGenerator = new AbstractCopybookGenerator();
            myGenerator.generate( myOutputStream );
            
            myOutputStream.close();
        }
        catch ( Exception myException )
        {
            myException.printStackTrace();
        }
    }
    
    
    public void generate( PrintStream aOutputStream )
    {
        generateHeader( aOutputStream );
        
        generateNumericDisplayPICMarshallerDelegation ( aOutputStream );
        generateDelegationSectionSeparator            ( aOutputStream );
        generateNumericNationalPICMarshallerDelegation( aOutputStream );
        generateDelegationSectionSeparator            ( aOutputStream );
        generateBinaryPICMarshallerDelegation         ( aOutputStream );
        generateDelegationSectionSeparator            ( aOutputStream );
        generateComputational1PICMarshallerDelegation ( aOutputStream );
        generateDelegationSectionSeparator            ( aOutputStream );
        generateComputational2PICMarshallerDelegation ( aOutputStream );
        generateDelegationSectionSeparator            ( aOutputStream );
        generateComputational3PICMarshallerDelegation ( aOutputStream );
        generateDelegationSectionSeparator            ( aOutputStream );
        generateComputational5PICMarshallerDelegation ( aOutputStream );
        generateDelegationSectionSeparator            ( aOutputStream );
        generateAlphanumericPICMarshallerDelegation   ( aOutputStream );
        
        generateFooter( aOutputStream );
    }
    
    
    private static void generateHeader( PrintStream aOutputStream )
    {
        aOutputStream.println( "package com.xylocore.commons.data.copybook.runtime;" );
        aOutputStream.println();
        aOutputStream.println( "import java.math.BigDecimal;" );
        aOutputStream.println( "import java.math.BigInteger;" );
        aOutputStream.println( "import java.util.Date;" );
        aOutputStream.println();
        aOutputStream.println( "import com.xylocore.commons.data.copybook.runtime.CopybookContext;" );
        aOutputStream.println( "import com.xylocore.commons.data.copybook.runtime.converters.Converter;" );
        aOutputStream.println( "import com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy;" );
        aOutputStream.println();
        aOutputStream.println();
        aOutputStream.println( "/**" );
        aOutputStream.println( " * FILLIN" );
        aOutputStream.println( " * " );
        aOutputStream.println( " * @author      Eric R. Medley" );
        aOutputStream.println( " */" );
        aOutputStream.println();
        aOutputStream.println( "public abstract class AbstractCopybook" );
        aOutputStream.println( "    extends" );
        aOutputStream.println( "        AbstractCopybookBase" );
        aOutputStream.println( "{" );
    }
    
    
    private static void generateNumericDisplayPICMarshallerDelegation( PrintStream aOutputStream )
    {
        generateNumericSectionPICMarshallerDelegation( aOutputStream, "Display" );
    }
    
    
    private static void generateNumericNationalPICMarshallerDelegation( PrintStream aOutputStream )
    {
        generateNumericSectionPICMarshallerDelegation( aOutputStream, "National" );
    }


    private static void generateBinaryPICMarshallerDelegation( PrintStream aOutputStream )
    {
        generateNumericCompPICMarshallerDelegation( aOutputStream, "Binary", "Binary" );
    }
    

    private static void generateComputational1PICMarshallerDelegation( PrintStream aOutputStream )
    {
        generateNumericComp12PICMarshallerDelegation( aOutputStream, "Comp1", "Computational1" );
    }


    private static void generateComputational2PICMarshallerDelegation( PrintStream aOutputStream )
    {
        generateNumericComp12PICMarshallerDelegation( aOutputStream, "Comp2", "Computational2" );
    }


    private static void generateComputational3PICMarshallerDelegation( PrintStream aOutputStream )
    {
        generateNumericCompPICMarshallerDelegation( aOutputStream, "Comp3", "Computational3" );
    }


    private static void generateComputational5PICMarshallerDelegation( PrintStream aOutputStream )
    {
        generateNumericCompPICMarshallerDelegation( aOutputStream, "Comp5", "Computational5" );
    }


    private static void generateAlphanumericPICMarshallerDelegation( PrintStream aOutputStream )
    {
        generateDelegationSectionTitle( aOutputStream, "AlphanumericPICMarshaller" );
        
        List<Parameter> myParameters = new ArrayList<Parameter>();
        myParameters.add( new Parameter( "CopybookContext", "aContext" ) );
        myParameters.add( new Parameter( "int"            , "aOffset"  ) );
        myParameters.add( new Parameter( "int"            , "aSize"    ) );
        myParameters.add( new Parameter( "int"            , "aFlags"   ) );

        generateIsBlankMethod( aOutputStream,
                               "public",
                               "isAlphanumericBlank",
                               "alphanumericMarshaller.isBlank",
                               myParameters,
                               0,
                               0                                 );
        
        for ( int i = 0, ci = allDataTypes.length ; i < ci ; i++ )
        {
            generateAlphanumericSectionMethodsForDataType( aOutputStream, allDataTypes[i][0], allDataTypes[i][1] );
        }
        
        generateIsConditionNameValidMethod( aOutputStream,
                                            "public",
                                            "isAlphanumericConditionNameValid",
                                            "alphanumericMarshaller.isConditionNameValid",
                                            myParameters,
                                            2,
                                            0                                              );
    }
    
    
    private static void generateFooter( PrintStream aOutputStream )
    {
        aOutputStream.println( "}" );
    }
    
    
    private static void generateDelegationSectionTitle( PrintStream   aOutputStream,
                                                        String        aTitle         )
    {
        aOutputStream.println( "    //"                           );
        aOutputStream.println( "    // " + aTitle + " delegation" );
        aOutputStream.println( "    //"                           );
        aOutputStream.println();
        aOutputStream.println();
    }
    
    
    private static void generateDelegationSectionSeparator( PrintStream aOutputStream )
    {
        aOutputStream.println();
        aOutputStream.println();
        aOutputStream.println();
        aOutputStream.println();
    }
    
    
    private static void generateNumericSectionPICMarshallerDelegation( PrintStream   aOutputStream,
                                                                       String        aNumericType   )
    {
        generateDelegationSectionTitle( aOutputStream, "Numeric" + aNumericType + "PICMarshaller" );
        
        List<Parameter> myParameters = new ArrayList<Parameter>();
        myParameters.add( new Parameter( "CopybookContext", "aContext"      ) );
        myParameters.add( new Parameter( "int"            , "aOffset"       ) );
        myParameters.add( new Parameter( "int"            , "aDigits"       ) );
        myParameters.add( new Parameter( "SignType"       , "aSignType"     ) );
        myParameters.add( new Parameter( "SignPosition"   , "aSignPosition" ) );

        String myMarshallerName = "numeric" + aNumericType + "Marshaller";
        String myNumericClass   = "Numeric" + aNumericType;
        
        generateIsBlankMethod( aOutputStream,
                               "public",
                               "is" + myNumericClass + "Blank",
                               myMarshallerName + ".isBlank",
                               myParameters,
                               0,
                               0                                );
        
        myParameters.add( new Parameter( "int", "aPrecision"       ) );
        myParameters.add( new Parameter( "int", "aScalingPosition" ) );
        
        for ( int i = 0, ci = allDataTypes.length ; i < ci ; i++ )
        {
            generateNumericSectionMethodsForDataType( aOutputStream,
                                                      myParameters,
                                                      myMarshallerName,
                                                      myNumericClass,
                                                      allDataTypes[i][0],
                                                      allDataTypes[i][1] );
        }
        
        generateIsConditionNameValidMethod( aOutputStream,
                                            "public",
                                            "is" + myNumericClass + "ConditionNameValid",
                                            myMarshallerName + ".isConditionNameValid",
                                            myParameters,
                                            2,
                                            0                                             );
    }
    
    
    private static void generateNumericSectionMethodsForDataType( PrintStream       aOutputStream,
                                                                  List<Parameter>   aParameters,
                                                                  String            aMarshallerName,
                                                                  String            aNumericClass,
                                                                  String            aDataType,
                                                                  String            aDataTypeName    )
    {
        List<Parameter> myParameters = new ArrayList<Parameter>( aParameters );

        if ( aDataTypeName.equals( "Date" ) )
        {
            myParameters.add( new Parameter( "Converter" , "aConverter" ) );
        }
        
        if
        (
            aDataTypeName.equals( "BigInteger" ) ||
            aDataTypeName.equals( "BigDecimal" ) ||
            aDataTypeName.equals( "String"     ) ||
            aDataTypeName.equals( "Date"       )
        )
        {
            myParameters.add( new Parameter( "NullEquivalentStrategy[]", "aNullEquivalentStrategies" ) );
        }

        generateIsValidMethod( aOutputStream,
                               "public",
                               "is" + aNumericClass + "As" + aDataTypeName + "Valid",
                               aMarshallerName + ".isValidAs" + aDataTypeName,
                               myParameters,
                               2,
                               0                                                      );

        generateDecodeMethod( aOutputStream,
                              "public",
                              aDataType,
                              "decode" + aNumericClass + "As" + aDataTypeName,
                              aMarshallerName + ".decodeAs" + aDataTypeName,
                              myParameters,
                              2,
                              0                                                );

        myParameters.add( 2, new Parameter( aDataType, "aValue" ) );
        
        generateEncodeMethod( aOutputStream,
                              "public",
                              aDataType,
                              "encode" + aNumericClass + "As" + aDataTypeName,
                              aMarshallerName + ".encodeAs" + aDataTypeName,
                              myParameters,
                              2,
                              0                                                );
    }
    
    
    private static void generateNumericComp12PICMarshallerDelegation( PrintStream   aOutputStream,
                                                                      String        aNumericType,
                                                                      String        aSectionHeaderType )
    {
        generateDelegationSectionTitle( aOutputStream, aSectionHeaderType + "PICMarshaller" );
        
        List<Parameter> myParameters = new ArrayList<Parameter>();
        myParameters.add( new Parameter( "CopybookContext", "aContext" ) );
        myParameters.add( new Parameter( "int"            , "aOffset"  ) );
        
        String myMarshallerName = buildMarshallerName( aNumericType );
        
        generateIsValidMethod( aOutputStream,
                               "public",
                               "is" + aNumericType + "Valid",
                               myMarshallerName + ".isValid",
                               myParameters,
                               0,
                               0                              );
        
        for ( int i = 0, ci = comp12DataTypes.length ; i < ci ; i++ )
        {
            generateNumericComp12SectionMethodsForDataType( aOutputStream, aNumericType, allDataTypes[i][0], allDataTypes[i][1] );
        }
        
        generateIsConditionNameValidMethod( aOutputStream,
                                            "public",
                                            "is" + aNumericType + "ConditionNameValid",
                                            myMarshallerName + ".isConditionNameValid",
                                            myParameters,
                                            2,
                                            0                                           );
    }
    
    
    private static void generateNumericComp12SectionMethodsForDataType( PrintStream   aOutputStream,
                                                                        String        aNumericType,
                                                                        String        aDataType,
                                                                        String        aDataTypeName  )
    {
        List<Parameter> myParameters = new ArrayList<Parameter>();
        myParameters.add( new Parameter( "CopybookContext", "aContext" ) );
        myParameters.add( new Parameter( "int"            , "aOffset"  ) );
        
        String myMarshallerName = buildMarshallerName( aNumericType );

        generateDecodeMethod( aOutputStream,
                              "public",
                              aDataType,
                              "decode" + aNumericType + "As" + aDataTypeName,
                              myMarshallerName + ".decodeAs" + aDataTypeName,
                              myParameters,
                              2,
                              0                                               );

        myParameters.add( new Parameter( aDataType, "aValue" ) );
        
        generateEncodeMethod( aOutputStream,
                              "public",
                              aDataType,
                              "encode" + aNumericType + "As" + aDataTypeName,
                              myMarshallerName + ".encodeAs" + aDataTypeName,
                              myParameters,
                              2,
                              0                                               );
    }
    
    
    private static void generateNumericCompPICMarshallerDelegation( PrintStream   aOutputStream,
                                                                    String        aNumericType,
                                                                    String        aSectionHeaderType )
    {
        List<Parameter> myParameters = new ArrayList<Parameter>();
        myParameters.add( new Parameter( "CopybookContext", "aContext"         ) );
        myParameters.add( new Parameter( "int"            , "aOffset"          ) );
        myParameters.add( new Parameter( "int"            , "aDigits"          ) );
        myParameters.add( new Parameter( "SignType"       , "aSignType"        ) );
        myParameters.add( new Parameter( "int"            , "aPrecision"       ) );
        myParameters.add( new Parameter( "int"            , "aScalingPosition" ) );

        String myMarshallerName = buildMarshallerName( aNumericType );
        
        generateDelegationSectionTitle( aOutputStream, aSectionHeaderType + "PICMarshaller" );
        
        for ( int i = 0, ci = allDataTypes.length ; i < ci ; i++ )
        {
            generateNumericCompSectionMethodsForDataType( aOutputStream,
                                                          aNumericType,
                                                          myParameters,
                                                          myMarshallerName,
                                                          allDataTypes[i][0],
                                                          allDataTypes[i][1] );
        }
        
        generateIsConditionNameValidMethod( aOutputStream,
                                            "public",
                                            "is" + aNumericType + "ConditionNameValid",
                                            myMarshallerName + ".isConditionNameValid",
                                            myParameters,
                                            0,
                                            0                                           );
    }
    
    
    private static void generateNumericCompSectionMethodsForDataType( PrintStream       aOutputStream,
                                                                      String            aNumericType,
                                                                      List<Parameter>   aParameters,
                                                                      String            aMarshallerName,
                                                                      String            aDataType,
                                                                      String            aDataTypeName    )
    {
        List<Parameter> myParameters = new ArrayList<Parameter>( aParameters );

        generateIsValidMethod( aOutputStream,
                               "public",
                               "is" + aNumericType + "As" + aDataTypeName + "Valid",
                               aMarshallerName + ".isValidAs" + aDataTypeName,
                               myParameters,
                               0,
                               2                                                     );

        generateDecodeMethod( aOutputStream,
                              "public",
                              aDataType,
                              "decode" + aNumericType + "As" + aDataTypeName,
                              aMarshallerName + ".decodeAs" + aDataTypeName,
                              myParameters,
                              0,
                              2                                               );

        myParameters.add( 2, new Parameter( aDataType, "aValue" ) );
        
        generateEncodeMethod( aOutputStream,
                              "public",
                              aDataType,
                              "encode" + aNumericType + "As" + aDataTypeName,
                              aMarshallerName + ".encodeAs" + aDataTypeName,
                              myParameters,
                              0,
                              2                                               );
    }

    
    private static void generateAlphanumericSectionMethodsForDataType( PrintStream   aOutputStream,
                                                                       String        aDataType,
                                                                       String        aDataTypeName  )
    {
        List<Parameter> myParameters = new ArrayList<Parameter>();
        myParameters.add( new Parameter( "CopybookContext", "aContext" ) );
        myParameters.add( new Parameter( "int"            , "aOffset"  ) );
        myParameters.add( new Parameter( "int"            , "aSize"    ) );
        myParameters.add( new Parameter( "int"            , "aFlags"   ) );

        if ( aDataTypeName.equals( "Date" ) )
        {
            myParameters.add( new Parameter( "Converter" , "aConverter" ) );
        }
        if
        (
            aDataTypeName.equals( "BigInteger" ) ||
            aDataTypeName.equals( "BigDecimal" ) ||
            aDataTypeName.equals( "String"     ) ||
            aDataTypeName.equals( "Date"       )
        )
        {
            myParameters.add( new Parameter( "NullEquivalentStrategy[]", "aNullEquivalentStrategies" ) );
        }
        
        generateIsValidMethod( aOutputStream,
                               "public",
                               "isAlphanumericAs" + aDataTypeName + "Valid",
                               "alphanumericMarshaller.isValidAs" + aDataTypeName,
                               myParameters,
                               2,
                               0                                                   );
        
        generateDecodeMethod( aOutputStream,
                              "public",
                              aDataType,
                              "decodeAlphanumericAs" + aDataTypeName,
                              "alphanumericMarshaller.decodeAs" + aDataTypeName,
                              myParameters,
                              2,
                              0                                                  );

        myParameters.add( 2, new Parameter( aDataType, "aValue"   ) );
        
        generateEncodeMethod( aOutputStream,
                              "public",
                              aDataType,
                              "encodeAlphanumericAs" + aDataTypeName,
                              "alphanumericMarshaller.encodeAs" + aDataTypeName,
                              myParameters,
                              2,
                              0                                                  );
    }
    
    
    private static String buildMarshallerName( String aNumericType )
    {
        return aNumericType.substring( 0, 1 ).toLowerCase() + aNumericType.substring( 1 ) + "Marshaller";
    }

    
    private static void generateIsBlankMethod( PrintStream       aOutputStream,
                                               String            aModifiers,
                                               String            aMethodName,
                                               String            aDelegateMethodPrefix,
                                               List<Parameter>   aParameters,
                                               int               aLinesBefore,
                                               int               aLinesAfter            )
    {
        for ( int i = 0 ; i < aLinesBefore ; i++ )
        {
            aOutputStream.println();
        }
        
        generateMethodHeader( aOutputStream,
                              aModifiers,
                              aMethodName,
                              "boolean",
                              aParameters    );

        aOutputStream.println( "    {"                          );
        aOutputStream.println( "        aContext.clearError();" );

        generateDelegateMethodCall( aOutputStream,
                                    "boolean myBlank = " + aDelegateMethodPrefix,
                                    aParameters                                   );

        aOutputStream.println( "        checkForError( aContext );" );
        aOutputStream.println( "        return myBlank;"            );
        aOutputStream.println( "    }"                              );
        
        for ( int i = 0 ; i < aLinesAfter ; i++ )
        {
            aOutputStream.println();
        }
    }

    
    private static void generateIsValidMethod( PrintStream       aOutputStream,
                                               String            aModifiers,
                                               String            aMethodName,
                                               String            aDelegateMethodPrefix,
                                               List<Parameter>   aParameters,
                                               int               aLinesBefore,
                                               int               aLinesAfter            )
    {
        for ( int i = 0 ; i < aLinesBefore ; i++ )
        {
            aOutputStream.println();
        }
        
        generateMethodHeader( aOutputStream,
                              aModifiers,
                              aMethodName,
                              "boolean",
                              aParameters    );

        aOutputStream.println( "    {" );
        
        generateDelegateMethodCall( aOutputStream,
                                    "return " + aDelegateMethodPrefix,
                                    aParameters                        );

        aOutputStream.println( "    }" );
        
        for ( int i = 0 ; i < aLinesAfter ; i++ )
        {
            aOutputStream.println();
        }
    }
    
    
    private static void generateIsConditionNameValidMethod( PrintStream       aOutputStream,
                                                            String            aModifiers,
                                                            String            aMethodName,
                                                            String            aDelegateMethodPrefix,
                                                            List<Parameter>   aParameters,
                                                            int               aLinesBefore,
                                                            int               aLinesAfter            )
    {
        List<Parameter> myParameters = new ArrayList<Parameter>( aParameters );
        myParameters.add( 2, new Parameter( "String"                               , "aConditionName"                ) );
        myParameters.add( 3, new Parameter( "java.util.Map<String,ConstantValue[]>", "aConditionNameValueMappings"   ) );
        
        for ( int i = 0 ; i < aLinesBefore ; i++ )
        {
            aOutputStream.println();
        }
        
        generateMethodHeader( aOutputStream,
                              aModifiers,
                              aMethodName,
                              "boolean",
                              myParameters   );

        aOutputStream.println( "    {" );
        
        generateDelegateMethodCall( aOutputStream,
                                    "return " + aDelegateMethodPrefix,
                                    myParameters                       );

        aOutputStream.println( "    }" );
        
        for ( int i = 0 ; i < aLinesAfter ; i++ )
        {
            aOutputStream.println();
        }
    }
    
    
    private static void generateDecodeMethod( PrintStream      aOutputStream,
                                              String           aModifiers,
                                              String           aDataType,
                                              String           aMethodName,
                                              String           aDelegateMethodPrefix,
                                              List<Parameter>  aParameters,
                                              int              aLinesBefore,
                                              int              aLinesAfter            )
    {
        for ( int i = 0 ; i < aLinesBefore ; i++ )
        {
            aOutputStream.println();
        }
        
        generateMethodHeader( aOutputStream,
                              aModifiers,
                              aMethodName,
                              aDataType,
                              aParameters    );

        aOutputStream.println( "    {"                          );
        aOutputStream.println( "        aContext.clearError();" );

        generateDelegateMethodCall( aOutputStream,
                                    aDataType + " myValue = " + aDelegateMethodPrefix,
                                    aParameters                                        );

        aOutputStream.println( "        checkForError( aContext );" );
        aOutputStream.println( "        return myValue;"            );
        aOutputStream.println( "    }"                              );
        
        for ( int i = 0 ; i < aLinesAfter ; i++ )
        {
            aOutputStream.println();
        }
    }
    
    
    private static void generateEncodeMethod( PrintStream       aOutputStream,
                                              String            aModifiers,
                                              String            aDataType,
                                              String            aMethodName,
                                              String            aDelegateMethodPrefix,
                                              List<Parameter>   aParameters,
                                              int               aLinesBefore,
                                              int               aLinesAfter            )
    {
        for ( int i = 0 ; i < aLinesBefore ; i++ )
        {
            aOutputStream.println();
        }
        
        generateMethodHeader( aOutputStream,
                              aModifiers,
                              aMethodName,
                              "void",
                              aParameters    );

        aOutputStream.println( "    {"                          );
        aOutputStream.println( "        aContext.clearError();" );

        generateDelegateMethodCall( aOutputStream,
                                    aDelegateMethodPrefix,
                                    aParameters            );

        aOutputStream.println( "        checkForError( aContext );" );
        aOutputStream.println( "    }"                              );
        
        for ( int i = 0 ; i < aLinesAfter ; i++ )
        {
            aOutputStream.println();
        }
    }
    

    /**
     * FILLIN
     * 
     * @param       aOutputStream
     * @param       aModifiers
     * @param       aMethodName
     * @param       aReturnType
     * @param       aParameters
     */
    private static void generateMethodHeader( PrintStream       aOutputStream,
                                              String            aModifiers,
                                              String            aMethodName,
                                              String            aReturnType,
                                              List<Parameter>   aParameters  )
    {
        assert StringUtils.isNotBlank( aMethodName );
        assert StringUtils.isNotBlank( aModifiers  );
        
        if ( aReturnType == null )
        {
            aReturnType = "void";
        }
        if ( aParameters == null )
        {
            aParameters = Collections.<Parameter>emptyList();
        }
        
        int myParameterCount = aParameters.size();
        
        aOutputStream.println( "    /**" );
        aOutputStream.println( "     * FILLIN" );
        aOutputStream.println( "     * " );
        
        for ( Parameter myParameter : aParameters )
        {
            aOutputStream.println( "     * @param       " + myParameter.getName() );
        }
        
        if ( ! aReturnType.equals( "void" ) )
        {
            aOutputStream.println( "     * " );
            aOutputStream.println( "     * @return" );
        }
        
        aOutputStream.println( "     */" );
        
        String myMethodPrefix = "    " + aModifiers + " " + aReturnType + " " + aMethodName;

        if ( myParameterCount != 0 )
        {
            String        myParameterSeparator  = ( myParameterCount > 1 ) ? "   " : " ";
            String        myLastParameterSuffix = ( myParameterCount > 1 ) ? " )"  : ")";
            StringBuilder myBuffer              = new StringBuilder();
            boolean       myFirst               = true;
            int           myMaxParamTypeLength  = 0;
            int           myMaxParamNameLength  = 0;
            
            for ( Parameter myParameter : aParameters )
            {
                myMaxParamTypeLength = Math.max( myMaxParamTypeLength, myParameter.getDataType().length() );
                myMaxParamNameLength = Math.max( myMaxParamNameLength, myParameter.getName().length()     );
            }
            
            for ( Iterator<Parameter> myIterator = aParameters.iterator() ; myIterator.hasNext() ; )
            {
                Parameter myParameter = myIterator.next();
                
                myBuffer.setLength( 0 );
                
                if ( myFirst )
                {
                    myBuffer.append( myMethodPrefix )
                            .append( "( "           )
                            ;
                    myFirst = false;
                }
                else
                {
                    FormatHelper.stringOfCharacters( myBuffer, ' ', myMethodPrefix.length()+2 );
                }
                
                String myParameterSuffix = ( myIterator.hasNext() ) ? "," : " ";
                
                FormatHelper.formatString( myBuffer,
                                           myParameter.getDataType(),
                                           myMaxParamTypeLength       );
                
                myBuffer.append( myParameterSeparator );
                
                FormatHelper.formatString( myBuffer,
                                           myParameter.getName() + myParameterSuffix,
                                           myMaxParamNameLength + myParameterSuffix.length() );
                
                if ( ! myIterator.hasNext() )
                {
                    myBuffer.append( myLastParameterSuffix );
                }
                
                aOutputStream.println( myBuffer.toString() );
            }
        }
        else
        {
            aOutputStream.println( myMethodPrefix + "()" );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aOutputStream
     * @param       aMethodCall
     * @param       aParameters
     */
    private static void generateDelegateMethodCall( PrintStream       aOutputStream,
                                                    String            aMethodCall,
                                                    List<Parameter>   aParameters    )
    {
        assert aOutputStream != null;
        assert StringUtils.isNotBlank( aMethodCall );
        
        if ( aParameters == null )
        {
            aParameters = Collections.<Parameter>emptyList();
        }
        
        int    myParameterCount   = aParameters.size();
        String myMethodCallPrefix = "        " + aMethodCall;
        
        if ( myParameterCount != 0 )
        {
            String        myLastParameterSuffix = ( myParameterCount > 1 ) ? " );" : ");";
            StringBuilder myBuffer              = new StringBuilder();
            boolean       myFirst               = true;
            int           myMaxParamTypeLength  = 0;
            int           myMaxParamNameLength  = 0;
            
            for ( Parameter myParameter : aParameters )
            {
                myMaxParamTypeLength = Math.max( myMaxParamTypeLength, myParameter.getDataType().length() );
                myMaxParamNameLength = Math.max( myMaxParamNameLength, myParameter.getName().length()     );
            }
            
            for ( Iterator<Parameter> myIterator = aParameters.iterator() ; myIterator.hasNext() ; )
            {
                Parameter myParameter = myIterator.next();
                
                myBuffer.setLength( 0 );
                
                if ( myFirst )
                {
                    myBuffer.append( myMethodCallPrefix )
                            .append( "( "               )
                            ;
                    myFirst = false;
                }
                else
                {
                    FormatHelper.stringOfCharacters( myBuffer, ' ', myMethodCallPrefix.length()+2 );
                }
                
                String myParameterSuffix = ( myIterator.hasNext() ) ? "," : " ";
                
                FormatHelper.formatString( myBuffer,
                                           myParameter.getName() + myParameterSuffix,
                                           myMaxParamNameLength + myParameterSuffix.length() );
                
                if ( ! myIterator.hasNext() )
                {
                    myBuffer.append( myLastParameterSuffix );
                }
                
                aOutputStream.println( myBuffer.toString() );
            }
        }
        else
        {
            aOutputStream.println( myMethodCallPrefix + "();" );
        }
    }
}
