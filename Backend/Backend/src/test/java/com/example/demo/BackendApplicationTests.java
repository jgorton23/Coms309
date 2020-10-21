package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// maybe add h2 dependency with scope <test>

@RunWith(SpringRunner.class) 
@DataJpaTest //changed to JpaTest according to tutorial
public class BackendApplicationTests {

	@Autowired
	private TestEntityManager entityManager; // only set up to test on MyDatabase, add more autowiring for item or subscritption

	@Autowired
	private MyDatabase myDatabase;

	@Autowired
	private MyController myController; //idk if this makes sense, but the tutorial didnt use a controller and idk if i have to autowire it

	@Test
	public void test1() { //change name to be more descriptive maybe, or keep coments
		 //write test here
	}

	//write more tests here

}
