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


package com.xylocore.commons.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */
public final class JavaLanguageHelper
{
    //
    // Members
    //
    
    
    private static final Set<String>    javaKeywords;
    private static final Pattern        javaClassNNamePartPattern;
    
    
    
    
    //
    // Static initialization
    //
    
    
    static
    {
        List<String> myKeywordList =
                Arrays.asList
                (
                    "abstract",  "assert",     "boolean",   "break",        "byte",
                    "case",      "catch",      "char",      "class",        "const",
                    "continue",  "default",    "do",        "double",       "else",
                    "enum",      "extends",    "false",     "final",        "finally",
                    "float",     "for",        "goto",      "if",           "implements",
                    "import",    "instanceof", "int",       "interface",    "long",
                    "native",    "new",        "null",      "package",      "private",
                    "protected", "public",     "return",    "short",        "static",
                    "strictfp",  "super",      "switch",    "synchronized", "this",
                    "throw",     "throws",     "transient", "true",         "try",
                    "void",      "volatile",   "while"
                );

        javaKeywords              = new HashSet<>( myKeywordList );
        javaClassNNamePartPattern = Pattern.compile( "[A-Za-z_$][a-zA-Z0-9_$]*" );
    }
    

    
    
    //
    // Class implementation
    //
    
    
    /*
     * Private constructor prevents class from being instantiated.
     */
    private JavaLanguageHelper()
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aString
     * 
     * @return
     */
    public static boolean isJavaKeyword( String aString )
    {
        return javaKeywords.contains( aString );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aClassName
     * 
     * @return
     */
    public static boolean isJavaClassName( String aClassName )
    {
        for ( String myPart : aClassName.split( "\\." ) )
        {
            if
            (
                isJavaKeyword( myPart )                                 ||
                ! javaClassNNamePartPattern.matcher( myPart ).matches()
            )
            {
                return false;
            }           
        }
        
        return ! aClassName.isEmpty();
    }
}
