package persistence;

import java.io.*;

import org.json.*;

import model.ClothingInventory;

//Represents a writer that writes data from a ClothingInventory object into JSON
public class JsonWriter {
    private PrintWriter writer; // the writer that writes to file
    private static final int TAB = 4; // the indentation value
    private String destination; // file destination

    // EFFECTS: Sets the source to address
    public JsonWriter(String address) {
        destination = address;
    }
    // MODIFIES: this
    // EFFECTS: creates the PrintWriter object.
    // FileNotFoundException thrown if dessination file cannot be opened for writing

    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: converts inventory into a JSONObject and writes that data to
    // destination files
    public void write(ClothingInventory inventory) {
        JSONObject objectJ = inventory.toJson();
        writer.print(objectJ.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes the writer
    public void close() {
        writer.close();
    }
}
