// класс набора домино , содержит массив фишек и размер
public class DominoSet {
    private DominoPiece[] domino_array = new DominoPiece[28];
    private int size;  // размер набора

    public DominoSet(){
        int k = 0;
        for (int i = 0; i < 7; i++){
            for (int j = i; j < 7; j++){
                domino_array[k] = new DominoPiece(i, j);
                k++;
            }
        }
        size = 28;
    }

    // обращение к элементу массива фишек с индексом i
    public DominoPiece getPiece(int i){
        return domino_array[i];
    }

    // вывод набора
    public void print(){
        System.out.println("Набор костей домино:");
        for(int i = 0; i < size; i++){
            System.out.print(i + "\t");
            domino_array[i].print();
        }
    }

    public int getSize() {
        return size;
    }

    // метод для превращения набора в последовательность (набор не меняется, возвращает последовательность)
    public Sequence_loh toSequence(){
        return new Sequence_loh(this);
    }
}
