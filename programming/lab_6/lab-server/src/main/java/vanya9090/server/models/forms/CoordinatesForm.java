//package vanya9090.server.models.forms;
//
//
//import vanya9090.client.models.Coordinates;
//import vanya9090.client.utils.ILogger;
//import vanya9090.common.exceptions.EmptyFieldException;
//import vanya9090.common.exceptions.ParseException;
//
//import java.util.Scanner;
//
///**
// * форма для ввода координат
// *
// * @author vanya9090
// */
//public class CoordinatesForm implements Form {
//    private final ILogger logger;
//    private final Scanner scanner;
//    private final boolean isExecute;
//
//    public CoordinatesForm(ILogger logger, Scanner scanner, boolean isExecute) {
//        this.logger = logger;
//        this.scanner = scanner;
//        this.isExecute = isExecute;
//    }
//
//    @Override
//    public Coordinates create() throws ParseException, EmptyFieldException {
//        return new Coordinates(this.askX(), this.askY());
//    }
//
//    public Integer askX() throws ParseException, EmptyFieldException {
//        int x;
//        while (true) {
//            try {
//                this.logger.field("Введите x: ");
////                Scanner scanner = new Scanner(System.in);
//                String field = this.scanner.nextLine().trim();
//                if (field.isEmpty()) throw new EmptyFieldException("координата x");
//                try {
//                    x = Integer.parseInt(field);
//                } catch (IllegalArgumentException e) {
//                    throw new ParseException("x", field);
//                }
//                break;
//            } catch (EmptyFieldException | ParseException e) {
//                if (this.isExecute) {
//                    throw e;
//                } else {
//                    logger.error(e);
//                }
//            }
//        }
//        return x;
//    }
//
//    public Float askY() throws ParseException, EmptyFieldException {
//        float y;
//        while (true) {
//            try {
//                this.logger.field("Введите y: ");
////                Scanner scanner = new Scanner(System.in);
//                String field = this.scanner.nextLine().trim();
//                if (field.isEmpty()) throw new EmptyFieldException("координата y");
//                try {
//                    y = Float.parseFloat(field);
//                } catch (IllegalArgumentException e) {
//                    throw new ParseException("y", field);
//                }
//                break;
//            } catch (EmptyFieldException | ParseException e) {
//                if (this.isExecute) {
//                    throw e;
//                } else {
//                    logger.error(e);
//                }
//            }
//        }
//        return y;
//    }
//}
