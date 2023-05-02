package components.graphics.wrappers;

import components.field.Field;
import components.scientist.Scientist;

import java.awt.*;

public class FieldWrapper extends Wrapper {
    private Field field;
    private Point p1;
    private Point p2;
    private String id;

    public FieldWrapper(Point p1, Point p2, String id, Field field){
        this.p1=p1;
        this.p2=p2;
        this.id=id;
        this.field=field;
    }
    public FieldWrapper(){

    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public String getId() {
        return id;
    }

    public Field getField() {
        return field;
    }

    @Override
    public String toString() {
        return id;
    }
}
