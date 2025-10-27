package library.service;

import java.util.ArrayList;
import java.util.List;
import library.model.*;

public class Library {
    private List<Lendable> items;

    public Library() {
        items = new ArrayList<>();
    }

    public void addItem(Lendable item) {
        items.add(item);
    }

    public void removeItem(Lendable item) {
        items.remove(item);
    }

    public List<Lendable> getAvailableItems() {
        List<Lendable> availableItems = new ArrayList<>();
        for (Lendable item : items) {
            if (item instanceof Book && !((Book) item).isBorrowed()) {
                availableItems.add(item);
            }
        }
        return availableItems;
    }

    public void listAllItems() {
        for (Lendable item : items) {
            System.out.println(item.getDescription());
        }
    }
}
