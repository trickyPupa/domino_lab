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
// 28 = 6 * a + 6
    public DominoPiece getPiece(int i){
        return domino_array[i];
    }

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

    public Sequence toSequence(){
        return new Sequence(this);
    }
}
