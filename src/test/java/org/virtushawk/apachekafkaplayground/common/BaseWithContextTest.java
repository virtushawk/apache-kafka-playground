package org.virtushawk.apachekafkaplayground.common;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

/**
 * Base test with application context. Context will be refreshed after class
 */
@SpringBootTest
@DirtiesContext
public abstract class BaseWithContextTest {

}
