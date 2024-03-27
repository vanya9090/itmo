//package vanya9090.server.commands.list;
//
//
//
//import vanya9090.common.commands.Command;
//import vanya9090.common.exceptions.EmptyFieldException;
//import vanya9090.common.exceptions.ParseException;
//import vanya9090.common.exceptions.ValidateException;
//import vanya9090.server.commands.Executable;
//import vanya9090.server.managers.CollectionManager;
//import vanya9090.server.models.HumanBeing;
//
//import java.util.Scanner;
//
///**
// * добавляет новый элемент в коллекцию
// *
// * @author vanya9090
// */
//public class Add extends Command implements Executable {
////    private final ILogger logger;
//    private final CollectionManager collectionManager;
//
//    public Add(ILogger logger, CollectionManager collectionManager) {
//        super("add", "добавить новый элемент в коллекцию");
//        this.logger = logger;
//        this.collectionManager = collectionManager;
//    }
//
//    /**
//     * выполняет команду
//     *
//     * @param args аргументы, переданные в командной строке
//     * @return пустая строка
//     * @throws ParseException      ошибка парсинга поля
//     * @throws EmptyFieldException пустое поле
//     * @throws ValidateException   ошибка синтетических ограничений
//     */
//    @Override
//    public String apply(String[] args) throws ParseException, EmptyFieldException, ValidateException {
//        HumanBeing.updateNextId(collectionManager);
//        HumanBeingForm humanBeingForm = new HumanBeingForm(this.logger, new Scanner(System.in), false);
//        HumanBeing humanBeing = humanBeingForm.create();
//        if (!humanBeing.validate()) {
//            throw new ValidateException("некоторые поля не соответствуют синтетическим ограничениям");
//        }
//        collectionManager.add(humanBeing);
//        return "";
//    }
//
//    /**
//     * выполняет команду в режиме execute
//     *
//     * @param args       аргументы, переданные в командной строке
//     * @param fileReader отдельные Scanner для скрипта
//     * @return пустая строка
//     * @throws ParseException      ошибка парсинга поля
//     * @throws EmptyFieldException пустое поле
//     * @throws ValidateException   ошибка синтетических ограничений
//     */
//    public String apply(String[] args, Scanner fileReader) throws ParseException, EmptyFieldException, ValidateException {
//        HumanBeing.updateNextId(collectionManager);
//        HumanBeingForm humanBeingForm = new HumanBeingForm(new ExecuteLogger(), fileReader, true);
//        HumanBeing humanBeing = humanBeingForm.create();
//        if (!humanBeing.validate()) {
//            throw new ValidateException("некоторые поля не соответствуют синтетическим ограничениям");
//        }
//        collectionManager.add(humanBeing);
//        return "";
//    }
//}
