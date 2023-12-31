// класс кости домино
// содержит количество точек и ссылку на следующую кость в последовательности
public class DominoPiece {
    // количество точек на сторонах фишки
    private int part1;
    private int part2;
    private DominoPiece link;

    public DominoPiece(int p1, int p2){
        part1 = p1;
        part2 = p2;
    }

    public DominoPiece(int p1, int p2, DominoPiece link){
        part1 = p1;
        part2 = p2;
        this.link = link;
    }

    public DominoPiece getLink() {
        return link;
    }

    public void setLink(DominoPiece link) {
        this.link = link;
    }

    public void print(){
        System.out.println(part1 + " " + part2);
    }

    @Override
    public String toString() {
        return "Кость домино с точками: " + part1 + " и " + part2;
    }

    public String getValue(){
        return part1 + " " + part2;
    }
}
