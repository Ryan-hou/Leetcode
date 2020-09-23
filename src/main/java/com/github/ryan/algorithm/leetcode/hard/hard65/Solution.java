package com.github.ryan.algorithm.leetcode.hard.hard65;

import java.util.regex.Pattern;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 02,2019
 */
public class Solution {

    // regex expression
    public boolean isNumber(String s) {
        return Pattern.compile("(-|\\+)?((\\d+\\.?\\d*)|(\\.?\\d+))(e(-|\\+)?\\d+)?").matcher(s.trim()).matches();
    }


    private static enum State {
        None,
        HasSign,
        HasE,
        IsNumber,
        IsFloat
    }

    public boolean isNumber2(String s) {
        s = s.trim();
        if (s.length() == 0) return false;

        int nrCount = 0;
        int nrOfSignAfterE = 0;
        State state = State.None;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            switch (state) {
                case None: {
                    if (ch == '+' || ch == '-') {
                        state = State.HasSign;
                        continue;
                    }
                    if ('0' <= ch && ch <= '9') {
                        state = State.IsNumber;
                        nrCount++;
                        continue;
                    }
                    if (ch == '.') {
                        state = State.IsFloat;
                        continue;
                    }
                    return false;
                }

                case HasSign: {
                    if ('0' <= ch && ch <= '9') {
                        state = State.IsNumber;
                        nrCount++;
                        continue;
                    }
                    if (ch == '.') {
                        state = State.IsFloat;
                        continue;
                    }
                    return false;
                }

                case IsNumber: {
                    if ('0' <= ch && ch <= '9') {
                        state = State.IsNumber;
                        nrCount++;
                        continue;
                    }
                    if (ch == '.') {
                        state = State.IsFloat;
                        continue;
                    }
                    if (ch == 'e') {
                        state = State.HasE;
                        continue;
                    }
                    return false;
                }

                case HasE: {
                    // be careful not to change state
                    if ('0' <= ch && ch <= '9') {
                        //state = State.IsNumber;
                        nrCount++;
                        continue;
                    }
                    if (ch == '-' || ch == '+') {
                        nrOfSignAfterE++;
                        continue;
                    }
                    return false;
                }

                case IsFloat: {
                    if ('0' <= ch && ch <= '9') {
                        nrCount++;
                        continue;
                    }
                    if (ch == 'e') {
                        if (i - 1 == 0
                                || (i - 2 == 0 && (s.charAt(i - 2) == '+' || s.charAt(i - 2) == '-'))) {
                            return false;
                        }
                        state = State.HasE;
                        continue;
                    }
                    return false;
                }

                default:
                    return false;
            }
        }

        if (nrCount == 0) return false;
        if (nrOfSignAfterE > 1) return false;
        if (state == State.HasSign) return false;
        int len = s.length();
        if (state == State.HasE
                && (s.charAt(len - 1) == '+' || s.charAt(len - 1) == '-' || s.charAt(len - 1) == 'e'))
            return false;

        return true;
    }
}
