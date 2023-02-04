public class FactOfCats {
    public String id;// - уникальный идентификатор записи
    public String text;// - сообщение
    public String type;// - тип животного
    public String user;// - имя пользователя
    public Integer upvotes;// - голоса

    public FactOfCats() {

    }

    public FactOfCats(String id, String text, String type, String user, Integer upvotes) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.upvotes = upvotes;
    }

    public Integer getUpvotes() {
        return upvotes;
    }

    @Override
    public String toString() {
        return "FactOfCats{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", user='" + user + '\'' +
                ", upvotes=" + upvotes +
                '}';
    }
}
