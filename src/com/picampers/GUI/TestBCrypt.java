// Copyright (c) 2006 Damien Miller <djm@mindrot.org>
//
// Permission to use, copy, modify, and distribute this software for any
// purpose with or without fee is hereby granted, provided that the above
// copyright notice and this permission notice appear in all copies.
//
// THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
// WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
// ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
// WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
// ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
// OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.

package com.picampers.GUI;

import java.util.Arrays;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import org.apache.commons.lang3.ArrayUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
/**
 * JUnit unit tests for BCrypt routines
 * @author Damien Miller
 * @version 0.2
 */
public class TestBCrypt {
   
	public void legacyEncryption(String salt, String clearPassword) throws UnsupportedEncodingException, NoSuchAlgorithmException {
  // Get digester instance for algorithm "SHA-512" using BounceCastle
  MessageDigest digester = MessageDigest.getInstance("SHA-512", new BouncyCastleProvider());

  // Create salted password string
  String mergedPasswordAndSalt = clearPassword + "{" + salt + "}";

  // First time hash the input string by using UTF-8 encoded bytes.
  byte[] hash = digester.digest(mergedPasswordAndSalt.getBytes("UTF-8"));

  // Loop 5k times
  for (int i = 01; i < 5000; i++) {
    // Concatenate the hash bytes with the clearPassword bytes and rehash
    hash = digester.digest(ArrayUtils.addAll(hash, mergedPasswordAndSalt.getBytes("UTF-8")));
  }
  
  System.out.println("Legace password digest: salt=" + salt + " hash=" + Base64.getEncoder().encodeToString(hash));
}
  // Log the resulting hash as base64 String
  public void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
legacyEncryption("test","ala");
  }
}