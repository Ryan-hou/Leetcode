package com.github.ryan.leetcode.algorithm.hard.hard273;

public class Solution {

    // Divide and conquer
    public String numberToWords(int num) {
        if (num == 0) return "Zero";

        int billion = num / 1000000000;
        int million = (num - billion * 1000000000) / 1000000;
        int thousand = (num - billion * 1000000000 - million * 1000000) / 1000;
        int rest = num - billion * 1000000000 - million * 1000000 - thousand * 1000;

        StringBuilder b = new StringBuilder();
        if (billion != 0) {
            b.append(threeDigit(billion)).append(" Billion");
        }
        if (million != 0) {
            if (b.length() != 0) {
                b.append(" ");
            }
            b.append(threeDigit(million)).append(" Million");
        }
        if (thousand != 0) {
            if (b.length() != 0) {
                b.append(" ");
            }
            b.append(threeDigit(thousand)).append(" Thousand");
        }
        if (rest != 0) {
            if (b.length() != 0) {
                b.append(" ");
            }
            b.append(threeDigit(rest));
        }
        return b.toString();
    }

    private String oneDigit(int num) {
        switch (num) {
            case 1: return "One";
            case 2: return "Two";
            case 3: return "Three";
            case 4: return "Four";
            case 5: return "Five";
            case 6: return "Six";
            case 7: return "Seven";
            case 8: return "Eight";
            case 9: return "Nine";
            default: throw new IllegalArgumentException("Invalid input");
        }
    }

    private String twoDigitLessThan20(int num) {
        switch (num) {
            case 10: return "Ten";
            case 11: return "Eleven";
            case 12: return "Twelve";
            case 13: return "Thirteen";
            case 14: return "Fourteen";
            case 15: return "Fifteen";
            case 16: return "Sixteen";
            case 17: return "Seventeen";
            case 18: return "Eighteen";
            case 19: return "Nineteen";
            default: throw new IllegalArgumentException("Invalid input");
        }
    }

    private String ten(int num) {
        switch (num) {
            case 2: return "Twenty";
            case 3: return "Thirty";
            case 4: return "Forty";
            case 5: return "Fifty";
            case 6: return "Sixty";
            case 7: return "Seventy";
            case 8: return "Eighty";
            case 9: return "Ninety";
            default: throw new IllegalArgumentException("Invalid input");
        }
    }


    private String twoDigit(int num) {
        if (num == 0) return "";
        if (num < 10) return oneDigit(num);
        if (num < 20) return twoDigitLessThan20(num);

        int tenner = num / 10;
        int rest = num - tenner * 10;
        if (rest == 0) {
            return ten(tenner);
        } else {
            return ten(tenner) + " " + oneDigit(rest);
        }
    }

    private String threeDigit(int num) {
        int hundred = num / 100;
        int rest = num - hundred * 100;
        if (hundred * rest != 0) {
            return oneDigit(hundred) + " Hundred " + twoDigit(rest);
        } else if (hundred == 0 && rest != 0) {
            return twoDigit(rest);
        } else if (hundred != 0 && rest == 0) {
            return oneDigit(hundred) + " Hundred";
        } else {
            return "";
        }
    }

}
