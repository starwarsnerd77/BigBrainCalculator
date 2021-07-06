package com.example.bigbraincalculator;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Calculator {
    public Calculator() {

    }

    public String evaluateMaths(String maths) {
        ArrayList<String> postfix = toPostfix(maths);
        int i = 0;
        int end = postfix.size();
        while(i < end) {
            if(postfix.get(i).equals("+")) {
                if(isNumeric(postfix.get(i-2)) && isNumeric(postfix.get(i-1))) {
                    postfix.set(i-2, String.valueOf(new Float(postfix.get(i-2)) + new Float(postfix.get(i-1))));
                    postfix.remove(i);
                    postfix.remove(i-1);
                    i -= 2;
                }
            } else if(postfix.get(i).equals("-")) {
                if(isNumeric(postfix.get(i-2)) && isNumeric(postfix.get(i-1))) {
                    postfix.set(i-2, String.valueOf(new Float(postfix.get(i-2)) - new Float(postfix.get(i-1))));
                    postfix.remove(i);
                    postfix.remove(i-1);
                    i -= 2;
                }
            } else if(postfix.get(i).equals("X")) {
                if(isNumeric(postfix.get(i-2)) && isNumeric(postfix.get(i-1))) {
                    postfix.set(i - 2, String.valueOf(new Float(postfix.get(i - 2)) * new Float(postfix.get(i - 1))));
                    postfix.remove(i);
                    postfix.remove(i - 1);
                    i -= 2;
                }
            } else if(postfix.get(i).equals("/")) {
                if(isNumeric(postfix.get(i-2)) && isNumeric(postfix.get(i-1))) {
                    postfix.set(i-2, String.valueOf(new Float(postfix.get(i-2)) / new Float(postfix.get(i-1))));
                    postfix.remove(i);
                    postfix.remove(i-1);
                    i -= 2;
                }
            }
            i++;
            end = postfix.size();
        }
        String result = fromPostfix(postfix);
        return result;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            float d = Float.parseFloat(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public ArrayList<String> toPostfix(String maths) {
        ArrayList<String> postfix = new ArrayList<>();
        ArrayList<String> ops = new ArrayList<>();
        for(String c : maths.split(" ")) {
            if(!c.equals("+") && !c.equals("-") && !c.equals("X") && !c.equals("/")) {
                postfix.add(c);
            } else if(c.equals("+") || c.equals("-")) {
                if (ops.size() > 0) {
                    for (int i = 0; i < ops.size(); i++) {
                        postfix.add(ops.get(ops.size() - i - 1));
                        ops.remove(ops.size() - i - 1);
                    }
                }
                ops.add(c);
            } else if(c.equals("X") || c.equals("/")) {
                if (ops.size() > 0 && !ops.get(ops.size() - 1).equals("+") && !ops.get(ops.size() - 1).equals("-")) {
                    while (ops.get(ops.size() - 1).equals("*") || ops.get(ops.size() - 1).equals("/")) {
                        postfix.add(ops.get(ops.size() - 1));
                        ops.remove(ops.size() - 1);
                    }
                }
                ops.add(c);

            }
        }
        for(int i = 1; i <= ops.size(); i++) {
            postfix.add(ops.get(ops.size()-i));
        }
        return postfix;
    }

    public String fromPostfix(ArrayList<String> postfix) {
        String result;
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < postfix.size(); i++) {
            if(postfix.get(i).equals("+") || postfix.get(i).equals("-") || postfix.get(i).equals("X") || postfix.get(i).equals("/")) {
                sb.append(postfix.get(i-2));
                sb.append(postfix.get(i));
                sb.append(postfix.get(i-1));
            }
//            else {
//                sb.append(postfix.get(i));
//                sb.append(" ");
//            }

        }
        result = sb.toString();
        return result;
    }

    public String toNegative(final String maths) {
        String result = "-";
        ArrayList<String> mathsList = new ArrayList<String>() {
            {
                for(String c : maths.split(" ")) {
                    add(c);
                }
            }
        };
        if(!mathsList.get(mathsList.size()-1).equals("+") && !mathsList.get(mathsList.size()-1).equals("-") && !mathsList.get(mathsList.size()-1).equals("X") && !mathsList.get(mathsList.size()-1).equals("/")) {
            result += mathsList.get(mathsList.size()-1);
        } else {
            result = mathsList.get(mathsList.size()-1);
        }
        mathsList.set(mathsList.size()-1, result);
        result = "";
        for(String c : mathsList) {
            result += c + " ";
        }
        return result;
    }
}
