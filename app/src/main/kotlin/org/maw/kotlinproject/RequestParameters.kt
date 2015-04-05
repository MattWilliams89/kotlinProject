package org.maw.kotlinproject

import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import java.util.HashMap

fun getRequestParameters() : HashMap<String, String> {
    val timeInMillis = System.currentTimeMillis().toString()
    val md5Hash = Hex.encodeHex(DigestUtils.md5(timeInMillis + MARVEL_PRIVATE_KEY + MARVEL_PUBLIC_KEY))
    val md5HashString = String(md5Hash)

    val map = HashMap<String, String>()
    map.put("ts", timeInMillis)
    map.put("apikey", MARVEL_PUBLIC_KEY)
    map.put("hash", md5HashString)
    return map
}