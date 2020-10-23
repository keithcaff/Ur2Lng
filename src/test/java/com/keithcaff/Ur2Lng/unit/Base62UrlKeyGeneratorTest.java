package com.keithcaff.Ur2Lng.unit;
import com.keithcaff.Ur2Lng.entity.Url;
import com.keithcaff.Ur2Lng.service.Base62UrlKeyGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Base62UrlKeyGeneratorTest {

    private Base62UrlKeyGenerator keyGenerator = new Base62UrlKeyGenerator();

    @Test
    public void encodeSmallId() {
        Url ulrToKeyGen = new Url(0L, "https://github.com/keithcaff");
        assertEquals("a", keyGenerator.encode(ulrToKeyGen));
    }

    @Test
    public void decodeSmallId() {
        assertEquals(0, keyGenerator.decode("a"));
    }

    @Test
    public void encodeIdSmallerThanBase62() {
        Url ulrToKeyGen = new Url(23L, "https://github.com/keithcaff");
        assertEquals("x", keyGenerator.encode(ulrToKeyGen));
    }

    @Test
    public void decodeIdSmallerThanBase62() {
        assertEquals(23L, keyGenerator.decode("x"));
    }

    @Test
    public void encodeIdLargerThanBase62() {
        Url ulrToKeyGen = new Url(88L, "https://github.com/keithcaff");
        assertEquals("bA", keyGenerator.encode(ulrToKeyGen));
    }

    @Test
    public void decodeIdLargerThanBase62() {
        assertEquals(88L, keyGenerator.decode("bA"));
    }
}
