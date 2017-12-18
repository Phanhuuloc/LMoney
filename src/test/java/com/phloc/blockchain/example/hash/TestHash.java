package com.phloc.blockchain.example.hash;


import com.google.common.hash.Hashing;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static org.junit.Assert.assertEquals;

public class TestHash {

    private String originalString;
    private String result;
    private String expectSha256hex;

    @Before
    public void init(){
        originalString = "I am Satoshi Nakamoto";
        expectSha256hex = DigestUtils.sha256Hex(originalString);
        result = "7509e5bda0c762d2bac7f90d758b5b2263fa01ccbc542ab5e3df163be08e6ca9";
        System.out.println(expectSha256hex);
    }

    @Test
    public void testBuildInHash() throws NoSuchAlgorithmException {
        //built-in
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(
                originalString.getBytes(StandardCharsets.UTF_8));
        assertEquals(expectSha256hex, Hash.bytesToHex(encodedhash));
//        assertEquals(result, Base64.getEncoder().encodeToString(encodedhash));
    }

    @Test
    public void testGuavaHash(){
        String sha256hex = Hashing.sha256()
                .hashString(originalString, StandardCharsets.UTF_8)
                .toString();
        assertEquals(expectSha256hex, sha256hex);
    }

    @Test
    public void testCommonCodecHash(){
        String sha256hex = DigestUtils.sha256Hex(originalString);
        assertEquals(expectSha256hex, sha256hex);
    }

    @Test
    public void testBouncyCastle() throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(
                originalString.getBytes(StandardCharsets.UTF_8));
        String sha256hex = new String(Hex.encode(hash));
        assertEquals(expectSha256hex, sha256hex);
    }


}
