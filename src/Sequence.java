// класс последовательности
public class Sequence {
    private DominoPiece first;  // ссылка на первый элемент последовательности
    private DominoPiece last;  // ссылка на последний элемент последовательности
    private int length;  // текущая длина последовательности

    // создание пустой последовательности
    public Sequence(){
        length = 0;
        first = last = null;
    }

    public void addPiece(DominoPiece piece){
        if (length == 0){
            first = piece;
            last = first;
        }
        else{
            last.setLink(piece);
            last = piece;
            last.setLink(first);
        }
        length++;
    }

    // генерация последовательности сразу из набора
    // можно удалить
    /*public Sequence(DominoSet set){
        length = set.getSize();

        boolean[] tempArr = new boolean[length];  // массив нужный, чтобы узнать занесли ли уже кость с индексом в последовательность

        int piecesLeft = length - 1;
        Random rand = new Random();

        int curIndex = rand.nextInt(length);
        first = set.getPiece(curIndex);
        DominoPiece prev = first;
        DominoPiece next = null;
        tempArr[curIndex] = true;

        // генерация случайной последовательности костей без повторений
        while (true){
            curIndex = rand.nextInt(length);
            if (!tempArr[curIndex]){
                tempArr[curIndex] = true;
                next = set.getPiece(curIndex);
                prev.setLink(next);

                prev = next;
                piecesLeft--;
            }
            if (piecesLeft == 0) {
                break;
            }
        }

        last = next;  // последняя добавленная кость
        last.setLink(first);
    }*/

    /* во всех следующих методах используется алгоритм для перебора последовательности:
    * начинаем с first и ссылки на следующий после него элемент - записываем в prev и next соответственно
    * чтобы перейти к следующему элементу записываем в prev next, а в next ссылку на следующий элемент, которая хранится в next.link
    * таким образов в next оказался следующий элемент, с котором можно делать что нам нужно
    * если при этом вести счетчик, к которому прибавлять 1 при каждом переходе, то можно найти n-ый по счету элемент
    * чтобы удалить элемент, нужно записать другое значение в link, где хранится ссылка на него
    * то есть нигде в последовательности на него не будет существовать ссылка -> он удален*/


    // вывод последовательности на экран
    public void print(){
        if(length > 0) {
            System.out.println("Последовательность костей домино: ");
            DominoPiece prev = first;
            DominoPiece next = first.getLink();
            int i = 1;

            do {
                System.out.printf("Фишка под номером %d в последовательности - %s\n", i, prev.getValue());

                prev = next;
                next = next.getLink();
                i++;
            } while (prev != first);
        } else{
            System.out.println("Последовательность костей домино пуста");
        }
    }

    // короткий вывод последовательности
    public void shortPrint(){
        if(length > 0) {
            String ans = "[";
            DominoPiece prev = first;
            DominoPiece next = first.getLink();
            int i = 1;

            do {
                ans = ans + "(" + prev.getValue() + "),";
                //System.out.printf("Фишка под номером %d в последовательности - %s\n", i, prev.getValue());

                prev = next;
                next = next.getLink();
                i++;
            } while (prev != first);
            ans = ans.substring(0, ans.length() - 1) + "]";
            System.out.println(ans);
        } else{
            System.out.println("[]");
        }
    }

    // получить кость под номером n (нумерация с 1); если n больше длины последовательности, возвращает null
    public DominoPiece getPiece(int n){
        if (n < 1) {
            System.out.println("Номер должен быть положительным");
            return null;
        }
        DominoPiece prev = first;
        DominoPiece next = first.getLink();
        int i = 1;

        do{
            if (i == n){
                return prev;
            }
            prev = next;
            next = next.getLink();
            i++;

        } while(prev != first);

        System.out.println("Слишком большое число, длина последовательности меньше.");
        return null;
    }

    // получить кость под номером n (нумерация с 1); если n больше длины последовательности, поиск идет циклически
    public DominoPiece getPieceCycle(int n){
        if (n < 1) {
            System.out.println("Номер должен быть положительным");
            return null;
        }
        DominoPiece prev = first;
        DominoPiece next = first.getLink();
        int i = 1;

        while (true){
            if (i == n){
                return prev;
            }
            prev = next;
            next = next.getLink();
            i++;

        }
    }

    // удалить и вернуть одну кость под номером n (нумерация с 1)
    public DominoPiece popN(int n){
        if (n < 1) {
            System.out.println("Номер должен быть положительным");
            return null;
        }

        DominoPiece prev = last;
        DominoPiece next = first;
        int i = 1;

        while (true){
            if (i == n){
                if (next == first) first = first.getLink();
                if (next == last) last = prev;
                prev.setLink(next.getLink());
                length--;
                return next;
            }

            prev = next;
            next = next.getLink();
            i++;
        }
    }

    // удалить и вернуть все кости последовательности, считая по n (нумерация с 1),
    public DominoPiece[] popAllN(int n){
        if (n < 1) {
            System.out.println("Номер должен быть положительным");
            return null;
        }

        DominoPiece prev = last;
        DominoPiece next = first;
        int i = 1;
        int resultIndex = 0;
        DominoPiece[] result = new DominoPiece[length];

        while (true){
            if (i == n){
                if (next == first) first = first.getLink();
                if (next == last) last = prev;
                prev.setLink(next.getLink());
                length--;
                result[resultIndex] = next;

                System.out.println("Удалена " + next);
                shortPrint();

                i = 0;
                resultIndex++;
                if (resultIndex == result.length) {
                    return result;
                }
            }

            prev = next;
            next = next.getLink();
            i++;
        }
    }
}
