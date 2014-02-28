\header{
title = "Au clair de la lune"
}

\new ChoirStaff
<<
% Soprano
\new Staff { 
	\set Staff.instrumentName = #"Soprano " 
	\clef treble 
	\relative c''{
		c c c d e2 d c4 e d d c2. r4
	}
}

% Alto
\new Staff { 
	\set Staff.instrumentName = #"Alto " 
	\clef treble 
	\relative c'' {
		g a a b c b a b a c b b a g a g
	}
}

% Tenor
\new Staff { 
	\set Staff.instrumentName = #"Tenor " 
	\clef treble
	\relative c' { 
		e f e g g g f g f g g g f e f e
	}
}

% Basse
\new Staff {
	\set Staff.instrumentName = #"Basse " 
	\clef bass
	\relative c { 
		c f a g c, e d g f c g' g f c f c}
	}
>>


\version "2.12.3"