package org.marco.designpattern.gof.behavioral.vistor;

import java.util.ArrayList;
import java.util.List;

public class ObjectStructure {
    final private List<Element> elementList = new ArrayList<>();

    public void addElement(Element element) {
        elementList.add(element);
    }

    public void accept(Vistor vistor) {
        elementList.forEach(element -> element.accept(vistor));
    }

}
