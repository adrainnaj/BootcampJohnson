package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {

    private static final String CONTRACT_FILE = "src/main/resources/contracts.csv";

    public void saveContract(Contract contract) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONTRACT_FILE, true))) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}