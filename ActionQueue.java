package assignment2;

public class ActionQueue extends MyQueue<Direction> {
    private MyStack<String> stack;

    public ActionQueue(){
        super();
        this.stack = new MyStack<>();
    }

    public void clear(){
        super.clear();
        stack.clear();
    }

    public void loadFromEncodedString(String encoded) {
        String k = "";
        int sectionK;
        String sectionDir = "";
        String sectionDirSum = "";
        String sumDir = "";

        for (int i = 0; i < encoded.length(); i++) {

            if (encoded.charAt(i) >= '0' && encoded.charAt(i) <= '9' && i != encoded.length() - 1) {
                k += encoded.charAt(i);

                if(i != 0 && (encoded.charAt(i - 1) == 'N' || encoded.charAt(i - 1) == 'S' || encoded.charAt(i - 1) == 'E' || encoded.charAt(i - 1) == 'W')){
                    sumDir += stack.pop();
                }

                if (encoded.charAt(i + 1) == '[') {
                    stack.push(k);
                    k = "";
                }
                else if (encoded.charAt(i + 1) == ']'){
                    throw new IllegalArgumentException("K cannot be followed by ]");
                }
            } else if (encoded.charAt(i) == '[' && i != encoded.length() - 1) {
                stack.push("[");

                if (encoded.charAt(i+1) == ']'){
                    throw new IllegalArgumentException("Empty Brackets not Allowed");
                }
            } else if ((encoded.charAt(i) == 'N' || encoded.charAt(i) == 'S' || encoded.charAt(i) == 'W' || encoded.charAt(i) == 'E')) {
                sectionDir += encoded.charAt(i);

                if (i == encoded.length() - 1 || ! (encoded.charAt(i + 1) == 'N' || encoded.charAt(i + 1) == 'S' || encoded.charAt(i + 1) == 'E' || encoded.charAt(i + 1) == 'W')) { // instead check if it is NWES instead of ]
                    stack.push(sectionDir);
                    sectionDir = "";
                }

                if(i == encoded.length() - 1){
                    sumDir += stack.pop();
                }

            } else if (encoded.charAt(i) == ']') {

                if (stack.peek().equals("[")) { // make sure this is good
                    sectionDir = sumDir;
                    sumDir = "";
                } else {
                    sectionDir = stack.pop();
                }

                if(stack.isEmpty()){
                    throw new IllegalArgumentException("Stack is too short");
                }
                stack.pop();

                if(stack.isEmpty()){
                    throw new IllegalArgumentException("Stack is too short");
                }

                checkIfNum(stack.peek());
                sectionK = Integer.parseInt(stack.pop());
                sectionDirSum = "";

                for (int j = 0; j < sectionK; j++) {
                    sectionDirSum += sectionDir;
                }

                sectionDir = "";

                sumDir += sectionDirSum;
            }
            else{
                throw new IllegalArgumentException("Unidentified character");
            }
        }

        if(!(stack.isEmpty())){
            throw new IllegalArgumentException("Stack was not cleared so possible open bracket");
        }


        /*System.out.println(sumDir);
        System.out.println(sumDir.length());*/

        // enqueues the stuff
        for(int i = 0; i < sumDir.length(); i++){
            Direction currDir;

            if (sumDir.charAt(i) == 'N'){
                currDir = Direction.NORTH;
            }
            else if (sumDir.charAt(i) == 'S') {
                currDir = Direction.SOUTH;
            }
            else if(sumDir.charAt(i) == 'W'){
                currDir = Direction.WEST;
            }
            else if(sumDir.charAt(i) == 'E'){
                currDir = Direction.EAST;
            }
            else{
                throw new IllegalArgumentException("Unidentified character");
            }

            this.enqueue(currDir);
        }
    }

    private void checkIfNum(String num){
        try{
            if (num.equals("")){
                throw new IllegalArgumentException("K is empty");
            }
            int testIfInt = Integer.parseInt(num);
        }
        catch (Exception e){
            throw new IllegalArgumentException("K is not type int");

        }
    }

    /*public static void main(String[] args) {
        ActionQueue test = new ActionQueue();
        *//*test.loadFromEncodedString("2[SS]3[EE]");
        test.loadFromEncodedString("2[3[EN]]");
        test.loadFromEncodedString("3[2[N]2[E]]1[S]");
        test.loadFromEncodedString("NE5[N]");
        test.loadFromEncodedString("NE");
        test.loadFromEncodedString("2[NE5[N]]");*//*

//        test.loadFromEncodedString("3[]"); // add code to check if the element the stack before is a string of NSWE
//        test.loadFromEncodedString("2[");

    }*/
}
