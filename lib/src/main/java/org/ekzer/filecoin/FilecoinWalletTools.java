package org.ekzer.filecoin;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.HexFormat;

import org.ekzer.filecoin.java.models.GeneratedKeys;

import supranational.blst.*;

public class FilecoinWalletTools {
    final private String FIL_DOMAIN_BLS_TOKEN = "BLS_SIG_BLS_12381G2_XMD:SHA-256_SSWU_RO_NUL";

    public GeneratedKeys generateKeyPair() {
        byte[] seed = SecureRandom.getSeed(32);
        SecretKey privateKey = new SecretKey();
        privateKey.derive_master_eip2333(seed);

        P1 publicKey = new P1(privateKey);
        return new GeneratedKeys(hex(publicKey.compress()), hex(privateKey.to_lendian()));
    }

    public GeneratedKeys generateKeyPair(byte[] inputSeed) {
        checkInputSeed(inputSeed);
        byte[] seed = inputSeed;
        SecretKey privateKey = new SecretKey();
        privateKey.derive_master_eip2333(seed);

        P1 publicKey = new P1(privateKey);
        return new GeneratedKeys(hex(publicKey.compress()), hex(privateKey.to_lendian()));
    }

    public GeneratedKeys recoverWallet(byte[] privateKey) {
        SecretKey secretKey = new SecretKey();
        secretKey.from_lendian(privateKey);
        P1 publicKey = new P1(privateKey);

        return new GeneratedKeys(hex(publicKey.compress()), hex(secretKey.to_lendian()));
    }    

    public SignedMessage signMessage(byte[] message, byte[] privateKey) {
        GeneratedKeys wallet = this.recoverWallet(privateKey);
        SecretKey secretKey = new SecretKey();
        secretKey.from_lendian(privateKey);
        
        P2 signedMessage = new P2();
        return new SignedMessage(hex(signedMessage
        .hash_to(message, FIL_DOMAIN_BLS_TOKEN, new byte[0])
        .sign_with(secretKey)
        .compress()), wallet.getPublicKey());
    }
    private void checkInputSeed(byte[] inputSeed) {
        if (inputSeed != null) {
            if (inputSeed.length != 32) {
                throw new IllegalArgumentException("Invalid seed size. Must be 32 bytes long");
            }
        }
    }

    private String hex(byte[] input) {
        return HexFormat.of().formatHex(input);
    }

    private byte[] unhex(String input) {
        return HexFormat.of().parseHex(input);
    }

    class SignedMessage {
        private String signedMessage;
        public SignedMessage(String signedMessage, String publicKey) {
            this.signedMessage = signedMessage;
            this.publicKey = publicKey;
        }
        public String getSignedMessage() {
            return signedMessage;
        }
        private String publicKey;
        public String getPublicKey() {
            return publicKey;
        }
    }
    
}
