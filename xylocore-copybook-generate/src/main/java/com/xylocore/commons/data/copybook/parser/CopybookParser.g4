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

parser grammar CopybookParser;

options
{
    tokenVocab = CopybookLexer;
}

copybook                        :   ( dataItemDescriptionEntry )*
                                    EOF
                                ;

dataItemDescriptionEntry        :   levelNumber
                                    (   
                                        { $levelNumber.value == 66 }? level66DescriptionEntry
                                    |   { $levelNumber.value == 88 }? conditionNameEntry
                                    |   dataDescriptionEntry
                                    )
                                    DOT
                                ;

levelNumber                         returns [int value]
                                :   INTEGER_LITERAL
                                    {
                                        $value = Integer.parseInt( $INTEGER_LITERAL.text );
                                    }
                                ;


dataDescriptionEntry            :   (   dataName
                                    |   FILLER
                                    |   /* empty */
                                    )
                                    ( redefinesClause )?
                                    (   blankWhenZeroClause
                                    |   externalClause
                                    |   globalClause
                                    |   groupUsageClause
                                    |   justifiedClause
                                    |   occursClause
                                    |   pictureClause
                                    |   signClause
                                    |   synchronizedClause
                                    |   usageClause
                                    |   valueClause
                                    |   dateFormatClause
                                    )*
                                ;

/* TODO */
level66DescriptionEntry         :   dataName renamesClause
                                ;

/* TODO */
conditionNameEntry              :   conditionName
                                    (
                                        VALUE  ( IS  )? 
                                    |   VALUES ( ARE )?
                                    )
                                    ( values+=conditionNameEntryValue )+
                                ;

conditionNameEntryValue         :   value1=literal ( ( THROUGH | THRU ) value2=literal )?
                                ;

redefinesClause                 :   REDEFINES dataName
                                ;

blankWhenZeroClause             :   BLANK ( WHEN )? ( ZERO | ZEROS | ZEROES )
                                ;

externalClause                  :   EXTERNAL
                                ;

globalClause                    :   GLOBAL
                                ;

groupUsageClause                :   GROUP_USAGE ( IS )? NATIONAL
                                ;

justifiedClause                 :   ( JUSTIFIED | JUST ) ( RIGHT )?
                                ;

/* TODO */
occursClause                    :   OCCURS
                                    ( value1=integerLiteral TO )? value2=integerLiteral ( TIMES )?
                                    ( DEPENDING ( ON )? dependingName=dataName )?
                                    ( ( ASCENDING | DESCENDING ) ( KEY )? ( IS )? ( dataName )+ )*
                                    ( INDEXED ( BY )? ( indexNames+=indexName )+ )?
                                ;

pictureClause                   :   PICTURE_IS PICTURE_STRING
                                ;

signClause                      :   ( SIGN ( IS )? )?
                                    (
                                        LEADING
                                    |   TRAILING
                                    )
                                    (
                                        SEPARATE ( CHARACTER )?
                                    )?
                                ;

synchronizedClause              :   ( SYNCHRONIZED | SYNC )
                                    (   LEFT
                                    |   RIGHT
                                    |   /* empty */
                                    )
                                ;

usageClause                     :   ( USAGE ( IS )? )?
                                    (
                                        (   ( BINARY | COMP | COMPUTATIONAL | COMP_4 | COMPUTATIONAL_4 )
                                        |   ( COMP_1 | COMPUTATIONAL_1 )
                                        |   ( COMP_2 | COMPUTATIONAL_2 )
                                        |   ( COMP_3 | COMPUTATIONAL_3 | PACKED_DECIMAL )
                                        |   ( COMP_5 | COMPUTATIONAL_5 )
                                        |   DISPLAY
                                        |   DISPLAY_1
                                        |   NATIONAL
                                        )
                                        ( NATIVE )?
                                    |   (   INDEX
                                        |   POINTER
                                        |   PROCEDURE_POINTER
                                        |   FUNCTION_POINTER
                                        )
                                    |   OBJECT REFERENCE objRefName=className
                                    )
                                ;

valueClause                     :   VALUE ( IS )? literal
                                ;

dateFormatClause                :   DATE FORMAT ( IS )? WORD
                                ;

/* TODO */
renamesClause                   :   RENAMES dataName ( ( THROUGH | THRU )? dataName )?
                                ;

dataName                        :   WORD
                                ;

indexName                       :   WORD
                                ;

conditionName                   :   WORD
                                ;

className                       :   WORD
                                ;

literal                         :   integerLiteral
                                |   floatingPointLiteral
                                |   characterLiteral
                                |   zeroLiteral
                                |   spaceLiteral
                                |   highValueLiteral
                                |   lowValueLiteral
                                |   quoteLiteral
                                |   nullLiteral
                                ;

integerLiteral                  :   INTEGER_LITERAL
                                ;
                                
floatingPointLiteral            :   FLOATING_POINT_LITERAL
                                ;

characterLiteral                :   CHARACTER_LITERAL
                                ;

zeroLiteral                     :   ZERO
                                |   ZEROS
                                |   ZEROES
                                ;

spaceLiteral                    :   SPACE
                                |   SPACES
                                ;

highValueLiteral                :   HIGH_VALUE
                                |   HIGH_VALUES
                                ;

lowValueLiteral                 :   LOW_VALUE
                                |   LOW_VALUES
                                ;

quoteLiteral                    :   QUOTE
                                |   QUOTES
                                ;

nullLiteral                     :   NULL
                                |   NULLS
                                ;
