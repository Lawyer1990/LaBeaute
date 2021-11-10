package com.example.LaBeaute.constant;

public class Constant {
    public class ErrorMessages{
        public final static String OBLIGATION_FIELD= "- все поля кроме 'Клиента' обязательны для заполнения";
        public final static String WORK= "- не указан клиент (при установленном статусе)";
        public final static String NOTWORK= "- указан клиент (при установленном статусе)";
        public final static String TIME_EQUALS= "- время окончания работы не может быть равно его началу";
        public final static String TIME_BEFORE= "- время окончания работы не может быть указано до времени начала работ";
        public final static String DATA_BEFORE= "- дата выполнения работы не может быть в прошлом";
    }
    public class Titles{
        public final static String ORDER="Информация о заказанных услугах";
    }
}
