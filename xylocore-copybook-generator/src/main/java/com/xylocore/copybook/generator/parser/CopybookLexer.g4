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

lexer grammar CopybookLexer;

ARE                             : 'ARE';
ASCENDING                       : 'ASCENDING';
BINARY                          : 'BINARY';
BLANK                           : 'BLANK';
BY                              : 'BY';
CHARACTER                       : 'CHARACTER';
COMP                            : 'COMP';
COMP_1                          : 'COMP-1';
COMP_2                          : 'COMP-2';
COMP_3                          : 'COMP-3';
COMP_4                          : 'COMP-4';
COMP_5                          : 'COMP-5';
COMPUTATIONAL                   : 'COMPUTATIONAL';
COMPUTATIONAL_1                 : 'COMPUTATIONAL-1';
COMPUTATIONAL_2                 : 'COMPUTATIONAL-2';
COMPUTATIONAL_3                 : 'COMPUTATIONAL-3';
COMPUTATIONAL_4                 : 'COMPUTATIONAL-4';
COMPUTATIONAL_5                 : 'COMPUTATIONAL-5';
DATE                            : 'DATE';
DEPENDING                       : 'DEPENDING';
DESCENDING                      : 'DESCENDING';
DISPLAY                         : 'DISPLAY';
DISPLAY_1                       : 'DISPLAY-1';
DOT                             : '.';
EXTERNAL                        : 'EXTERNAL';
FILLER                          : 'FILLER';
FORMAT                          : 'FORMAT';
FUNCTION_POINTER                : 'FUNCTION-POINTER';
GLOBAL                          : 'GLOBAL';
GROUP_USAGE                     : 'GROUP-USAGE';
HIGH_VALUE                      : 'HIGH-VALUE';
HIGH_VALUES                     : 'HIGH-VALUES';
INDEX                           : 'INDEX';
INDEXED                         : 'INDEXED';
IS                              : 'IS';
JUST                            : 'JUST';
JUSTIFIED                       : 'JUSTIFIED';
KEY                             : 'KEY';
LEADING                         : 'LEADING';
LEFT                            : 'LEFT';
LOW_VALUE                       : 'LOW-VALUE';
LOW_VALUES                      : 'LOW-VALUES';
NATIONAL                        : 'NATIONAL';
NATIVE                          : 'NATIVE';
NULL                            : 'NULL';
NULLS                           : 'NULLS';
OBJECT                          : 'OBJECT';
OCCURS                          : 'OCCURS';
ON                              : 'ON';
PACKED_DECIMAL                  : 'PACKED-DECIMAL';
//PIC                             : 'PIC';
//PICTURE                         : 'PICTURE';
POINTER                         : 'POINTER';
PROCEDURE_POINTER               : 'PROCEDURE-POINTER';
QUOTE                           : 'QUOTE';
QUOTES                          : 'QUOTES';
REDEFINES                       : 'REDEFINES';
REFERENCE                       : 'REFERENCE';
RENAMES                         : 'RENAMES';
RIGHT                           : 'RIGHT';
SEPARATE                        : 'SEPARATE';
SIGN                            : 'SIGN';
SPACE                           : 'SPACE';
SPACES                          : 'SPACES';
SYNC                            : 'SYNC';
SYNCHRONIZED                    : 'SYNCHRONIZED';
THROUGH                         : 'THROUGH';
THRU                            : 'THRU';
TIMES                           : 'TIMES';
TO                              : 'TO';
TRAILING                        : 'TRAILING';
USAGE                           : 'USAGE';
VALUE                           : 'VALUE';
VALUES                          : 'VALUES';
WHEN                            : 'WHEN';
ZERO                            : 'ZERO';
ZEROES                          : 'ZEROES';
ZEROS                           : 'ZEROS';

    
fragment
DIGIT                           :   ( '0'..'9' )
                                ;

fragment
HEX_DIGIT                       :   ( DIGIT | 'A'..'F' | 'a'..'f' )
                                ;

