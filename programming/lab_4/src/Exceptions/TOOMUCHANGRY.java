package Exceptions;

import Actions.Say;
import Humans.Human;

public class TOOMUCHANGRY extends StackOverflowError{
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public TOOMUCHANGRY(String message, Human human){
        super(message);
        System.out.println(ANSI_RED + "Ошибка: слишком много зла" + ANSI_RESET);
        Say say = new Say("злостно сказать", ANSI_RED + "- Ах, что вы! Ни за что на свете! Вы знаете, дорогая, что сказал мне недавно этот гадкий Пилюлькин? \nОн сказал, что я больных не вылечиваю, а, наоборот, здоровых могу сделать больными. Какое невежество! \nНет, я его продержу здесь точно до положенного срока. Раньше он отсюда не выйдет. И Ворчун тоже." + ANSI_RESET);
        human.applyAction(say);
    }
}
