package ui;

import java.util.HashSet;

/** Interface om UI en domeincontroller te verbinden wanneer men de gebruiker één item uit een HashSet<T> laat kiezen. **/
public interface ChoiceMenu<T> {
    T geefKeuze(HashSet<? extends T> elements);
}