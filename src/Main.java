public class Main {
    public static void main(String[] args) {
        DominoSet set = new DominoSet();
//        set.print();

        /*System.out.println();
        Sequence dominoSequence = set.toSequence();
        dominoSequence.print();

        System.out.println();
//        System.out.println(dominoSequence.popN(5));
//        System.out.println();
        DominoPiece[] a = dominoSequence.popAllN(1);
        for (DominoPiece i : a){
            System.out.println(i);
        }
        dominoSequence.print();*/

        Sequence2 seq2 = new Sequence2(set);
        seq2.print();
        System.out.println();
        System.out.println(seq2.getPiece(6));
        System.out.println(seq2.getPieceCycle(30));

        for (DominoPiece i : seq2.popAllN(1)){
            System.out.println(i);
        }
    }
}