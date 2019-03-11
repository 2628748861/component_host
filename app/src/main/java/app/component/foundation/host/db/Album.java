package app.component.foundation.host.db;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class Album extends LitePalSupport {

    @Column(unique = true, defaultValue = "unknown")
    private String name;

    private float price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
