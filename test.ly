\header{
 title = "salutation"
}
\new ChoirStaff
<<% Soprano
 \new Staff { 
\set Staff.instrumentName = #"Soprano " 
\clef treble 
\relative c''{
c4 c4 c4 d4 }}
% Alto
\new Staff { 
\set Staff.instrumentName = #"Alto " 
\clef treble 
\relative c'' {
g4 a4 a4 b4 }}
% Tenor
\new Staff { 
\set Staff.instrumentName = #"Tenor " 
\clef treble
\relative c' { 
e4 f4 e4 g4 }}
% Basse
\new Staff { 
\set Staff.instrumentName = #"Basse " 
\clef bass
\relative c {
c4 f4 a4 g4 }}
>>
\version "2.14.2"