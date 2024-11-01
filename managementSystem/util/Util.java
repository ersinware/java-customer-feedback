package util;

public class Util {
    public static boolean isNullExist(Object... params) {
        for (Object param : params)
            if (param instanceof String && ((String) param).isEmpty())
                return true;
            else if (param == null)
                return true;

        return false;
    }
}
