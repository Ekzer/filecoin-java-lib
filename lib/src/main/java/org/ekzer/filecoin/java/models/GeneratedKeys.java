package org.ekzer.filecoin.java.models;

/**
 * Hex reprensation
 */
public class GeneratedKeys {
    private String publicKey;
    private String privateKey;
    
    public String getPrivateKey() {
        return privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public GeneratedKeys(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }
}
