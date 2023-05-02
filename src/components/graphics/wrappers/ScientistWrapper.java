package components.graphics.wrappers;

import components.scientist.Scientist;

public class ScientistWrapper extends Wrapper {
    private final Scientist scientist;
    private final String name;

    public ScientistWrapper(Scientist s, String n, int sCounter){
        super(String.format("scientist%d.png", sCounter));
        scientist=s;
        name=n;
    }

    public Scientist getScientist() {
        return scientist;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return name;
    }
}
