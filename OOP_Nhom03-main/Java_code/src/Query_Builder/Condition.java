package Query_Builder;

public class Condition {
    private final String condition;

    @Override
    public String toString(){
        return this.condition;
    }
    public Condition(String con){
        this.condition=con;
    }
    public Condition multiCondition(Condition A,Operator O,Condition B){
        return new Condition(A.toString()+O.toString()+B.toString());
    }
    public Condition whereCondition(String ColA,Operator O,String ColB) {
        return new Condition(ColA + O.toString() + ColB);
    }
    public Condition joinCondition(Key KeyA,Operator O,Key KeyB){
        return new Condition(KeyA.toString()+O.toString()+KeyB.toString());
    }

}
