import java.util.Random;

public class Sequence_loh {
    private int last;  // индекс (в наборе) последней кости
    private int first;  // индекс (в наборе) первой кости последовательности
    private final DominoSet set;  // ссылка на набор домино
    private int[] nextPieces;  // массив, в котором для каждой фишки хранятся ссылки (индексы массива) на следующую кость
    private int length;  // текущая длина последовательности

    public Sequence_loh(DominoSet set){
        this.set = set;
        nextPieces = new int[set.getSize()];
        length = nextPieces.length;

        boolean[] tempArr = new boolean[length];  // массив нужный, чтобы узнать занесли ли уже кость с индексом в последовательность

        int indexForNextPiece = 0;  // место, куда вставляем следующую кость
        int piecesLeft = length - 1;
        Random rand = new Random();

        first = rand.nextInt(length);
        tempArr[first] = true;
        indexForNextPiece = first;

        // генерация случайной последовательности костей без повторений
        while (true){
            int randIndex = rand.nextInt(length);
            if (!tempArr[randIndex]){
                tempArr[randIndex] = true;
                nextPieces[indexForNextPiece] = randIndex;
                indexForNextPiece = randIndex;
                piecesLeft--;
            }
            if (piecesLeft == 0) {
                break;
            }
        }

//        System.out.println(indexForNextPiece);
        last = indexForNextPiece;  // последняя добавленная кость
        nextPieces[last] = first;
        length = nextPieces.length;
    }

    // метод для печати всей последовательности
    public void print() {
        if(length > 0) {
            System.out.println("Последовательность костей домино: ");
            int prev = first;
            int next = nextPieces[first];
            int i = 1;

            do {
                String pieceValue = set.getPiece(prev).getValue();
                String nextPieceValue = set.getPiece(next).getValue();

//            System.out.printf("Фишка с номером %d в последовательности - %s (%d), следующая для нее - %s (%d)\n", i, pieceValue, prev, nextPieceValue, next);
                System.out.printf("Фишка под номером %d в последовательности - %s, следующая для ней - %s\n", i, pieceValue, nextPieceValue);

                prev = next;
                next = nextPieces[prev];
                i++;
            } while (prev != first);
        } else{
            System.out.println("Последовательность костей домино пуста");
        }

        /*System.out.println();

        prev = first;

        for(int j = 0; j < 28; j++){
            int nxt = nextPieces[prev];

            String pieceValue = set.getPiece(prev).getValue();
            String nextPieceValue = set.getPiece(nxt).getValue();

            System.out.printf("Фишка с номером %d в последовательности - %s (%d), следующая для нее - %s (%d)\n", j + 1, pieceValue, prev, nextPieceValue, nxt);
            prev = nxt;
        }
        System.out.println(first + " " + last);
        System.out.println(prev + " " + nextPieces[prev]);*/
    }

    // получить кость под номером n (нумерация с 1), если n больше длины последовательности -> null
    public DominoPiece getPiece(int n){
        if (n < 1) {
            System.out.println("Номер должен быть положительным");
            return null;
        }
        int prev = first;
        int next = nextPieces[first];
        int i = 1;

        do{
            if (i == n){
                return set.getPiece(prev);
            }
            prev = next;
            next = nextPieces[prev];
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
        int prev = first;
        int next = nextPieces[first];
        int i = 1;

        while (true){
            if (i == n){
                return set.getPiece(prev);
            }
            prev = next;
            next = nextPieces[prev];
            i++;

        }
    }

    // удалить и вернуть одну кость под номером n (нумерация с 1)
    public DominoPiece popN(int n){
        if (n < 1) {
            System.out.println("Номер должен быть положительным");
            return null;
        }

        int prev = last;
        int next = first;
        int i = 1;

        while (true){
            if (i == n){
                if (next == first) first = nextPieces[first];
                if (next == last) last = prev;
                nextPieces[prev] = nextPieces[next];
                length--;
                return set.getPiece(next);
            }

            prev = next;
            next = nextPieces[prev];
            i++;
        }
    }

    // удалить и вернуть все кости последовательности, считая по n (нумерация с 1),
    public DominoPiece[] popAllN(int n){
        if (n < 1) {
            System.out.println("Номер должен быть положительным");
            return null;
        }

        int prev = last;
        int next = first;
        int i = 1;
        int resultIndex = 0;
        DominoPiece[] result = new DominoPiece[length];

        length = 0;

        while (true){
            if (i == n){
                nextPieces[prev] = nextPieces[next];
                result[resultIndex] = set.getPiece(next);
                i = 0;
                resultIndex++;
                if (resultIndex == result.length) return result;
            }

            prev = next;
            next = nextPieces[prev];
            i++;
        }
    }
}