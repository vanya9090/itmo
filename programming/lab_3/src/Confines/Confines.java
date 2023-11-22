package Confines;

import Humans.Human;

public enum Confines {
    FREEDOM{
        @Override
        public String toString() {
            return "Свобода";
        }
    },
    CONFINED {
        @Override
        public String toString() {
            return "Неволя";
        }
    };
}


