package hac.adderspringbackend.dao;

public class ComputeOperands {

        private int operand1;
        private int operand2;
        // operation type
        private String operationType; // e.g., "+", "-", "*", "/"


        public int getOperand1() {
            return operand1;
        }

        public void setOperand1(int operand1) {
            this.operand1 = operand1;
        }

        public int getOperand2() {
            return operand2;
        }

        public void setOperand2(int operand2) {
            this.operand2 = operand2;
        }
}
