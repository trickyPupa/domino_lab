public class Node {
    private Node link;
    private DominoPiece value;

    public Node(DominoPiece value, Node link) {
        this.link = link;
        this.value = value;
    }

    public Node(DominoPiece value) {
        this.value = value;
    }

    public Node getLink() {
        return link;
    }

    public void setLink(Node link) {
        this.link = link;
    }

    public DominoPiece getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Node со значением \"" + getValue() + "\" и ссылкой на \"" + getLink() + "\"";
    }
}
