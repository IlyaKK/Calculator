package com.elijahcorp.calculator;

public enum Symbols {
    ZERO {
        @Override
        public String getSymbol() {
            return "0";
        }
    },
    ONE {
        @Override
        public String getSymbol() {
            return "1";
        }
    },
    TWO {
        @Override
        public String getSymbol() {
            return "2";
        }
    },
    THREE {
        @Override
        public String getSymbol() {
            return "3";
        }
    },
    FOUR {
        @Override
        public String getSymbol() {
            return "4";
        }
    },
    FIVE {
        @Override
        public String getSymbol() {
            return "5";
        }
    },
    SIX {
        @Override
        public String getSymbol() {
            return "6";
        }
    },
    SEVEN {
        @Override
        public String getSymbol() {
            return "7";
        }
    },
    EIGHT {
        @Override
        public String getSymbol() {
            return "8";
        }
    },
    NINE {
        @Override
        public String getSymbol() {
            return "9";
        }
    },
    POINT {
        @Override
        public String getSymbol() {
            return ".";
        }
    },
    MINUS {
        @Override
        public String getSymbol() {
            return "-";
        }
    },
    PLUS {
        @Override
        public String getSymbol() {
            return "+";
        }
    },
    MULTIPLE {
        @Override
        public String getSymbol() {
            return "*";
        }
    },
    DIVIDE {
        @Override
        public String getSymbol() {
            return "/";
        }
    },
    EQUALS {
        @Override
        public String getSymbol() {
            return "=";
        }
    };

    public abstract String getSymbol();
}
