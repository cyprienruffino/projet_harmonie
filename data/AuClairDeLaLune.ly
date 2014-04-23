\header{
 title = "data/AuClairDeLaLune"
}
\new ChoirStaff
<<% Soprano
 \new Staff { 
\set Staff.instrumentName = #"Soprano " 
\clef treble 
\relative c''{
c4 c4 c4 d4 e2 d2 c4 e4 d4 d4 c2. }}
% Alto
\new Staff { 
\set Staff.instrumentName = #"Alto " 
\clef treble 
\relative c'' {
g,4 g4 g4 d'4 c2 b,2 e'4 e4 f4 b,4 e'2. }}
% Tenor
\new Staff { 
\set Staff.instrumentName = #"Tenor " 
\clef treble
\relative c' { 
e4 e4 e4 b4 g2 g2 g4 g4 a4 g4 g2. }}
% Basse
\new Staff { 
\set Staff.instrumentName = #"Basse " 
\clef bass
\relative c {
c4 c4 c4 g,4 c'2 g,2 c'4 c4 d4 g,4 c'2. }}
>>
\version "2.14.2"