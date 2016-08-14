/* Copyright 2014 Sheldon Neilson www.neilson.co.za
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.example.dell.alarmmaster.alert;

import java.util.ArrayList;
import java.util.Random;

public class MathProblem {

    enum Operator {
        ADD, SUBTRACT, MULTIPLY, DIVIDE;

        /*
         * (non-Javadoc)
         *
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString() {
            String string = null;
            switch (ordinal()) {
                case 0:
                    string = "+";
                    break;
                case 1:
                    string = "-";
                    break;
                case 2:
                    string = "*";
                    break;
                case 3:
                    string = "/";
                    break;
            }
            return string;
        }
    }

    private ArrayList<Float> parts;
    private ArrayList<Operator> operators;
    private float answer = 0f;
    private int min = 0;
    private int max = 10;
    public MathProblem() {
        this(3);
    }

    public MathProblem(int numParts) {
        super();
        Random random = new Random(System.currentTimeMillis());

        parts = new ArrayList<Float>(numParts);
        for (int i = 0; i < numParts; i++)
            parts.add(i, (float) random.nextInt(max - min + 1) + min);

        operators = new ArrayList<MathProblem.Operator>(numParts - 1);
        for (int i = 0; i < numParts - 1; i++)
            operators.add(i,Operator.values()[random.nextInt(2)+1]);

        ArrayList<Object> combinedParts = new ArrayList<Object>();
        for (int i = 0; i < numParts; i++){
            combinedParts.add(parts.get(i));
            if(i<numParts-1)
                combinedParts.add(operators.get(i));
        }

        while(combinedParts.contains(Operator.DIVIDE)){
            int i = combinedParts.indexOf(Operator.DIVIDE);
            answer = (Float)combinedParts.get(i-1) / (Float)combinedParts.get(i+1);
            for (int r = 0; r < 2; r++)
                combinedParts.remove(i-1);
            combinedParts.set(i-1, answer);
        }
        while(combinedParts.contains(Operator.MULTIPLY)){
            int i = combinedParts.indexOf(Operator.MULTIPLY);
            answer = (Float)combinedParts.get(i-1) * (Float)combinedParts.get(i+1);
            for (int r = 0; r < 2; r++)
                combinedParts.remove(i-1);
            combinedParts.set(i-1, answer);
        }

//		while(combinedParts.contains(Operator.ADD) ||combinedParts.contains(Operator.SUBTRACT)){
//			int i = 0;
//			while(!(combinedParts.get(i) instanceof Operator)){
//				i++;
//			}
//			if(combinedParts.get(i) == Operator.ADD){
//				answer = (Float)combinedParts.get(i-1) + (Float)combinedParts.get(i+1);
//			}else{
//				answer = (Float)combinedParts.get(i-1) - (Float)combinedParts.get(i+1);
//			}
//			for (int r = 0; r < 2; r++)
//				combinedParts.remove(i-1);
//			combinedParts.set(i-1, answer);
//		}

        while(combinedParts.contains(Operator.ADD)){
            int i = combinedParts.indexOf(Operator.ADD);
            answer = (Float)combinedParts.get(i-1) + (Float)combinedParts.get(i+1);
            for (int r = 0; r < 2; r++)
                combinedParts.remove(i-1);
            combinedParts.set(i-1, answer);
        }
        while(combinedParts.contains(Operator.SUBTRACT)){
            int i = combinedParts.indexOf(Operator.SUBTRACT);
            answer = (Float)combinedParts.get(i-1) - (Float)combinedParts.get(i+1);
            for (int r = 0; r < 2; r++)
                combinedParts.remove(i-1);
            combinedParts.set(i-1, answer);
        }

//		2 5 7 8 9 11
//		 + - * / -
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder problemBuilder = new StringBuilder();
        for (int i = 0; i < parts.size(); i++) {
            problemBuilder.append(parts.get(i));
            problemBuilder.append(" ");
            if (i < operators.size()){
                problemBuilder.append(operators.get(i).toString());
                problemBuilder.append(" ");
            }
        }
        return problemBuilder.toString();
    }

    public float getAnswer() {
        return answer;
    }

}
