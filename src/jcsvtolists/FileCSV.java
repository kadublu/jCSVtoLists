/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcsvtolists;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author carlos
 */
public class FileCSV {

    /**
     * List of Categories.
     */
    private final HashMap<String, Category> categories;

    /**
     * File Input.
     */
    private File fileInput;

    /**
     * Folder Output.
     */
    private File folderOutput;

    public FileCSV() {
        this.categories = new HashMap<>();
    }

    /**
     * Run the conversor.
     *
     * @param fileInput
     * @param folderOutput
     */
    public int run(File fileInput, File folderOutput) throws Exception {
        if (fileInput == null) {
            throw new Exception("Input file does not exist!");
        }
        if (!fileInput.exists()) {
            throw new Exception("Input file does not exist!");
        }
        if (fileInput.isDirectory()) {
            throw new Exception("Invalid input file. The URI specified is a directory!");
        }
        if (folderOutput == null) {
            throw new Exception("Output directory does not exist!");
        }
        if (!folderOutput.exists()) {
            throw new Exception("Output directory does not exist!");
        }
        if (folderOutput.isFile()) {
            throw new Exception("Invalid output directory. The URI specified is a file!");
        }
        converter(fileInput);
        return writeOutputFiles(folderOutput);
    }

    /**
     * Convert the file to categories.
     *
     * @param fileInput
     * @param folderOutput
     * @throws IOException
     */
    private void converter(File fileInput) throws IOException {
        Path file = Paths.get(fileInput.getAbsolutePath());
        Charset charset = Charset.forName("ISO-8859-1");
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {

            // Line.
            String strLine = null;

            // Read whole file.
            Contact contact;
            String[] data;
            String[] cats;
            while ((strLine = reader.readLine()) != null) {
                data = strLine.split("\\,");

                contact = new Contact();
                contact.setName(data[0], data[1], data[2]);
                contact.setEmailAdd1(data[6]);
                contact.setEmailAdd2(data[7]);
                contact.setEmailAdd3(data[8]);

                if (data.length > 33) {
                    cats = data[33].split("\\;");

                    for (String cat : cats) {
                        if (!cat.trim().equals("")) {
                            addContactToCategory(contact, cat);
                        }
                    }
                } else {
                    addContactToCategory(contact, "Sem categoria");
                }
            }
        }
    }

    /**
     * Add a contact to a category.
     *
     * @param contact
     * @param category
     */
    private void addContactToCategory(Contact contact, String categoryName) {
        Category cat;
        if (categories.containsKey(categoryName)) {
            cat = categories.get(categoryName);
            categories.remove(categoryName);
        } else {
            cat = new Category();
            cat.setName(categoryName);
        }
        cat.addContact(contact);
        this.categories.put(categoryName, cat);
    }

    /**
     * Write files by category.
     *
     * @param str
     */
    private int writeOutputFiles(File outputDirectory) {

        int ret = 0;
        Category cat;
        File fileOut;
        Charset charset = Charset.forName("ISO-8859-1");

        for (Map.Entry pairs : categories.entrySet()) {
            ret++;
            cat = ((Category) pairs.getValue());
            fileOut = new File(outputDirectory.getAbsolutePath() + File.separator + cat.getName() + ".txt");

            try (OutputStreamWriter out = new OutputStreamWriter(
                    Files.newOutputStream(Paths.get(fileOut.toURI()), CREATE, TRUNCATE_EXISTING), charset)) {
                for (Contact contact : cat.getContacts()) {
                    out.write(contact.toString());
                }
            } catch (IOException x) {
                System.err.println(x);
            }
        }
        return ret;
    }

    public File getFileInput() {
        return fileInput;
    }

    public void setFileInput(File fileInput) {
        this.fileInput = fileInput;
    }

    public File getFolderOutput() {
        return folderOutput;
    }

    public void setFolderOutput(File folderOutput) {
        this.folderOutput = folderOutput;
    }

}