fragment
ALPHA                           :   ( 'A'..'Z' | 'a'..'z' )
                                ;

fragment
ALPHANUMERIC                    :   ( ALPHA | DIGIT )
                                ;

fragment
PICTURE_PART                    :   ( 'P' | 'p' )
                                    ( 'I' | 'i' )
                                    ( 'C' | 'c' )
                                    (
                                        ( 'T' | 't' )
                                        ( 'U' | 'u' )
                                        ( 'R' | 'r' )
                                        ( 'E' | 'e' )
                                    )?
                                ;

fragment
IS_PART                         :   WS
                                    ( 'I' | 'i' )
                                    ( 'S' | 's' )
                                ;

fragment
NEWLINE                         :   ( '\r' )? '\n'
                                ;

fragment
SINGLE_WS                       :   (   NEWLINE
                                    |   ' '
                                    |   '\t'
                                    )
                                ;

WS                              :   (   ',' SINGLE_WS 
                                    |   ';' SINGLE_WS
                                    |   SINGLE_WS
                                    )+
                                    -> skip
                                ;

PICTURE_IS                      :   (   PICTURE_PART IS_PART WS
                                    |   PICTURE_PART WS
                                    )
                                    -> pushMode(PIC)
                                ;
                                        
WORD                            :   ( ( DIGIT )+ ( '-' )+ )* ALPHA ( ALPHANUMERIC )* ( ( '-' )+ ( ALPHANUMERIC )+ )*
                                ;

CHARACTER_LITERAL               :   (   ( 'X' | 'x' )
                                    |   ( 'Z' | 'z' )
                                    |   ( 'G' | 'g' )
                                    |   ( 'N' | 'n' )
                                        (
                                            ( 'X' | 'x' )
                                        )?
                                    |   /* empty */
                                    )
                                    '"'
                                    (
                                        ( '"' '"' )
                                    |   ~( '"' | '\r' | '\n' )
                                    )*
                                    '"'
                                |   (   ( 'X' | 'x' )
                                    |   ( 'Z' | 'z' )
                                    |   ( 'G' | 'g' )
                                    |   ( 'N' | 'n' )
                                        (
                                            ( 'X' | 'x' )
                                        )?
                                    |   /* empty */
                                    )
                                    '\''
                                    (
                                        ( '\'' '\'' )
                                    |   ~( '\'' | '\r' | '\n' )
                                    )*
                                    '\''
                                ;

INTEGER_LITERAL                 :   ( DIGIT )+
                                ;

FLOATING_POINT_LITERAL          :   ( '+' | '-' )?
                                    ( DIGIT )+
                                    '.'
                                    ( DIGIT )+
                                    (
                                        ( 'E' | 'e' )
                                        ( '+' | '-' )?
                                        ( DIGIT )+
                                    )?
                                ;


mode PIC;

PICTURE_STRING                  :   PICTURE_FRAGMENT
                                    (   (   ','
                                        |   '.'
                                        )?
                                        PICTURE_FRAGMENT
                                    )*
                                    -> popMode
                                ;
                                            
fragment
PICTURE_FRAGMENT                :   (   (   'A' | 'a'
                                        |   'B' | 'b'
                                        |   'G' | 'g'
                                        |   'N' | 'n'
                                        |   'P' | 'p'
                                        |   'X' | 'x'
                                        |   'Z' | 'z'
                                        |   '0'
                                        |   '9'
                                        |   '/'
                                        |   '+'
                                        |   '-'
                                        |   '*'
                                        |   '$'
                                        )
                                        ( '(' ( '0'..'9' )+ ')' )?
                                    |   (   'E' | 'e'
                                        |   'S' | 's'
                                        |   'V' | 'v'
                                        |   ( 'C' | 'c' ) ( 'R' | 'r' )
                                        |   ( 'D' | 'd' ) ( 'B' | 'b' )
                                        )
                                    )
                                ;
