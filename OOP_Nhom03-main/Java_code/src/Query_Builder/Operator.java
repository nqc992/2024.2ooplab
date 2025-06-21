package Query_Builder;

public class Operator {
    private final String operator;
    public Operator(String op){
        this.operator=op;
    }
    @Override
    public String toString(){
        return this.operator;
    }
    public Operator Greater(){
        return new Operator(" > ");
    }
    public Operator Equal(){
        return new Operator(" = ");
    }
    public Operator Smaller(){
        return new Operator(" < ");
    }
    public Operator GreaterOrEqual(){
        return new Operator(" >= ");
    }
    public Operator SmallerOrEqual(){
        return new Operator(" <= ");
    }
    public Operator And(){
        return new Operator(" AND ");
    }
    public Operator Or(){
        return new Operator(" OR ");
    }
    public Operator IsNull(){
        return new Operator(" IS NULL ");
    }
}
