% suma(l1,l2,...,ln) = false, daca l e vida
%                          = return l1, daca l are un singur
%                          element
%                          = return l1+suma(l3,...ln), daca l2 == "+"
%                          = return l1-suma(l3,...ln), daca l2 === "-"


suma([H|[]],Suma):-number(H),
    Suma is H.
suma([H1,H2|List],Suma):-
    number(H1),
    H2 = '+',
    suma(List,Rest),
    Suma is H1 + Rest.
suma([H1,H2|List],Suma):-
    number(H1),
    H2 = '-',
    suma(List,Rest),
    Suma is H1 - Rest.

