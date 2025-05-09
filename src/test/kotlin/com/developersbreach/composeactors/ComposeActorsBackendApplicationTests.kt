package com.developersbreach.composeactors

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@Disabled("No integration context needed in CI yet")
@SpringBootTest
class ComposeActorsBackendApplicationTests {

	@Test
	fun contextLoads() {
	}

}
