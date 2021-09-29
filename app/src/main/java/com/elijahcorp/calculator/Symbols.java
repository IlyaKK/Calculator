package com.elijahcorp.calculator;

import android.content.Context;

public enum Symbols {
    ZERO {
        @Override
        public String getSymbol(Context cnt) {
            return cnt.getString(R.string.zero);
        }
    },
    ONE {
        @Override
        public String getSymbol(Context cnt) {
            return cnt.getString(R.string.one);
        }
    },
    TWO {
        @Override
        public String getSymbol(Context cnt) {
            return cnt.getString(R.string.two);
        }
    },
    THREE {
        @Override
        public String getSymbol(Context cnt) {
            return cnt.getString(R.string.three);
        }
    },
    FOUR {
        @Override
        public String getSymbol(Context cnt) {
            return cnt.getString(R.string.four);
        }
    },
    FIVE {
        @Override
        public String getSymbol(Context cnt) {
            return cnt.getString(R.string.five);
        }
    },
    SIX {
        @Override
        public String getSymbol(Context cnt) {
            return cnt.getString(R.string.six);
        }
    },
    SEVEN {
        @Override
        public String getSymbol(Context cnt) {
            return cnt.getString(R.string.seven);
        }
    },
    EIGHT {
        @Override
        public String getSymbol(Context cnt) {
            return cnt.getString(R.string.eight);
        }
    },
    NINE {
        @Override
        public String getSymbol(Context cnt) {
            return cnt.getString(R.string.nine);
        }
    },
    POINT {
        @Override
        public String getSymbol(Context cnt) {
            return cnt.getString(R.string.point);
        }
    },
    MINUS {
        @Override
        public String getSymbol(Context cnt) {
            return cnt.getString(R.string.minus);
        }
    },
    PLUS {
        @Override
        public String getSymbol(Context cnt) {
            return cnt.getString(R.string.plus);
        }
    },
    MULTIPLE {
        @Override
        public String getSymbol(Context cnt) {
            return cnt.getString(R.string.multiply);
        }
    },
    DIVIDE {
        @Override
        public String getSymbol(Context cnt) {
            return cnt.getString(R.string.divide);
        }
    },
    EQUALS {
        @Override
        public String getSymbol(Context cnt) {
            return cnt.getString(R.string.equals);
        }
    };

    public abstract String getSymbol(Context cnt);
}
