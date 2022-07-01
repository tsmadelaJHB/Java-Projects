package za.co.wethinkcode.server.webAPI;

public class Quote {
    private Integer id;
    private String text;
    private String name;
    private String size;

    public void setSize(String size){
        System.out.println(size);
        this.size = size;}
    public String getSize(){return size;}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Quote create(String text, String name) {
        Quote quote = new Quote();
        quote.setText(text);
        quote.setName(name);
        return quote;
    }
}
