package net.ibonus.myapplication;

import org.xrpl.xrpl4j.crypto.keys.Base58EncodedSecret;
import org.xrpl.xrpl4j.crypto.keys.KeyPair;
import org.xrpl.xrpl4j.crypto.keys.PublicKey;
import org.xrpl.xrpl4j.crypto.keys.Seed;
import org.xrpl.xrpl4j.model.transactions.Address;

public class XrpUtil {

  public static String familySeedToRAddress(String familySeed) {
    Seed seedFromSecret = Seed.fromBase58EncodedSecret(Base58EncodedSecret.of(familySeed));
    KeyPair keyPair = seedFromSecret.deriveKeyPair();
    PublicKey publicKey = keyPair.publicKey();
    Address address = publicKey.deriveAddress();
    return address.value();
  }

}