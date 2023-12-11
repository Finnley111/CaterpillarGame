package assignment2;

public class TargetQueue extends MyQueue<Position>{
    MyStack<String> stackTargets;

    public TargetQueue(){
        super();
        this.stackTargets = new MyStack<>();
    }

    public void clear(){
        super.clear();
        stackTargets.clear();
    }

    public void addTargets(String targets){
        String num = "";
        boolean prevIsPeriod = false;

        for(int i = 0; i < targets.length(); i++){
            if (targets.charAt(i) == '('  && i != targets.length() - 1){
                stackTargets.push("(");
                prevIsPeriod = false;

            }
            else if (targets.charAt(i) >= '0' && targets.charAt(i) <= '9' && i != targets.length() - 1) {
                num = num + targets.charAt(i);
                prevIsPeriod = false;
            }
            else if (targets.charAt(i) == ',' && i != targets.length() - 1) {
                checkIfNum(num);
                stackTargets.push(num);
                stackTargets.push(",");
                num = "";
                prevIsPeriod = false;
            }
            else if (targets.charAt(i) == ')') {

                if (stackTargets.getSize() != 3){
                    throw new IllegalArgumentException("Improper size of stack");
                }

                if(i != targets.length() - 1 && targets.charAt(i + 1) != '.'){
                    throw new IllegalArgumentException("Period must separate positions");
                }

                String ele1 = stackTargets.pop();
                String ele2 = stackTargets.pop();
                String ele3 = stackTargets.pop();

                if (!(ele1.equals(","))){
                    throw new IllegalArgumentException("Not proper Syntax for Targets");
                }

                checkIfNum(ele2);

                if (!(ele3.equals("("))){
                    throw new IllegalArgumentException("Not proper Syntax for Targets");
                }


                checkIfNum(num); // checks if the y coordinate is good

                int newX = Integer.parseInt(ele2);
                int newY = Integer.parseInt(num);

                Position pos = new Position(newX, newY);

                this.enqueue(pos);

                num = "";
                prevIsPeriod = false;
            }
            else if (targets.charAt(i) == '.') {

                if (!(num.equals(""))){ // check whether to use && or ||
                    throw new IllegalArgumentException("Period reached and num and stack is not empty");
                }
                if(!(this.stackTargets.isEmpty())){
                    throw new IllegalArgumentException("Period reached and num and stack is not empty");
                }
                if (prevIsPeriod){
                    throw new IllegalArgumentException("Multiple periods in a row");
                }

                prevIsPeriod = true;
            }
            else {
                throw new IllegalArgumentException("Unidentified character");
            }
        }
    }

    private void checkIfNum(String num){
        try{
            if (num.equals("")){
                throw new IllegalArgumentException("num is empty");
            }
            int testIfInt = Integer.parseInt(num);
        }
        catch (Exception e){
            throw new IllegalArgumentException("num is not type int");

        }
    }

    /*public static void main(String[] args) {
        *//*TargetQueue tQ1 = new TargetQueue();
        String sTest1Valid = "(1,2).(2,4)";
        MyQueue<Position> myQueueTester = new MyQueue<Position>();
        Position myPositionTester1 = new Position(1,2);
        Position myPositionTester2 = new Position(2,4);
        myQueueTester.enqueue(myPositionTester1);
        myQueueTester.enqueue(myPositionTester2);

        tQ1.addTargets(sTest1Valid);
        System.out.println(myQueueTester.equals(tQ1.tempQueue));
        //assertEquals(true, myQueueTester.equals(tQ1.tempQueue));
        TargetQueue test = new TargetQueue();

        test.addTargets("(7,5)(0,5)");*//*
        TargetQueue test1 = new TargetQueue();
        TargetQueue test2 = new TargetQueue();
        test1.addTargets("(0,0).(1,1).(0,3)");
        test1.clear();
        System.out.println(test1.equals(test2));
    }*/
}

