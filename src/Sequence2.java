import java.util.Random;

public class Sequence2 {
    private Node first;
    private Node last;
    private DominoSet set;
    private int length;

    public Sequence2(DominoSet set){
        this.set = set;
        length = set.getSize();

        boolean[] tempArr = new boolean[length];  // массив нужный, чтобы узнать занесли ли уже кость с индексом в последовательность

        int piecesLeft = length - 1;
        Random rand = new Random();

        int curIndex = rand.nextInt(length);
        first = new Node(set.getPiece(curIndex));
        Node prev = first;
        Node next = null;
        tempArr[curIndex] = true;

        // генерация случайной последовательности костей без повторений
        while (true){
            curIndex = rand.nextInt(length);
            if (!tempArr[curIndex]){
                tempArr[curIndex] = true;
                next = new Node(set.getPiece(curIndex));
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
    }

    public void print(){
        if(length > 0) {
            System.out.println("Последовательность костей домино: ");
            Node prev = first;
            Node next = first.getLink();
            int i = 1;

            do {
                String pieceValue = prev.getValue().getValue();
                String nextPieceValue = next.getValue().getValue();

//            System.out.printf("Фишка с номером %d в последовательности - %s (%d), следующая для нее - %s (%d)\n", i, pieceValue, prev, nextPieceValue, next);
                System.out.printf("Фишка под номером %d в последовательности - %s, следующая для ней - %s\n", i, pieceValue, nextPieceValue);

                prev = next;
                next = next.getLink();
                i++;
            } while (prev != first);
        } else{
            System.out.println("Последовательность костей домино пуста");
        }
    }

    public DominoPiece getPiece(int n){
        if (n < 1) {
            System.out.println("Номер должен быть положительным");
            return null;
        }
        Node prev = first;
        Node next = first.getLink();
        int i = 1;

        do{
            if (i == n){
                return prev.getValue();
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
        Node prev = first;
        Node next = first.getLink();
        int i = 1;

        while (true){
            if (i == n){
                return prev.getValue();
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

        Node prev = last;
        Node next = first;
        int i = 1;

        while (true){
            if (i == n){
                if (next == first) first = first.getLink();
                if (next == last) last = prev;
                prev.setLink(next.getLink());
                length--;
                return next.getValue();
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

        Node prev = last;
        Node next = first;
        int i = 1;
        int resultIndex = 0;
        DominoPiece[] result = new DominoPiece[length];

        length = 0;

        while (true){
            if (i == n){
                prev.setLink(next.getLink());
                result[resultIndex] = next.getValue();
                i = 0;
                resultIndex++;
                if (resultIndex == result.length) return result;
            }

            prev = next;
            next = next.getLink();
            i++;
        }
    }
}
